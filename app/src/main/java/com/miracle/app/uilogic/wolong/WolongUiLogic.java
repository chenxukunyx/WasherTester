package com.miracle.app.uilogic.wolong;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private Button btn_over;//中止
    private RotateImageView mRotateImageView;
    private TextView mTipTextView;
    private Button btn_back;

    private RelativeLayout step_first, step_second;
    private Button btn_pass, btn_fail;

    private LinearLayout mLlCycle;
    private EditText mEtCwSpeed;
    private EditText mEtCwTime;
    private EditText mEtCcwSpeed;
    private EditText mEtCcwTime;
    private EditText mEtStableSpeed1, mEtStableSpeed2;

    private static final String CWSPEED = "CWSPEED";
    private static final String CWTIME = "CWTIME";
    private static final String CCWSPEED = "CCWSPEED";
    private static final String CCWTIME = "CCWTIME";
    private static final String STABLESPEED1 = "STABLESPEED1";
    private static final String STABLESPEED2 = "STABLESPEED2";

    private static final int NORMAL = 0x01;
    private static final int SPECIAL = 0x02;
    private int status = NORMAL;

    public WolongUiLogic(Activity activity) {
        super(activity);
        initView();
        mWolongUiLogicImpl = new WolongUiLogicImpl(activity, mRotateImageView);
        initEvent();
        String startType = getIntent().getStringExtra("startType");
        if (startType.equals("broad")) {
            normal();
            step(1);
        } else {
            step(2);
            special();
        }
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
        btn_over = findViewById(R.id.btn_over);
        mRotateImageView = findViewById(R.id.rotateImageView);
        mTipTextView = findViewById(R.id.tipTextView);
        btn_back = findViewById(R.id.btn_back);

        mLlCycle = findViewById(R.id.ll_cycle);
        mEtCwSpeed = findViewById(R.id.et_cw_speed);
        mEtCwTime = findViewById(R.id.et_cw_time);
        mEtCcwSpeed = findViewById(R.id.et_ccw_speed);
        mEtCcwTime = findViewById(R.id.et_ccw_time);
        mEtStableSpeed1 = findViewById(R.id.et_stable_speed_1);
        mEtStableSpeed2 = findViewById(R.id.et_stable_speed_2);
        mEtCwSpeed.setText(SharedPrefUtils.getData(CWSPEED, 200) + "");
        mEtCwTime.setText(SharedPrefUtils.getData(CWTIME, 20) + "");
        mEtCcwSpeed.setText(SharedPrefUtils.getData(CCWSPEED, 200) + "");
        mEtCcwTime.setText(SharedPrefUtils.getData(CCWTIME, 20) + "");
        mEtStableSpeed1.setText(SharedPrefUtils.getData(STABLESPEED1, 10000) + "");
        mEtStableSpeed2.setText(SharedPrefUtils.getData(STABLESPEED1, 16000) + "");

        step_first = findViewById(R.id.step_first);
        step_second = findViewById(R.id.step_second);
        step(1);

        btn_pass = findViewById(R.id.btn_pass);
        btn_fail = findViewById(R.id.btn_failed);
        btn_pass.setOnClickListener(this);
        btn_fail.setOnClickListener(this);
    }

    private void step(int step) {
        step_first.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        step_second.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
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
        btn_over.setOnClickListener(this);
        mWolongUiLogicImpl.setOnNewUmdbListener(this);
        btn_back.setOnClickListener(this);
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
                int stable1 = StringUtils.parseInt(mEtStableSpeed1.getText().toString().trim());
                if (stable1 <= 0) {
                    stable1 = 200;
                }
                if (stable1 > 16800) {
                    stable1 = 16800;
                }
                SharedPrefUtils.saveData(STABLESPEED1, stable1);
                start(stable1, WolongUiLogicImpl.SPECIAL);
                break;
            case R.id.btn_16000:
                int stable2 = StringUtils.parseInt(mEtStableSpeed2.getText().toString().trim());
                if (stable2 <= 0) {
                    stable2 = 200;
                }
                if (stable2 > 16800) {
                    stable2 = 16800;
                }
                SharedPrefUtils.saveData(STABLESPEED2, stable2);
                start(stable2, WolongUiLogicImpl.SPECIAL);
                break;
            case R.id.btn_ccw:
                start(-16000, WolongUiLogicImpl.NORMAL);
                break;
            case R.id.btn_cw:
                start(16000, WolongUiLogicImpl.NORMAL);
                break;
            case R.id.btn_cycle:
                cycle();
                break;
            case R.id.btn_over:
                if (status == NORMAL) {
                    mWolongUiLogicImpl.setNormalState(true);
                }
                over();
                break;
            case R.id.btn_stop:
                if (status == NORMAL) {
                    mWolongUiLogicImpl.setNormalState(true);
                }
                stop();
                break;
            case R.id.btn_back:
                stop();
                getActivity().finish();
                break;
            case R.id.btn_pass:
                mWolongUiLogicImpl.lowVoltageCheckResult(true);
                step(2);
                break;
            case R.id.btn_failed:
                mWolongUiLogicImpl.lowVoltageCheckResult(false);
                step(2);
                break;
        }
    }

    private void start(int rpm, String step) {
        hideErrorMsg();
        int realRpm = (int) (rpm / 11.5);
        mWolongUiLogicImpl.start(realRpm, step);
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

    private void over() {
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
        mWolongUiLogicImpl.over();
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
        status = SPECIAL;
        mWolongUiLogicImpl.setNormalState(false);
        stop();
        mLlNormal.setVisibility(View.GONE);
        mLlSpecial.setVisibility(View.VISIBLE);
        mLlCycle.setVisibility(View.VISIBLE);
    }

    private void normal() {
        status = NORMAL;
        mWolongUiLogicImpl.setNormalState(false);
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
