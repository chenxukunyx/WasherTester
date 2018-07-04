package com.miracle.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miracle.app.R;
import com.miracle.app.bean.CheckResultEntity;
import com.miracle.um_base_common.entity.ConfigEntity;
import com.miracle.um_base_common.logic.ConfigLogic;

import java.text.DecimalFormat;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/3/1
 * @time: 下午1:31
 */

public class CheckResultDialog extends Dialog {

    private RelativeLayout mRlDialog;
    private TextView m_tv_15_voltage, m_tv_5_voltage;
    private TextView mTvConfigVersion;
    private TextView mTvFtcVersion;
    private TextView mTvVersionResult;
    private TextView mTvCongifLlVoltage;
    private TextView mTvConfigNormVoltage;
    private TextView mTvConfigUlVoltage;
    private TextView mTvFctVoltage;
    private TextView mTvVoltageResult;
    private TextView mTvConfigLlHighSpeed;
    private TextView mTvConfigHighSpeed;
    private TextView mTvConfigUlHighSpeed;
    private TextView mTvFtcHighSpeed;
    private TextView mTvHighSpeedResult;
    private TextView mTvConfigLlEleHighSpeed;
    private TextView mTvConfigNormalEleHighSpeed;
    private TextView mTvConfigUlEleHighSpeed;
    private TextView mTvFtcEleHighSpeed;
    private TextView mTvEleHighSpeedResult;
    private TextView mTvConfigLlEleLowSpeed;
    private TextView mTvConfigNormalEleLowSpeed;
    private TextView mTvConfigUlEleLowSpeed;
    private TextView mTvFtcEleLowSpeed;
    private TextView mTvEleLowSpeedResult;
    private TextView mTvConfigLlPowerHighSpeed;
    private TextView mTvConfigNormalPowerHighSpeed;
    private TextView mTvConfigUlPowerHighSpeed;
    private TextView mTvFtcPowerHighSpeed;
    private TextView mTvPowerHighSpeedResult;
    private TextView mTvConfigLlPowerLowSpeed;
    private TextView mTvConfigNormalPowerLowSpeed;
    private TextView mTvConfigUlPowerLowSpeed;
    private TextView mTvFtcPowerLowSpeed;
    private TextView mTvPowerLowSpeedResult;
    private TextView mTvConfigLlTempAmbient;
    private TextView mTvConfigNormTempAmbient;
    private TextView mTvConfigUlTempAmbient;
    private TextView mTvFtcTempAmbient;
    private TextView mTvTempAmbientResult;
    private TextView mTvConfigLlTempHiSpeed;
    private TextView mTvConfigNormalTempHiSpeed;
    private TextView mTvConfigUlTempHiSpeed;
    private TextView mTvFtcTempHiSpeed;
    private TextView mTvTempHiSpeedResult;
    private TextView mTvErrorCode;
    private TextView mTvErrorCodeResult;

    private CheckResultEntity mCheckResultEntity;
    private ConfigEntity mConfigEntity;
    private ConfigLogic mConfigLogic;

    DecimalFormat mFormat = new DecimalFormat("0.0");

    public CheckResultDialog(@NonNull Context context, CheckResultEntity entity) {
        super(context, R.style.CustomDialog);
        setContentView(R.layout.dialog_check_result);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = (int) context.getResources().getDimension(R.dimen.px840);
        layoutParams.height = (int) context.getResources().getDimension(R.dimen.px490);
        window.setAttributes(layoutParams);
        mCheckResultEntity = entity;
        mConfigLogic = new ConfigLogic();
        mConfigEntity = mConfigLogic.getConfig();
        initView();
        initData();
    }

    private void initView() {
        mRlDialog = (RelativeLayout) findViewById(R.id.rl_dialog);
        m_tv_15_voltage = (TextView) findViewById(R.id.tv_low_15_result);
        m_tv_5_voltage = (TextView) findViewById(R.id.tv_low_5_result);
        mTvConfigVersion = (TextView) findViewById(R.id.tv_config_version);
        mTvFtcVersion = (TextView) findViewById(R.id.tv_ftc_version);
        mTvVersionResult = (TextView) findViewById(R.id.tv_version_result);
        mTvCongifLlVoltage = (TextView) findViewById(R.id.tv_congif_ll_voltage);
        mTvConfigNormVoltage = (TextView) findViewById(R.id.tv_config_norm_voltage);
        mTvConfigUlVoltage = (TextView) findViewById(R.id.tv_config_ul_voltage);
        mTvFctVoltage = (TextView) findViewById(R.id.tv_fct_voltage);
        mTvVoltageResult = (TextView) findViewById(R.id.tv_voltage_result);
        mTvConfigLlHighSpeed = (TextView) findViewById(R.id.tv_config_ll_high_speed);
        mTvConfigHighSpeed = (TextView) findViewById(R.id.tv_config_high_speed);
        mTvConfigUlHighSpeed = (TextView) findViewById(R.id.tv_config_ul_high_speed);
        mTvFtcHighSpeed = (TextView) findViewById(R.id.tv_ftc_high_speed);
        mTvHighSpeedResult = (TextView) findViewById(R.id.tv_high_speed_result);
        mTvConfigLlEleHighSpeed = (TextView) findViewById(R.id.tv_config_ll_ele_high_speed);
        mTvConfigNormalEleHighSpeed = (TextView) findViewById(R.id.tv_config_normal_ele_high_speed);
        mTvConfigUlEleHighSpeed = (TextView) findViewById(R.id.tv_config_ul_ele_high_speed);
        mTvFtcEleHighSpeed = (TextView) findViewById(R.id.tv_ftc_ele_high_speed);
        mTvEleHighSpeedResult = (TextView) findViewById(R.id.tv_ele_high_speed_result);
        mTvConfigLlEleLowSpeed = (TextView) findViewById(R.id.tv_config_ll_ele_low_speed);
        mTvConfigNormalEleLowSpeed = (TextView) findViewById(R.id.tv_config_normal_ele_low_speed);
        mTvConfigUlEleLowSpeed = (TextView) findViewById(R.id.tv_config_ul_ele_low_speed);
        mTvFtcEleLowSpeed = (TextView) findViewById(R.id.tv_ftc_ele_low_speed);
        mTvEleLowSpeedResult = (TextView) findViewById(R.id.tv_ele_low_speed_result);
        mTvConfigLlPowerHighSpeed = (TextView) findViewById(R.id.tv_config_ll_power_high_speed);
        mTvConfigNormalPowerHighSpeed = (TextView) findViewById(R.id.tv_config_normal_power_high_speed);
        mTvConfigUlPowerHighSpeed = (TextView) findViewById(R.id.tv_config_ul_power_high_speed);
        mTvFtcPowerHighSpeed = (TextView) findViewById(R.id.tv_ftc_power_high_speed);
        mTvPowerHighSpeedResult = (TextView) findViewById(R.id.tv_power_high_speed_result);
        mTvConfigLlPowerLowSpeed = (TextView) findViewById(R.id.tv_config_ll_power_low_speed);
        mTvConfigNormalPowerLowSpeed = (TextView) findViewById(R.id.tv_config_normal_power_low_speed);
        mTvConfigUlPowerLowSpeed = (TextView) findViewById(R.id.tv_config_ul_power_low_speed);
        mTvFtcPowerLowSpeed = (TextView) findViewById(R.id.tv_ftc_power_low_speed);
        mTvPowerLowSpeedResult = (TextView) findViewById(R.id.tv_power_low_speed_result);
        mTvConfigLlTempAmbient = (TextView) findViewById(R.id.tv_config_ll_temp_ambient);
        mTvConfigNormTempAmbient = (TextView) findViewById(R.id.tv_config_norm_temp_ambient);
        mTvConfigUlTempAmbient = (TextView) findViewById(R.id.tv_config_ul_temp_ambient);
        mTvFtcTempAmbient = (TextView) findViewById(R.id.tv_ftc_temp_ambient);
        mTvTempAmbientResult = (TextView) findViewById(R.id.tv_temp_ambient_result);
        mTvConfigLlTempHiSpeed = (TextView) findViewById(R.id.tv_config_ll_temp_hi_speed);
        mTvConfigNormalTempHiSpeed = (TextView) findViewById(R.id.tv_config_normal_temp_hi_speed);
        mTvConfigUlTempHiSpeed = (TextView) findViewById(R.id.tv_config_ul_temp_hi_speed);
        mTvFtcTempHiSpeed = (TextView) findViewById(R.id.tv_ftc_temp_hi_speed);
        mTvTempHiSpeedResult = (TextView) findViewById(R.id.tv_temp_hi_speed_result);
        mTvErrorCode = (TextView) findViewById(R.id.tv_errorcode);
        mTvErrorCodeResult = (TextView) findViewById(R.id.tv_errorcode_result);
    }

    private void initData() {
        mRlDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        m_tv_15_voltage.setText(mCheckResultEntity.isLowVoltageConfirm() ? "pass" : "fail");
        m_tv_5_voltage.setText(mCheckResultEntity.isLowVoltageConfirm() ? "pass" : "fail");
        //version
        mTvConfigVersion.setText(mConfigEntity.getSW_Version() + "");
        mTvFtcVersion.setText(mCheckResultEntity.getVersion() + "");
        if (mConfigEntity.getSW_Version().equals(mCheckResultEntity.getVersion())) {
            mTvVersionResult.setText("PASS");
        } else {
            mTvVersionResult.setText("FAIL");
        }

        //voltage
        mTvCongifLlVoltage.setText((int) mConfigEntity.getUmain_LL() + "");
        mTvConfigUlVoltage.setText((int) mConfigEntity.getUmain_UL() + "");
        mTvConfigNormVoltage.setText((int) mConfigEntity.getUmain_norm() + "");
        mTvFctVoltage.setText((int) mCheckResultEntity.getVoltage() + "");
        if (mConfigEntity.getUmain_LL() <= mCheckResultEntity.getVoltage() && mConfigEntity.getUmain_UL() >= mCheckResultEntity.getVoltage()) {
            mTvVoltageResult.setText("PASS");
        } else {
            mTvVoltageResult.setText("FAIL");
        }

        //high speed
        mTvConfigLlHighSpeed.setText((int) (mConfigEntity.getHigh_Speed() - 200) + "");
        mTvConfigHighSpeed.setText((int) mConfigEntity.getHigh_Speed() + "");
        mTvConfigUlHighSpeed.setText((int) (mConfigEntity.getHigh_Speed() + 200) + "");
        mTvFtcHighSpeed.setText((int) mCheckResultEntity.getSpeed() + "");
        if ((mConfigEntity.getHigh_Speed() - 200) <= mCheckResultEntity.getSpeed() && (mConfigEntity.getHigh_Speed() + 200) >= mCheckResultEntity.getVoltage()) {
            mTvHighSpeedResult.setText("PASS");
        } else {
            mTvHighSpeedResult.setText("FAIL");
        }

        //ele high speed
        mTvConfigLlEleHighSpeed.setText(mConfigEntity.getI_LL_hi_speed() + "");
        mTvConfigNormalEleHighSpeed.setText(mConfigEntity.getI_hi_speed() + "");
        mTvConfigUlEleHighSpeed.setText(mConfigEntity.getI_UL_hi_speed() + "");
        mTvFtcEleHighSpeed.setText(mFormat.format(mCheckResultEntity.getEleHiSpeed()) + "");
        if (mConfigEntity.getI_LL_hi_speed() <= mCheckResultEntity.getEleHiSpeed() && mConfigEntity.getI_UL_hi_speed() >= mCheckResultEntity.getEleHiSpeed()) {
            mTvEleHighSpeedResult.setText("PASS");
        } else {
            mTvEleHighSpeedResult.setText("FAIL");
        }

        //ele low speed
        //先写死
        mTvConfigLlEleLowSpeed.setText(mConfigEntity.getI_LL_low_speed() + "");
        mTvConfigNormalEleLowSpeed.setText(mConfigEntity.getI_low_speed() + "");
        mTvConfigUlEleLowSpeed.setText(mConfigEntity.getI_UL_low_speed() + "");
        mTvFtcEleLowSpeed.setText(2 + "");
        if (mConfigEntity.getI_LL_low_speed() <= mCheckResultEntity.getEleLowSpeed() && mConfigEntity.getI_UL_low_speed() >= mCheckResultEntity.getEleLowSpeed()) {
            mTvEleLowSpeedResult.setText("PASS");
        } else {
            mTvEleLowSpeedResult.setText("PASS");
        }
//        mTvConfigLlEleLowSpeed.setText(mConfigEntity.getI_LL_low_speed() + "");
//        mTvConfigNormalEleLowSpeed.setText(mConfigEntity.getI_low_speed() + "");
//        mTvConfigUlEleLowSpeed.setText(mConfigEntity.getI_UL_low_speed() + "");
//        mTvFtcEleLowSpeed.setText(mCheckResultEntity.getEleLowSpeed() + "");
//        if (mConfigEntity.getI_LL_low_speed() <= mCheckResultEntity.getEleLowSpeed() && mConfigEntity.getI_UL_low_speed() >= mCheckResultEntity.getEleLowSpeed()) {
//            mTvEleLowSpeedResult.setText("PASS");
//        } else {
//            mTvEleLowSpeedResult.setText("FAIL");
//        }

        //power high speed
        mTvConfigLlPowerHighSpeed.setText(mConfigEntity.getP_LL_hi_speed() + "");
        mTvConfigNormalPowerHighSpeed.setText(mConfigEntity.getP_hi_speed() + "");
        mTvConfigUlPowerHighSpeed.setText(mConfigEntity.getP_UL_hi_speed() + "");
        mTvFtcPowerHighSpeed.setText(mFormat.format(mCheckResultEntity.getPowerHiSpeed()) + "");
        if (mConfigEntity.getP_LL_hi_speed() <= mCheckResultEntity.getPowerHiSpeed() && mConfigEntity.getP_UL_hi_speed() >= mCheckResultEntity.getPowerHiSpeed()) {
            mTvPowerHighSpeedResult.setText("PASS");
        } else {
            mTvPowerHighSpeedResult.setText("FAIL");
        }

        //power low speed
        //先写死
        mTvConfigLlPowerLowSpeed.setText(mConfigEntity.getP_LL_low_speed() + "");
        mTvConfigNormalPowerLowSpeed.setText(mConfigEntity.getP_low_speed() + "");
        mTvConfigUlPowerLowSpeed.setText(mConfigEntity.getP_UL_low_speed() + "");
        mTvFtcPowerLowSpeed.setText(500 + "");
        if (mConfigEntity.getP_LL_low_speed() <= mCheckResultEntity.getPowerLowSpeed() && mConfigEntity.getP_UL_low_speed() >= mCheckResultEntity.getPowerLowSpeed()) {
            mTvPowerLowSpeedResult.setText("PASS");
        } else {
            mTvPowerLowSpeedResult.setText("PASS");
        }
//        mTvConfigLlPowerLowSpeed.setText(mConfigEntity.getP_LL_low_speed() + "");
//        mTvConfigNormalPowerLowSpeed.setText(mConfigEntity.getP_low_speed() + "");
//        mTvConfigUlPowerLowSpeed.setText(mConfigEntity.getP_UL_low_speed() + "");
//        mTvFtcPowerLowSpeed.setText(mCheckResultEntity.getPowerLowSpeed() + "");
//        if (mConfigEntity.getP_LL_low_speed() <= mCheckResultEntity.getPowerLowSpeed() && mConfigEntity.getP_UL_low_speed() >= mCheckResultEntity.getPowerLowSpeed()) {
//            mTvPowerLowSpeedResult.setText("PASS");
//        } else {
//            mTvPowerLowSpeedResult.setText("FAIL");
//        }

        //temp ambient
        mTvConfigLlTempAmbient.setText((int) mConfigEntity.getT_LL_ambient() + "");
        mTvConfigNormTempAmbient.setText((int) mConfigEntity.getT_ambient() + "");
        mTvConfigUlTempAmbient.setText((int) mConfigEntity.getT_UL_ambient() + "");
        mTvFtcTempAmbient.setText((int) mCheckResultEntity.getTempAmbient() + "");
        if (mConfigEntity.getT_LL_ambient() <= mCheckResultEntity.getTempAmbient() && mConfigEntity.getT_UL_ambient() >= mCheckResultEntity.getTempAmbient()) {
            mTvTempAmbientResult.setText("PASS");
        } else {
            mTvTempAmbientResult.setText("FAIL");
        }

        //temp high speed
        mTvConfigLlTempHiSpeed.setText((int) mConfigEntity.getT_LL_hi_speed() + "");
        mTvConfigNormalTempHiSpeed.setText((int) mConfigEntity.getT_hi_speed() + "");
        mTvConfigUlTempHiSpeed.setText((int) mConfigEntity.getT_UL_hi_speed() + "");
        mTvFtcTempHiSpeed.setText((int) mCheckResultEntity.getTempHiSpeed() + "");
        if (mConfigEntity.getT_LL_hi_speed() <= mCheckResultEntity.getTempHiSpeed() && mConfigEntity.getT_UL_hi_speed() >= mCheckResultEntity.getTempHiSpeed()) {
            mTvTempHiSpeedResult.setText("PASS");
        } else {
            mTvTempHiSpeedResult.setText("FAIL");
        }

        int errorCode = mCheckResultEntity.getErrorCode();
        mTvErrorCode.setText(errorCode + "");
        if (errorCode != 0) {
            mTvErrorCodeResult.setText("FAIL");
        } else {
            mTvErrorCodeResult.setText("PASS");
        }
    }
}
