package com.rust.sip.GB28181.gb28181;

//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.converters.reflection.FieldDictionary;
//import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: zhuohf
 * Version: V0.1 2018/2/19
 */
public class XMLUtil {

    public static final String XML_MANSCDP_TYPE ="Application/MANSCDP+xml";

    public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";


    public static Object convertXmlToBean(String message) {
        return "";
    }


    public static String getSubUtilSimple(String soap, String rgex){
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }
}
