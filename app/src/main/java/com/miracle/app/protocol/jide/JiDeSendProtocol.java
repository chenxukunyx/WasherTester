package com.miracle.app.protocol.jide;

import com.miracle.app.model.JiDeModel;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created by 万启林 on 2015/7/30.
 */
public class JiDeSendProtocol extends StreamProtocol {
    @Override
    public void initDefines() {

        //设定转速-应答
        String headString = JiDeModel.CMD_STX + JiDeModel.CMD_SRC_ADDR + JiDeModel.CMD_DEST_ADDR;

        //设定转速
        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_S), 32, 112, 0, 32, 8, UMDB.MotorCmdData0);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData0);
        addDefine(0, 40, 8, UMDB.MotorCmdData1);
        addDefine(0, 48, 8, UMDB.MotorCmdData2);
        addDefine(0, 56, 8, UMDB.MotorCmdData3);
        addDefine(0, 64, 8, UMDB.MotorCmdData4);
        addDefine(0, 72, 8, UMDB.MotorCmdData5);
        addDefine(0, 80, 8, UMDB.MotorCmdData6);
        addDefine(0, 88, 8, UMDB.MotorCmdData7);
        addDefine(0, 96, 8, UMDB.MotorCmdData8);
        addDefine(0, 104, 8, UMDB.MotorCmdData9);

        //查询设定转速
//            addDefine(StringUtils.hexStringToLong(headString+HisenseUMDBModel.DATA_CMD_S), 32, 48, 0, 0, 8, UMDB.MotorCmdDataStx);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdDataEtx);
//            addDefine(0, 40, 8, UMDB.MotorCmdDataChk);

        //查询实际转速
        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_I), 32, 48, 0, 32, 8, UMDB.MotorCmdData10);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdDataEtx);
        addDefine(0, 40, 8, UMDB.MotorCmdData11);

        //查询状态
        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_C), 32, 48, 0, 32, 8, UMDB.MotorCmdData12);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData13);
        addDefine(0, 40, 8, UMDB.MotorCmdData13);


        //设定参数
        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_P), 32, 96, 0, 32, 8, UMDB.MotorCmdData15);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData0);
        addDefine(0, 40, 8, UMDB.MotorCmdData16);
        addDefine(0, 48, 8, UMDB.MotorCmdData17);
        addDefine(0, 56, 8, UMDB.MotorCmdData18);
        addDefine(0, 64, 8, UMDB.MotorCmdData19);
        addDefine(0, 72, 8, UMDB.MotorCmdData20);
        addDefine(0, 80, 8, UMDB.MotorCmdData21);
        addDefine(0, 88, 8, UMDB.MotorCmdData22);

        //查询参数
//            addDefine(StringUtils.hexStringToLong(headString+HisenseUMDBModel.DATA_CMD_P), 32, 64, 0, 0, 8, UMDB.MotorCmdDataStx);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData0);
//            addDefine(0, 40, 8, UMDB.MotorCmdData1);
//            addDefine(0, 48, 8, UMDB.MotorCmdDataEtx);
//            addDefine(0, 56, 8, UMDB.MotorCmdDataChk);

        //查询直流母线电压、电流
        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_M), 32, 48, 0, 32, 8, UMDB.MotorCmdData23);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdDataEtx);
        addDefine(0, 40, 8, UMDB.MotorCmdData24);

        //PMU指令
//        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_U), 32, 48, 0, 32, 8, UMDB.MotorCmdData38);
//        addDefine(0, 40, 8, UMDB.MotorCmdData39);

        //PMB指令
//        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_B), 32, 48, 0, 32, 8, UMDB.MotorCmdData36);
//        addDefine(0, 40, 8, UMDB.MotorCmdData37);


    }
}
