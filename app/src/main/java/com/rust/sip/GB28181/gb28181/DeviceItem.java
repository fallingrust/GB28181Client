package com.rust.sip.GB28181.gb28181;


//import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 <Item>
 <DeviceID>33080002001326031122</DeviceID>
 <Name>RokidName</Name>
 <Manufacturer>Rokid</Manufacturer>
 <Model>Rokid</Model>
 <Owner>Rokid</Owner>
 <CivilCode>01234567</CivilCode>
 <Address>Rokid</Address>
 <Parental>0</Parental>
 <SafetyWay>0</SafetyWay>
 <RegisterWay>1</RegisterWay>
 <Secrecy>0</Secrecy>
 <IPAddress>192.168.1.164</IPAddress>
 <Port>80</Port>
 <Password>9999</Password>
 <Status>ON</Status>
 </Item>
 */

/**
 * Author: zhuohf
 * Version: V0.1 2018/2/19
 */
@XMLSequence({"DeviceID", "Name", "Manufacturer", "Model", "Owner", "CivilCode", "Address",
        "Parental", "SafetyWay", "RegisterWay", "Secrecy", "IPAddress", "Port", "Password", "Status"})
//@XStreamAlias("Item")
public class DeviceItem {

    private String DeviceID;

    private String Name;

    private String Manufacturer;

    private String Model;

    private String Owner;

    private String CivilCode;

    private String Address;

    private String Parental;

    private String SafetyWay;

    private String RegisterWay;

    private String Secrecy;

    private String IPAddress;

    private String Port;

    private String Password;

    private String Status;

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getCivilCode() {
        return CivilCode;
    }

    public void setCivilCode(String civilCode) {
        CivilCode = civilCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getParental() {
        return Parental;
    }

    public void setParental(String parental) {
        Parental = parental;
    }

    public String getSafetyWay() {
        return SafetyWay;
    }

    public void setSafetyWay(String safetyWay) {
        SafetyWay = safetyWay;
    }

    public String getRegisterWay() {
        return RegisterWay;
    }

    public void setRegisterWay(String registerWay) {
        RegisterWay = registerWay;
    }

    public String getSecrecy() {
        return Secrecy;
    }

    public void setSecrecy(String secrecy) {
        Secrecy = secrecy;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "DeviceItem{" +
                "DeviceID='" + DeviceID + '\'' +
                ", Name='" + Name + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", Model='" + Model + '\'' +
                ", Owner='" + Owner + '\'' +
                ", CivilCode='" + CivilCode + '\'' +
                ", Address='" + Address + '\'' +
                ", Parental='" + Parental + '\'' +
                ", SafetyWay='" + SafetyWay + '\'' +
                ", RegisterWay='" + RegisterWay + '\'' +
                ", Secrecy='" + Secrecy + '\'' +
                ", IPAddress='" + IPAddress + '\'' +
                ", Port='" + Port + '\'' +
                ", Password='" + Password + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
