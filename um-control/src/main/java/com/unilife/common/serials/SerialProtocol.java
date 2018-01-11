package com.unilife.common.serials;

import com.unilife.common.converter.IDataFrameConverter;
import com.unilife.common.protocol.StreamProtocol;

/**
 * Created by nicholas on 2015/7/14.
 */
public class SerialProtocol implements IDataFrameConverter {
    protected SerialConfig m_serial = null;
    protected StreamProtocol m_send = null;
    protected StreamProtocol m_recv = null;

    public SerialConfig getSerialConfig()
    {
        return m_serial;
    }

    public StreamProtocol getSendProtocol()
    {
        return m_send;
    }

    public StreamProtocol getRecvProtocol()
    {
        return m_recv;
    }

    @Override
    public int calcChksum(int[] data, int size) {
        return 0;
    }

    @Override
    public boolean chkChksum(int[] data, int size) {
        return false;
    }

    @Override
    public int simulate(int[] src, int[] dst) {
        return 0;
    }

    @Override
    public int parseFrame(comfifo frame, int[] data, int size) {
        return -1;
    }

    @Override
    public int getFrameLength(int[] data) {
        return 0;
    }

    @Override
    public long getFrameHead() {
        return 0;
    }

    public boolean isSimulate() {
        if (m_serial != null)
            return m_serial.isSimulate();
        else
            return false;
    }
}
