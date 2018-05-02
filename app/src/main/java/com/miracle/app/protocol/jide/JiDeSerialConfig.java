package com.miracle.app.protocol.jide;

import com.unilife.common.serials.SerialConfig;

/**
 * Created by 万启林 on 2015/7/30.
 */
public class JiDeSerialConfig extends SerialConfig {
    public JiDeSerialConfig() {

        m_bateRate = 1200;
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
