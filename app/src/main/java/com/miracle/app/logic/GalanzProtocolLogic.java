package com.miracle.app.logic;

import android.text.TextUtils;
import android.util.Log;

import com.miracle.app.base.BaseProtocolCmdLogic;
import com.miracle.app.model.GalanzModel;
import com.miracle.um_base_common.entity.ProtocolCmd;
import com.miracle.um_base_common.entity.TesterEntity;
import com.miracle.um_base_common.logic.ConfigLogic;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.entities.UMDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 万启林 on 2015/8/12.
 */
public class GalanzProtocolLogic extends BaseProtocolCmdLogic {
    private static GalanzProtocolLogic mInstance;

    private int high = 4;
    private int media = 2;

    private int maxPower = 0;
    private float maxEle = 0;

    private GalanzModel mModel;
//    private boolean mCheckOk = false;

    private GalanzProtocolLogic() {
        super();
    }

    java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");

    public static GalanzProtocolLogic getInstance() {
        if (mInstance == null) {
            synchronized (GalanzProtocolLogic.class) {
                mInstance = new GalanzProtocolLogic();
            }
        }
        return mInstance;
    }

    @Override
    public void recvResponse(int error, int ackCmd, int[] data) {

    }

    @Override
    public void recvResponse(int error, int ackCmd, UMDB data) {

        Integer rpm = null;
        Integer voltage = null;
        Integer power = null;
        Integer temperature = null;
        Float electricity = null;
        String version = null;

        //转速
        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData18))) {
            Log.d(TAG, "MotorAckData18 back");
            int highRpm = (int) data.getIntValue(UMDB.MotorAckData11);
            int mediumRpm = (int) data.getIntValue(UMDB.MotorAckData12);
            int lowRpm = (int) data.getIntValue(UMDB.MotorAckData13);
            int decimalRpm = (int) data.getIntValue(UMDB.MotorAckData14);
            rpm = ascToHex(highRpm, mediumRpm, lowRpm, decimalRpm);
            rpm = rpm / 16;
        }

        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData52))) {
            Log.d(TAG, "MotorAckData52 back");
            int highTemp = (int) data.getIntValue(UMDB.MotorAckData50);
            int lowTemp = (int) data.getIntValue(UMDB.MotorAckData51);
            temperature = ascToHex(highTemp, lowTemp);
        }

        //版本
        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData32))) {
            Log.d(TAG, "MotorAckData32 back");
            int version3 = getIntUmdbValue(data, UMDB.MotorAckData30);
            int version4 = getIntUmdbValue(data, UMDB.MotorAckData31);
            Log.i(TAG, version3 + " : " + version4);
            if (getIntUmdbValue(data, UMDB.MotorAckData26) == '0' &&
                    getIntUmdbValue(data, UMDB.MotorAckData27) == '4') {
                version = "00";
                version += String.valueOf((char) version3);
                version += String.valueOf((char) version4);
            }
        }

        if (!TextUtils.isEmpty(data.getValue(UMDB.MotorAckData42))) {
            Log.d(TAG, "MotorAckData42 back");
            //电压
            int highVoltage = getIntUmdbValue(data, UMDB.MotorAckData34);
            int lowVoltage = getIntUmdbValue(data, UMDB.MotorAckData35);
            voltage = ascToHex(highVoltage, lowVoltage);
            voltage = voltage * 2;

            int highPower = getIntUmdbValue(data, UMDB.MotorAckData38);
            int lowPower = getIntUmdbValue(data, UMDB.MotorAckData39);
            //功率
            power = ascToHex(highPower, lowPower);
            power = power * 32;
        }

        if (voltage != null && voltage != 0) {
            electricity = (float) power / voltage;
        }

        if (voltage != null && voltage != 0) {
            electricity = (float) power / voltage;
            if (electricity > maxEle) {
                maxEle = electricity;
            }
        }

        if (rpm != null) {
            rpmChange(rpm + "");
        }
        temperatureChange(temperature + " ℃");
        if (voltage != null) {
            voltageChange(voltage + "V");
        }
        if (power != null) {
//            if(getCurrentCmd().isShowMaxPower()) {
//                powerChange(maxPower + "W 最大");
//            }else {
            powerChange(power + "W");
//            }
        }
        if (electricity != null) {
//            if (getCurrentCmd().isShowMaxPower()){
//                electricityChange(df.format(maxEle) + "A 最大");
//            }else {
            electricityChange(df.format(electricity) + "A");
//            }
        }

        if (version != null) {
            versionChange(version + "");
        }
        ProtocolCmd protocolCmd = getCurrentCmd();
        if (protocolCmd.isCheckRpm()) {
            if (rpm != null && rpm > protocolCmd.getMaxRpm()) {
                if (mProtocolListener != null) {
                    mProtocolListener.onError(true, "速度不稳定");
                }
            } else if (rpm != null) {
                mCheckResponse.setRpm(rpm);
                if (rpm >= protocolCmd.getMinRpm()) {
                    mCheckResponse.setRpmOk(true);
                }
                if (rpm != 0) {
                    mCheckResponse.setRpmZero(false);
                }
            }
        }
//        if (protocolCmd.isCheckRpm()) {
//            if (rpm == 0) {
//                if (mProtocolListener != null) {
//                    mProtocolListener.onError(true, "速度不稳定");
//                }
//            }else if (rpm != null) {
//                mCheckResponse.setPower(rpm);
//            }
//        }
        if (protocolCmd.isCheckVoltage()) {
            if (voltage != null) {
                if (voltage < protocolCmd.getMinVoltage() || voltage > protocolCmd.getMaxVoltage()) {
                    if (mProtocolListener != null) {
                        mProtocolListener.onError(true, "电压异常");
                    }
                }
            }
        }

        if (protocolCmd.isCheckElectricity()) {
            if (electricity != null) {
                if (electricity > protocolCmd.getMaxElectricity()) {
                    if (mProtocolListener != null) {
                        mProtocolListener.onError(true, "电流过高");
                    }
                }
            }
        }

        if (electricity != null) {
            if (electricity > mCheckResponse.getElectricity()) {
                mCheckResponse.setElectricity(electricity);
            }
        }

        if (power != null) {
            if (power > mCheckResponse.getPower()) {
                mCheckResponse.setPower(power);
            }
        }
    }

    protected int getIntUmdbValue(UMDB umdb, String key) {
        return (int) umdb.getIntValue(key);
    }

    private int getUMDBValue(UMDB db, String key) {
        return (int) (db.getIntValue(key) & 0x7f);
//        return (int)(db.getIntValue(key));
    }

    @Override
    protected Map<Integer, String> initErrorInfo() {
        Map<Integer, String> errorInfo = new HashMap<>();
        return errorInfo;
    }

    @Override
    public void start() {
        TransmatterLogic.getTransmatterLogic().getTransmitter().write(mModel.getVersionCmd(), 9);
        super.start();
    }

    @Override
    protected List<ProtocolCmd> initCmdList() {
        //rpm 电压 功率 电流
        ConfigLogic logic = new ConfigLogic();
        TesterEntity config = logic.loadConfig();
        int minVoltage = StringUtils.parseInteger(config.getMinVoltage());
        int maxVoltage = StringUtils.parseInteger(config.getMaxVoltage());
        int minElectricity = StringUtils.parseInteger(config.getMinElectricity());
        int maxElectricity = StringUtils.parseInteger(config.getMaxElectricity());
        int minLowerPower = StringUtils.parseInteger(config.getMinLowerPower());
        int maxLowerPower = StringUtils.parseInteger(config.getMaxLowerPower());
        int minHigherPower = StringUtils.parseInteger(config.getMinHigherPower());
        int maxHigherPower = StringUtils.parseInteger(config.getMaxHigherPower());
        int minTemperature = StringUtils.parseInteger(config.getMinTemperature());
        int maxTemperature = StringUtils.parseInteger(config.getMaxTemperature());

        List<ProtocolCmd> list = new ArrayList<>();
        ProtocolCmd cmd;

        //前1s发送设定状态指令
        cmd = new ProtocolCmd("前1s发送设定状态指令", mModel.getRatioCmd(), 1000, false, 0, 0, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.NONE);
        list.add(cmd);

        //查版本
        cmd = new ProtocolCmd("查版本", mModel.getSetTemperatureCmd(), 1000, false, 0, 0, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.NONE);
        list.add(cmd);

        //查询状态指令 m
        cmd = new ProtocolCmd("查询状态指令-m", mModel.getFetchStatusM(), 1000, false, 0, 0, false, 200, 245, false, 0, 5, false, 0, 0, ProtocolCmd.NONE);
        list.add(cmd);

        //洗涤正转
        cmd = new ProtocolCmd("洗涤正转", mModel.getWashCWCmd(), 1000, false, 55, 100, false, 200, 245, false, 0, 5, false, 0, 5, ProtocolCmd.CW);
        cmd.setRmpNeedZero(true);
        list.add(cmd);

        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                //查询状态指令 i
                cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 1000, false, 55, 150, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.CW);
                list.add(cmd);
            } else {
                //查询状态指令 m
                cmd = new ProtocolCmd("查询状态指令-m", mModel.getFetchStatusM(), 1000, false, 0, 0, false, 200, 245, false, 0, 5, false, 0, 0, ProtocolCmd.CW);
                list.add(cmd);
            }
        }


        //停止
        cmd = new ProtocolCmd();
        cmd.setName("停止6s");
        cmd.setUmdb(mModel.getPauseCmd());
        cmd.setTime(6000);
        cmd.setCircleDirect(ProtocolCmd.NONE);
        list.add(cmd);

//        //洗涤反转
//        cmd = new ProtocolCmd("洗涤反转", mModel.getWashCCWCmd(), 1000, false, 55, 100, false, 200, 245, false, 0, 5, false, 0, 5, ProtocolCmd.CCW);
//        cmd.setRmpNeedZero(true);
//        list.add(cmd);
//
//
//        for (int i = 0; i < 40; i++) {
//            if (i % 2 == 0) {
//                //查询状态指令 i 查询转速
//                cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 1000, true, 0, 850, false, minVoltage, maxVoltage, false, minLowerPower, maxLowerPower, false, minElectricity, maxElectricity, ProtocolCmd.CCW);
//                list.add(cmd);
//            } else {
//                //查询状态指令 m 查询电压,电流,功率
//                cmd = new ProtocolCmd("查询状态指令-m", mModel.getFetchStatusM(), 1000, false, 0, 0, true, minVoltage, maxVoltage, false, 0, 5, false, minElectricity, maxElectricity, ProtocolCmd.CCW);
//                list.add(cmd);
//            }
//        }


//        cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 30000, true, 700, 850, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.CCW);
//        list.add(cmd);
//
//        cmd = new ProtocolCmd("查询状态指令-m", mModel.getFetchStatusM(), 700, false, 0, 0, true, minVoltage, maxVoltage, false, 0, 0, false, 0, 0, ProtocolCmd.CCW);
//        list.add(cmd);
//
//        cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 700, false, 0, 0, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.CCW);
//        list.add(cmd);


//        //停止
//        cmd = new ProtocolCmd();
//        cmd.setName("停止6s");
//        cmd.setUmdb(mModel.getPauseCmd());
//        cmd.setTime(6000);
//        cmd.setCircleDirect(ProtocolCmd.NONE);
//        cmd.setShowMaxPower(true);
//        list.add(cmd);

//        //脱水检测
//        cmd = new ProtocolCmd("脱水检测", mModel.getSpinCmd(), 1000, false, 380, 500, false, 200, 245, false, 0, 5, false, 0, 5, ProtocolCmd.CCW);
//        cmd.setRmpNeedZero(true);
//        list.add(cmd);
//
////        //===add
////        //查询状态指令 i
////        cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 15000, true, 350, 500, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.CCW);
////        list.add(cmd);
//        for(int i=0; i<20; i++) {
//            if(i%2==0) {
//                //查询状态指令 i
//                cmd = new ProtocolCmd("查询状态指令-i", mModel.getFetchStatusI(), 700, false, 55, 150, false, 0, 0, false, 0, 0, false, 0, 0, ProtocolCmd.CCW);
//                list.add(cmd);
//            }else {
//                //查询状态指令 m
//                cmd = new ProtocolCmd("查询状态指令-m", mModel.getFetchStatusM(), 700, false, 0, 0, false, 200, 245, false, 0, 5, false, 0, 0, ProtocolCmd.CCW);
//                list.add(cmd);
//            }
//        }

        return list;

    }

    @Override
    protected int initResponseTimeOut() {
        return 3000;
    }

    @Override
    protected int initAckDataSuccess() {
        return 0x00;
    }

    @Override
    protected void initModel() {
        mModel = new GalanzModel();
    }

    @Override
    protected boolean checkResponseOk() {
        final ProtocolCmd currentCmd = getCurrentCmd();
        if (currentCmd.isCheckRpm()) {
//            if (mCheckResponse.getPower() <= 0){
            if (!mCheckResponse.isRpmOk()) {
                if (mProtocolListener != null) {
                    mProtocolListener.onError(true, "速度不稳定");
                }
                return false;
            }
        }

        if (currentCmd.isCheckPower()) {
            if (mCheckResponse.getPower() <= 0) {
                if (mProtocolListener != null) {
                    mProtocolListener.onError(true, "无功率");
                }
                return false;
            }
//            }else if(mCheckResponse.getPower() > currentCmd.getMaxPower()) {
//                if(mProtocolListener != null) {
//                    mProtocolListener.onError(true, "功率过高");
//                }
//                return false;
//            }
        }

        if (currentCmd.isCheckElectricity()) {
            if (mCheckResponse.getElectricity() <= 0) {
                if (mProtocolListener != null) {
                    mProtocolListener.onError(true, "无电流");
                }
                return false;
            } else if (mCheckResponse.getElectricity() > currentCmd.getMaxElectricity()) {
                if (mProtocolListener != null) {
                    mProtocolListener.onError(true, "电流过高");
                }
                return false;
            }
        }
        return true;
    }

    private String getErrorInfoC3(int value) {
        if ((value & 1) > 0) {
            return "IPM模块温度过温-电机";
        } else if ((value & 2) > 0) {
            return "电机缺相-电机";
        } else if ((value & 4) > 0) {
            return "通信故障-电机";
        } else if ((value & 8) > 0) {
            return "硬件故障-电机";
        }
        return null;
    }

    private String getErrorInfoC2(int value) {
        if ((value & 1) > 0) {
            return "IPM模块温度检测失效-电机";
        } else if ((value & 2) > 0) {
            return "电压故障-电机";
        } else if ((value & 4) > 0) {
            return "复位-电机";
        } else if ((value & 8) > 0) {
            return "堵转-电机";
        }
        return null;
    }

    private String getErrorInfoC1(int value) {
        if ((value & 1) > 0) {
            return "爬坡阶段-电机";
        } else if ((value & 2) > 0) {
            return "无测速信号-电机";
        } else if ((value & 4) > 0) {
            return "IPM模块温度高温-电机";
        } else if ((value & 8) > 0) {
            return "电机过电流-电机";
        }
        return null;
    }

    private String getErrorInfoC0(int value) {
        if ((value & 1) > 0) {
            return "设定方向. = 右转方向-电机";
        } else if ((value & 2) > 0) {
            return "设定方向. = 左转方向-电机";
        } else if ((value & 4) > 0) {
            return "实际方向. = 右转方向-电机";
        } else if ((value & 8) > 0) {
            return "实际方向. = 左转方向-电机";
        }
        return null;
    }

    /**
     * 变频回复是否正确
     *
     * @param ack
     * @return
     */
    public boolean ackIsSuccess(int ack, int error) {
        super.ackIsSuccess(ack, error);
        return error != 1;
    }

    private int ascToHex(int... param) {
        if (param.length == 0) {
            return 0;
        }
        String hexString = "";

        for (int i = 0; i < param.length; i++) {
            hexString += String.valueOf((char) param[i]);
        }
        try {
            return Integer.parseInt(hexString, 16);
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    protected UMDB getPauseCmd() {
        return mModel.getPauseCmd();
    }

//    @Override
//    public void reset() {
//        super.reset();
//        mCheckOk = false;
//    }

//    @Override
//    protected ProtocolCmd sendNextCmd() {
//        ProtocolCmd cmd = super.sendNextCmd();
//        if(!getCurrentCmd().isShowMaxPower()) {
////            maxPower = 0;
////            macEle = 0;
////            maxEle = 0;
//        }
//        return cmd;
//    }

//    public void setMaxPower(){
//        maxPower = 0;
//        maxEle = 0;
//    }
}
