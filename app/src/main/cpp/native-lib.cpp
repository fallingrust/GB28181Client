#include <jni.h>
#include <string>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <syslog.h>
#include <dirent.h>
#include <sys/time.h>
#include <sys/socket.h>
#include <netinet/tcp.h>
#include <sys/ioctl.h>

#define PS_HDR_LEN  14
#define SYS_HDR_LEN 18
#define PSM_HDR_LEN 24
#define PES_HDR_LEN 14
#define RTP_HDR_LEN 12
#define RTP_VERSION 2
#define RTP_MAX_PACKET_BUFF 1460
#define PS_PES_PAYLOAD_SIZE 65522

union LESize
{
    unsigned short int  length;
    unsigned char   byte[2];
};

struct bits_buffer_s {
    unsigned char* p_data;
    unsigned char  i_mask;
    int i_size;
    int i_data;
};

struct Data_Info_s {
    uint64_t s64CurPts;
    int      IFrame;
    uint16_t u16CSeq;
    uint32_t u32Ssrc;
    char szBuff[RTP_MAX_PACKET_BUFF];
};

//int _socketFd;

/***
 *@remark:  讲传入的数据按地位一个一个的压入数据
 *@param :  buffer   [in]  压入数据的buffer
 *          count    [in]  需要压入数据占的位数
 *          bits     [in]  压入的数值
 */
#define bits_write(buffer, count, bits)\
{\
  bits_buffer_s *p_buffer = (buffer);\
  int i_count = (count);\
  uint64_t i_bits = (bits);\
  while( i_count > 0 )\
  {\
    i_count--;\
    if( ( i_bits >> i_count )&0x01 )\
    {\
      p_buffer->p_data[p_buffer->i_data] |= p_buffer->i_mask;\
    }\
    else\
    {\
      p_buffer->p_data[p_buffer->i_data] &= ~p_buffer->i_mask;\
    }\
    p_buffer->i_mask >>= 1;         /*操作完一个字节第一位后，操作第二位*/\
    if( p_buffer->i_mask == 0 )     /*循环完一个字节的8位后，重新开始下一位*/\
    {\
      p_buffer->i_data++;\
      p_buffer->i_mask = 0x80;\
    }\
  }\
}

int gb28181_make_rtp_header(char* pData, int marker_flag, unsigned short cseq, long long curpts, unsigned int ssrc);
int gb28181_make_pes_header(char* pData, int stream_id, int payload_len, unsigned long long pts, unsigned long long dts);
int gb28181_make_psm_header(char* pData);
int gb28181_make_sys_header(char* pData);
int gb28181_make_ps_header(char* pData, unsigned long long s64Scr);

int findStartCode(unsigned char* buf, int zeros_in_startcode)
{
    int info;
    int i;

    info = 1;
    for (i = 0; i < zeros_in_startcode; i++)
        if (buf[i] != 0)
            info = 0;

    if (buf[i] != 1)
        info = 0;
    return info;
}
/***
 *@remark:   ps头的封装,里面的具体数据的填写已经占位，可以参考标准
 *@param :   pData  [in] 填充ps头数据的地址
 *           s64Src [in] 时间戳
 *@return:   0 success, others failed
*/
int gb28181_make_ps_header(char* pData, unsigned long long s64Scr)
{
    unsigned long long lScrExt = (s64Scr) % 100;
    s64Scr = s64Scr / 100;

    // 这里除以100是由于sdp协议返回的video的频率是90000，帧率是25帧/s，所以每次递增的量是3600,
    // 所以实际你应该根据你自己编码里的时间戳来处理以保证时间戳的增量为3600即可，
    //如果这里不对的话，就可能导致卡顿现象了
    bits_buffer_s   bitsBuffer;
    bitsBuffer.i_size = PS_HDR_LEN;
    bitsBuffer.i_data = 0;
    bitsBuffer.i_mask = 0x80; // 二进制：10000000 这里是为了后面对一个字节的每一位进行操作，避免大小端夸字节字序错乱
    bitsBuffer.p_data = (unsigned char*)(pData);
    memset(bitsBuffer.p_data, 0, PS_HDR_LEN);
    bits_write(&bitsBuffer, 32, 0x000001BA);      /*start codes*/
    bits_write(&bitsBuffer, 2, 1);           /*marker bits '01b'*/
    bits_write(&bitsBuffer, 3, (s64Scr >> 30) & 0x07);     /*System clock [32..30]*/
    bits_write(&bitsBuffer, 1, 1);           /*marker bit*/
    bits_write(&bitsBuffer, 15, (s64Scr >> 15) & 0x7FFF);   /*System clock [29..15]*/
    bits_write(&bitsBuffer, 1, 1);           /*marker bit*/
    bits_write(&bitsBuffer, 15, s64Scr & 0x7fff);         /*System clock [14..0]*/
    bits_write(&bitsBuffer, 1, 1);           /*marker bit*/
    bits_write(&bitsBuffer, 9, lScrExt & 0x01ff);    /*System clock ext*/
    bits_write(&bitsBuffer, 1, 1);           /*marker bit*/
    bits_write(&bitsBuffer, 22, (255) & 0x3fffff);    /*bit rate(n units of 50 bytes per second.)*/
    bits_write(&bitsBuffer, 2, 3);           /*marker bits '11'*/
    bits_write(&bitsBuffer, 5, 0x1f);          /*reserved(reserved for future use)*/
    bits_write(&bitsBuffer, 3, 0);           /*stuffing length*/
    return 0;
}
/***
 *@remark:   sys头的封装,里面的具体数据的填写已经占位，可以参考标准
 *@param :   pData  [in] 填充ps头数据的地址
 *@return:   0 success, others failed
*/
int gb28181_make_sys_header(char* pData)
{

    bits_buffer_s   bitsBuffer;
    bitsBuffer.i_size = SYS_HDR_LEN;
    bitsBuffer.i_data = 0;
    bitsBuffer.i_mask = 0x80;
    bitsBuffer.p_data = (unsigned char*)(pData);
    memset(bitsBuffer.p_data, 0, SYS_HDR_LEN);
    /*system header*/
    bits_write(&bitsBuffer, 32, 0x000001BB); /*start code*/
    bits_write(&bitsBuffer, 16, SYS_HDR_LEN - 6);/*header_length 表示次字节后面的长度，后面的相关头也是次意思*/
    bits_write(&bitsBuffer, 1, 1);            /*marker_bit*/
    bits_write(&bitsBuffer, 22, 50000);    /*rate_bound*/
    bits_write(&bitsBuffer, 1, 1);            /*marker_bit*/
    bits_write(&bitsBuffer, 6, 1);            /*audio_bound*/
    bits_write(&bitsBuffer, 1, 0);            /*fixed_flag */
    bits_write(&bitsBuffer, 1, 1);          /*CSPS_flag */
    bits_write(&bitsBuffer, 1, 1);          /*system_audio_lock_flag*/
    bits_write(&bitsBuffer, 1, 1);          /*system_video_lock_flag*/
    bits_write(&bitsBuffer, 1, 1);          /*marker_bit*/
    bits_write(&bitsBuffer, 5, 1);          /*video_bound*/
    bits_write(&bitsBuffer, 1, 0);          /*dif from mpeg1*/
    bits_write(&bitsBuffer, 7, 0x7F);       /*reserver*/
    /*audio stream bound*/
    bits_write(&bitsBuffer, 8, 0xC0);         /*stream_id*/
    bits_write(&bitsBuffer, 2, 3);          /*marker_bit */
    bits_write(&bitsBuffer, 1, 0);            /*PSTD_buffer_bound_scale*/
    bits_write(&bitsBuffer, 13, 1024);          /*PSTD_buffer_size_bound*/
    /*video stream bound*/
    bits_write(&bitsBuffer, 8, 0xE0);         /*stream_id*/
    bits_write(&bitsBuffer, 2, 3);          /*marker_bit */
    bits_write(&bitsBuffer, 1, 0);          /*PSTD_buffer_bound_scale*/
    bits_write(&bitsBuffer, 13, 128);       /*PSTD_buffer_size_bound*/
    return 0;
}
/***
 *@remark:   psm头的封装,里面的具体数据的填写已经占位，可以参考标准
 *@param :   pData  [in] 填充ps头数据的地址
 *@return:   0 success, others failed
*/
int gb28181_make_psm_header(char* pData)
{

    bits_buffer_s   bitsBuffer;
    bitsBuffer.i_size = PSM_HDR_LEN;
    bitsBuffer.i_data = 0;
    bitsBuffer.i_mask = 0x80;
    bitsBuffer.p_data = (unsigned char*)(pData);
    memset(bitsBuffer.p_data, 0, PSM_HDR_LEN);
    bits_write(&bitsBuffer, 24, 0x000001); /*start code*/
    bits_write(&bitsBuffer, 8, 0xBC);   /*map stream id*/
    bits_write(&bitsBuffer, 16, 18);     /*program stream map length*/
    bits_write(&bitsBuffer, 1, 1);      /*current next indicator */
    bits_write(&bitsBuffer, 2, 3);      /*reserved*/
    bits_write(&bitsBuffer, 5, 0);      /*program stream map version*/
    bits_write(&bitsBuffer, 7, 0x7F);   /*reserved */
    bits_write(&bitsBuffer, 1, 1);      /*marker bit */
    bits_write(&bitsBuffer, 16, 0);      /*programe stream info length*/
    bits_write(&bitsBuffer, 16, 8);     /*elementary stream map length  is*/
    /*audio*/
    bits_write(&bitsBuffer, 8, 0x90);       /*stream_type*/
    bits_write(&bitsBuffer, 8, 0xC0);   /*elementary_stream_id*/
    bits_write(&bitsBuffer, 16, 0);     /*elementary_stream_info_length is*/
    /*video*/
    bits_write(&bitsBuffer, 8, 0x1B);       /*stream_type*/
    bits_write(&bitsBuffer, 8, 0xE0);   /*elementary_stream_id*/
    bits_write(&bitsBuffer, 16, 0);     /*elementary_stream_info_length */
    /*crc (2e b9 0f 3d)*/
    bits_write(&bitsBuffer, 8, 0x45);   /*crc (24~31) bits*/
    bits_write(&bitsBuffer, 8, 0xBD);   /*crc (16~23) bits*/
    bits_write(&bitsBuffer, 8, 0xDC);   /*crc (8~15) bits*/
    bits_write(&bitsBuffer, 8, 0xF4);   /*crc (0~7) bits*/
    return 0;
}
static long long get_timestamp()
{
    long long time_stamp;
    struct timeval tv;
    gettimeofday(&tv, NULL);
    time_stamp = ((tv.tv_sec * 1000) + (tv.tv_usec/1000));

    return time_stamp;
}
/***
 *@remark:   pes头的封装,里面的具体数据的填写已经占位，可以参考标准
 *@param :   pData      [in] 填充ps头数据的地址
 *           stream_id  [in] 码流类型
 *           paylaod_len[in] 负载长度
 *           pts        [in] 时间戳
 *           dts        [in]
 *@return:   0 success, others failed
*/
int gb28181_make_pes_header(char* pData, int stream_id, int payload_len, unsigned long long pts, unsigned long long dts)
{

    bits_buffer_s   bitsBuffer;
    bitsBuffer.i_size = PES_HDR_LEN;
    bitsBuffer.i_data = 0;
    bitsBuffer.i_mask = 0x80;
    bitsBuffer.p_data = (unsigned char*)(pData);
    memset(bitsBuffer.p_data, 0, PES_HDR_LEN);
    /*system header*/
    bits_write(&bitsBuffer, 24, 0x000001);  /*start code*/
    bits_write(&bitsBuffer, 8, (stream_id)); /*streamID*/
    bits_write(&bitsBuffer, 16, (payload_len)+8);  /*packet_len*/ //指出pes分组中数据长度和该字节后的长度和
    bits_write(&bitsBuffer, 2, 2);    /*'10'*/
    bits_write(&bitsBuffer, 2, 0);    /*scrambling_control*/
    bits_write(&bitsBuffer, 1, 0);    /*priority*/
    bits_write(&bitsBuffer, 1, 0);    /*data_alignment_indicator*/
    bits_write(&bitsBuffer, 1, 0);    /*copyright*/
    bits_write(&bitsBuffer, 1, 0);    /*original_or_copy*/
    bits_write(&bitsBuffer, 1, 1);    /*PTS_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*DTS_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*ESCR_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*ES_rate_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*DSM_trick_mode_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*additional_copy_info_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*PES_CRC_flag*/
    bits_write(&bitsBuffer, 1, 0);    /*PES_extension_flag*/
    bits_write(&bitsBuffer, 8, 5);    /*header_data_length*/
    // 指出包含在 PES 分组标题中的可选字段和任何填充字节所占用的总字节数。该字段之前
    //的字节指出了有无可选字段。



    bits_write(&bitsBuffer, 4, 3);                    /*'0011'*/
    bits_write(&bitsBuffer, 3, ((pts) >> 30) & 0x07);     /*PTS[32..30]*/
    bits_write(&bitsBuffer, 1, 1);
    bits_write(&bitsBuffer, 15, ((pts) >> 15) & 0x7FFF);    /*PTS[29..15]*/
    bits_write(&bitsBuffer, 1, 1);
    bits_write(&bitsBuffer, 15, (pts) & 0x7FFF);          /*PTS[14..0]*/
    bits_write(&bitsBuffer, 1, 1);


//    bits_write(&bitsBuffer, 4, 1);                    /*'0001'*/
//    bits_write(&bitsBuffer, 3, ((dts) >> 30) & 0x07);     /*DTS[32..30]*/
//    bits_write(&bitsBuffer, 1, 1);
//    bits_write(&bitsBuffer, 15, ((dts) >> 15) & 0x7FFF);    /*DTS[29..15]*/
//    bits_write(&bitsBuffer, 1, 1);
//    bits_write(&bitsBuffer, 15, (dts) & 0x7FFF);          /*DTS[14..0]*/
//    bits_write(&bitsBuffer, 1, 1);
    return 0;
}


int gb28181_make_rtp_header(char* pData, int marker_flag, unsigned short cseq, long long curpts, unsigned int ssrc)
{
    bits_buffer_s   bitsBuffer;
    if (pData == NULL)
        return -1;
    bitsBuffer.i_size = RTP_HDR_LEN;
    bitsBuffer.i_data = 0;
    bitsBuffer.i_mask = 0x80;
    bitsBuffer.p_data = (unsigned char*)(pData);
    memset(bitsBuffer.p_data, 0, RTP_HDR_LEN);
    bits_write(&bitsBuffer, 2, RTP_VERSION);  /* rtp version  */
    bits_write(&bitsBuffer, 1, 0);        /* rtp padding  */
    bits_write(&bitsBuffer, 1, 0);        /* rtp extension  */
    bits_write(&bitsBuffer, 4, 0);        /* rtp CSRC count */
    bits_write(&bitsBuffer, 1, (marker_flag));      /* rtp marker   */
    bits_write(&bitsBuffer, 7, 96);     /* rtp payload type*/
    bits_write(&bitsBuffer, 16, (cseq));      /* rtp sequence    */
    bits_write(&bitsBuffer, 32, (curpts));    /* rtp timestamp   */
    bits_write(&bitsBuffer, 32, (ssrc));    /* rtp SSRC    */
    return 0;
}

extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_rust_sip_GB28181_tools_PSmuxer_getHeader(JNIEnv* env,jobject ,jint len, jlong times,jint isKeyFrame){
    char szTempPacketHead[256];
    int  nSizePos = 0;
    memset(szTempPacketHead, 0, 256);

    gb28181_make_ps_header(szTempPacketHead + nSizePos, times);
    nSizePos += PS_HDR_LEN;
    if (isKeyFrame == 1)
    {
        gb28181_make_sys_header(szTempPacketHead + nSizePos);
        nSizePos += SYS_HDR_LEN;
        gb28181_make_psm_header(szTempPacketHead + nSizePos);
    }
    jbyteArray back=env->NewByteArray(256);
    env->SetByteArrayRegion(back,0,256,(jbyte *)szTempPacketHead);
    return back;
}
extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_rust_sip_GB28181_tools_PSmuxer_getPESHeader(JNIEnv *env,jobject,jint stream_id,jint payloadLen,jint streaType,jlong times){
    char szTempPacketHead[256];
    memset(szTempPacketHead, 0, 256);
    gb28181_make_pes_header(szTempPacketHead, streaType ? 0xC0 : 0xE0, payloadLen, times, times);
    jbyteArray back=env->NewByteArray(256);
    env->SetByteArrayRegion(back,0,256,(jbyte *)szTempPacketHead);
    return back;
}
extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_rust_sip_GB28181_tools_PSmuxer_getRtpHeader(JNIEnv *env,jobject,jint marker_flag,jint cseq,jlong curpts,jint ssrc){
    char szTempPacketHead[256];
    memset(szTempPacketHead, 0, 256);
    gb28181_make_rtp_header(szTempPacketHead, marker_flag, cseq, curpts, ssrc);
    jbyteArray back=env->NewByteArray(256);
    env->SetByteArrayRegion(back,0,256,(jbyte *)szTempPacketHead);
    return back;
}
