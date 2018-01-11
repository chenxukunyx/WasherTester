package com.unilife.variety.logic;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.unilife.common.entities.UMDB;
import com.unilife.variety.R;
import com.unilife.variety.listener.OnRefreshUiListener;
import com.unilife.variety.listener.OnSaveListener;
import com.unilife.variety.logic.base.HomeBaseUILogic;
import com.unilife.variety.ui.SettingActivity;
import com.unilife.variety.ui.dialog.SetRpmDialog;
import com.unilife.variety.utils.MLog;
import com.unilife.variety.utils.StringUtils;
import com.unilife.variety.utils.UMTimer;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 上午11:57
 */

public class HomeUIRefreshLogic extends HomeBaseUILogic implements View.OnClickListener, OnRefreshUiListener, UMTimer.UMTimerOutListener{

    private TextView mTvVoltage;
    private TextView mTvPower;
    private TextView mTvElectricity;
    private TextView mTvTemperature;
    private TextView mTvVersion;
    private TextView mTipTextView;
    private TextView mTvRpm;
    private Button mBtnLowRpm, mBtnHighRpm, mBtnStop;
    private ImageView setting;

    private static final String TIMER_SET_RPM = "timer_set_rpm";
    Button mBtnSetRpmLeft, mBtnSetRpmRight;
    private int setRpmCount;

    private TestRunningLogic mTestRunningLogic;

    public HomeUIRefreshLogic(Activity activity) {
        super(activity);
        initView();
        mTestRunningLogic = new TestRunningLogic(activity);
        refreshRpm();
        addListener();
    }

    public void refreshRpm() {
        String lowRpm = mTestRunningLogic.getConfigEntity().getLowRpm();
        String highRpm = mTestRunningLogic.getConfigEntity().getHighRpm();
        mBtnLowRpm.setText(lowRpm);
        mBtnHighRpm.setText(highRpm);
    }

    private void initView() {
        mTvVoltage = findViewById(R.id.tv_voltage);
        mTvPower = findViewById(R.id.tv_power);
        mTvElectricity = findViewById(R.id.tv_electricity);
        mTvTemperature = findViewById(R.id.tv_temperature);
        mTvVersion = findViewById(R.id.tv_version);
        mTipTextView = findViewById(R.id.tipTextView);
        mTvRpm = findViewById(R.id.tv_rpm);
        mBtnLowRpm = findViewById(R.id.btn_low_rpm);
        mBtnHighRpm = findViewById(R.id.btn_high_rpm);
        mBtnStop = findViewById(R.id.btn_stop);
        setting = findViewById(R.id.setting);

        mBtnSetRpmLeft = findViewById(R.id.btn_set_rpm_left);
        mBtnSetRpmRight = findViewById(R.id.btn_set_rpm_right);
    }

    private void addListener() {
        mTestRunningLogic.setRefreshUiListener(this);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, SettingActivity.class));
            }
        });
        mBtnLowRpm.setOnClickListener(this);
        mBtnHighRpm.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnSetRpmLeft.setOnClickListener(this);
        mBtnSetRpmRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.btn_low_rpm == id) {
            int rpm = StringUtils.parseInteger(mTestRunningLogic.getConfigEntity().getLowRpm());
            MLog.i(rpm);
            this.start(rpm);
        } else if (R.id.btn_high_rpm == id) {
            int rpm = StringUtils.parseInteger(mTestRunningLogic.getConfigEntity().getHighRpm());
            MLog.i(rpm);
            this.start(rpm);
        } else if (R.id.btn_stop == id) {
            this.stop();
        } else if (R.id.btn_set_rpm_left == id) {
            if (setRpmCount % 2 == 0) {
                setRpmCount++;
            } else {
                setRpmCount = 0;
            }
            UMTimer.getInstance().stopTimer(TIMER_SET_RPM);
            UMTimer.getInstance().startTimer(TIMER_SET_RPM, 10000, 1, this);
        } else if (R.id.btn_set_rpm_right == id) {
            if (setRpmCount % 2 == 1) {
                setRpmCount++;
            } else {
                setRpmCount = 0;
            }
            setRpm();
            UMTimer.getInstance().stopTimer(TIMER_SET_RPM);
            UMTimer.getInstance().startTimer(TIMER_SET_RPM, 10000, 1, this);
        }
        btnStatus(id);
    }

    @Override
    public void UMTimeOut(String name) {
        setRpmCount = 0;
    }

    private void setRpm() {
        if (3 < setRpmCount) {
            SetRpmDialog dialog = new SetRpmDialog(mActivity, new OnSaveListener() {
                @Override
                public void onSave() {
                    refreshRpm();
                }
            });
            dialog.show();
        }
    }

    @Override
    protected void start(int rpm) {
        mTestRunningLogic.start(rpm);
    }

    @Override
    protected void stop() {
        mTestRunningLogic.stop();
    }

    private void btnStatus(int id) {
        if (id == R.id.btn_low_rpm) {
            mBtnLowRpm.setClickable(false);
            mBtnHighRpm.setClickable(true);
            mBtnLowRpm.setBackgroundResource(R.drawable.shape_togglebutton_bg);
            mBtnHighRpm.setBackgroundResource(R.drawable.shape_button_bg);
        } else if (id == R.id.btn_high_rpm) {
            mBtnHighRpm.setClickable(false);
            mBtnLowRpm.setClickable(true);
            mBtnLowRpm.setBackgroundResource(R.drawable.shape_button_bg);
            mBtnHighRpm.setBackgroundResource(R.drawable.shape_togglebutton_bg);
        } else if (id == R.id.btn_stop) {
            mBtnLowRpm.setClickable(true);
            mBtnHighRpm.setClickable(true);
            mBtnLowRpm.setBackgroundResource(R.drawable.shape_button_bg);
            mBtnHighRpm.setBackgroundResource(R.drawable.shape_button_bg);
        } else if (id == -1){
            mBtnLowRpm.setClickable(true);
            mBtnHighRpm.setClickable(true);
            mBtnLowRpm.setBackgroundResource(R.drawable.shape_button_bg);
            mBtnHighRpm.setBackgroundResource(R.drawable.shape_button_bg);
        }
    }

    public void onNewUmdbData(UMDB db) {
        mTestRunningLogic.onNewUmdbData(db);
        if (mTestRunningLogic.isStoped()) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnStatus(-1);
                    mTvElectricity.setText("0A");
                    mTvPower.setText("0W");
                    mTvRpm.setText("0");
                    mTvTemperature.setText("0℃");
                    mTvVersion.setText("0");
                    mTvVoltage.setText("0V");
                }
            });
        }
    }

    public void showTipView(String msg) {
        mTipTextView.setText(msg);
        mTipTextView.setVisibility(View.VISIBLE);
    }

    public void hideTipView() {
        mTipTextView.setVisibility(View.GONE);
    }

    public void onRpmChange(String rpm) {
        this.mTvRpm.setText(rpm);
    }

    public void onTemperatureChange(String temp) {
        this.mTvTemperature.setText(temp);
    }

    public void onVoltageChange(String voltage) {
        this.mTvVoltage.setText(voltage);
    }

    public void onElectricityChange(String ele) {
        this.mTvElectricity.setText(ele);
    }

    public void onPowerChange(String power) {
        this.mTvPower.setText(power);
    }

    public void onVersionChange(String version) {
        this.mTvVersion.setText(version);
    }
}
