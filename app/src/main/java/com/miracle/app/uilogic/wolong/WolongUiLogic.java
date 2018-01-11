package com.miracle.app.uilogic.wolong;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miracle.app.R;
import com.miracle.app.base.BaseHomeUiLogic;
import com.miracle.um_base_common.listener.OnNewUmdbListener;
import com.miracle.um_base_common.view.RotateImageView;
import com.unilife.common.entities.UMDB;

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
        mWolongUiLogicImpl.stop();
    }

    private void cycle() {
        mWolongUiLogicImpl.cycle();
    }

    private void special() {
        stop();
        mLlNormal.setVisibility(View.GONE);
        mLlSpecial.setVisibility(View.VISIBLE);
    }

    private void normal() {
        stop();
        mLlNormal.setVisibility(View.VISIBLE);
        mLlSpecial.setVisibility(View.GONE);
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
