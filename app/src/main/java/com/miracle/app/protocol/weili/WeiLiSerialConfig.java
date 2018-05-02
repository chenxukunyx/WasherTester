package com.miracle.app.protocol.weili;

import com.unilife.common.serials.SerialConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/4/24
 * @time: 下午2:59
 */

public class WeiLiSerialConfig extends SerialConfig{

    public WeiLiSerialConfig() {
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
