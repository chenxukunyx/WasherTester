package com.miracle.app.uilogic.wolong;

import android.app.Activity;
import android.text.TextUtils;

import com.miracle.app.model.WolongModel;
import com.miracle.um_base_common.base.BaseUiLogicImpl;
import com.miracle.um_base_common.listener.OnNewUmdbListener;
import com.miracle.um_base_common.model.BaseModel;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/26
 * @time: 下午3:15
 */

public class WolongUiLogicImpl extends BaseUiLogicImpl {

    private WolongModel mModel;
    public WolongUiLogicImpl(Activity activity, RotateImageView rotateImageView) {
        super(activity, rotateImageView);
        mModel = new WolongModel();
    }

    @Override
    public void onNewUmdbData(UMDB db) {
        Integer version = null;
        Integer voltage = null;
        Float ele = null;
        Float power = null;
        Integer rpm = null;
        Integer temperature = null;
        Integer faultCode = null;
        String s = db.getValue(UMDB.SoftwareVersion_A1);
        boolean b = db.containValue(UMDB.SoftwareVersion_A1);
        if (db.containValue(UMDB.SoftwareVersion_A1) && !TextUtils.isEmpty(db.getValue(UMDB.SoftwareVersion_A1))) {
            version = db.getIntegerValue(UMDB.SoftwareVersion_A1);
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onVersionChange(version + "");
            }
        }

        if (db.containValue(UMDB.DCBusVoltage_A4) && !TextUtils.isEmpty(db.getValue(UMDB.DCBusVoltage_A4))) {
            voltage = db.getIntegerValue(UMDB.DCBusVoltage_A4);
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onVoltageChange(voltage + " V");
            }
        }

        if (db.containValue(UMDB.Current_A4) && !TextUtils.isEmpty(db.getValue(UMDB.Current_A4))) {
            ele = db.getFloatValue(UMDB.Current_A4);
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onElectricityChange(ele + " A");
            }
        }

        if (db.containValue(UMDB.DrumSpeed_A4) && !TextUtils.isEmpty(db.getValue(UMDB.DrumSpeed_A4))) {
            rpm = db.getIntegerValue(UMDB.DrumSpeed_A4);
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onRpmChange(rpm + "");
            }
        }

        if (db.containValue(UMDB.IPMTemperature_A4) && !TextUtils.isEmpty(db.getValue(UMDB.IPMTemperature_A4))) {
            temperature = db.getIntegerValue(UMDB.IPMTemperature_A4);
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onTemperatureChange(temperature + " ℃");
            }
        }

        if (ele != null && voltage != null) {
            power = ele * voltage;
            if (mOnNewUmdbListener != null) {
                mOnNewUmdbListener.onPowerChange(power + " W");
            }
        }

        if (db.containValue(UMDB.FaultCode_A4) && !TextUtils.isEmpty(db.getValue(UMDB.FaultCode_A4))) {
            faultCode = db.getIntegerValue(UMDB.FaultCode_A4);
            String errorMsg = FaultCodeInfo.getErrorMsg(faultCode);
            if (errorMsg != null) {
                if (mOnNewUmdbListener != null) {
                    mOnNewUmdbListener.onError(errorMsg);
                }
            }
        }
    }

    public static class FaultCodeInfo {
        static Map<Integer, String> mErrorMsg;

        static {
            mErrorMsg = new HashMap<>();
            mErrorMsg.put(0x0001, "Reset");
            mErrorMsg.put(0x0002, "Read and Write Flash");
            mErrorMsg.put(0x0004, "IPM temperature circuit");
            mErrorMsg.put(0x0008, "Over current detect");
            mErrorMsg.put(0x0010, "Bus over voltage detect");
            mErrorMsg.put(0x0020, "Bus under voltage detect");
            mErrorMsg.put(0x0040, "Exceeded maximum power");
            mErrorMsg.put(0x0080, "Heat sink over temperature (>115℃)");
            mErrorMsg.put(0x0200, "Open phase detect");
            mErrorMsg.put(0x0400, "Tachometer signal missing");
        }

        static String getErrorMsg(int key) {
            for (Map.Entry<Integer, String> entry : mErrorMsg.entrySet()) {
                if (key == entry.getKey()) {
                    return entry.getValue();
                }
            }
            return null;
        }
    }

    private void checkCmd() {
        onSendCmd(mModel.getPingCmd(), mRotateImageView, NONE);
        onSendCmd(mModel.getFTCTestCmd(), mRotateImageView, NONE);
    }

    public void start(int rpm) {
        checkCmd();
        if (rpm > 0) {
            onSendCmd(mModel.getSpeedSetCmd(rpm), mRotateImageView, CW);
            onSendCmd(mModel.getFTCTestCmd(), mRotateImageView, CW);
        } else {
            onSendCmd(mModel.getSpeedSetCmd(rpm), mRotateImageView, CCW);
            onSendCmd(mModel.getFTCTestCmd(), mRotateImageView, CCW);
        }
    }

    public void stop() {
        onSendCmd(mModel.getStopCmd(), mRotateImageView, NONE);
    }

    public void cycle() {

    }
}
