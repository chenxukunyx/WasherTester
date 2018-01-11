package com.unilife.variety.logic;

import android.content.Context;

import com.unilife.variety.entity.TesterEntity;
import com.unilife.variety.logic.base.BaseLogic;
import com.unilife.variety.model.ConfigInfoModel;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/28
 * @time: 下午5:41
 */

public class ConfigLogic extends BaseLogic {

    private ConfigInfoModel configInfoModel;
    public ConfigLogic() {
        super();
        configInfoModel = new ConfigInfoModel();
    }

    public void saveConfig(Context context, TesterEntity entity) {
        configInfoModel.saveConfig(context, entity);
    }

    public TesterEntity loadConfig(Context context) {
        TesterEntity entity = configInfoModel.loadConfig(context);
        if (entity == null) {
            // 默认
            entity = new TesterEntity();
            entity.setMaxElectricity("7000");
            entity.setMinElectricity("0");
            entity.setMaxHigherPower("100");
            entity.setMinHigherPower("1");
            entity.setMaxLowerPower("132");
            entity.setMinLowerPower("1");
            entity.setMaxTemperature("100");
            entity.setMinTemperature("0");
            entity.setMaxVoltage("245");
            entity.setMinVoltage("200");
            entity.setVersion("2");
//            entity.setDriveRatio("5");
            entity.setLowRpm("1200");
            entity.setHighRpm("1400");
        }
        return entity;
    }
}
