package com.miracle.um_base_common.entity;

/**
 * Created by 万启林 on 2015/8/3.
 */
public class CheckResponse {
    private int voltage;
    private float electricity;
    private int power;
    private int temperature;
    private int rpm;

    private boolean voltageOk;
    private boolean electricityOk;
    private boolean powerOk;
    private boolean temperatureOk;
    private boolean rpmOk;
    private boolean rpmZero = true;
    private boolean electricityZero = true;

    public CheckResponse() {
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public float getElectricity() {
        return electricity;
    }

    public void setElectricity(float electricity) {
        this.electricity = electricity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isVoltageOk() {
        return voltageOk;
    }

    public void setVoltageOk(boolean voltageOk) {
        this.voltageOk = voltageOk;
    }

    public boolean isElectricityOk() {
        return electricityOk;
    }

    public void setElectricityOk(boolean electricityOk) {
        this.electricityOk = electricityOk;
    }

    public boolean isPowerOk() {
        return powerOk;
    }

    public void setPowerOk(boolean powerOk) {
        this.powerOk = powerOk;
    }

    public boolean isTemperatureOk() {
        return temperatureOk;
    }

    public void setTemperatureOk(boolean temperatureOk) {
        this.temperatureOk = temperatureOk;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public boolean isRpmOk() {
        return rpmOk;
    }

    public void setRpmOk(boolean rpmOk) {
        this.rpmOk = rpmOk;
    }

    public boolean isRpmZero() {
        return rpmZero;
    }

    public void setRpmZero(boolean rpmZero) {
        this.rpmZero = rpmZero;
    }

    public boolean isElectricityZero() {
        return electricityZero;
    }

    public void setElectricityZero(boolean electricityZero) {
        this.electricityZero = electricityZero;
    }
}
