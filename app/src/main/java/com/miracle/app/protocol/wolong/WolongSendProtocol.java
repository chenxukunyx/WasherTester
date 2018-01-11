package com.miracle.app.protocol.wolong;

import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/19
 * @time: 下午5:51
 */

public class WolongSendProtocol extends StreamProtocol {

    @Override
    public void initDefines() {
        addDefine(0xb201a103l, 32, 72, 0, 32, 8, UMDB.MotorCmdData1);

        addDefine(0xb201a200l, 32, 48, 0, 32, 0, UMDB.MotorCmdData2);

        addDefine(0xb201a300l, 32, 48, 0, 32, 0, UMDB.MotorCmdData3);

        addDefine(0xb201a400l, 32, 48, 0, 32, 0, UMDB.MotorCmdData4);

        addDefine(0xb201a504l, 32, 80, 0, 32, 16, UMDB.MotorCmdData5);
        addDefine(0, 48, 16, UMDB.MotorCmdData6);

        addDefine(0xb201a700l, 32, 48, 0, 32, 0, UMDB.MotorCmdData7);
    }
}
