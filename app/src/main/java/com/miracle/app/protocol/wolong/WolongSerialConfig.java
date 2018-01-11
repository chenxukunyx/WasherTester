package com.miracle.app.protocol.wolong;

import com.unilife.common.serials.SerialConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:37
 */

public class WolongSerialConfig extends SerialConfig {

    public WolongSerialConfig() {
        m_bateRate = 4800;
        m_isParity = 0;// 'e'; 奇偶校验
        m_stopBit = 1;//停止位
        m_bitWidth = 8;
        m_circleTime = 300;
        m_rwGapTime = 100;
        m_hostOrSlave = 1;
        m_multiframes = 1;
        m_rdsz = 21;//读
        m_wrsz = 20;//写
        m_simulate = false;
    }
}
