package com.miracle.app.base;


import com.miracle.app.config.WasherConfig;
import com.miracle.app.protocol.wolong.WolongRecvProtocol;
import com.miracle.app.protocol.wolong.WolongSendProtocol;
import com.unilife.common.serials.SerialProtocol;
import com.unilife.common.serials.comfifo;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午10:43
 */

public abstract class BaseProtocol extends SerialProtocol{

    public BaseProtocol() {
        setProtocol();
    }

    protected void setProtocol() {
        m_serial = WasherConfig.getWasherConfig().getSerialConfig();
        try {
            m_send = WasherConfig.getWasherConfig().getM_clsSendProtocol().newInstance();
            m_recv = WasherConfig.getWasherConfig().getM_clsRecvProtocol().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (m_send == null) {
                m_send = new WolongSendProtocol();
            }
            if (m_recv == null) {
                m_recv = new WolongRecvProtocol();
            }
        }
    }

    @Override
    public int calcChksum(int[] data, int size) {
        return calcVarietyChksum(data, size);
    }

    @Override
    public boolean chkChksum(int[] data, int size) {
        return chkVarietyChksum(data, size);
    }

    @Override
    public int parseFrame(comfifo frame, int[] data, int size) {
        return parseVarietyFrame(frame, data, size);
    }

    protected abstract int calcVarietyChksum(int[] data, int size);

    protected abstract boolean chkVarietyChksum(int[] data, int size);

    protected abstract int parseVarietyFrame(comfifo frame, int[] data, int size);
}
