package com.unilife.variety.logic;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;

import com.unilife.common.entities.UMDB;
import com.unilife.variety.R;
import com.unilife.variety.entity.TesterEntity;
import com.unilife.variety.listener.OnRefreshUiListener;
import com.unilife.variety.logic.base.HomeBaseUILogic;
import com.unilife.variety.model.GeLanshiModel;
import com.unilife.variety.utils.MLog;
import com.unilife.variety.utils.StringUtils;
import com.unilife.variety.utils.UMTimer;
import com.unilife.variety.widget.RotateImageView;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 下午5:19
 */

public class TestRunningLogic extends HomeBaseUILogic {

    private static final String TAG = "TestRunningLogic";

    private RotateImageView mRotateImageView;
    private OnRefreshUiListener mRefreshUiListener;
    private Handler mHandler = new Handler();
    private GeLanshiModel mModel;
    private char[] speeds;
    private int mRpm;
    private boolean stoped = true;

    java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");

    public TestRunningLogic(Activity activity) {
        super(activity);
        if (mModel == null) {
            mModel = new GeLanshiModel();
        }
        mRotateImageView = findViewById(R.id.rotateImageView);
    }

    private void checkCmd() {
        onSendCmd(mModel.getRatioCmd(), mRotateImageView, NONE);
        onSendCmd(mModel.getSetTemperatureCmd(), mRotateImageView, NONE);
        TransmatterLogic.getTransmatterLogic().getTransmitter().write(mModel.getVersionCmd(), 9);
        onSendCmd(mModel.getFetchStatusM(), mRotateImageView, NONE);
    }

    private Runnable sendCheckCmd = new Runnable() {
        @Override
        public void run() {
            checkCmd();
            mHandler.postDelayed(sendRunnable, 500);
        }
    };

    private Runnable sendRunnable = new Runnable() {
        @Override
        public void run() {
            onSendCmd(mModel.getSpinCmd(speeds[0], speeds[1], speeds[2]), mRotateImageView, CCW);
            mHandler.postDelayed(sendCheckCmdRunnable, 1000);
        }
    };

    private Runnable sendCheckCmdRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.post(sendFetchStatusI);
        }
    };

    private Runnable sendFetchStatusI = new Runnable() {
        @Override
        public void run() {
            onSendCmd(mModel.getFetchStatusI(), mRotateImageView, CCW);
            mHandler.postDelayed(sendFetchStatusM, 1000);
        }
    };

    private Runnable sendFetchStatusM = new Runnable() {
        @Override
        public void run() {
            onSendCmd(mModel.getFetchStatusM(), mRotateImageView, CCW);
            mHandler.postDelayed(sendFetchStatusI, 1000);
        }
    };

    @Override
    protected void start(int rpm) {
        stopSpin();
        mRpm = rpm;
        startSpin(rpm);
    }

    @Override
    protected void stop() {
        stoped = true;
        mRpm = 0;
        stopSpin();
        UMTimer.getInstance().startTimer("timer_check_rpm", 1000, 1, true, new UMTimer.UMTimerOutListener() {
            @Override
            public void UMTimeOut(String name) {
                onSendCmd(mModel.getFetchStatusI(), mRotateImageView, NONE);
            }
        });
        UMTimer.getInstance().startTimer("timer_stop", 10000, 1, true, new UMTimer.UMTimerOutListener() {
            @Override
            public void UMTimeOut(String name) {
                onSendCmd(mModel.getPauseCmd(), mRotateImageView, NONE);
            }
        });
    }

    private void startSpin(int rpm) {
        UMTimer.getInstance().stopTimer("timer_check_rpm");
        UMTimer.getInstance().stopTimer("timer_stop");
        stoped = false;
        if (mRefreshUiListener != null) {
            mRefreshUiListener.hideTipView();
        }
        speeds = parseIntToCharArray(rpm);
        mHandler.post(sendCheckCmd);
    }

    private void stopSpin() {
        removeAllCallbacks();
        onSendCmd(mModel.getPauseCmd(), mRotateImageView, NONE);
    }

    private void removeAllCallbacks() {
        mHandler.removeCallbacks(sendCheckCmd);
        mHandler.removeCallbacks(sendRunnable);
        mHandler.removeCallbacks(sendCheckCmdRunnable);
        mHandler.removeCallbacks(sendFetchStatusI);
        mHandler.removeCallbacks(sendFetchStatusM);
    }

    public void setRefreshUiListener(OnRefreshUiListener listener) {
        mRefreshUiListener = listener;
    }

    public void onNewUmdbData(UMDB db) {
        int error = (int) db.getIntValue(UMDB.PadCommErr);
        if (error == 1) {
            if (mRefreshUiListener != null) {
                mRefreshUiListener.showTipView("无通讯");
            }
        }
        int loadVersion = StringUtils.parseInteger(getConfigEntity().getVersion());
        int loadMaxVoltage = StringUtils.parseInteger(getConfigEntity().getMaxVoltage());
        int loadMaxEle = StringUtils.parseInteger(getConfigEntity().getMaxElectricity());

        Integer int_rpm = null;
        Integer int_voltage = null;
        Integer int_power = null;
        Float flo_electricity = null;
        String str_version = null;
        Integer int_temperature = null;

        //转速
        if (!TextUtils.isEmpty(db.getValue(UMDB.MotorAckData18))) {
            MLog.d(TAG, "MotorAckData18 back");
            int highRpm = (int) db.getIntValue(UMDB.MotorAckData11);
            int mediumRpm = (int) db.getIntValue(UMDB.MotorAckData12);
            int lowRpm = (int) db.getIntValue(UMDB.MotorAckData13);
            int decimalRpm = (int) db.getIntValue(UMDB.MotorAckData14);
            int_rpm = ascToHex(highRpm, mediumRpm, lowRpm, decimalRpm);
            int_rpm = int_rpm / 16;
            if (!stoped) {
                if (int_rpm > mRpm - 15) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stop();
                        }
                    }, 3000);
                }
            }
        }

        if (!TextUtils.isEmpty(db.getValue(UMDB.MotorAckData52))) {
            MLog.d(TAG, "MotorAckData52 back");
            int highTemp = (int) db.getIntValue(UMDB.MotorAckData50);
            int lowTemp = (int) db.getIntValue(UMDB.MotorAckData51);
            int_temperature = ascToHex(highTemp, lowTemp);
        }

        //版本
        if (!TextUtils.isEmpty(db.getValue(UMDB.MotorAckData32))) {
            MLog.d(TAG, "MotorAckData32 back");
            int version3 = getIntUmdbValue(db, UMDB.MotorAckData30);
            int version4 = getIntUmdbValue(db, UMDB.MotorAckData31);
            MLog.i(version3 + " : " + version4);
            if (getIntUmdbValue(db, UMDB.MotorAckData26) == '0' &&
                    getIntUmdbValue(db, UMDB.MotorAckData27) == '4') {
                str_version = "00";
                str_version += String.valueOf((char) version3);
                str_version += String.valueOf((char) version4);
            }
        }

        if (!TextUtils.isEmpty(db.getValue(UMDB.MotorAckData42))) {
            MLog.d(TAG, "MotorAckData42 back");
            //电压
            int highVoltage = getIntUmdbValue(db, UMDB.MotorAckData34);
            int lowVoltage = getIntUmdbValue(db, UMDB.MotorAckData35);
            int_voltage = ascToHex(highVoltage, lowVoltage);
            int_voltage = int_voltage * 2;

            int highPower = getIntUmdbValue(db, UMDB.MotorAckData38);
            int lowPower = getIntUmdbValue(db, UMDB.MotorAckData39);
            //功率
            int_power = ascToHex(highPower, lowPower);
            int_power = int_power * 32;
        }

        if (int_voltage != null && int_voltage != 0) {
            flo_electricity = (float) int_power / int_voltage;
        }

        if (int_rpm != null) {
            if (mRefreshUiListener != null) {
                mRefreshUiListener.onRpmChange(int_rpm + "");
            }
        }
        if (int_voltage != null) {
            mRefreshUiListener.onVoltageChange(int_voltage + "V");
            if (int_voltage > loadMaxVoltage) {
                mRefreshUiListener.showTipView("电压异常");
            }
        }
        if (int_power != null) {
            mRefreshUiListener.onPowerChange(int_power + "W");
        }
        if (flo_electricity != null) {
            mRefreshUiListener.onElectricityChange(df.format(flo_electricity) + "A");
            if (flo_electricity > loadMaxEle) {
                mRefreshUiListener.showTipView("电流过大");
            }
        }
        if (str_version != null) {
            mRefreshUiListener.onVersionChange(str_version);
            if (StringUtils.parseInteger(str_version) != loadVersion) {
                mRefreshUiListener.showTipView("版本设置不正确");
            }
        }
        if (int_temperature != null) {
            mRefreshUiListener.onTemperatureChange(int_temperature + "℃");
        }
    }

    public boolean isStoped() {
        return stoped;
    }

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    public TesterEntity getConfigEntity() {
        ConfigLogic logic = new ConfigLogic();
        TesterEntity entity = logic.loadConfig(mActivity);
        return entity;
    }
}
