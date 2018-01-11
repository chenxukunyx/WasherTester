package com.miracle.um_base_common.listener;

import com.unilife.common.entities.UMDB;

/**
 * Created by 万启林 on 2015/7/31.
 */
public interface IProtocolListener {
    public void onSuccess();//成功
    public void onSendCmd(UMDB umdb, int circleDirect);
    public void onError(boolean isHigh, String msg);
    public void onTemperatureChange(String temperature);
    public void onRpmChange(String rpm);
    public void onVoltageChange(String voltage);
    public void onPowerChange(String power);
    public void onElectricityChange(String electricity);
    public void onVersionChange(String version);
    public String getUMDBValue(String key);
}
