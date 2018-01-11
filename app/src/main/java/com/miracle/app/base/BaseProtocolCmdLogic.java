package com.miracle.app.base;

import com.miracle.app.config.BrandConfig;
import com.miracle.um_base_common.base.BaseLogic;
import com.miracle.um_base_common.entity.CheckResponse;
import com.miracle.um_base_common.entity.ProtocolCmd;
import com.miracle.um_base_common.listener.IProtocolListener;
import com.miracle.um_base_common.util.CountTimerUtil;
import com.miracle.um_base_common.util.MLog;
import com.unilife.common.entities.UMDB;
import com.unilife.common.managers.StatusManager;

import java.util.List;
import java.util.Map;

/**
 * Created by 万启林 on 2015/8/12.
 */
public abstract class BaseProtocolCmdLogic extends BaseLogic implements CountTimerUtil.CountTimerListener {
    private static int RESPONSE_TIME_OUT = 3000;//变频回复超时时间
    private static int ACK_DATA_SUCCESS = 0x06;

    private static Map<Integer, String> ERROR_INFO;
    private List<ProtocolCmd> mCmdList;
    protected CheckResponse mCheckResponse;
    protected IProtocolListener mProtocolListener;

    private int mPosition = 0;//当前命令下标
    private boolean isReset = true;//当前状态是否复位
    private boolean isStart = false;//是否开始
    private int mRunTime = 0;//当前命令运行的时间
    private int mResponseTime = 0;

    public BaseProtocolCmdLogic() {
        super();
        initData();
    }

    public void recreateCmdList() {
        mCmdList = initCmdList();
    }

    //初始化
    private void initData() {
        initModel();
        mCheckResponse = new CheckResponse();
        ERROR_INFO = initErrorInfo();
        mCmdList = initCmdList();
        RESPONSE_TIME_OUT = initResponseTimeOut();
        ACK_DATA_SUCCESS = initAckDataSuccess();
        CountTimerUtil.getInstance().registerListener(this);
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
        //MyLog.d(TAG, "ackIsSuccess");
        //if(error != 1) {
            mResponseTime = 0;
        //}
        return ack == ACK_DATA_SUCCESS && error != 1;
    }

    /**
     * 设置回调
     * @param listener
     */
    public void setProtocolListener(IProtocolListener listener) {
        this.mProtocolListener = listener;
    }

    /**
     * 开始
     */
    public void start() {
        ProtocolCmd currentCmd = getCurrentCmd();
        if(isReset) {
            mPosition = 0;
            mRunTime = 0;
            isStart = true;
            isReset = false;
            MLog.d(TAG, "start: send umdb:"+currentCmd.getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(currentCmd.getUmdb(), currentCmd.getCircleDirect());
            }
        }else {
            isStart = true;
            isReset = false;
            MLog.d(TAG, "start: send umdb:" + currentCmd.getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(currentCmd.getUmdb(), currentCmd.getCircleDirect());
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
        StatusManager.getUIStatus().GetCurrentStatus().initDefault();
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
     * 获取当前的命令
     * @return
     */
    protected ProtocolCmd getCurrentCmd() {
        if(mPosition < 0 || mPosition >= mCmdList.size()) {
            return new ProtocolCmd();
        }else {
            return mCmdList.get(mPosition);
        }
    }

    protected ProtocolCmd getNextCmd() {
        if(mPosition+1 < 0 || mPosition+1 >= mCmdList.size()) {
            return new ProtocolCmd();
        }else {
            return mCmdList.get(mPosition+1);
        }
    }

    /**
     * 处理回复
     * @param ackCmd
     * @param data
     */
    public abstract void recvResponse(int error, int ackCmd, int[] data);

    /**
     * 处理回复
     * @param ackCmd
     * @param data
     */
    public void recvResponse(int error, int ackCmd, UMDB data){}

    /**
     * 初始化错误信息
     * @return
     */
    protected abstract Map<Integer, String> initErrorInfo();

    /**
     * 初始化命令列表
     * @return
     */
    protected abstract List<ProtocolCmd> initCmdList();

    /**
     * 初始化回复超时时间
     * @return
     */
    protected abstract int initResponseTimeOut();

    /**
     * 初始化回复正确代码
     * @return
     */
    protected abstract int initAckDataSuccess();

    protected abstract void initModel();

    /**
     * 检测是否通过
     * @return
     */
    protected abstract boolean checkResponseOk();

    /**
     * 获取暂停命令
     * @return
     */
    protected abstract UMDB getPauseCmd();

    /**
     * 发送下一组命令
     */
    protected ProtocolCmd sendNextCmd() {
        if(!checkResponseOk()) {
            return null;
        }
        ProtocolCmd currentCmd = getCurrentCmd();
        ProtocolCmd nextCmd = getNextCmd();
        ProtocolCmd checkRpmCmd = null;
        if(BrandConfig.DEVICE_MODEL.equals(BrandConfig.MODEL_SANYANG) || BrandConfig.DEVICE_MODEL.equals(BrandConfig.MODEL_TCL) || BrandConfig.DEVICE_MODEL.equals(BrandConfig.MODEL_EV6106) || BrandConfig.DEVICE_MODEL.equals(BrandConfig.MODEL_HISENSE)) {
            checkRpmCmd = nextCmd;
        }else if(BrandConfig.DEVICE_MODEL.equals(BrandConfig.MODEL_XIAOTIANE)) {
            checkRpmCmd = currentCmd;
        }
        if(mCheckResponse.getRpm() > 0 && checkRpmCmd.isRmpNeedZero()) {
            MLog.d(TAG, "速度不为0，不发下个命令");
            return null;
        }
        mCheckResponse.setRpm(0);// = new CheckResponse();
        mCheckResponse.setRpmZero(false);
        mCheckResponse.setRpmOk(false);
        mRunTime = 0;
        mPosition++;
        currentCmd = getCurrentCmd();
        if(mPosition >= mCmdList.size()) {
            if(mProtocolListener != null) {
                mProtocolListener.onSuccess();
            }
        }else {
            MLog.d(TAG, "send umdb:" + currentCmd.getName());
            if(mProtocolListener != null) {
                mProtocolListener.onSendCmd(currentCmd.getUmdb(), currentCmd.getCircleDirect());
            }
        }
        return checkRpmCmd;
    }

    @Override
    public void onTick(int step) {
        if(!isReset && isStart) {
            if(mProtocolListener != null) {
                String error = mProtocolListener.getUMDBValue(UMDB.PadCommErr);
                if(!("1".equals(error))) {
                    mResponseTime = 0;
                }
            }
            mRunTime += step;
            mResponseTime += step;
            if(mResponseTime >= RESPONSE_TIME_OUT) {
                mResponseTime = 0;
                if(mProtocolListener != null) {
                    MLog.d(TAG, "---->mResponseTime="+mResponseTime);
                    mProtocolListener.onError(true, "无通讯");
                }
            }
            //MyLog.d(TAG, "mRunTime="+mRunTime+", getCurrentCmd().getTime()="+getCurrentCmd().getTime());
            if(mRunTime >= getCurrentCmd().getTime()) {
                sendNextCmd();
            }
        }
    }

    public void release() {
        reset();
        CountTimerUtil.getInstance().unregisterListener(this);
    }

    protected void temperatureChange(String temperature) {
        if(mProtocolListener != null) {
            mProtocolListener.onTemperatureChange(temperature);
        }
    }

    protected void rpmChange(String rpm) {
        if(mProtocolListener != null) {
            mProtocolListener.onRpmChange(rpm);
        }
    }

    protected void voltageChange(String voltage) {
        if(mProtocolListener != null) {
            mProtocolListener.onVoltageChange(voltage);
        }
    }

    protected void powerChange(String power) {
        if(mProtocolListener != null) {
            mProtocolListener.onPowerChange(power);
        }
    }

    protected void electricityChange(String electricity) {
        if(mProtocolListener != null) {
            mProtocolListener.onElectricityChange(electricity);
        }
    }

    protected void versionChange(String version) {
        if(mProtocolListener != null) {
            mProtocolListener.onVersionChange(version);
        }
    }
}
