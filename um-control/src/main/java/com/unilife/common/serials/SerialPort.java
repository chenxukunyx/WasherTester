package com.unilife.common.serials;

import android.os.Message;
import android.util.Log;

import com.unilife.common.entities.UMDB;
import com.unilife.common.receiver.IReceiverListener;

/**
 * Created by nicholasyu on 7/8/15.
 */
public class SerialPort {

    private final String TAG = "SerialPort";

    private final int MSG_DATA_RECEIVED = 1;
    private final int MSG_DATA_ERR_REPORT = 2;
    private final int MSG_DATA_ERR_UPDATE = 3;

    private SerialProtocol m_protocol = null;
    private Thread m_thread = null;
    private SerialTransmitter m_transmiter = null;
    private IReceiverListener m_receiveListener = null;
    private int[] m_data = new int[64];
    private int[] m_ack = new int[64];
    private comfifo m_fiford = new comfifo(512);
    private comfifo m_fifowr = new comfifo(512);
    private boolean m_exit = false;
    private boolean m_wr = false;
    private long m_curTime = 0;
    private long m_deltaTime = 0;
    private long m_circleTime = 0;
    private long m_gapTime = 0;
    private int m_data_len = 0;
    private int m_ack_len = 0;
    private int m_ack_real_len = 0;
    private long m_commTimeout = 0;
    private boolean m_isSimulate = false;

    private long m_chksumErrCounter = 0;// 连续校验失败次数
    private long m_responseCounter = 0;// 主控板反馈次数
    private long m_noResponseCounter = 0;// 主控板无反馈次数
    private long m_commCounter = 0;// 通信总次数
    private long m_commSlaveCounter = 0;// Slave模式通信总次数，用户故障时计数

    private long m_port_time;// debug timer

    public boolean isSimulate(){
        return m_isSimulate;
    }

    public SerialPort(SerialProtocol protocol)
    {
        setProtocol(protocol);
    }

    /**
     * set serial port communication protocol
     * @param protocol
     */
    void setProtocol(SerialProtocol protocol)
    {
        m_protocol = protocol;
    }

    /**
     * read data from serial port
     * @return: 0: read 0 bytes, -1: chksum err, >0: read bytes number
     */
    int portRead()
    {
        int ret = -1;
        String vstr = "";
        Message msg = Message.obtain();
        msg.obj = this;

        m_ack[0] = 0;
        ret = m_transmiter.read(m_ack, m_ack_len);
        if (ret == 0) {
            //Log.d(TAG, String.format("READ PORT, SIZE ==>0"));
            //m_wr = false;
            return 0;
        }

//        Log.d(TAG, "size="+ret+" time=" + System.currentTimeMillis());
//        for (int i=0; i<ret; i++) {
//            vstr += String.format(" %x", m_ack[i]);
//        }
//        Log.d(TAG, vstr);

        if (m_isSimulate) {
            ret = m_protocol.simulate(m_ack, m_data);
        }

        m_fiford.write(m_ack, ret);
        ret = m_protocol.parseFrame(m_fiford, m_ack, m_ack_len);

//        vstr = "parse[] = ";
        for (int i=0; i<ret; i++) {
            vstr += String.format(" %x", m_ack[i]);
        }
//        Log.d(TAG, vstr);

        if (ret == 0) {
            //Log.d(TAG, String.format("NO FRAME"));
            //m_wr = false;
            return ret;
        } else if (!m_protocol.chkChksum(m_ack, ret)) {
            Log.d(TAG, String.format("ACK CHKSUM ERR"));
            m_chksumErrCounter ++;
            if (m_chksumErrCounter > m_commTimeout/m_circleTime) {
                Log.d(TAG, String.format("CHKSUM ERR = " + m_chksumErrCounter));
                ackMsgHandler(MSG_DATA_ERR_REPORT, ret);
            } else {
                ackMsgHandler(MSG_DATA_ERR_UPDATE, ret);
            }
            ret = 0;
        } else {
            m_chksumErrCounter = 0;
            m_noResponseCounter = 0;
            m_responseCounter = m_commCounter;
            m_ack_real_len = ret;
            ackMsgHandler(MSG_DATA_RECEIVED, ret);
        }

        if (m_port_time == 0)
            m_port_time = System.currentTimeMillis();
        vstr = "recv[" + (System.currentTimeMillis() - m_port_time) + "] = " + vstr;
        if (m_protocol.getSerialConfig().getHostOrSlave() == 0)
            m_port_time = System.currentTimeMillis();

        Log.d(TAG, vstr);
        Log.d(TAG, "-------------------------------------------");
        m_wr = false;
        return ret;
    }

    /**
     * write data to serial port
     * @return
     */
    int portWrite()
    {
        int ret;
        String vstr = "";

        if (m_protocol.getSerialConfig().getMultiFrames() == 0) {
            m_protocol.calcChksum(m_data, m_data_len);
            ret = m_data_len;
        } else {
            ret = m_protocol.parseFrame(m_fifowr, m_data, m_data_len);
            if (ret > 0)
                m_protocol.calcChksum(m_data, ret);
        }
        if (ret > 0) {
            for (int i=0; i<ret; i++) {
                vstr += String.format("%x ", m_data[i]);
            }

            if (m_port_time == 0)
                m_port_time = System.currentTimeMillis();
            vstr = "send[" + (System.currentTimeMillis() - m_port_time) + "] = " + vstr;
            if (m_protocol.getSerialConfig().getHostOrSlave() == 1)
                m_port_time = System.currentTimeMillis();
            Log.d(TAG, vstr);
            Log.d(TAG, "-------------------------------------------");
            m_transmiter.write(m_data, ret);
        }

        m_wr = true;
        return ret;
    }

    /**
     * data responsed
     */
    void onResponse() {
        m_responseCounter ++;
    }

    /**
     * no data received
     */
    void noResponse() {
        long lNoResponse = m_commCounter - m_responseCounter;
        if (m_protocol.getSerialConfig().getHostOrSlave() == 0) {
            if (m_commCounter == 0 && m_responseCounter == 0) {
                lNoResponse = m_commSlaveCounter - m_responseCounter;
            }
        }
        if (lNoResponse > m_noResponseCounter) {
            if (lNoResponse % (m_commTimeout/m_circleTime) == 0) {
                Log.d(TAG, String.format("ERR TIMOUT"));
                ackMsgHandler(MSG_DATA_ERR_REPORT, m_ack_real_len);
            }
            if (lNoResponse % (4) == 0) {
                ackMsgHandler(MSG_DATA_ERR_UPDATE, m_ack_real_len);
            }
            m_noResponseCounter = lNoResponse;
        }
    }

    private void ackMsgHandler(int msg, int length) {
        UMDB db = new UMDB();
        switch (msg) {
            case MSG_DATA_RECEIVED:
            {
                if (m_receiveListener != null) {
                    if (m_protocol == null) {
                        Log.w(TAG, "Serial Protocol is null");
                        throw new IllegalArgumentException();
                    } else {
                        m_protocol.getRecvProtocol().toUMDB(m_ack, length, db);
                    }
                    if (!db.getValue(UMDB.PadCommErr).equals("1"))
                        db.addValue(UMDB.PadCommErr, 0);
                    else
                        db.addValue(UMDB.PadCommErr, 2);
                    db.addValue(UMDB.CommCount, m_commCounter);
                    m_receiveListener.OnReceive(this, db);
                }
                break;
            }
            case MSG_DATA_ERR_REPORT:
            {
                if (m_receiveListener != null) {
                    long totalCount;
                    if (m_protocol == null) {
                        Log.w(TAG, "Serial Protocol is null");
                        throw new IllegalArgumentException();
                    }
                    db.addValue(UMDB.PadCommErr, 1);
                    if (m_commCounter == 0)
                        totalCount = m_commSlaveCounter;
                    else
                        totalCount = m_commCounter;
                    db.addValue(UMDB.CommNoResponseCount, totalCount - m_responseCounter);
                    db.addValue(UMDB.CommChkSumErrCount, m_chksumErrCounter);
                    m_receiveListener.OnReceive(this, db);
                }
                break;
            }
            case MSG_DATA_ERR_UPDATE:
            {
                if (m_receiveListener != null) {
                    if (m_protocol == null) {
                        Log.w(TAG, "Serial Protocol is null");
                        throw new IllegalArgumentException();
                    }
                    db.addValue(UMDB.CommCount, m_commCounter);
                    db.addValue(UMDB.CommNoResponseCount, m_commCounter-m_responseCounter);
                    db.addValue(UMDB.CommChkSumErrCount, m_chksumErrCounter);
                    m_receiveListener.OnReceive(this, db);
                }
                break;
            }
        }
    }
    /**
     * start serail port communication
     */
    public void start()
    {
        if (m_protocol == null) {
            Log.w(TAG, "no Serial Protocol");
            throw new IllegalArgumentException();
        }
        if (m_protocol.getSerialConfig().getHostOrSlave() > 0)
            m_wr = false;
        else
            m_wr = true;
        m_data_len = m_protocol.getSerialConfig().getWRSZ();
        m_ack_len = m_protocol.getSerialConfig().getRDSZ();
        m_circleTime = m_protocol.getSerialConfig().getCircleTime();
        m_gapTime = m_protocol.getSerialConfig().getRWGapTime();
        m_commTimeout = m_protocol.getSerialConfig().getCommTimeout();
        m_isSimulate = m_protocol.getSerialConfig().isSimulate();
        m_ack_real_len = m_ack_len;

        m_curTime = System.currentTimeMillis();
        m_port_time = 0;

        m_transmiter = new SerialTransmitter();
        m_transmiter.init(m_protocol.getSerialConfig().getBateRate(),
                m_protocol.getSerialConfig().getBitWidth(),
                m_protocol.getSerialConfig().getIsParity(),
                m_protocol.getSerialConfig().getStopBit(),
                m_protocol.getSerialConfig().getFormat(),
                m_protocol.getSerialConfig().isSimulate());

        m_thread = new Thread(new Runnable(){

            public void run(){

                android.os.Process.setThreadPriority(-10);

                while (!m_exit) {
                    m_deltaTime = System.currentTimeMillis() - m_curTime;
                    if (m_deltaTime < 0) {
                        m_deltaTime = 0;
                        m_curTime = System.currentTimeMillis();
                    } else if (m_deltaTime >= m_circleTime*10) {
                        m_curTime += (m_deltaTime/m_circleTime-1)*m_circleTime;
                    }

                    if (m_protocol.getSerialConfig().getHostOrSlave() > 0) {
                        if (m_deltaTime > m_circleTime) {
                            m_curTime += m_circleTime;
                            portWrite();
                            m_commCounter ++;
                        } else if (m_deltaTime >= m_gapTime && m_wr) {
                            if (portRead() > 0) {
                                onResponse();
                            } else {
                                noResponse();
                            }
                        }
                    } else {
                        if (portRead() > 0) {
                            try {
                                Thread.sleep(m_gapTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            portWrite();
                            m_commCounter ++;
                            onResponse();
                        } else {
                            noResponse();
                        }
                        if (m_deltaTime > m_circleTime) {
                            m_curTime += m_circleTime;
                            m_commSlaveCounter++;
                        }
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        m_thread.start();

    }

    public void stop()
    {
        try {
            m_exit = true;
            m_thread.join(1000);
            m_transmiter.deinit();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * write db to serial port
     * @param db
     */
    public void write(UMDB db)
    {
        int[] data = new int[512];

        if (m_protocol == null)
        {
            Log.w(TAG, "Serial Protocol is null");
            throw new IllegalArgumentException();
        } else {
            if (m_protocol.getSerialConfig().getMultiFrames() == 0) {
                int len = m_protocol.getSendProtocol().toBuffer(db, m_data);
            } else {
                int len = m_protocol.getSendProtocol().toBuffer(db, data);
                if (len == 0)
                    len = m_data_len;
                m_fifowr.write(data, len);
            }
            //Log.d(TAG, db.getValue(UMDB.VarRoomClose));
        }
    }

    /**
     * read db from serial port
     * @param : none
     * @return : umdb
     */
    public UMDB read()
    {
        UMDB db = new UMDB();

        if (m_protocol == null)
        {
            Log.w(TAG, "Serial Protocol is null");
            throw new IllegalArgumentException();
        } else {
            m_protocol.getRecvProtocol().toUMDB(m_ack, m_ack_real_len, db);
        }
        return db;
    }

    /**
     * get send bytes array, normally for debug
     * @return
     */
    public int[] getSendBytes()
    {
        return m_data;
    }

    /**
     * get received bytes array, normally for debug
     * @return
     */
    public int[] getRecvBytes()
    {
        return m_ack;
    }

    /**
     * set receive listener
     * @param listener
     */
    public void setReceiveListener(IReceiverListener listener)
    {
        m_receiveListener = listener;
    }
}
