package com.miracle.um_base_common.entity;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/27
 * @time: 上午11:27
 */

public class TesterEntity {

    private long id;
    private String name;
    private String minVoltage;//最小电压
    private String maxVoltage;//最大电压
    private String minElectricity;//最小电流
    private String maxElectricity;//最大电流
    private String minLowerPower;//低功率最小功率
    private String maxLowerPower;//低功率最大功率
    private String minHigherPower;//高功率最小功率
    private String maxHigherPower;//高功率最大功率
    private String minTemperature;//最小温度
    private String maxTemperature;//最大温度
    private long time;
    private String version;

    private String lowRpm;
    private String highRpm;

    public TesterEntity() {
    }

    public TesterEntity(long id, String name, String minVoltage, String maxVoltage, String minElectricity, String maxElectricity, String minLowerPower, String maxLowerPower, String minHigherPower, String maxHigherPower, String minTemperature, String maxTemperature, long time, String version) {
        this.id = id;
        this.name = name;
        this.minVoltage = minVoltage;
        this.maxVoltage = maxVoltage;
        this.minElectricity = minElectricity;
        this.maxElectricity = maxElectricity;
        this.minLowerPower = minLowerPower;
        this.maxLowerPower = maxLowerPower;
        this.minHigherPower = minHigherPower;
        this.maxHigherPower = maxHigherPower;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.time = time;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(String minVoltage) {
        this.minVoltage = minVoltage;
    }

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public String getMinElectricity() {
        return minElectricity;
    }

    public void setMinElectricity(String minElectricity) {
        this.minElectricity = minElectricity;
    }

    public String getMaxElectricity() {
        return maxElectricity;
    }

    public void setMaxElectricity(String maxElectricity) {
        this.maxElectricity = maxElectricity;
    }

    public String getMinLowerPower() {
        return minLowerPower;
    }

    public void setMinLowerPower(String minLowerPower) {
        this.minLowerPower = minLowerPower;
    }

    public String getMaxLowerPower() {
        return maxLowerPower;
    }

    public void setMaxLowerPower(String maxLowerPower) {
        this.maxLowerPower = maxLowerPower;
    }

    public String getMinHigherPower() {
        return minHigherPower;
    }

    public void setMinHigherPower(String minHigherPower) {
        this.minHigherPower = minHigherPower;
    }

    public String getMaxHigherPower() {
        return maxHigherPower;
    }

    public void setMaxHigherPower(String maxHigherPower) {
        this.maxHigherPower = maxHigherPower;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLowRpm() {
        return lowRpm;
    }

    public void setLowRpm(String lowRpm) {
        this.lowRpm = lowRpm;
    }

    public String getHighRpm() {
        return highRpm;
    }

    public void setHighRpm(String highRpm) {
        this.highRpm = highRpm;
    }
}
