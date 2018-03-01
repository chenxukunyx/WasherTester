package com.miracle.um_base_common.entity;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/2/26
 * @time: 上午11:28
 */

public class ConfigEntity extends BaseEntity {
    private float Tambient;//环境温度
    private float SW_Version;
    private float Umain_norm;
    private float Umain_UL;
    private float Umain_LL;
    private float High_Speed;
    private float Low_Speed;
    private float I_hi_speed;
    private float I_UL_hi_speed;
    private float I_LL_hi_speed;
    private float I_low_speed;
    private float I_UL_low_speed;
    private float I_LL_low_speed;
    private   float P_hi_speed ;
    private float P_UL_hi_speed;
    private float P_LL_hi_speed;
    private float P_low_speed;
    private float P_UL_low_speed;
    private float P_LL_low_speed;
    private float T_ambient;
    private float T_UL_ambient;
    private float T_LL_ambient;
    private float T_hi_speed;
    private float T_UL_hi_speed;
    private float T_LL_hi_speed;

    public float getTambient() {
        return Tambient;
    }

    public void setTambient(float tambient) {
        Tambient = tambient;
    }

    public float getSW_Version() {
        return SW_Version;
    }

    public void setSW_Version(float SW_Version) {
        this.SW_Version = SW_Version;
    }

    public float getUmain_norm() {
        return Umain_norm;
    }

    public void setUmain_norm(float umain_norm) {
        Umain_norm = umain_norm;
    }

    public float getUmain_UL() {
        return Umain_UL;
    }

    public void setUmain_UL(float umain_UL) {
        Umain_UL = umain_UL;
    }

    public float getUmain_LL() {
        return Umain_LL;
    }

    public void setUmain_LL(float umain_LL) {
        Umain_LL = umain_LL;
    }

    public float getHigh_Speed() {
        return High_Speed;
    }

    public void setHigh_Speed(float high_Speed) {
        High_Speed = high_Speed;
    }

    public float getLow_Speed() {
        return Low_Speed;
    }

    public void setLow_Speed(float low_Speed) {
        Low_Speed = low_Speed;
    }

    public float getI_hi_speed() {
        return I_hi_speed;
    }

    public void setI_hi_speed(float i_hi_speed) {
        I_hi_speed = i_hi_speed;
    }

    public float getI_UL_hi_speed() {
        return I_UL_hi_speed;
    }

    public void setI_UL_hi_speed(float i_UL_hi_speed) {
        I_UL_hi_speed = i_UL_hi_speed;
    }

    public float getI_LL_hi_speed() {
        return I_LL_hi_speed;
    }

    public void setI_LL_hi_speed(float i_LL_hi_speed) {
        I_LL_hi_speed = i_LL_hi_speed;
    }

    public float getI_low_speed() {
        return I_low_speed;
    }

    public void setI_low_speed(float i_low_speed) {
        I_low_speed = i_low_speed;
    }

    public float getI_UL_low_speed() {
        return I_UL_low_speed;
    }

    public void setI_UL_low_speed(float i_UL_low_speed) {
        I_UL_low_speed = i_UL_low_speed;
    }

    public float getI_LL_low_speed() {
        return I_LL_low_speed;
    }

    public void setI_LL_low_speed(float i_LL_low_speed) {
        I_LL_low_speed = i_LL_low_speed;
    }

    public float getP_hi_speed() {
        return P_hi_speed;
    }

    public void setP_hi_speed(float p_hi_speed) {
        P_hi_speed = p_hi_speed;
    }

    public float getP_UL_hi_speed() {
        return P_UL_hi_speed;
    }

    public void setP_UL_hi_speed(float p_UL_hi_speed) {
        P_UL_hi_speed = p_UL_hi_speed;
    }

    public float getP_LL_hi_speed() {
        return P_LL_hi_speed;
    }

    public void setP_LL_hi_speed(float p_LL_hi_speed) {
        P_LL_hi_speed = p_LL_hi_speed;
    }

    public float getP_low_speed() {
        return P_low_speed;
    }

    public void setP_low_speed(float p_low_speed) {
        P_low_speed = p_low_speed;
    }

    public float getP_UL_low_speed() {
        return P_UL_low_speed;
    }

    public void setP_UL_low_speed(float p_UL_low_speed) {
        P_UL_low_speed = p_UL_low_speed;
    }

    public float getP_LL_low_speed() {
        return P_LL_low_speed;
    }

    public void setP_LL_low_speed(float p_LL_low_speed) {
        P_LL_low_speed = p_LL_low_speed;
    }

    public float getT_ambient() {
        return T_ambient;
    }

    public void setT_ambient(float t_ambient) {
        T_ambient = t_ambient;
    }

    public float getT_UL_ambient() {
        return T_UL_ambient;
    }

    public void setT_UL_ambient(float t_UL_ambient) {
        T_UL_ambient = t_UL_ambient;
    }

    public float getT_LL_ambient() {
        return T_LL_ambient;
    }

    public void setT_LL_ambient(float t_LL_ambient) {
        T_LL_ambient = t_LL_ambient;
    }

    public float getT_hi_speed() {
        return T_hi_speed;
    }

    public void setT_hi_speed(float t_hi_speed) {
        T_hi_speed = t_hi_speed;
    }

    public float getT_UL_hi_speed() {
        return T_UL_hi_speed;
    }

    public void setT_UL_hi_speed(float t_UL_hi_speed) {
        T_UL_hi_speed = t_UL_hi_speed;
    }

    public float getT_LL_hi_speed() {
        return T_LL_hi_speed;
    }

    public void setT_LL_hi_speed(float t_LL_hi_speed) {
        T_LL_hi_speed = t_LL_hi_speed;
    }

    @Override
    public String toString() {
        return "ConfigEntity{" +
                "Tambient=" + Tambient +
                ", SW_Version=" + SW_Version +
                ", Umain_norm=" + Umain_norm +
                ", Umain_UL=" + Umain_UL +
                ", Umain_LL=" + Umain_LL +
                ", High_Speed=" + High_Speed +
                ", Low_Speed=" + Low_Speed +
                ", I_hi_speed=" + I_hi_speed +
                ", I_UL_hi_speed=" + I_UL_hi_speed +
                ", I_LL_hi_speed=" + I_LL_hi_speed +
                ", I_low_speed=" + I_low_speed +
                ", I_UL_low_speed=" + I_UL_low_speed +
                ", I_LL_low_speed=" + I_LL_low_speed +
                ", P_hi_speed=" + P_hi_speed +
                ", P_UL_hi_speed=" + P_UL_hi_speed +
                ", P_LL_hi_speed=" + P_LL_hi_speed +
                ", P_low_speed=" + P_low_speed +
                ", P_UL_low_speed=" + P_UL_low_speed +
                ", P_LL_low_speed=" + P_LL_low_speed +
                ", T_ambient=" + T_ambient +
                ", T_UL_ambient=" + T_UL_ambient +
                ", T_LL_ambient=" + T_LL_ambient +
                ", T_hi_speed=" + T_hi_speed +
                ", T_UL_hi_speed=" + T_UL_hi_speed +
                ", T_LL_hi_speed=" + T_LL_hi_speed +
                '}';
    }
}
