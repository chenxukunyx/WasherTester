package com.miracle.um_base_common;

import android.text.TextUtils;

import com.miracle.um_base_common.util.CountTimerUtil;
import com.miracle.um_base_common.util.MLog;
import com.miracle.um_base_common.util.SystemUtils;
import com.unilife.common.apps.UIBaseApplication;

/**
 * Created by 万启林 on 2015/7/27.
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
        CountTimerUtil.getInstance();//开启计时器
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
