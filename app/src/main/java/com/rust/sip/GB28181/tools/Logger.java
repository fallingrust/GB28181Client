package com.rust.sip.GB28181.tools;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

/**
 * Author: zhuohf
 * Version: V0.1 2018/2/19
 */
public class Logger {

    private static final String FORMAT_STRING = "(%1$s.%2$s %3$dL)";
    private static final String TAG = "zhf_sip";
    private static volatile Logger instance;
    public static final int DEBUG = 0;
    public static final int RELEASE = 1;
    private static int logLevel = DEBUG;

    private Logger() {

    }

    public static Logger getInstance() {
        if (null == instance) {
            synchronized (Logger.class) {
                if (null == instance) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public static void initialize() {
        getInstance();
    }


    public void setLogLevel(int level) {
        logLevel = level;
    }

    public static void i(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        Log.i(TAG, msg + " " + msgInfo());
    }

    public static void d(String msg) {
        if (TextUtils.isEmpty(msg) || logLevel == RELEASE)
            return;
        Log.d(TAG, msg + " " + msgInfo());
    }

    public static void v(String msg) {
        if (TextUtils.isEmpty(msg) || logLevel == RELEASE)
            return;
        Log.v(TAG, msg + " " + msgInfo());
    }

    public static void e(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        Log.e(TAG, msg + " " + msgInfo());
    }

    public static void w(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        Log.w(TAG, msg + " " + msgInfo());
    }

    public static void e(Exception e) {
        if (null == e)
            return;
        Log.e(TAG, e.getMessage());
    }

    public static void i(String luaTag, String msg) {
        if (TextUtils.isEmpty(luaTag) || TextUtils.isEmpty(msg))
            return;
        Log.i(luaTag, msg);
    }

    public static void d(String luaTag,String msg){
        if (TextUtils.isEmpty(luaTag) || TextUtils.isEmpty(msg)|| logLevel == RELEASE)
            return;
        Log.i(luaTag, msg);
    }

    public static void w(String luaTag, String msg) {
        if (TextUtils.isEmpty(luaTag) || TextUtils.isEmpty(msg))
            return;
        Log.w(luaTag, msg);
    }

    public static void e(String luaTag, String msg) {
        if (TextUtils.isEmpty(luaTag) || TextUtils.isEmpty(msg))
            return;
        Log.e(luaTag, msg);
    }

    private static String msgInfo() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String className = stackTraceElement.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        return String.format(Locale.getDefault(), FORMAT_STRING,
                className, stackTraceElement.getMethodName(),
                stackTraceElement.getLineNumber());
    }

//
//    @android.support.annotation.IntDef({DEBUG, RELEASE})
//    public @interface LEVEL {
//    }
}

