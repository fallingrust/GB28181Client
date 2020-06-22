package com.rust.sip.GB28181.gb28181;

import android.hardware.Camera;
import android.os.Environment;

public class GB28181Params {
    //默认UserAgent
    public static final String defaultUserAgent="RUST";
    //默认传输协议
    public static final String defaultProtol="udp";
    //本机IP地址
    private static String LocalSIPIPAddress;
    //SIP服务器IP地址
    private static String SIPServerIPAddress;
    //SIP服务器端口
    private static int RemoteSIPServerPort;
    //SIP服务器域
    private static String RemoteSIPServerSerial;
    //SIP服务器ID
    private static String RemoteSIPServerID;
    //密码
    private static String Password;
    //本机SIP端口
    private static int LocalSIPPort;
    //媒体服务器IP地址
    private static String MediaServerIPAddress;
    //媒体服务器端口
    private static int MediaServerPort;
    //本机SIP设备ID
    private static String LocalSIPDeviceId;
    //本机SIP媒体ID
    private static String LocalSIPMediaId;
    //Camera 宽
    private static int CameraWidth;
    //Camera 长
    private static int CameraHeigth;
    //Camera;
    private static Camera Camera;
    //当前国标状态(1已启用、0未启用）
    public static int CurGBState;
    //当前设备实时播放媒体状态(1已启用、0未启用）
    public static int CurDevicePlayMediaState;
    //当前设备回放媒体状态(1已启用、0未启用）
    private static int CurDevicePlaybackMediaState;
    //当前设备下载媒体状态(1已启用、0未启用）
    private static int CurDeviceDownloadMeidaState;

    //Camera状态（大于0已使用，0未使用）
    public static int CameraState;

    public static String getLocalSIPIPAddress() {
        return LocalSIPIPAddress;
    }

    public static void setLocalSIPIPAddress(String localSIPIPAddress) {
        LocalSIPIPAddress = localSIPIPAddress;
    }

    public static String getSIPServerIPAddress() {
        return SIPServerIPAddress;
    }

    public static void setSIPServerIPAddress(String sIPServerIPAddress) {
        SIPServerIPAddress = sIPServerIPAddress;
    }

    public static int getRemoteSIPServerPort() {
        return RemoteSIPServerPort;
    }

    public static void setRemoteSIPServerPort(int remoteSIPServerPort) {
        RemoteSIPServerPort = remoteSIPServerPort;
    }

    public static int getLocalSIPPort() {
        return LocalSIPPort;
    }

    public static void setLocalSIPPort(int localSIPPort) {
        LocalSIPPort = localSIPPort;
    }

    public static String getMediaServerIPAddress() {
        return MediaServerIPAddress;
    }

    public static void setMediaServerIPAddress(String mediaServerIPAddress) {
        MediaServerIPAddress = mediaServerIPAddress;
    }

    public static int getMediaServerPort() {
        return MediaServerPort;
    }

    public static void setMediaServerPort(int mediaServerPort) {
        MediaServerPort = mediaServerPort;
    }

    public static String getLocalSIPDeviceId() {
        return LocalSIPDeviceId;
    }

    public static void setLocalSIPDeviceId(String localSIPDeviceId) {
        LocalSIPDeviceId = localSIPDeviceId;
    }

    public static String getLocalSIPMediaId() {
        return LocalSIPMediaId;
    }

    public static void setLocalSIPMediaId(String localSIPMediaId) {
        LocalSIPMediaId = localSIPMediaId;
    }

    public static int getCameraWidth() {
        return CameraWidth;
    }

    public static void setCameraWidth(int cameraWidth) {
        CameraWidth = cameraWidth;
    }

    public static int getCameraHeigth() {
        return CameraHeigth;
    }

    public static void setCameraHeigth(int cameraHeigth) {
        CameraHeigth = cameraHeigth;
    }

    public static Camera getmCamera() {
        return Camera;
    }

    public static void setmCamera(Camera mCamera) {
        Camera = mCamera;
    }

    public static int getCurGBState() {
        return CurGBState;
    }

    public static void setCurGBState(int curGBState) {
        CurGBState = curGBState;
    }

    public static int getCurDevicePlayMediaState() {
        return CurDevicePlayMediaState;
    }

    public static void setCurDevicePlayMediaState(int curDevicePlayMediaState) {
        CurDevicePlayMediaState = curDevicePlayMediaState;
    }

    public static int getCurDevicePlaybackMediaState() {
        return CurDevicePlaybackMediaState;
    }

    public static void setCurDevicePlaybackMediaState(int curDevicePlaybackMediaState) {
        CurDevicePlaybackMediaState = curDevicePlaybackMediaState;
    }

    public static int getCurDeviceDownloadMeidaState() {
        return CurDeviceDownloadMeidaState;
    }

    public static void setCurDeviceDownloadMeidaState(int curDeviceDownloadMeidaState) {
        CurDeviceDownloadMeidaState = curDeviceDownloadMeidaState;
    }

    public static String getRemoteSIPServerSerial() {
        return RemoteSIPServerSerial;
    }

    public static void setRemoteSIPServerSerial(String remoteSIPServerSerial) {
        RemoteSIPServerSerial = remoteSIPServerSerial;
    }

    public static String getRemoteSIPServerID() {
        return RemoteSIPServerID;
    }

    public static void setRemoteSIPServerID(String remoteSIPServerID) {
        RemoteSIPServerID = remoteSIPServerID;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static int getCameraState() {
        return CameraState;
    }

    public static void setCameraState(int cameraState) {
        CameraState = cameraState;
    }
}
