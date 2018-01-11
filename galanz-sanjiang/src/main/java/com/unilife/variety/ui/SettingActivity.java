package com.unilife.variety.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unilife.common.entities.UMDB;
import com.unilife.variety.R;
import com.unilife.variety.config.AppConfig;
import com.unilife.variety.entity.TesterEntity;
import com.unilife.variety.logic.ConfigLogic;
import com.unilife.variety.ui.base.BaseActivity;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 下午2:42
 */

public class SettingActivity extends BaseActivity {

    private TextView title;
    private Button back;
    private Button setConfig;
    private ImageView saveConfig;
    private ImageView loadConfig;
    private EditText minVoltageEdit;
    private EditText minElectricityEdit;
    private EditText minLowerPowerEdit;
    private EditText minHigherPowerEdit;
    private EditText minTemperatureEdit;
    private EditText maxVoltageEdit;
    private EditText maxElectricityEdit;
    private EditText maxLowerPowerEdit;
    private EditText maxHigherPowerEdit;
    private EditText maxTemperatureEdit;
    private EditText versionEdit;

    private ConfigLogic mConfigLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        init();
        initData();
        addListener();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.back);
        setConfig = (Button) findViewById(R.id.setConfig);
        saveConfig = (ImageView) findViewById(R.id.saveConfig);
        loadConfig = (ImageView) findViewById(R.id.loadConfig);
        minVoltageEdit = (EditText) findViewById(R.id.minVoltageEdit);
        minElectricityEdit = (EditText) findViewById(R.id.minElectricityEdit);
        minLowerPowerEdit = (EditText) findViewById(R.id.minLowerPowerEdit);
        minHigherPowerEdit = (EditText) findViewById(R.id.minHigherPowerEdit);
        minTemperatureEdit = (EditText) findViewById(R.id.minTemperatureEdit);
        maxVoltageEdit = (EditText) findViewById(R.id.maxVoltageEdit);
        maxElectricityEdit = (EditText) findViewById(R.id.maxElectricityEdit);
        maxLowerPowerEdit = (EditText) findViewById(R.id.maxLowerPowerEdit);
        maxHigherPowerEdit = (EditText) findViewById(R.id.maxHigherPowerEdit);
        maxTemperatureEdit = (EditText) findViewById(R.id.maxTemperatureEdit);
        versionEdit = (EditText) findViewById(R.id.versionEdit);
    }

    private void init() {
        if (mConfigLogic == null) {
            mConfigLogic = new ConfigLogic();
        }
    }

    private void initData() {
        TesterEntity config = mConfigLogic.loadConfig(this);
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
        versionEdit.setText(config.getVersion());

        if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_SANYANG)) {
            title.setText("三洋测试工具");
        } else if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_XIAOTIANE)) {
            title.setText("小天鹅测试工具");
        } else if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_TCL)) {
            title.setText("TCL测试工具");
        } else if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_EV6106)) {
            title.setText("EV6106测试工具");
        } else if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_HISENSE)) {
            title.setText("海信测试工具");
        } else if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_GELANSHI)) {
            title.setText("格兰仕测试工具");
        }
    }

    private void addListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TesterEntity config = getConfigFromUi();
                if (config == null) {
                    return;
                }
                mConfigLogic.saveConfig(SettingActivity.this, config);
//                Intent it = new Intent("refresh_config");
//                sendBroadcast(it);
                showMsg("保存成功");
            }
        });

        setConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    // 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                    startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                } else {
                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                }
            }
        });
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
        String version = versionEdit.getText().toString().trim();
        if (TextUtils.isEmpty(minVoltage) || TextUtils.isEmpty(maxVoltage) ||
                TextUtils.isEmpty(minElectricity) || TextUtils.isEmpty(maxElectricity) ||
                TextUtils.isEmpty(minLowerPower) || TextUtils.isEmpty(maxLowerPower) ||
                TextUtils.isEmpty(minHigherPower) || TextUtils.isEmpty(maxHigherPower) ||
                TextUtils.isEmpty(minTemperature) || TextUtils.isEmpty(maxTemperature) ||
                TextUtils.isEmpty(version)) {
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
        config.setVersion(version);
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
}
