package com.unilife.variety.utils;

import android.util.Log;

/**
 * Create with Android studio
 *
 * @fuction:
 * @author: chenxukun
 * @date: 2017-08-17
 * @time: 09:38
 * @age: 24
 */
public class MLog {

    private static final String TAG = "MLog";

    public static void v(Object msg) {
        v(null, msg);
    }

    public static void v(String tag, Object msg){
        checkTagNull(tag);
        Log.v(tag, "----->>" + msg.toString());
    }

    public static void d(Object msg) {
        d(null, msg);
    }

    public static void d(String tag, Object msg){
        checkTagNull(tag);
        Log.d(tag, "----->>" + msg.toString());
    }

    public static void i(Object msg) {
        i(null, msg);
    }

    public static void i(String tag, Object msg){
        checkTagNull(tag);
        Log.i(tag, "----->>" + msg.toString());
    }

    public static void w(Object msg) {
        w(null, msg);
    }

    public static void w(String tag, Object msg){
        checkTagNull(tag);
        Log.w(tag, "----->>" + msg.toString());
    }

    public static void e(Object msg) {
        e(null, msg);
    }

    public static void e(String tag, Object msg){
        checkTagNull(tag);
        Log.e(tag, "----->>" + msg.toString());
    }

    private static void checkTagNull(String tag) {
        if (tag == null) {
            tag = TAG;
        }
    }



}
