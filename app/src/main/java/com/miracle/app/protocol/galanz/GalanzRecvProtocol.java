package com.miracle.app.protocol.galanz;

import com.miracle.app.model.GalanzModel;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created by 万启林 on 2015/7/30.
 */
public class GalanzRecvProtocol extends StreamProtocol {
    @Override
    public void initDefines() {

        String headString = GalanzModel.CMD_STX + GalanzModel.CMD_DEST_ADDR + GalanzModel.CMD_SRC_ADDR;

        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_S), 32, 120, 0, 32, 8, UMDB.MotorAckData0);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorAck);
//            addDefine(0, 32, 8, UMDB.MotorAckData0);
        addDefine(0, 40, 8, UMDB.MotorAckData1);
        addDefine(0, 48, 8, UMDB.MotorAckData2);
        addDefine(0, 56, 8, UMDB.MotorAckData3);
        addDefine(0, 64, 8, UMDB.MotorAckData4);
        addDefine(0, 72, 8, UMDB.MotorAckData5);
        addDefine(0, 80, 8, UMDB.MotorAckData6);
        addDefine(0, 88, 8, UMDB.MotorAckData7);
        addDefine(0, 96, 8, UMDB.MotorAckData8);
        addDefine(0, 104, 8, UMDB.MotorAckData9);


        //查询实际转速-应答
        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_I), 32, 120, 0, 32, 8, UMDB.MotorAckData10);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorAck);
//            addDefine(0, 32, 8, UMDB.MotorAckData0);
        addDefine(0, 40, 8, UMDB.MotorAckData11);
        addDefine(0, 48, 8, UMDB.MotorAckData12);
        addDefine(0, 56, 8, UMDB.MotorAckData13);
        addDefine(0, 64, 8, UMDB.MotorAckData14);
        addDefine(0, 72, 8, UMDB.MotorAckData15);
        addDefine(0, 80, 8, UMDB.MotorAckData16);
        addDefine(0, 88, 8, UMDB.MotorAckData17);
        addDefine(0, 96, 8, UMDB.MotorAckData18);
        addDefine(0, 104, 8, UMDB.MotorAckData19);

        //查询状态-应答
        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_C), 32, 88, 0, 32, 8, UMDB.MotorAckData20);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorAck);
//            addDefine(0, 32, 8, UMDB.MotorAckData0);
        addDefine(0, 40, 8, UMDB.MotorAckData21);
        addDefine(0, 48, 8, UMDB.MotorAckData22);
        addDefine(0, 56, 8, UMDB.MotorAckData23);
        addDefine(0, 64, 8, UMDB.MotorAckData24);
        addDefine(0, 72, 8, UMDB.MotorAckData25);

        //设定参数-应答
        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_P), 32, 104, 0, 32, 8, UMDB.MotorAckData26);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorAck);
//            addDefine(0, 32, 8, UMDB.MotorAckData0);
        addDefine(0, 40, 8, UMDB.MotorAckData27);
        addDefine(0, 48, 8, UMDB.MotorAckData28);
        addDefine(0, 56, 8, UMDB.MotorAckData29);
        addDefine(0, 64, 8, UMDB.MotorAckData30);
        addDefine(0, 72, 8, UMDB.MotorAckData31);
        addDefine(0, 80, 8, UMDB.MotorAckData32);
//            addDefine(0, 88, 8, UMDB.MotorAckData33);

        //查询直流母线电压、电流-应答
        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_M), 32, 120, 0, 32, 8, UMDB.MotorAckData34);
//            addDefine(0, 8, 8, UMDB.MotorCmdDataSrcAddr);
//            addDefine(0, 16, 8, UMDB.MotorCmdDataDestAddr);
//            addDefine(0, 24, 8, UMDB.MotorAck);
//            addDefine(0, 32, 8, UMDB.MotorAckData0);
        addDefine(0, 40, 8, UMDB.MotorAckData35);
        addDefine(0, 48, 8, UMDB.MotorAckData36);
        addDefine(0, 56, 8, UMDB.MotorAckData37);
        addDefine(0, 64, 8, UMDB.MotorAckData38);
        addDefine(0, 72, 8, UMDB.MotorAckData39);
        addDefine(0, 80, 8, UMDB.MotorAckData40);
        addDefine(0, 88, 8, UMDB.MotorAckData41);
        addDefine(0, 96, 8, UMDB.MotorAckData42);
        addDefine(0, 104, 8, UMDB.MotorAckData43);

        addDefine(StringUtils.hexStringToLong(headString + GalanzModel.DATA_CMD_T), 32, 72, 0, 32, 8, UMDB.MotorAckData50);
        addDefine(0, 40, 8, UMDB.MotorAckData51);
        addDefine(0, 48, 8, UMDB.MotorAckData52);
    }
}
