package com.rust.sip.GB28181.sip.transaction;

import android.text.TextUtils;

import com.rust.sip.GB28181.gb28181.DeviceItem;
import com.rust.sip.GB28181.gb28181.DeviceList;
//import com.rust.sip.GB28181.gb28181.ResponseMessage;
import com.rust.sip.GB28181.gb28181.XMLUtil;
import com.rust.sip.GB28181.sip.address.NameAddress;
import com.rust.sip.GB28181.sip.address.SipURL;
import com.rust.sip.GB28181.sip.header.AuthorizationHeader;
import com.rust.sip.GB28181.sip.message.Message;
import com.rust.sip.GB28181.sip.message.MessageFactory;
import com.rust.sip.GB28181.sip.message.SipMethods;
import com.rust.sip.GB28181.sip.message.SipResponses;
import com.rust.sip.GB28181.sip.provider.SipProvider;
import com.rust.sip.GB28181.sip.provider.SipStack;
import com.rust.sip.GB28181.sip.provider.TransactionIdentifier;
import com.rust.sip.GB28181.tools.Logger;
import com.rust.sip.GB28181.ua.UserAgentProfile;

import java.util.ArrayList;
import java.util.List;

public class MESSAGETransactionClient extends Transaction {
    private boolean isMessage;
    private UserAgentProfile user_profile;
    private TransactionClientGB28181Listener listener;

    /**
     * Costructs a new Transaction
     *
     * @param sip_provider
     */
    public MESSAGETransactionClient(SipProvider sip_provider, TransactionClientGB28181Listener listener, UserAgentProfile user_profile) {
        super(sip_provider);
        this.user_profile=user_profile;
        this.listener=listener;
    }


    public void request() {
        sip_provider.addSipProviderListener(new TransactionIdentifier("MESSAGE"), this);
        //connection_id = sip_provider.sendMessage(request);
    }

    public void onReceivedMessage(SipProvider provider, Message msg) {
//        android.util.Log.d("aaaa", "aaaaa" + msg);
//        if (msg.isRequest()) {
//            String req_method = msg.getRequestLine().getMethod();
//            if (req_method.equals(SipMethods.MESSAGE)) {
//                isMessage=true;
//
//                request = new Message(msg);
//                connection_id = request.getConnectionId();
//                transaction_id = request.getTransactionId();
//               /* sip_provider.addSipProviderListener(transaction_id, this);
//                sip_provider
//                        .removeSipProviderListener(new TransactionIdentifier(
//                                SipMethods.INVITE));*/
//                String str=msg.getBody();
//
//                AuthorizationHeader ah=msg.getAuthorizationHeader();
//
//                String sn=str.substring(str.indexOf("<SN>")+4,str.indexOf("</SN>"));
//                Message msg200 = MessageFactory.createResponse(
//                        request, 200, SipResponses.reasonOf(200), null);
//                String contact_user = request.getFromHeader().getNameAddress().getAddress().getUserName();
//
//                String channelID = user_profile.username;
////                channelID = "33080002001326030001";
//
//                String IPAddress =  "192.168.1.164";
//                if (!TextUtils.isEmpty(user_profile.contact_url)) {
//                    IPAddress = new NameAddress(user_profile.contact_url).getAddress().getHost();
//                }
//
//                String cmdType = XMLUtil.getSubUtilSimple( str,"<CmdType>(.*?)</CmdType>");
//                Logger.d("Recv the MESSAGE cmdType="+cmdType);
//
//                if(cmdType.equals("Catalog")){
//                    DeviceItem item = new DeviceItem();
//                    item.setDeviceID(channelID);
//                    item.setName("RokidName");
//                    item.setManufacturer("Rokid");
//                    item.setModel("Rokid");
//                    item.setOwner("Rokid");
//                    item.setCivilCode("01234567");
//                    item.setAddress("Rokid");
//                    item.setParental("0");
//                    item.setSafetyWay("0");
//                    item.setRegisterWay("1");
//                    item.setSecrecy("0");
//                    item.setIPAddress(IPAddress);
//                    item.setPort("80");
//                    item.setPassword("9999");
//                    item.setStatus("ON");
//
//                    List<DeviceItem> itemList = new ArrayList<>();
//                    itemList.add(item);
//                    DeviceList deviceList = new DeviceList();
//                    deviceList.setDeviceList(itemList);
//                    deviceList.setNum(String.valueOf(itemList.size()));
//
//                    ResponseMessage responseMessage=new ResponseMessage();
//                    responseMessage.setCmdType(cmdType);
//                    responseMessage.setSN(sn);
//                    responseMessage.setDeviceID(channelID);
//                    responseMessage.setSumNum(String.valueOf(itemList.size()));
//                    responseMessage.setDeviceList(deviceList);
//
//                    String body = XMLUtil.convertBeanToXml(responseMessage);
//
//                    Message messageRequest = MessageFactory.createMessageRequest(sip_provider,
//                            request.getFromHeader().getNameAddress(),
//                            request.getToHeader().getNameAddress(),
//                            null,
//                            XMLUtil.XML_MANSCDP_TYPE,
//                            body
//                    );
//                    sip_provider.sendMessage(msg200, connection_id);
//                    sip_provider.sendMessage(messageRequest,connection_id);
//                    if(listener!=null){
//                        listener.onTransGB28181SuccessResponse(this,request);
//                    }
//                }else if(cmdType.equals("DeviceInfo")){
//
//                    ResponseMessage responseMessage = new ResponseMessage();
//                    responseMessage.setCmdType(cmdType);
//                    responseMessage.setSN(sn);
//                    responseMessage.setDeviceID(user_profile.username);
//                    responseMessage.setDeviceType(SipStack.ua_info);
//                    responseMessage.setManufacture("Rokid");
//                    responseMessage.setModel("Rokid");
//                    responseMessage.setFirmware("Rokidv2.1");
//                    responseMessage.setResult("OK");
//
//                    String body = XMLUtil.convertBeanToXml(responseMessage);
//
//                    Message messageRequest = MessageFactory.createMessageRequest(sip_provider,
//                            request.getFromHeader().getNameAddress(),
//                            request.getToHeader().getNameAddress(),
//                            null,
//                            XMLUtil.XML_MANSCDP_TYPE,
//                            body);
//                    sip_provider.sendMessage(msg200, connection_id);
//                    sip_provider.sendMessage(messageRequest,connection_id);
//
//                }else if(cmdType.equals("Broadcast")){
//
//                    NameAddress contact = new NameAddress(new SipURL(contact_user,
//                            sip_provider.getViaAddress(), sip_provider.getPort()));
//
//                    ResponseMessage responseMessage = new ResponseMessage();
//                    responseMessage.setCmdType(cmdType);
//                    responseMessage.setSN(sn);
//                    responseMessage.setDeviceID(user_profile.username);
//                    responseMessage.setResult("OK");
//                    String body = XMLUtil.convertBeanToXml(responseMessage);
//
//                    Message messageRequest = MessageFactory.createMessageRequest(sip_provider,
//                            request.getFromHeader().getNameAddress(),
//                            request.getToHeader().getNameAddress(),
//                            null,
//                            XMLUtil.XML_MANSCDP_TYPE,
//                            body
//                            );
//
//                    sip_provider.sendMessage(msg200, connection_id);
//                    sip_provider.sendMessage(messageRequest,connection_id);
//
//                }else if(cmdType.equals("DeviceControl")){
//
//                    NameAddress contact = new NameAddress(new SipURL(contact_user,
//                            sip_provider.getViaAddress(), sip_provider.getPort()));
//
//                    ResponseMessage responseMessage = new ResponseMessage();
//                    responseMessage.setCmdType(cmdType);
//                    responseMessage.setSN(sn);
//                    responseMessage.setDeviceID(user_profile.username);
//                    responseMessage.setResult("OK");
//                    String body = XMLUtil.convertBeanToXml(responseMessage);
//
//                    Message messageRequest = MessageFactory.createMessageRequest(sip_provider,
//                            request.getFromHeader().getNameAddress(),
//                            request.getToHeader().getNameAddress(),
//                            null,
//                            XMLUtil.XML_MANSCDP_TYPE,
//                            body
//                    );
//
//                    sip_provider.sendMessage(msg200, connection_id);
//                    sip_provider.sendMessage(messageRequest,connection_id);

               // }



                //retransmission_to.halt();
           // }
       // }
    }

    @Override
    public void terminate() {

    }
}
