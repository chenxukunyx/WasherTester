package com.miracle.app.model;

import com.miracle.um_base_common.model.BaseModel;
import com.unilife.common.entities.UMDB;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/22
 * @time: 下午1:56
 */

public class WolongModel extends BaseModel {

    public UMDB getPingCmd(){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData1, 0x39);
        return umdb;
    }

    public UMDB getRequestCmd(){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData2, 0);
        return umdb;
    }

    public UMDB getLoadEnableCmd(){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData3, 0);
        return umdb;
    }

    public UMDB getFTCTestCmd(){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData4, 0);
        return umdb;
    }

    public UMDB getSpeedSetCmd(int rpm){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData5, 40);
        umdb.addValue(UMDB.MotorCmdData6, rpm);
        return umdb;
    }

    public UMDB getStopCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData5, 0);
        umdb.addValue(UMDB.MotorCmdData6, 0);
        return umdb;
    }

    public UMDB getSpeedSetCmd(int accelerate, int rpm) {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData5, accelerate);
        umdb.addValue(UMDB.MotorCmdData6, rpm);
        return umdb;
    }

    public UMDB getFTCPingCmd(){
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData7, 0);
        return umdb;
    }
}
