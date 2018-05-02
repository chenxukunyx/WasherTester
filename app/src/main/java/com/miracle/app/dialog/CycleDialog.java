package com.miracle.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miracle.app.R;
import com.unilife.common.utils.SharedPrefUtils;
import com.unilife.common.utils.StringUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/2/24
 * @time: 上午10:44
 */

public class CycleDialog extends Dialog implements View.OnClickListener {

    private static final String CWSPEED  = "CWSPEED";
    private static final String CWTIME  = "CWTIME";
    private static final String CCWSPEED  = "CCWSPEED";
    private static final String CCWTIME  = "CCWTIME";

    private EditText mEtCwSpeed;
    private EditText mEtCwTime;
    private EditText mEtCcwSpeed;
    private EditText mEtCcwTime;
    private Button mBtnStart;
    private Button mBtnQuit;
    private OnCycleListener mOnCycleListener;

    public CycleDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        setContentView(R.layout.dialog_cycle);
        initView();
    }

    private void initView() {
        mEtCwSpeed = (EditText) findViewById(R.id.et_cw_speed);
        mEtCwTime = (EditText) findViewById(R.id.et_cw_time);
        mEtCcwSpeed = (EditText) findViewById(R.id.et_ccw_speed);
        mEtCcwTime = (EditText) findViewById(R.id.et_ccw_time);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnQuit = (Button) findViewById(R.id.btn_quit);

        mEtCwSpeed.setText(SharedPrefUtils.getData(CWSPEED, 200) + "");
        mEtCwTime.setText(SharedPrefUtils.getData(CWTIME, 20) + "");
        mEtCcwSpeed.setText(SharedPrefUtils.getData(CCWSPEED, 200) + "");
        mEtCcwTime.setText(SharedPrefUtils.getData(CCWTIME, 20) + "");

        mBtnStart.setOnClickListener(this);
        mBtnQuit.setOnClickListener(this);
    }

    public void setOnCycleListener(OnCycleListener listener) {
        mOnCycleListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            dismiss();
            start();
        } else if (v.getId() == R.id.btn_quit) {
            dismiss();
        }
    }

    private boolean checkNotEmpty(String cwspeed, String cwtime, String ccwspeed, String ccwtime) {
        if (!TextUtils.isEmpty(cwspeed) && !TextUtils.isEmpty(cwtime) && !TextUtils.isEmpty(ccwspeed) && !TextUtils.isEmpty(ccwtime)) {
            return true;
        }
        return false;
    }

    private void start() {
        String cw_speed = mEtCwSpeed.getText().toString().trim();
        String cw_time = mEtCwTime.getText().toString().trim();
        String ccw_speed = mEtCcwSpeed.getText().toString().trim();
        String ccw_time = mEtCcwTime.getText().toString().trim();
        if (checkNotEmpty(cw_speed, cw_time, ccw_speed, ccw_time)) {
            int i_cw_speed = StringUtils.parseInt(cw_speed);
            int i_cw_time = StringUtils.parseInt(cw_time);
            int i_ccw_speed = StringUtils.parseInt(ccw_speed);
            int i_ccw_time = StringUtils.parseInt(ccw_time);
            if (i_cw_speed == 0) {
                i_cw_speed = 200;
            }
            if (i_cw_speed > 16800) {
                i_cw_speed = 16800;
            }
            if (i_cw_time == 0) {
                i_cw_time = 20;
            }
            if (i_cw_time > 100) {
                i_cw_time = 100;
            }

            if (i_ccw_speed == 0) {
                i_ccw_speed = 200;
            }
            if (i_ccw_speed > 16800) {
                i_ccw_speed = 16800;
            }
            if (i_ccw_time == 0) {
                i_ccw_time = 20;
            }
            if (i_ccw_time > 100) {
                i_ccw_time = 100;
            }

            SharedPrefUtils.saveData(CWSPEED, i_cw_speed);
            SharedPrefUtils.saveData(CWTIME, i_cw_time);
            SharedPrefUtils.saveData(CCWSPEED, i_ccw_speed);
            SharedPrefUtils.saveData(CCWTIME, i_ccw_time);
            if (mOnCycleListener != null) {
                mOnCycleListener.onCycle(i_cw_speed, i_cw_time, i_ccw_speed, i_ccw_time);
            }
        }
    }

    public interface OnCycleListener {
        void onCycle(int cwspeed, int cwtime, int ccwspeed, int ccwtime);
    }
}
