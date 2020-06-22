package com.rust.sip.GB28181.gb28181;

/**
 * Author: zhuohf
 * Version: V0.1 2018/2/19
 */
public class BaseMessage {

    protected String CmdType;

    protected String SN;

    protected String DeviceID;

    protected String Status;

    protected String Result;

    protected String DeviceType;

    protected String Manufacture;

    protected String Model;

    protected String Firmware;

    public String getCmdType() {
        return CmdType;
    }

    public void setCmdType(String cmdType) {
        CmdType = cmdType;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getManufacture() {
        return Manufacture;
    }

    public void setManufacture(String manufacture) {
        Manufacture = manufacture;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getFirmware() {
        return Firmware;
    }

    public void setFirmware(String firmware) {
        Firmware = firmware;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "CmdType='" + CmdType + '\'' +
                ", SN='" + SN + '\'' +
                ", DeviceID='" + DeviceID + '\'' +
                ", Status='" + Status + '\'' +
                ", Result='" + Result + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", Manufacture='" + Manufacture + '\'' +
                ", Model='" + Model + '\'' +
                ", Firmware='" + Firmware + '\'' +
                '}';
    }
}
