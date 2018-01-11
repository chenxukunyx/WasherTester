package com.unilife.variety.protocol.gelanshi;

import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;
import com.unilife.variety.model.GeLanshiModel;
import com.unilife.variety.utils.StringUtils;

/**
 * Created by 万启林 on 2015/7/30.
 */
public class GeLanshiSendProtocol extends StreamProtocol {
    @Override
    public void initDefines() {

        //设定转速-应答
        String headString = GeLanshiModel.CMD_STX + GeLanshiModel.CMD_SRC_ADDR + GeLanshiModel.CMD_DEST_ADDR;

        //设定转速
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_S), 32, 112, 0, 32, 8, UMDB.MotorCmdData0);
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
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_I), 32, 48, 0, 32, 8, UMDB.MotorCmdData10);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdDataEtx);
        addDefine(0, 40, 8, UMDB.MotorCmdData11);

        //查询状态
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_C), 32, 48, 0, 32, 8, UMDB.MotorCmdData12);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData13);
        addDefine(0, 40, 8, UMDB.MotorCmdData13);


        //设定参数
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_P), 32, 104, 0, 32, 8, UMDB.MotorCmdData15);
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


        //查询直流母线电压、电流
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_M), 32, 48, 0, 32, 8, UMDB.MotorCmdData23);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdDataEtx);
        addDefine(0, 40, 8, UMDB.MotorCmdData24);


        /**
         * 查询温度
         */
        addDefine(StringUtils.hexStringToLong(headString + GeLanshiModel.DATA_CMD_T), 32, 48, 0, 32, 8, UMDB.MotorCmdData28);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorCmd);
//            addDefine(0, 32, 8, UMDB.MotorCmdData13);
        addDefine(0, 40, 8, UMDB.MotorCmdData29);


    }
}
