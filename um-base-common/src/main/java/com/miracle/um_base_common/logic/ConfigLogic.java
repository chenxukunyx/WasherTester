package com.miracle.um_base_common.logic;

import android.content.Context;

import com.miracle.um_base_common.base.BaseLogic;
import com.miracle.um_base_common.entity.TesterEntity;
import com.miracle.um_base_common.model.ConfigInfoModel;


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

    public void saveConfig(TesterEntity entity) {
        configInfoModel.saveConfig(entity);
    }

    public TesterEntity loadConfig() {
        TesterEntity entity = configInfoModel.loadConfig();
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
