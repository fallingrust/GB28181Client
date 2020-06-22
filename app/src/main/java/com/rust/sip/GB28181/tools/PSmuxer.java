package com.rust.sip.GB28181.tools;

public class PSmuxer {
    static {
        System.loadLibrary("native-lib");
    }
    public static byte[] GetPSHeader(int len,long times,int iskeyFrame){
        byte[] PSheader;
        byte[] header=getHeader(len,times,iskeyFrame);
        if(iskeyFrame == 0){
            PSheader=new byte[14];
            System.arraycopy(header,0,PSheader,0,PSheader.length);
        }else{
            PSheader=new byte[56];
            System.arraycopy(header,0,PSheader,0,PSheader.length);
        }
        return PSheader;
    }
    public static byte[] GetPESHeader(int paylaodlen,int streamType,long times){
        byte[] PESHeader=new byte[19];
        byte[] header=getPESHeader(0,paylaodlen,streamType,times);
        System.arraycopy(header,0,PESHeader,0,PESHeader.length);
        return PESHeader;
    }
    public static byte[] GetRtpHeader(int marker_flag,int cseq,long curpts,int ssrc){
        byte[] RtpHeader=new byte[12];
        byte[] header=getRtpHeader(marker_flag,cseq,curpts,ssrc);
        System.arraycopy(header,0,RtpHeader,0,RtpHeader.length);
        return RtpHeader;
    }

    public static native byte[] getHeader(int len,long times,int iskeyFrame);
    public static native byte[] getPESHeader(int stream_id,int payloadLen,int streaType,long times);
    public static native byte[] getRtpHeader(int marker_flag,int cseq,long curpts,int ssrc);
}
