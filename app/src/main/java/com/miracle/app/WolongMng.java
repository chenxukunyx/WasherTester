package com.miracle.app;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/3/13
 * @time: 下午1:56
 */

public class WolongMng {

    private static boolean mLowSpeed;
    private static boolean mHighSpeed;

    public static boolean ismLowSpeed() {
        return mLowSpeed;
    }

    public static void setmLowSpeed(boolean lowSpeed) {
        mLowSpeed = lowSpeed;
        mHighSpeed = !lowSpeed;
    }

    public static boolean ismHighSpeed() {
        return mHighSpeed;
    }

    public static void setmHighSpeed(boolean highSpeed) {
        mHighSpeed = highSpeed;
        mLowSpeed = !highSpeed;
    }

    public static void reset() {
        mHighSpeed = false;
        mLowSpeed = false;
    }
}
