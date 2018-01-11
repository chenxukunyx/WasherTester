package com.miracle.app.model;

import com.miracle.um_base_common.checkcrc.BaseCheck;
import com.miracle.um_base_common.model.BaseModel;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 上午9:43
 */

public class GalanzModel extends BaseModel {

    public static final String CMD_STX = "02";//"02";
    public static final String CMD_ETX = "03";//"03";
    public static final String CMD_SRC_ADDR = "70";//"70";
    public static final String CMD_DEST_ADDR = "6D";//"6D";

    public static final String DATA_CMD_S = "73";//"73";
    public static final String DATA_CMD_I = "69";//"69";
    public static final String DATA_CMD_C = "63";//"63";
    public static final String DATA_CMD_P = "70";//"70";
    public static final String DATA_CMD_M = "6D";//"6D";
    public static final String DATA_CMD_T = "74";

    public GalanzModel() {
        super();
    }

    /**
     * 获取待机命令
     * @return
     */
    public UMDB getPauseCmd() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData0, 'l');
        fridgeDB.addValue(UMDB.MotorCmdData1, '0');
        fridgeDB.addValue(UMDB.MotorCmdData2, '0');
        fridgeDB.addValue(UMDB.MotorCmdData3, '0');
        fridgeDB.addValue(UMDB.MotorCmdData4, '0');
        fridgeDB.addValue(UMDB.MotorCmdData5, '1');
        fridgeDB.addValue(UMDB.MotorCmdData6, '4');
        fridgeDB.addValue(UMDB.MotorCmdData7, '0');
        fridgeDB.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取查询版本指令
     * @return
     */
    public int[] getVersionCmd() {
        int[] data = new int[9];
        data[0] = 0x02;
        data[1] = 0x70;
        data[2] = 0x6d;
        data[3] = 0x70;
        data[4] = 0x30;
        data[5] = 0x34;
        data[6] = 0x03;
        int crc = BaseCheck.getCRC16(data, 0, 7);
        data[7] = crc & 0xff;
        data[8] = (crc & 0xff00) >> 8;
        return data;
    }

    /**
     * 查询温度指令
     * @return
     */
    public UMDB getSetTemperatureCmd() {
        UMDB umdb = new UMDB();
        umdb.setValue(UMDB.MotorCmdData28, StringUtils.hexStringToLong(CMD_ETX));
        return umdb;
    }

    /**
     * 获取设定传动比指令
     * @return
     */
    public UMDB getRatioCmd() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData15, '0');
        fridgeDB.addValue(UMDB.MotorCmdData16, '0');
        fridgeDB.addValue(UMDB.MotorCmdData17, '7');
        fridgeDB.addValue(UMDB.MotorCmdData18, '0');
        fridgeDB.addValue(UMDB.MotorCmdData19, '8');
        fridgeDB.addValue(UMDB.MotorCmdData20, 'c');
        fridgeDB.addValue(UMDB.MotorCmdData21, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取查询状态指令 c
     * @return
     */
    public UMDB getFetchStatusC() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData12, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取查询状态指令 i
     * @return
     */
    public UMDB getFetchStatusI() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData10, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取查询状态指令 m
     * @return
     */
    public UMDB getFetchStatusM() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData23, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }


    /**
     * 获取洗涤正转命令
     * @return
     */
    public UMDB getWashCWCmd() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData0, 'r');
        fridgeDB.addValue(UMDB.MotorCmdData1, '3');
        fridgeDB.addValue(UMDB.MotorCmdData2, 'e');
        fridgeDB.addValue(UMDB.MotorCmdData3, '8');
        fridgeDB.addValue(UMDB.MotorCmdData4, '0');
        fridgeDB.addValue(UMDB.MotorCmdData5, '2');
        fridgeDB.addValue(UMDB.MotorCmdData6, '8');
        fridgeDB.addValue(UMDB.MotorCmdData7, '0');
        fridgeDB.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取洗涤反转命令
     * @return
     */
    public UMDB getWashCCWCmd() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData0, 'l');
        fridgeDB.addValue(UMDB.MotorCmdData1, '0');
        fridgeDB.addValue(UMDB.MotorCmdData2, '3');
        fridgeDB.addValue(UMDB.MotorCmdData3, 'c');
        fridgeDB.addValue(UMDB.MotorCmdData4, '0');
        fridgeDB.addValue(UMDB.MotorCmdData5, '2');
        fridgeDB.addValue(UMDB.MotorCmdData6, '8');
        fridgeDB.addValue(UMDB.MotorCmdData7, '0');
        fridgeDB.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    /**
     * 获取脱水命令
     * @return
     */
    public UMDB getSpinCmd() {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData0, 'l');
        fridgeDB.addValue(UMDB.MotorCmdData1, '1');
        fridgeDB.addValue(UMDB.MotorCmdData2, '9');
        fridgeDB.addValue(UMDB.MotorCmdData3, '0');
        fridgeDB.addValue(UMDB.MotorCmdData4, '0');
        fridgeDB.addValue(UMDB.MotorCmdData5, '2');
        fridgeDB.addValue(UMDB.MotorCmdData6, '8');
        fridgeDB.addValue(UMDB.MotorCmdData7, '0');
        fridgeDB.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    public UMDB getSpinCmd(char direction, char speedHundreds, char speedTens, char speedUnits) {
        UMDB fridgeDB = new UMDB();
        fridgeDB.addValue(UMDB.MotorCmdData0, direction);
        fridgeDB.addValue(UMDB.MotorCmdData1, speedHundreds);
        fridgeDB.addValue(UMDB.MotorCmdData2, speedTens);
        fridgeDB.addValue(UMDB.MotorCmdData3, speedUnits);
        fridgeDB.addValue(UMDB.MotorCmdData4, '0');
        fridgeDB.addValue(UMDB.MotorCmdData5, '2');
        fridgeDB.addValue(UMDB.MotorCmdData6, '8');
        fridgeDB.addValue(UMDB.MotorCmdData7, '0');
        fridgeDB.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        return fridgeDB;
    }

    public UMDB getSpinCmd(char speedHundreds, char speedTens, char speedUnits) {
        return getSpinCmd('l', speedHundreds, speedTens, speedUnits);
    }
}
