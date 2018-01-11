package com.miracle.um_base_common.base;

import android.app.Activity;
import android.support.annotation.IntDef;

import com.miracle.um_base_common.entity.TesterEntity;
import com.miracle.um_base_common.listener.OnNewUmdbListener;
import com.miracle.um_base_common.logic.ConfigLogic;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;
import com.unilife.common.ui.activities.UMBaseActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/26
 * @time: 下午3:13
 */

public abstract class BaseUiLogicImpl {
    protected Activity mActivity;
    protected RotateImageView mRotateImageView;
    public static final int NONE = 0;
    public static final int CW = 1;
    public static final int CCW = 2;

    public BaseUiLogicImpl(Activity activity, RotateImageView rotateImageView) {
        mActivity = activity;
        mRotateImageView = rotateImageView;
    }

    protected OnNewUmdbListener mOnNewUmdbListener;

    protected TesterEntity getParamConfig() {
        ConfigLogic logic = new ConfigLogic();
        TesterEntity entity = logic.loadConfig();
        return entity;
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

    public void setOnNewUmdbListener(OnNewUmdbListener onNewUmdbListener) {
        mOnNewUmdbListener = onNewUmdbListener;
    }

    public abstract void onNewUmdbData(UMDB db);

    public void onSendCmd(UMDB fridgeDB, RotateImageView view, @TYPE int direction) {
        ((UMBaseActivity) mActivity).update(fridgeDB);
        if (direction == CW) {
            view.cw();
        } else if (direction == CCW) {
            view.ccw();
        } else {
            view.hide();
        }
    }

    @IntDef({NONE, CW, CCW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {

    }
}
