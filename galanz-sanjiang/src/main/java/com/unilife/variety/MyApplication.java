package com.unilife.variety;

import android.text.TextUtils;

import com.unilife.common.apps.UIBaseApplication;
import com.unilife.variety.utils.MLog;
import com.unilife.variety.utils.SystemUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/27
 * @time: 上午11:21
 */

public class MyApplication extends UIBaseApplication {
    private final String TAG = "MyApplication";
    private static MyApplication mInstance;
    private String mVerName;
    private int mVerCode;
    private String mDevCode;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        CountTimerUtil.getInstance();//开启计时器
        MLog.d(TAG, "onCreate");
    }

    public String getVerName() {
        if(TextUtils.isEmpty(mVerName)) {
            mVerName = SystemUtils.getVersionName(this);
        }
        return mVerName;
    }

    public int getVerCode() {
        if(mVerCode == 0) {
            mVerCode = SystemUtils.getVersionCode(this);
        }
        return mVerCode;
    }

    public String getDevCode() {
        if(TextUtils.isEmpty(mDevCode)) {
            mDevCode = SystemUtils.getMac(this);
        }
        return mDevCode;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
