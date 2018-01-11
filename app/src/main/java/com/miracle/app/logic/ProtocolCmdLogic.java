package com.miracle.app.logic;

import com.miracle.um_base_common.base.BaseLogic;
import com.miracle.um_base_common.entity.CheckResponse;
import com.miracle.um_base_common.entity.ProtocolCmd;
import com.miracle.um_base_common.listener.IProtocolListener;
import com.miracle.um_base_common.util.CountTimerUtil;
import com.miracle.um_base_common.util.MLog;
import com.unilife.common.entities.UMDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 万启林 on 2015/7/31.
 */
public class ProtocolCmdLogic extends BaseLogic implements CountTimerUtil.CountTimerListener {
    private static final int RESPONSE_TIME_OUT = 3000;//变频回复超时时间
    private static final int ACK_DATA_SUCCESS = 0x06;
    private static final int ACK_DATA_ERROR = 0x15;

    private static ProtocolCmdLogic mInstance;
    private static Map<Integer, String> ERROR_INFO;
    private List<ProtocolCmd> mCmdList;
    private CheckResponse mCheckResponse;

    private IProtocolListener mProtocolListener;

    private int mPosition = 0;//当前命令下标
    private boolean isReset = true;//当前状态是否复位
    private boolean isStart = false;//是否开始
    private int mRunTime = 0;//当前命令运行的时间
    private int mResponseTime = 0;


    private ProtocolCmdLogic() {
        super();
        mCheckResponse = new CheckResponse();

        ERROR_INFO = new HashMap<>();
        ERROR_INFO.put(0x0000, "SUCCESS");
        ERROR_INFO.put(0x0001, "高电流");
        ERROR_INFO.put(0x0002, "高电压");
        ERROR_INFO.put(0x0004, "低电压");
        ERROR_INFO.put(0x0008, "启动失败或运行中堵转");
        ERROR_INFO.put(0x0010, "电流检测异常");
        ERROR_INFO.put(0x0020, "速度反馈异常");
        ERROR_INFO.put(0x0040, "高温");
        mCmdList = initCmdList();
        CountTimerUtil.getInstance().registerListener(this);
    }

    public static ProtocolCmdLogic getInstance() {
        if(mInstance == null) {
            synchronized (ProtocolCmdLogic.class) {
                mInstance = new ProtocolCmdLogic();
            }
        }
        return mInstance;
    }

    /**
     * 设置回调
     * @param listener
     */
    public void setProtocolListener(IProtocolListener listener) {
        this.mProtocolListener = listener;
    }

    /**
     * 获取错误信息
     * @param key  错误代码
     * @return  SUCCESS
     */
    public String getErrorInfo(Integer key) {
        String errorInfo = ERROR_INFO.get(key);
        if(errorInfo == null) {
            errorInfo = String.valueOf(key);
        }
        return errorInfo;
    }

    /**
     * 变频回复是否正确
     * @param ack
     * @return
     */
    public boolean ackIsSuccess(int ack, int error) {
        return ack == ACK_DATA_SUCCESS && error != 1;
    }

    /**
     * 获取命令列表
     * @return
     */
    public List<ProtocolCmd> getCmdList() {
        return mCmdList;
    }

    /**
     * 获取当前的命令
     * @return
     */
    public ProtocolCmd getCurrentCmd() {
        if(mPosition < 0 || mPosition >= mCmdList.size()) {
            return new ProtocolCmd();
        }else {
            return mCmdList.get(mPosition);
        }
    }

    /**
     * 收到变频的反馈信息
     * @param ackCmd
     * @param data
     */
    public void recvResponse(int error, int ackCmd, int[] data) {
        if(error == 1) {
            MLog.d(TAG, "recvResponse:error");
            //return;
        }

        mResponseTime = 0;

        if(ackCmd != ACK_DATA_SUCCESS) {
            return;
        }

        int rpm = data[1] * 256 + data[2];
        int errorL = data[4];
        int voltage = data[5] * 2;
        int power = data[6];
        int electricity = 0;
        if(voltage != 0) {
            electricity = power/voltage;
        }
        if(errorL != 0) {
            String errorMsg = getErrorInfo(errorL);
            if(errorMsg != null) {
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, errorMsg);
                }
                return;
            }
        }
        ProtocolCmd protocolCmd = getCurrentCmd();
        if(protocolCmd.isCheckRpm()) {
            if(rpm > protocolCmd.getMaxRpm()) {
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, "速度不稳定");
                }
            }else {
                mCheckResponse.setRpm(rpm);
                if(rpm >= protocolCmd.getMinRpm()) {
                    mCheckResponse.setRpmOk(true);
                }
                if(rpm != 0) {
                    mCheckResponse.setRpmZero(false);
                }
            }
            /*if(rpm < protocolCmd.getMinRpm() || rpm > protocolCmd.getMaxRpm()) {
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, "速度不稳定");
                }
            }else if(rpm == 0) {
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, "无功率");
                }
            }*/
        }
        if(protocolCmd.isCheckVoltage()) {
            if(voltage < protocolCmd.getMinVoltage() || voltage > protocolCmd.getMaxVoltage()) {
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, "电压异常");
                }
            }
        }
    }

    /**
     * 获取暂停命令
     * @return
     */
    public UMDB getPauseCmd() {
        return null;
    }

    /**
     * 开始
     */
    public void start() {
        if(isReset) {
            mPosition = 0;
            mRunTime = 0;
            isStart = true;
            isReset = false;
            MLog.d(TAG, "send umdb:"+getCurrentCmd().getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(getCurrentCmd().getUmdb(), getCurrentCmd().getCircleDirect());
            }
        }else {
            isStart = true;
            isReset = false;
            MLog.d(TAG, "send umdb:"+getCurrentCmd().getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(getCurrentCmd().getUmdb(), getCurrentCmd().getCircleDirect());
            }
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        isStart = false;
        isReset = false;
        if(mProtocolListener != null) {
            mProtocolListener.onSendCmd(getPauseCmd(), ProtocolCmd.NONE);
        }
    }

    /**
     * 复位
     */
    public void reset() {
        isStart = false;
        isReset = true;
        mPosition = 0;
        mRunTime = 0;
        mCheckResponse = new CheckResponse();
        if(mProtocolListener != null) {
            mProtocolListener.onSendCmd(getPauseCmd(), ProtocolCmd.NONE);
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isReset() {
        return isReset;
    }

    /**
     * 检测转速
     * @return
     */
    private boolean checkResponseOk() {
        if(!getCurrentCmd().isCheckRpm()) {
            return true;
        }
        if(mCheckResponse.isRpmZero()) {
            if(mProtocolListener != null) {
                mProtocolListener.onError(true, "无功率");
            }
            return false;
        }else if(!mCheckResponse.isRpmOk()) {
            if(mProtocolListener != null) {
                mProtocolListener.onError(true, "速度不稳定");
            }
            return false;
        }
        return true;
    }

    /**
     * 发送下一组命令
     */
    private void sendNextCmd() {
        if(!checkResponseOk()) {
            return;
        }
        mRunTime = 0;
        mPosition++;
        if(mPosition >= mCmdList.size()) {
            if(mProtocolListener != null) {
                mProtocolListener.onSuccess();
            }
        }else {
            MLog.d(TAG, "send umdb:"+getCurrentCmd().getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(getCurrentCmd().getUmdb(), getCurrentCmd().getCircleDirect());
            }
        }
    }

    /**
     * 初始化命令列表
     * @return
     */
    private List<ProtocolCmd> initCmdList() {
        List<ProtocolCmd> list = new ArrayList<>();
        ProtocolCmd cmd;

//        //静态检查命令
//        cmd = new ProtocolCmd("静态检查", mModel.getCheckCmd(), 3000, false, 0, 0, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.NONE);
//        list.add(cmd);
//
//        //洗涤正转
//        cmd = new ProtocolCmd("洗涤正转", mModel.getWashCWCmd(), 12000, true, 55, 100, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.CW);
//        list.add(cmd);
//
//        //停止12s
//        cmd = new ProtocolCmd();
//        cmd.setName("停止12s");
//        cmd.setUmdb(mModel.getPauseCmd());
//        cmd.setTime(12000);
//        cmd.setCircleDirect(ProtocolCmd.NONE);
//        list.add(cmd);
//
//        //洗涤反转
//        cmd = new ProtocolCmd("洗涤反转", mModel.getWashCCWCmd(), 12000, true, 55, 100, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.CCW);
//        list.add(cmd);
//
//        //停止12s
//        cmd = new ProtocolCmd();
//        cmd.setName("停止12s");
//        cmd.setUmdb(mModel.getPauseCmd());
//        cmd.setTime(12000);
//        cmd.setCircleDirect(ProtocolCmd.NONE);
//        list.add(cmd);
//
//        //重复一组
//        //洗涤正转
//        cmd = new ProtocolCmd("洗涤正转", mModel.getWashCWCmd(), 12000, true, 55, 100, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.CW);
//        list.add(cmd);
//
//        //停止12s
//        cmd = new ProtocolCmd();
//        cmd.setName("停止12s");
//        cmd.setUmdb(mModel.getPauseCmd());
//        cmd.setTime(12000);
//        cmd.setCircleDirect(ProtocolCmd.NONE);
//        list.add(cmd);
//
//        //洗涤反转
//        cmd = new ProtocolCmd("洗涤反转", mModel.getWashCCWCmd(), 12000, true, 55, 100, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.CCW);
//        list.add(cmd);
//
//        //停止12s
//        cmd = new ProtocolCmd();
//        cmd.setName("停止12s");
//        cmd.setUmdb(mModel.getPauseCmd());
//        cmd.setTime(12000);
//        cmd.setCircleDirect(ProtocolCmd.NONE);
//        list.add(cmd);
//
//        //脱水检测
//        cmd = new ProtocolCmd("脱水检测", mModel.getSpinCmd(), 10000, true, 380, 500, true, 200, 245, true, 0, 5, true, 0, 5, ProtocolCmd.CW);
//        list.add(cmd);
        return list;
    }

    @Override
    public void onTick(int step) {
        if(!isReset && isStart) {
            mRunTime += step;
            mResponseTime += step;
            if(mResponseTime >= RESPONSE_TIME_OUT) {
                mResponseTime = 0;
                if(mProtocolListener != null) {
                    mProtocolListener.onError(true, "无通讯");
                }
            }
            if(mRunTime >= getCurrentCmd().getTime()) {
                sendNextCmd();
            }
        }
    }
}
