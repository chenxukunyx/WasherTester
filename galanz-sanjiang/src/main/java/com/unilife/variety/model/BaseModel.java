package com.unilife.variety.model;

import android.content.Context;

import com.unilife.variety.MyApplication;


/**
 * Created by 万启林 on 2015/7/27.
 */
public class BaseModel {
    private Context mContext;

    public BaseModel() {
        mContext = MyApplication.getInstance();
    }

    public void init(){}

    public void unInit(){}

    public Context getContext() {
        return mContext;
    }
}
