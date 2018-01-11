package com.unilife.common.apps;

import android.app.Application;

import com.unilife.common.UmInit;
import com.unilife.common.services.IControlService;
import com.unilife.common.utils.SharedPrefUtils;

/**
 * Created by Kevin on 2015/7/16.
 */
public class UIBaseApplication extends Application {

    public static UIBaseApplication _instance;
    private String brand;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        UmInit.getInstance().init(this);
    }

    public static UIBaseApplication get_instance() {
        if (_instance == null) {
            _instance = new UIBaseApplication();
        }
        return _instance;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

}
