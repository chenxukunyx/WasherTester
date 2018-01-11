package com.unilife.common.serials;

/**
 * Created by nicholasyu on 7/8/15.
 */
public class SerialConfig {

    private final static String TAG = "SerialConfig";

    protected int m_bateRate = 9600;
    protected int m_isParity = 0;
    protected int m_stopBit = 1;
    protected int m_bitWidth = 8;
    protected int m_format = 0; // 0-lsb, 1-msb
    protected int m_circleTime = 100;
    protected int m_rwGapTime = 20;
    protected int m_hostOrSlave = 0;
    protected int m_commTimeout = 30000; // 30s not response is communication error
    protected int m_rdsz = 0;
    protected int m_wrsz = 0;
    protected int m_multiframes = 0;
    protected boolean m_simulate = false;

    /**
     * set serial port hardware parameters
     * @param bautRate : bautrate
     * @param isParity : 'n' - no parity, 'e' - even parity, 'o' - odd parity
     * @param stopBit : 1 - has stop bit, 0 - no stop bit
     * @param bitWidth : number of bits
     * @param msb : 0 - lsb, 1 - msb
     */
    public void setPort(int bautRate, int isParity, int stopBit, int bitWidth, int msb)
    {
        m_bateRate = bautRate;
        m_isParity = isParity;
        m_stopBit = stopBit;
        m_bitWidth = bitWidth;
        m_format = msb;
    }

    /**
     * set communication parameters
     * @param circleTime : one communication session circle time, milliseconds
     * @param rwGapTime : time between read and write, milliseconds
     * @param isHost : 1 - host, 0 - slave
     * @param readlen :  read data length of bytes
     * @param writelen : write data length of bytes
     */
    public void setComm(int circleTime, int rwGapTime, int isHost, int readlen, int writelen, int timeout)
    {
        m_circleTime = circleTime;
        m_rwGapTime = rwGapTime;
        m_hostOrSlave = isHost;
        m_rdsz = readlen;
        m_wrsz = writelen;
        m_commTimeout = timeout;
    }

    public void setLength(int rdsz, int wrsz) {
        m_rdsz = rdsz;
        m_wrsz = wrsz;
    }

    public boolean isSimulate()
    {
        return m_simulate;
    }

    public int getBateRate()
    {
        return m_bateRate;
    }

    public int getFormat() { return m_format;}

    public int getIsParity()
    {
        return m_isParity;
    }

    public int getStopBit()
    {
        return m_stopBit;
    }

    public int getBitWidth()
    {
        return m_bitWidth;
    }

    public int getCircleTime()
    {
        return m_circleTime;
    }

    public int getRWGapTime()
    {
        return m_rwGapTime;
    }

    public int getRDSZ()
    {
        return m_rdsz;
    }

    public int getWRSZ()
    {
        return m_wrsz;
    }

    public int getHostOrSlave()
    {
        return m_hostOrSlave;
    }

    public int getCommTimeout()
    {
        return m_commTimeout;
    }

    public int getMultiFrames()
    {
        return m_multiframes;
    }

}
