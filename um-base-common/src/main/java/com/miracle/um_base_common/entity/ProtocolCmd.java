package com.miracle.um_base_common.entity;

import com.unilife.common.entities.UMDB;

/**
 * Created by 万启林 on 2015/7/31.
 */
public class ProtocolCmd {
    public static final int NONE = 0;
    public static final int CW = 1;
    public static final int CCW = 2;

    private String name;
    private UMDB umdb;
    private int time;//运行时间  ms
    private boolean checkRpm;//是否检测rpm
    private int minRpm;
    private int maxRpm;
    private boolean checkVoltage;//是否检测电压
    private int minVoltage = 200;
    private int maxVoltage = 245;
    private boolean checkPower;
    private int minPower = 0;
    private int maxPower = 5;
    private boolean checkElectricity;
    private int minElectricity;
    private int maxElectricity;
    private int circleDirect;
    private boolean showMaxPower;//显示最大功率

    private boolean rmpNeedZero;

    public ProtocolCmd() {
    }

    public ProtocolCmd(String name, UMDB umdb, int time, boolean checkRpm, int minRpm, int maxRpm, boolean checkVoltage, int minVoltage, int maxVoltage, boolean checkPower, int minPower, int maxPower, boolean checkElectricity, int minElectricity, int maxElectricity, int circleDirect) {
        this.name = name;
        this.umdb = umdb;
        this.time = time;
        this.checkRpm = checkRpm;
        this.minRpm = minRpm;
        this.maxRpm = maxRpm;
        this.checkVoltage = checkVoltage;
        this.minVoltage = minVoltage;
        this.maxVoltage = maxVoltage;
        this.checkPower = checkPower;
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.checkElectricity = checkElectricity;
        this.minElectricity = minElectricity;
        this.maxElectricity = maxElectricity;
        this.circleDirect = circleDirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UMDB getUmdb() {
        return umdb;
    }

    public void setUmdb(UMDB umdb) {
        this.umdb = umdb;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isCheckRpm() {
        return checkRpm;
    }

    public void setCheckRpm(boolean checkRpm) {
        this.checkRpm = checkRpm;
    }

    public int getMinRpm() {
        return minRpm;
    }

    public void setMinRpm(int minRpm) {
        this.minRpm = minRpm;
    }

    public int getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(int maxRpm) {
        this.maxRpm = maxRpm;
    }

    public boolean isCheckVoltage() {
        return checkVoltage;
    }

    public void setCheckVoltage(boolean checkVoltage) {
        this.checkVoltage = checkVoltage;
    }

    public int getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(int minVoltage) {
        this.minVoltage = minVoltage;
    }

    public int getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(int maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public boolean isCheckPower() {
        return checkPower;
    }

    public void setCheckPower(boolean checkPower) {
        this.checkPower = checkPower;
    }

    public int getMinPower() {
        return minPower;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getCircleDirect() {
        return circleDirect;
    }

    public void setCircleDirect(int circleDirect) {
        this.circleDirect = circleDirect;
    }

    public boolean isCheckElectricity() {
        return checkElectricity;
    }

    public void setCheckElectricity(boolean checkElectricity) {
        this.checkElectricity = checkElectricity;
    }

    public int getMinElectricity() {
        return minElectricity;
    }

    public void setMinElectricity(int minElectricity) {
        this.minElectricity = minElectricity;
    }

    public int getMaxElectricity() {
        return maxElectricity;
    }

    public void setMaxElectricity(int maxElectricity) {
        this.maxElectricity = maxElectricity;
    }

    public boolean isRmpNeedZero() {
        return rmpNeedZero;
    }

    public void setRmpNeedZero(boolean rmpNeedZero) {
        this.rmpNeedZero = rmpNeedZero;
    }

    public boolean isShowMaxPower() {
        return showMaxPower;
    }

    public void setShowMaxPower(boolean showMaxPower) {
        this.showMaxPower = showMaxPower;
    }
}
