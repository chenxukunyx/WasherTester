package com.unilife.variety.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by 万启林 on 2015/8/3.
 */
public class SharedpreferencesUtil {
    public static void putString(Context context, String dbName, String key, String value) {
        SharedPreferences sp = null;
        if(TextUtils.isEmpty(dbName)) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }else {
            sp = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor et = sp.edit();
        et.putString(key, value);
        et.commit();
    }

    public static void putInt(Context context, String name, String key, int value) {
        putString(context, name, key, value+"");
    }

    public static void putLong(Context context, String name, String key, long value) {
        putString(context, name, key, value+"");
    }

    public static String getString(Context context, String name, String key) {
        SharedPreferences sp = null;
        if(TextUtils.isEmpty(name)) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }else {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        return sp.getString(key, "");
    }

    public static long getLong(Context context, String name, String key) {
        String value = getString(context, name, key);
        long tmp = 0;
        try {
            tmp = Long.parseLong(value);
        } catch (Exception e) {
        }
        return tmp;
    }

    public static int getInt(Context context, String name, String key) {
        String value = getString(context, name, key);
        int tmp = 0;
        try {
            tmp = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return tmp;
    }
}
