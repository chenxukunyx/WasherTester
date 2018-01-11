package com.miracle.um_base_common.base;

import android.content.Context;

import com.miracle.um_base_common.MyApplication;


/**
 * Created by 万启林 on 2015/7/27.
 */
public class BaseLogic {
    protected static String TAG;
    private Context mContext;

    public BaseLogic() {
        TAG = getClass().getSimpleName();
        mContext = MyApplication.getInstance();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void init() {

    }

    public void unInit() {

    }
}
