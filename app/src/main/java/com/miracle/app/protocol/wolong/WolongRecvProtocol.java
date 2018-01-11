package com.miracle.app.protocol.wolong;

import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/19
 * @time: 下午6:04
 */

public class WolongRecvProtocol extends StreamProtocol {
    @Override
    public void initDefines() {
        addDefine(0xb201a104l, 32, 80, 0, 32, 16, UMDB.SoftwareVersion_A1);
        addDefine(0, 48, 8, UMDB.MotorNumber_A1);

        addDefine(0xb201a20al, 32, 128, 0, 40, 8, UMDB.IPMTemperature_A2);
        addDefine(0, 48, 16, UMDB.FaultCode_A2);
        addDefine(0, 64, 16, UMDB.DrumSpeed_A2);
        addDefine(0, 80, 16, UMDB.Load_A2);
        addDefine(0, 96, 16, UMDB.OOB_A2);

        addDefine(0xb201a300l, 32, 48, 0, 32, 8, UMDB.MotorAck );

        addDefine(0xb201a40dl, 32, 152, 0, 40, 8, UMDB.IPMTemperature_A4);
        addDefine(0, 48, 16, UMDB.FaultCode_A4);
        addDefine(0, 64, 16, UMDB.DrumSpeed_A4);
        addDefine(0, 80, 16, UMDB.Load_A4);
        addDefine(0, 96, 16, UMDB.OOB_A4);
        addDefine(0, 112, 8, UMDB.Current_A4);
        addDefine(0, 120, 16, UMDB.DCBusVoltage_A4);

        addDefine(0xb201a500l, 32, 48, 0, 32, 8, UMDB.MotorAckData0);

        addDefine(0xb201a702l, 32, 64, 0, 32, 16, UMDB.SoftwareVersion_A7);
    }
}
