package com.unilife.common;

import android.content.Context;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/1/4
 * @time: 下午5:26
 */

public class UmInit {

    private Context mContext;
    private static UmInit mInstance;

    public static UmInit getInstance() {
        if (mInstance == null) {
            synchronized (UmInit.class) {
                if (mInstance == null) {
                    mInstance = new UmInit();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public Context getContext() {
        return mContext;
    }
}
