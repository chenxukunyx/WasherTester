package com.miracle.app.protocol.weili;

import com.miracle.app.model.GalanzModel;
import com.miracle.app.model.JiDeModel;
import com.miracle.app.model.WeiLiModel;
import com.miracle.app.protocol.galanz.GalanzRecvProtocol;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/4/23
 * @time: 上午9:47
 */

public class WeiLiRecvProtocol extends StreamProtocol {

    @Override
    public void initDefines() {
//设定转速-应答
        String headString = WeiLiModel.CMD_STX + WeiLiModel.CMD_DEST_ADDR + WeiLiModel.CMD_SRC_ADDR;
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_S), 32, 112, 0, 32, 8, UMDB.MotorAckData0);
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
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_I), 32, 112, 0, 32, 8, UMDB.MotorAckData10);
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
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_C), 32, 80, 0, 32, 8, UMDB.MotorAckData20);
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
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_D), 32, 96, 0, 32, 8, UMDB.MotorAckData26);
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
        addDefine(0, 88, 8, UMDB.MotorAckData33);

        //查询直流母线电压、电流-应答
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_M), 32, 112, 0, 32, 8, UMDB.MotorAckData34);
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

//        //PMU指令
//        addDefine(StringUtils.hexStringToLong(headString + JiDeModel.DATA_CMD_U), 32, 112, 0, 32, 8, UMDB.MotorAckData54);
//        addDefine(0, 40, 8, UMDB.MotorAckData46);
//        addDefine(0, 48, 8, UMDB.MotorAckData47);
//        addDefine(0, 56, 8, UMDB.MotorAckData48);
//        addDefine(0, 64, 8, UMDB.MotorAckData49);
//        addDefine(0, 72, 8, UMDB.MotorAckData50);
//        addDefine(0, 80, 8, UMDB.MotorAckData51);
//        addDefine(0, 88, 8, UMDB.MotorAckData52);
////            addDefine(0, 96, 8, UMDB.MotorAckData53);
////            addDefine(0, 104, 8, UMDB.MotorAckData54);
//
        //PMT指令
        addDefine(StringUtils.hexStringToLong(headString + WeiLiModel.DATA_CMD_T), 32, 80, 0, 32, 8, UMDB.MotorAckData55);
        addDefine(0, 40, 8, UMDB.MotorAckData56);
        addDefine(0, 48, 8, UMDB.MotorAckData57);
        addDefine(0, 56, 8, UMDB.MotorAckData58);
        addDefine(0, 64, 8, UMDB.MotorAckData59);
        addDefine(0, 72, 8, UMDB.MotorAckData60);
    }
}
