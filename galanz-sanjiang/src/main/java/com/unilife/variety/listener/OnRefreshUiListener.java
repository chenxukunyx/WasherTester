package com.unilife.variety.listener;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/13
 * @time: 下午12:55
 */

public interface OnRefreshUiListener {

    void showTipView(String msg);

    void hideTipView();

    void onRpmChange(String rpm);

    void onTemperatureChange(String temp);

    void onVoltageChange(String voltage);

    void onElectricityChange(String ele);

    void onPowerChange(String power);

    void onVersionChange(String version);
}
