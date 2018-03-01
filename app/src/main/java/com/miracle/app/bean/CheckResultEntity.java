package com.miracle.app.bean;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/3/1
 * @time: 下午2:47
 */

public class CheckResultEntity {
    private float version;
    private float voltage;
    private float speed;
    private float eleHiSpeed;
    private float eleLowSpeed;
    private float powerHiSpeed;
    private float powerLowSpeed;
    private float tempAmbient;
    private float tempHiSpeed;

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getEleHiSpeed() {
        return eleHiSpeed;
    }

    public void setEleHiSpeed(float eleHiSpeed) {
        this.eleHiSpeed = eleHiSpeed;
    }

    public float getEleLowSpeed() {
        return eleLowSpeed;
    }

    public void setEleLowSpeed(float eleLowSpeed) {
        this.eleLowSpeed = eleLowSpeed;
    }

    public float getPowerHiSpeed() {
        return powerHiSpeed;
    }

    public void setPowerHiSpeed(float powerHiSpeed) {
        this.powerHiSpeed = powerHiSpeed;
    }

    public float getPowerLowSpeed() {
        return powerLowSpeed;
    }

    public void setPowerLowSpeed(float powerLowSpeed) {
        this.powerLowSpeed = powerLowSpeed;
    }

    public float getTempAmbient() {
        return tempAmbient;
    }

    public void setTempAmbient(float tempAmbient) {
        this.tempAmbient = tempAmbient;
    }

    public float getTempHiSpeed() {
        return tempHiSpeed;
    }

    public void setTempHiSpeed(float tempHiSpeed) {
        this.tempHiSpeed = tempHiSpeed;
    }

    @Override
    public String toString() {
        return "CheckResultEntity{" +
                "version=" + version +
                ", voltage=" + voltage +
                ", speed=" + speed +
                ", eleHiSpeed=" + eleHiSpeed +
                ", eleLowSpeed=" + eleLowSpeed +
                ", powerHiSpeed=" + powerHiSpeed +
                ", powerLowSpeed=" + powerLowSpeed +
                ", tempAmbient=" + tempAmbient +
                ", tempHiSpeed=" + tempHiSpeed +
                '}';
    }
}
