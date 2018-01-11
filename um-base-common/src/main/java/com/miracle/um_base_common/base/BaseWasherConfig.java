package com.miracle.um_base_common.base;

import com.unilife.common.protocol.StreamProtocol;
import com.unilife.common.serials.SerialConfig;
import com.unilife.common.serials.SerialProtocol;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:05
 */

public class BaseWasherConfig {

    /**
     * MainActivity资源文件
     */
    int layoutId;

    /**
     * 电机品牌
     */
    String motorBrand;

    /**
     * 电机品牌 中文
     */
    String brandChinese;

    /**
     * 串口配置文件 波特率，数据位....
     */
    SerialConfig mSerialConfig;

    /**
     * 串口发送接受协议
     */
    Class<? extends StreamProtocol> m_clsSendProtocol;
    Class<? extends StreamProtocol> m_clsRecvProtocol;

    /**
     * 数据解析协议
     */
    Class<? extends SerialProtocol> m_serialProtocol;

    public String getMotorBrand() {
        return motorBrand;
    }

    public void setMotorBrand(String motorBrand) {
        this.motorBrand = motorBrand;
    }

    public Class<? extends StreamProtocol> getM_clsSendProtocol() {
        return m_clsSendProtocol;
    }

    public void setM_clsSendProtocol(Class<? extends StreamProtocol> m_clsSendProtocol) {
        this.m_clsSendProtocol = m_clsSendProtocol;
    }

    public Class<? extends StreamProtocol> getM_clsRecvProtocol() {
        return m_clsRecvProtocol;
    }

    public void setM_clsRecvProtocol(Class<? extends StreamProtocol> m_clsRecvProtocol) {
        this.m_clsRecvProtocol = m_clsRecvProtocol;
    }

    public SerialConfig getSerialConfig() {
        return mSerialConfig;
    }

    public void setSerialConfig(SerialConfig serialConfig) {
        mSerialConfig = serialConfig;
    }

    public Class<? extends SerialProtocol> getM_serialProtocol() {
        return m_serialProtocol;
    }

    public void setM_serialProtocol(Class<? extends SerialProtocol> m_serialProtocol) {
        this.m_serialProtocol = m_serialProtocol;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getBrandChinese() {
        return brandChinese;
    }

    public void setBrandChinese(String brandChinese) {
        this.brandChinese = brandChinese;
    }

}
