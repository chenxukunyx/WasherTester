package com.miracle.app.uilogic.jide;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.miracle.app.model.JiDeModel;
import com.miracle.um_base_common.base.BaseUiLogicImpl;
import com.miracle.um_base_common.entity.ConfigEntity;
import com.miracle.um_base_common.listener.OnNewUmdbListener;
import com.miracle.um_base_common.logic.ConfigLogic;
import com.miracle.um_base_common.util.UMTimer;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;

import java.text.DecimalFormat;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/4/7
 * @time: 下午1:39
 */

public class JiDeUiLogicImpl extends BaseUiLogicImpl {

    private static final String TAG = "JiDeUiLogicImpl";
    private JiDeModel mModel;
    private boolean running;
    private static final String TIMER_CCW = "TIMER_CCW";
    private static final String TIMER_STOP = "TIMER_STOP";
    private ConfigLogic mConfigLogic;
    private ConfigEntity mConfigEntity;
    private Handler mHandler = new Handler();
    private int mDirect;

    private boolean lowVoltageResult;

    DecimalFormat mFormat = new DecimalFormat("0.0");

    public JiDeUiLogicImpl(Activity activity, RotateImageView rotateImageView) {
        super(activity, rotateImageView);
        mModel = new JiDeModel();
        mConfigLogic = new ConfigLogic();
        mConfigEntity = mConfigLogic.getConfig();
        Log.i("cxk", "init WolongUiLogicImpl: ");
    }

    @Override
    public void onNewUmdbData(UMDB data) {
        Integer rpm = null;
        Integer voltage = null;
        String version = null;
        float electricity = 0.0f;
        Integer power = null;

        //查询状态c 返回值
        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData24))) {
            Log.d(TAG, "======>MotorAckData24 back");

        }
        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData18))) {
            Log.d(TAG, "======>MotorAckData18 back");

            //实际转向
            int highRpm = getUMDBValue(data, UMDB.MotorAckData11);
            int mediaRpm = getUMDBValue(data, UMDB.MotorAckData12);
            int lowRpm = getUMDBValue(data, UMDB.MotorAckData13);
            int xiaoshuRpm = getUMDBValue(data, UMDB.MotorAckData14);
            rpm = ascToHex(highRpm, mediaRpm, lowRpm, xiaoshuRpm);
            rpm = rpm / 16;

        }


        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData32))) {
            Log.d(TAG, "======>MotorAckData32 back");
            int version3 = getUMDBValue(data, UMDB.MotorAckData30);
            int version4 = getUMDBValue(data, UMDB.MotorAckData31);
            Log.i(TAG, "--------------------->version: " + version3 + "   " + version4);
            version = "00";
            if (version3 == 0) {
                version += "0";
            } else {
                version += String.valueOf((char) version3);
            }
            if (version4 == 0) {
                version += "0";
            } else {
                version += String.valueOf((char) version4);
            }
        }

        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData42))) {
            Log.d(TAG, "======>MotorAckData42 back");
            //电压
            int highVoltage = getUMDBValue(data, UMDB.MotorAckData34);
            int lowVoltage = getUMDBValue(data, UMDB.MotorAckData35);
            voltage = ascToHex(highVoltage, lowVoltage);
            voltage = voltage * 2;

            int highPower = getUMDBValue(data, UMDB.MotorAckData38);
            int lowPower = getUMDBValue(data, UMDB.MotorAckData39);
            if (highPower > 80) {
                highPower = 48;
            }

            //功率
            power = ascToHex(highPower, lowPower);
            power = power * 32;
//            Log.i(TAG, "--------------->power: " + highPower + "  " + lowPower);
//            Log.i(TAG, "--------------->power: " + power);
        }
        checkListenerNotNull(mOnNewUmdbListener);
        if (rpm != null) {
            mOnNewUmdbListener.onRpmChange(((int)(rpm * 11.5)) + "");
        }
        if (power != null) {
            mOnNewUmdbListener.onPowerChange(power + " W");
        }
        mOnNewUmdbListener.onTemperatureChange("25");
        if (voltage != null) {
            mOnNewUmdbListener.onVoltageChange(voltage + " V");
        }

        if (version != null) {
            mOnNewUmdbListener.onVersionChange(version + "");
        }
        if (voltage != null && voltage != 0) {
            electricity = (float) (power * 1.0 / voltage);
            mOnNewUmdbListener.onElectricityChange(mFormat.format(electricity) + " A");
        }
    }

    private void checkListenerNotNull(OnNewUmdbListener listener) {
        if (listener == null) {
            return;
        }
    }

    private void checkCmd() {
        onSendCmd(mModel.getSetStatusVersionCmd(), mRotateImageView, NONE);
        onSendCmd(mModel.getFetchStatusM(), mRotateImageView, NONE);
    }

    public void start(int direct, int rpm) {
        running = true;
        mDirect = direct;
        checkCmd();
        onSendCmd(mModel.getRunningCmd(direct, (int) (rpm / 11.5)), mRotateImageView, direct);
        checkRunningStatus();
    }

    public void checkRunningStatus() {
        mHandler.post(sendFetchStatusI);
    }

    public void removeAllHandlerCall() {
        mDirect = NONE;
        mHandler.removeCallbacks(sendFetchStatusI);
        mHandler.removeCallbacks(sendFetchStatusM);
    }

    private Runnable sendFetchStatusI = new Runnable() {
        @Override
        public void run() {
            onSendCmd(mModel.getFetchStatusI(), mRotateImageView, mDirect);
            mHandler.postDelayed(sendFetchStatusM, 600);
        }
    };

    private Runnable sendFetchStatusM = new Runnable() {
        @Override
        public void run() {
            onSendCmd(mModel.getFetchStatusM(), mRotateImageView, mDirect);
            mHandler.postDelayed(sendFetchStatusI, 600);
        }
    };

    public void stop() {
        running = false;
        removeAllHandlerCall();
        onSendCmd(mModel.getPauseCmd(), mRotateImageView, NONE);
        UMTimer.getInstance().stopTimer(TIMER_CCW);
        UMTimer.getInstance().stopTimer(TIMER_STOP);
    }

    public void cycle(int cwspeed, int cwtime, final int ccwspeed, int ccwtime) {
        start(JiDeModel.CW, cwspeed);
        UMTimer.getInstance().startTimer(TIMER_CCW, (cwtime + 20) * 1000, 1, new UMTimer.UMTimerOutListener() {
            @Override
            public void UMTimeOut(String name) {
                start(JiDeModel.CCW, ccwspeed);
            }
        });
        UMTimer.getInstance().startTimer(TIMER_STOP, (cwtime + ccwtime + 20) * 1000, 1, new UMTimer.UMTimerOutListener() {
            @Override
            public void UMTimeOut(String name) {
                stop();
            }
        });
    }

    private int getUMDBValue(UMDB db, String key) {
        return (int) (db.getIntValue(key) & 0x7f);
    }

    public void lowVoltageCheckResult(boolean result) {
        lowVoltageResult = result;
    }
}
