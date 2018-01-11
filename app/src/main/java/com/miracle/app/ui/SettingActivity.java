package com.miracle.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miracle.app.R;
import com.miracle.app.config.WasherConfig;
import com.miracle.um_base_common.base.BaseActivity;
import com.miracle.um_base_common.entity.TesterEntity;
import com.miracle.um_base_common.logic.ConfigLogic;
import com.miracle.um_base_common.util.MLog;
import com.unilife.common.entities.UMDB;

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private ConfigLogic mConfigLogic;

    protected EditText minVoltageEdit;
    protected EditText maxVoltageEdit;
    protected EditText minElectricityEdit;
    protected EditText maxElectricityEdit;
    protected EditText minLowerPowerEdit;
    protected EditText maxLowerPowerEdit;
    protected EditText minHigherPowerEdit;
    protected EditText maxHigherPowerEdit;
    protected EditText minTemperatureEdit;
    protected EditText maxTemperatureEdit;
    protected TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        minVoltageEdit = (EditText) findViewById(R.id.minVoltageEdit);
        maxVoltageEdit = (EditText) findViewById(R.id.maxVoltageEdit);
        minElectricityEdit = (EditText) findViewById(R.id.minElectricityEdit);
        maxElectricityEdit = (EditText) findViewById(R.id.maxElectricityEdit);
        minLowerPowerEdit = (EditText) findViewById(R.id.minLowerPowerEdit);
        maxLowerPowerEdit = (EditText) findViewById(R.id.maxLowerPowerEdit);
        minHigherPowerEdit = (EditText) findViewById(R.id.minHigherPowerEdit);
        maxHigherPowerEdit = (EditText) findViewById(R.id.maxHigherPowerEdit);
        minTemperatureEdit = (EditText) findViewById(R.id.minTemperatureEdit);
        maxTemperatureEdit = (EditText) findViewById(R.id.maxTemperatureEdit);
        title = (TextView) findViewById(R.id.title);
    }

    protected void initData() {
        mConfigLogic = new ConfigLogic();
        TesterEntity config = mConfigLogic.loadConfig();
        minVoltageEdit.setText(config.getMinVoltage());
        maxVoltageEdit.setText(config.getMaxVoltage());
        minElectricityEdit.setText(config.getMinElectricity());
        maxElectricityEdit.setText(config.getMaxElectricity());
        minLowerPowerEdit.setText(config.getMinLowerPower());
        maxLowerPowerEdit.setText(config.getMaxLowerPower());
        minHigherPowerEdit.setText(config.getMinHigherPower());
        maxHigherPowerEdit.setText(config.getMaxHigherPower());
        minTemperatureEdit.setText(config.getMinTemperature());
        maxTemperatureEdit.setText(config.getMaxTemperature());

        title.setText(WasherConfig.getWasherConfig().getBrandChinese());
    }

    private void initEvent() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.saveConfig).setOnClickListener(this);
        findViewById(R.id.setConfig).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back) {
            back();
        } else if (id == R.id.saveConfig) {
            saveConfig();
        } else if (id == R.id.setConfig) {
            setConfig();
        }
    }


    /**
     * 返回
     */
    protected void back() {
        finish();
    }

    /**
     * 保存配置
     */
    protected void saveConfig() {
        MLog.d(TAG, "saveConfig");
        TesterEntity config = getConfigFromUi();
        if (config == null) {
            return;
        }
        mConfigLogic.saveConfig(config);
        showMsg("保存成功");
    }

    protected void setConfig() {
        if(android.os.Build.VERSION.SDK_INT > 10) {
            // 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
            startActivity(new Intent( android.provider.Settings.ACTION_SETTINGS));
        } else {
            startActivity(new Intent( android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    private TesterEntity getConfigFromUi() {
        String minVoltage = minVoltageEdit.getText().toString().trim();
        String maxVoltage = maxVoltageEdit.getText().toString().trim();
        String minElectricity = minElectricityEdit.getText().toString().trim();
        String maxElectricity = maxElectricityEdit.getText().toString().trim();
        String minLowerPower = minLowerPowerEdit.getText().toString().trim();
        String maxLowerPower = maxLowerPowerEdit.getText().toString().trim();
        String minHigherPower = minHigherPowerEdit.getText().toString().trim();
        String maxHigherPower = maxHigherPowerEdit.getText().toString().trim();
        String minTemperature = minTemperatureEdit.getText().toString().trim();
        String maxTemperature = maxTemperatureEdit.getText().toString().trim();
        if (TextUtils.isEmpty(minVoltage) || TextUtils.isEmpty(maxVoltage) ||
                TextUtils.isEmpty(minElectricity) || TextUtils.isEmpty(maxElectricity) ||
                TextUtils.isEmpty(minLowerPower) || TextUtils.isEmpty(maxLowerPower) ||
                TextUtils.isEmpty(minHigherPower) || TextUtils.isEmpty(maxHigherPower) ||
                TextUtils.isEmpty(minTemperature) || TextUtils.isEmpty(maxTemperature)) {
            showMsg("请将信息填写完整");
            return null;
        }
        TesterEntity config = new TesterEntity();

        config.setMinVoltage(minVoltage);
        config.setMaxVoltage(maxVoltage);
        config.setMinElectricity(minElectricity);
        config.setMaxElectricity(maxElectricity);
        config.setMinLowerPower(minLowerPower);
        config.setMaxLowerPower(maxLowerPower);
        config.setMinHigherPower(minHigherPower);
        config.setMaxHigherPower(maxHigherPower);
        config.setMinTemperature(minTemperature);
        config.setMaxTemperature(maxTemperature);
        config.setName("配置文件:" + System.currentTimeMillis());
        return config;
    }

    @Override
    protected void onNewUMDbData(UMDB db) {

    }

    @Override
    protected void onNewUpdateRequest(int moduleId) {

    }

    @Override
    protected int hasModuleId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}
