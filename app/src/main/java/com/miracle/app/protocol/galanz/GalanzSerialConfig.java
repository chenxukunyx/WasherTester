package com.miracle.app.protocol.galanz;

import com.unilife.common.serials.SerialConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/28
 * @time: 下午6:10
 */

public class GalanzSerialConfig extends SerialConfig {

    public GalanzSerialConfig() {
        m_bateRate = 4800;
        m_isParity = 2;// 'e'; 奇偶校验
        m_stopBit = 1;//停止位
        m_bitWidth = 9;
        m_circleTime = 300;
        m_rwGapTime = 100;
        m_hostOrSlave = 1;
        m_multiframes = 1;
        m_rdsz = 21;//读
        m_wrsz = 20;//写
        m_simulate = false;
    }
}
