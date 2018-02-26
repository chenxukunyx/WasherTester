package com.miracle.app.uilogic.wolong;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miracle.app.R;
import com.miracle.app.base.BaseHomeUiLogic;
import com.miracle.um_base_common.listener.OnNewUmdbListener;
import com.miracle.um_base_common.util.UMTimer;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;
import com.unilife.common.utils.SharedPrefUtils;
import com.unilife.common.utils.StringUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 下午2:57
 */

public class WolongUiLogic extends BaseHomeUiLogic implements View.OnClickListener, OnNewUmdbListener {
    private WolongUiLogicImpl mWolongUiLogicImpl;

    private TextView mVoltage;
    private TextView mPower;
    private TextView mElectricity;
    private TextView mTemperature;
    private TextView mVersion;
    private TextView mRpm;
    private LinearLayout mLlNormal;
    private Button mBtnSpecial;
    private Button mBtnCw;
    private Button mBtnCcw;
    private LinearLayout mLlSpecial;
    private Button mBtnNormal;
    private Button mBtn10000;
    private Button mBtn16000;
    private Button mBtnCycle;
    private Button mBtnStop;
    private RotateImageView mRotateImageView;
    private TextView mTipTextView;

    private LinearLayout mLlCycle;
    private EditText mEtCwSpeed;
    private EditText mEtCwTime;
    private EditText mEtCcwSpeed;
    private EditText mEtCcwTime;

    private static final String CWSPEED  = "CWSPEED";
    private static final String CWTIME  = "CWTIME";
    private static final String CCWSPEED  = "CCWSPEED";
    private static final String CCWTIME  = "CCWTIME";


    public WolongUiLogic(Activity activity) {
        super(activity);
        initView();
        mWolongUiLogicImpl = new WolongUiLogicImpl(activity, mRotateImageView);
        initEvent();
    }

    private void initView() {
        mVoltage = findViewById(R.id.voltage);
        mPower = findViewById(R.id.power);
        mElectricity = findViewById(R.id.electricity);
        mTemperature = findViewById(R.id.temperature);
        mVersion = findViewById(R.id.version);
        mRpm = findViewById(R.id.rpm);
        mLlNormal = findViewById(R.id.ll_normal);
        mBtnSpecial = findViewById(R.id.btn_special);
        mBtnCw = findViewById(R.id.btn_cw);
        mBtnCcw = findViewById(R.id.btn_ccw);
        mLlSpecial = findViewById(R.id.ll_special);
        mBtnNormal = findViewById(R.id.btn_normal);
        mBtn10000 = findViewById(R.id.btn_10000);
        mBtn16000 = findViewById(R.id.btn_16000);
        mBtnCycle = findViewById(R.id.btn_cycle);
        mBtnStop = findViewById(R.id.btn_stop);
        mRotateImageView = findViewById(R.id.rotateImageView);
        mTipTextView = findViewById(R.id.tipTextView);

        mLlCycle = findViewById(R.id.ll_cycle);
        mEtCwSpeed = findViewById(R.id.et_cw_speed);
        mEtCwTime = findViewById(R.id.et_cw_time);
        mEtCcwSpeed = findViewById(R.id.et_ccw_speed);
        mEtCcwTime = findViewById(R.id.et_ccw_time);
        mEtCwSpeed.setText(SharedPrefUtils.getData(CWSPEED, 200) + "");
        mEtCwTime.setText(SharedPrefUtils.getData(CWTIME, 20) + "");
        mEtCcwSpeed.setText(SharedPrefUtils.getData(CCWSPEED, 200) + "");
        mEtCcwTime.setText(SharedPrefUtils.getData(CCWTIME, 20) + "");
    }

    private void initEvent() {
        mBtnSpecial.setOnClickListener(this);
        mBtnNormal.setOnClickListener(this);
        mBtn10000.setOnClickListener(this);
        mBtn16000.setOnClickListener(this);
        mBtnCcw.setOnClickListener(this);
        mBtnCw.setOnClickListener(this);
        mBtnCycle.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mWolongUiLogicImpl.setOnNewUmdbListener(this);
    }

    @Override
    public void onNewUmdbData(UMDB db) {
        mWolongUiLogicImpl.onNewUmdbData(db);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_special:
                special();
                break;
            case R.id.btn_normal:
                normal();
                break;
            case R.id.btn_10000:
                start(10000);
                break;
            case R.id.btn_16000:
                start(16000);
                break;
            case R.id.btn_ccw:
                start(-6000);
                break;
            case R.id.btn_cw:
                start(6000);
                break;
            case R.id.btn_cycle:
                cycle();
                break;
            case R.id.btn_stop:
                stop();
                break;
        }
    }

    private void start(int rpm) {
        hideErrorMsg();
        mWolongUiLogicImpl.start(rpm);
    }

    private void stop() {
        UMTimer.getInstance().startTimer("timer_reset_data", 500, 1, new UMTimer.UMTimerOutListener() {
            @Override
            public void UMTimeOut(String name) {
                mVoltage.setText("0 V");
                mPower.setText("0 W");
                mTemperature.setText("0 ℃");
                mVersion.setText("");
                mElectricity.setText("0 A");
            }
        });
        mWolongUiLogicImpl.stop();
    }

    private void cycle() {
        int cwspeed = StringUtils.parseInt(mEtCwSpeed.getText().toString().trim());
        int cwtime = StringUtils.parseInt(mEtCwTime.getText().toString().trim());
        int ccwspeed = StringUtils.parseInt(mEtCcwSpeed.getText().toString().trim());
        int ccwtime = StringUtils.parseInt(mEtCcwTime.getText().toString().trim());
        if (cwspeed == 0) {
            cwspeed = 200;
        }
        if (cwspeed > 16800) {
            cwspeed = 16800;
        }
        if (cwtime == 0) {
            cwtime = 20;
        }
        if (cwtime > 100) {
            cwtime = 100;
        }

        if (ccwspeed == 0) {
            ccwspeed = 200;
        }
        if (ccwspeed > 16800) {
            ccwspeed = 16800;
        }
        if (ccwtime == 0) {
            ccwtime = 20;
        }
        if (ccwtime > 100) {
            ccwtime = 100;
        }
        SharedPrefUtils.saveData(CWSPEED, cwspeed);
        SharedPrefUtils.saveData(CWTIME, cwtime);
        SharedPrefUtils.saveData(CCWSPEED, ccwspeed);
        SharedPrefUtils.saveData(CCWTIME, ccwtime);
        mWolongUiLogicImpl.cycle(cwspeed, cwtime, ccwspeed, ccwtime);
    }

    private void special() {
        stop();
        mLlNormal.setVisibility(View.GONE);
        mLlSpecial.setVisibility(View.VISIBLE);
        mLlCycle.setVisibility(View.VISIBLE);
    }

    private void normal() {
        stop();
        mLlNormal.setVisibility(View.VISIBLE);
        mLlSpecial.setVisibility(View.GONE);
        mLlCycle.setVisibility(View.GONE);
    }

    private void showErrorMsg(String msg) {
        mTipTextView.setText(msg);
        mTipTextView.setVisibility(View.VISIBLE);
    }

    private void hideErrorMsg() {
        mTipTextView.setText("");
        mTipTextView.setVisibility(View.GONE);
    }


    @Override
    public void onError(String msg) {
        stop();
        showErrorMsg(msg);
    }

    @Override
    public void onTemperatureChange(String temperature) {
        mTemperature.setText(temperature);
    }

    @Override
    public void onRpmChange(String rpm) {
        mRpm.setText(rpm);
    }

    @Override
    public void onVoltageChange(String voltage) {
        mVoltage.setText(voltage);
    }

    @Override
    public void onPowerChange(String power) {
        mPower.setText(power);
    }

    @Override
    public void onElectricityChange(String electricity) {
        mElectricity.setText(electricity);
    }

    @Override
    public void onVersionChange(String version) {
        mVersion.setText(version);
    }

    @Override
    public void release() {
        if (mWolongUiLogicImpl != null) {
            mWolongUiLogicImpl = null;
        }
    }
}
