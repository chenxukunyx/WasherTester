package com.unilife.common.uilogic;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.Window;

import com.unilife.common.entities.UMDB;
import com.unilife.common.ui.activities.UMBaseActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 下午2:05
 */

public abstract class UMBaseUiLogic {

    public static final String TAG = "HomeBaseUILogic";
    private Window mWindow;
    protected Activity mActivity;

    public UMBaseUiLogic(Activity activity, Window window) {
        mActivity = activity;
        mWindow = window;
    }

    public UMBaseUiLogic(Activity activity) {
        mActivity = activity;
        mWindow = mActivity.getWindow();
    }

    protected <T extends View> T findViewById(int id) {
        if (mWindow == null) {
            return null;
        }
        return (T) mWindow.findViewById(id);
    }

    public void startActivity(Intent intent) {
        try {
            mActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int ascToHex(int... param) {
        if (param.length == 0) {
            return 0;
        }
        String hexString = "";

        for (int i = 0; i < param.length; i++) {
            hexString += String.valueOf((char) param[i]);
        }
        try {
            return Integer.parseInt(hexString, 16);
        } catch (Exception e) {

        }
        return 0;
    }

    protected int getIntUmdbValue(UMDB umdb, String key) {
        return (int) umdb.getIntValue(key);
    }

    protected char[] parseIntToCharArray(int value) {
        char[] data = new char[3];
        String hex_value = Integer.toHexString(value);
        int len = hex_value.length();
        if (len == 0) {
            hex_value = "000";
        } else if (len == 1) {
            hex_value  = "00" + hex_value;
        } else if (len == 2) {
            hex_value = "0" + hex_value;
        }
        for (int i = 0; i < hex_value.length(); i++) {
            data[i] = hex_value.charAt(i);
        }
        return data;
    }

    public void release() {

    }

    public abstract void onNewUmdbData(UMDB db);
}
