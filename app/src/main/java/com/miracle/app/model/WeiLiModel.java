package com.miracle.app.model;

import com.miracle.um_base_common.model.BaseModel;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/4/23
 * @time: 上午9:57
 */

public class WeiLiModel extends BaseModel {

    public static final String CMD_STX = "82";//"02";
    public static final String CMD_ETX = "03";//"03";
    public static final String CMD_SRC_ADDR = "F0";//"70";
    public static final String CMD_DEST_ADDR = "ED";//"6D";

    public static final String DATA_CMD_S = "F3";//"73";
    public static final String DATA_CMD_I = "69";//"69";
    public static final String DATA_CMD_C = "63";//"63";
    public static final String DATA_CMD_P = "F0";//"70";
    public static final String DATA_CMD_M = "ED";//"6D";
    public static final String DATA_CMD_D = "E4";
    public static final String DATA_CMD_T = "74";

    public WeiLiModel() {
        super();
    }

    /**
     * 获取待机命令
     * @return
     */
    public UMDB getPauseCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData0, 'l');
        umdb.addValue(UMDB.MotorCmdData1, '0');
        umdb.addValue(UMDB.MotorCmdData2, '0');
        umdb.addValue(UMDB.MotorCmdData3, '0');
        umdb.addValue(UMDB.MotorCmdData4, '0');
        umdb.addValue(UMDB.MotorCmdData5, '1');
        umdb.addValue(UMDB.MotorCmdData6, '4');
        umdb.addValue(UMDB.MotorCmdData7, '0');
        umdb.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData9, 0);
        return umdb;
    }

    /**
     * 获取设定状态指令
     * @return
     */
    public UMDB getSetStatusCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData15, '0');
        umdb.addValue(UMDB.MotorCmdData16, '3');
        umdb.addValue(UMDB.MotorCmdData17, '7');
        umdb.addValue(UMDB.MotorCmdData18, '5');
        umdb.addValue(UMDB.MotorCmdData19, '6');
        umdb.addValue(UMDB.MotorCmdData20, '4');
        umdb.addValue(UMDB.MotorCmdData21, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData22, 0);
        return umdb;
    }

    /**
     * 获取设定状态指令
     * @return
     */
    public UMDB getSetStatusVersionCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData15, '0');
        umdb.addValue(UMDB.MotorCmdData16, '3');
        umdb.addValue(UMDB.MotorCmdData17, '0');
        umdb.addValue(UMDB.MotorCmdData18, '0');
        umdb.addValue(UMDB.MotorCmdData19, '0');
        umdb.addValue(UMDB.MotorCmdData20, '0');
        umdb.addValue(UMDB.MotorCmdData21, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData22, 0);
        return umdb;
    }

    public UMDB getSetWeightVersionCmd(char b, char c) {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData15, '0');
        umdb.addValue(UMDB.MotorCmdData16, '1');
        umdb.addValue(UMDB.MotorCmdData17, b);
        umdb.addValue(UMDB.MotorCmdData18, c);
        umdb.addValue(UMDB.MotorCmdData19, '8');
        umdb.addValue(UMDB.MotorCmdData20, 'c');
        umdb.addValue(UMDB.MotorCmdData21, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData22, 0);
        return umdb;
    }

    /**
     * 获取查询状态指令 c
     * @return
     */
    public UMDB getFetchStatusC() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData12, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData13, 0);
        return umdb;
    }

    /**
     * 获取查询状态指令 i
     * @return
     */
    public UMDB getFetchStatusI() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData10, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData11, 0);
        return umdb;
    }

    public UMDB getFetchStatusU() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData38, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData39, 0);
        return umdb;
    }

    public UMDB getFetchStatusB() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData36, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData37, 0);
        return umdb;
    }

    /**
     * 获取查询状态指令 m
     * @return
     */
    public UMDB getFetchStatusM() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData23, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData24, 0);
        return umdb;
    }


    /**
     * 获取洗涤正转命令
     * @return
     */
    public UMDB getWashCWCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData0, 'r');
        umdb.addValue(UMDB.MotorCmdData1, '1');
        umdb.addValue(UMDB.MotorCmdData2, '9');
        umdb.addValue(UMDB.MotorCmdData3, '0');
        umdb.addValue(UMDB.MotorCmdData4, '0');
        umdb.addValue(UMDB.MotorCmdData5, '2');
        umdb.addValue(UMDB.MotorCmdData6, '8');
        umdb.addValue(UMDB.MotorCmdData7, '0');
        umdb.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData9, 0);
        return umdb;
    }

    /**
     * 获取洗涤反转命令
     * @return
     */
    public UMDB getWashCCWCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData0, 'l');
        umdb.addValue(UMDB.MotorCmdData1, '3');
        umdb.addValue(UMDB.MotorCmdData2, 'e');
        umdb.addValue(UMDB.MotorCmdData3, '8');
        umdb.addValue(UMDB.MotorCmdData4, '0');
        umdb.addValue(UMDB.MotorCmdData5, '2');
        umdb.addValue(UMDB.MotorCmdData6, '8');
        umdb.addValue(UMDB.MotorCmdData7, '0');
        umdb.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData9, 0);
        return umdb;
    }

    /**
     * 获取脱水命令
     * @return
     */
    public UMDB getSpinCmd() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData0, 'l');
        umdb.addValue(UMDB.MotorCmdData1, '1');
        umdb.addValue(UMDB.MotorCmdData2, '9');
        umdb.addValue(UMDB.MotorCmdData3, '0');
        umdb.addValue(UMDB.MotorCmdData4, '0');
        umdb.addValue(UMDB.MotorCmdData5, '2');
        umdb.addValue(UMDB.MotorCmdData6, '8');
        umdb.addValue(UMDB.MotorCmdData7, '0');
        umdb.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
        umdb.addValue(UMDB.MotorCmdData9, 0);
        return umdb;
    }

    public UMDB getPMB() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData38, StringUtils.hexStringToLong(CMD_ETX));
        return umdb;
    }

    public UMDB getPMT() {
        UMDB umdb = new UMDB();
        umdb.addValue(UMDB.MotorCmdData36, StringUtils.hexStringToLong(CMD_ETX));
        return umdb;
    }

//    public UMDB getRunningCmd(@DIRECTION int direct, int speed) {
//        UMDB umdb = new UMDB();
//        if (direct == CCW) {
//            umdb.addValue(UMDB.MotorCmdData0, 'l');
//        } else {
//            umdb.addValue(UMDB.MotorCmdData0, 'r');
//        }
//        String hexSpeed = Integer.toHexString(speed);
//        if (hexSpeed.length() == 1) {
//            hexSpeed = "00" + hexSpeed;
//        } else if (hexSpeed.length() == 2) {
//            hexSpeed = "0" + hexSpeed;
//        }
//        umdb.addValue(UMDB.MotorCmdData1, hexSpeed.charAt(0));
//        umdb.addValue(UMDB.MotorCmdData2, hexSpeed.charAt(1));
//        umdb.addValue(UMDB.MotorCmdData3, hexSpeed.charAt(2));
//        umdb.addValue(UMDB.MotorCmdData4, '0');
//        umdb.addValue(UMDB.MotorCmdData5, '2');
//        umdb.addValue(UMDB.MotorCmdData6, '8');
//        umdb.addValue(UMDB.MotorCmdData7, '0');
//        umdb.addValue(UMDB.MotorCmdData8, StringUtils.hexStringToLong(CMD_ETX));
//        umdb.addValue(UMDB.MotorCmdData9, 0);
//        return umdb;
//    }

}
