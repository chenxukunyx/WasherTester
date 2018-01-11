package com.miracle.um_base_common.listener;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/26
 * @time: 下午6:22
 */

public interface OnNewUmdbListener {

    public void onError(String msg);
    public void onTemperatureChange(String temperature);
    public void onRpmChange(String rpm);
    public void onVoltageChange(String voltage);
    public void onPowerChange(String power);
    public void onElectricityChange(String electricity);
    public void onVersionChange(String version);
}
