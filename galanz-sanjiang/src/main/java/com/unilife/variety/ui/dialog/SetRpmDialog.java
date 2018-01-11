package com.unilife.variety.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unilife.variety.R;
import com.unilife.variety.entity.TesterEntity;
import com.unilife.variety.listener.OnSaveListener;
import com.unilife.variety.logic.ConfigLogic;
import com.unilife.variety.utils.StringUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/14
 * @time: 下午2:37
 */

public class SetRpmDialog extends Dialog implements View.OnClickListener{

    private ConfigLogic mConfigLogic;
    private TesterEntity mTesterEntity;
    private OnSaveListener mOnSaveListener;

    private EditText mEtLow;
    private EditText mEtHigh;
    private Button mBtnCancel;
    private Button mBtnSave;

    public SetRpmDialog(Context context, OnSaveListener listener) {
        super(context, R.style.CustomDialog);
        setContentView(R.layout.dialog_set_rpm);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) context.getResources().getDimension(R.dimen.px751);
        layoutParams.height = (int) context.getResources().getDimension(R.dimen.px328);
        window.setAttributes(layoutParams);
        initView();
        addListener();
        mOnSaveListener = listener;
    }

    private void initView() {
        mEtLow = (EditText) findViewById(R.id.et_low);
        mEtHigh = (EditText) findViewById(R.id.et_high);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnSave = (Button) findViewById(R.id.btn_save);

        mConfigLogic = new ConfigLogic();
        mTesterEntity = mConfigLogic.loadConfig(getContext());
    }

    @Override
    public void show() {
        setCancelable(false);
        super.show();
    }

    private void addListener() {
        mBtnCancel.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cancel) {
            dismiss();
        } else if (v.getId() == R.id.btn_save){
            int low = StringUtils.parseInteger(mEtLow.getText().toString().trim());
            int high = StringUtils.parseInteger(mEtHigh.getText().toString().trim());
            if (low == 0) {
                low = 200;
            }
            if (high == 0) {
                high = 400;
            }
            if (low > 1200) {
                low = 1200;
            }
            if (high > 1400) {
                high = 1400;
            }
            mTesterEntity.setLowRpm(low + "");
            mTesterEntity.setHighRpm(high + "");
            mConfigLogic.saveConfig(getContext(), mTesterEntity);
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
            if (mOnSaveListener != null) {
                mOnSaveListener.onSave();
            }
            dismiss();
        }
    }
}
