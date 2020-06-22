package com.rust.sip.GB28181;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rust.sip.GB28181.ua.Receiver;
import com.rust.sip.GB28181.ua.Settings;

public class SipEngine {

    public static boolean on(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(Settings.PREF_ON, Settings.DEFAULT_ON);
    }

    public static void on(Context context,boolean on) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(Settings.PREF_ON, on);
        edit.commit();
        if (on) Receiver.engine(context).isRegistered();
    }

}
