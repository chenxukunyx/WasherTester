package com.unilife.common.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;

import com.unilife.common.apps.UIBaseApplication;
import com.unilife.common.entities.BitDB;
import com.unilife.common.entities.UMDB;
import com.unilife.common.managers.DeviceManager;
import com.unilife.common.managers.StatusManager;
import com.unilife.common.receiver.BaseReceiver;
import com.unilife.common.receiver.IReceiverListener;
import com.unilife.common.receiver.IUMReceiver;
import com.unilife.common.sender.IUMSender;
import com.unilife.common.sender.UISender;
import com.unilife.common.serials.SerialPort;
import com.unilife.common.serials.SerialProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas.yu on 7/8/15.
 *
 */
public abstract class UMBaseControlService extends Service implements IControlService {

    private final static String TAG = "UMBaseControlService";
    private IUMReceiver m_receiver;
    private SerialPort m_serial;
    private IUMSender m_svrSender;
    private UISender m_uiSender;

    protected IUMSender getServerSender(){
        return m_svrSender;
    }
    protected IUMSender getUISender(){ return m_uiSender;}

    private List<String> m_sensorTemps = new ArrayList<>();
    private long m_curTime = System.currentTimeMillis();

    PowerManager.WakeLock m_CSWakeLock = null;

    private void initSensorKeys() {
    }

    @Override
    public void onCreate() {

        try {
            PowerManager mPowerManager = (PowerManager) UIBaseApplication.get_instance().getSystemService(getApplicationContext().POWER_SERVICE);
            m_CSWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "");
            m_CSWakeLock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DeviceManager.getInstance().initContext(getApplicationContext());
        Log.v(TAG, "UMBaseControlService@onCreate");
        if (m_receiver == null)
        {
            m_receiver = initReceiver();
            if (m_receiver != null) {
                m_receiver.setReceiverListener(m_receiverListener);
                m_receiver.startReceive(this);
                Log.v(TAG, "PushReceiver started...");
            }
        }
        m_uiSender = new UISender();
        if (m_uiSender != null) {
            m_uiSender.start();
        }
        m_serial = new SerialPort(getSerialProtocol());
        m_serial.setReceiveListener(m_receiverListener);
        m_serial.start();
        m_svrSender = initSvrSender();
        if (m_svrSender != null) {
            m_svrSender.start();
        }

        StatusManager.getUIStatus().setStatusManagerListener(m_localStatusManagerListener);
        StatusManager.getInstance().setStatusManagerListener(m_statusManagerListener);
        super.onCreate();
    }

    protected abstract IUMSender initSvrSender();

    protected abstract SerialProtocol getSerialProtocol();

    @Override
    /**
     *
     */
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // TO
        super.onStartCommand(intent, flags, startId);
        // 服务被中止后会自动重启（系统管理)
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (m_serial != null) {
            m_serial.stop();
            m_serial = null;
        }

        try {
            if (m_CSWakeLock != null) {
                m_CSWakeLock.release();
                m_CSWakeLock = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return m_binder;
    }


    @Override
    public IBinder asBinder() {
        return m_binder;
    }

    protected abstract BaseReceiver initReceiver();

    protected int onStatusInit(UMDB db){
        return 0;
    }

    protected void onSerialPreWrite(UMDB db){}
    protected void onSerialReceived(UMDB db){
        //降低传感器温度上报频率
        if (System.currentTimeMillis() - m_curTime > 10*1000
                || System.currentTimeMillis() < m_curTime) {
            m_curTime = System.currentTimeMillis();
            // 上报传感器温度
        } else {
            // 不上报传感器温度
            db.removeValues(m_sensorTemps);
        }
    }

    protected void onRemoteCtrlReceived(UMDB db) {}
    protected UMDB onRemoteCtrlSend(UMDB db) {return db;}

    /**
     * receiver Listener接口
     */
    IReceiverListener m_receiverListener = new IReceiverListener(){

        @Override
        public void OnReceive(Object obj, UMDB db) {
            if (obj == m_serial)
            {
                /**
                 * 收到串口数据，更新串口数据db
                 */
                //onSerialReceived(db);
                //wanql edit true
                StatusManager.getInstance().flushDB(db, false, StatusManager.FROM_SERIAl);
            }
            else
            {
                /**
                 * 收到服务器数据，更新UI数据db
                 */
                onRemoteCtrlReceived(db);
                StatusManager.getUIStatus().flushDB(db, false, StatusManager.FROM_SERVER);
                m_serial.write(db);
            }
        }

        @Override
        public void OnUIUpdateRequest(int moduleId) {
            if (m_uiSender != null) {
                m_uiSender.sendUIUpdateRequest(UMBaseControlService.this, moduleId);
            }
        }
    };

    /**
     * UI status manager listener 接口
     */
    StatusManager.IStatusManagerListener m_localStatusManagerListener = new StatusManager.IStatusManagerListener() {
        @Override
        public synchronized int onChanged(UMDB db, int from) {
            Log.d(TAG, "UI onChanged");
            /**
             * 更新界面
             */
            if (m_uiSender != null) {
                if (from == StatusManager.FROM_SERIAl) {
                    /**
                     * 串口数据上报UI需要过滤
                     */
                    UMDB svdb = new UMDB();
                    svdb = (UMDB)db.getUpdateDB(BitDB.UPDATE_TO_UI, svdb);
                    m_uiSender.send(UMBaseControlService.this, svdb);
                } else {
                    m_uiSender.send(UMBaseControlService.this, db);
                }
            }

            /**
             * 上报服务器
             */
            if (m_svrSender != null) {
                UMDB svdb = new UMDB();
                /**
                 * 过滤需要上报的数据
                 */
                svdb = (UMDB)db.getUpdateDB(BitDB.UPDATE_TO_SERVER, svdb);
                if (!svdb.isEmpty()) {
                    onRemoteCtrlSend(svdb);
                    m_svrSender.send(UMBaseControlService.this, svdb);
                }
            }
            return 0;
        }

        @Override
        public int onInitValue(UMDB db) {
            return onStatusInit(db);
        }
    };

    /**
     * 串口 status manager listener 接口
     */
    StatusManager.IStatusManagerListener m_statusManagerListener = new StatusManager.IStatusManagerListener() {
        @Override
        public synchronized int onChanged(UMDB db, int from) {
            Log.d(TAG, "SerialPort onChanged");

            UMDB updateDB = new UMDB();

            /**
             * 获取需要从设备更新到UI和SERVER的状态数据
             */
            db.getUpdateDB(BitDB.REPORT_FROM_SERIAL| BitDB.UPDATE_TO_SERVER, updateDB);
            db.getUpdateDB(BitDB.REPORT_FROM_SERIAL| BitDB.UPDATE_TO_UI, updateDB);

            /**
             * 更新UIDB
             */
            //wanql edit true
            StatusManager.getUIStatus().flushDB(updateDB, false, from);
            return 0;
        }

        @Override
        public int onInitValue(UMDB db) {
            return onStatusInit(db);
        }
    };

    private IControlService.Stub m_binder = new IControlService.Stub()
    {
        @Override
        public void update(String key, String value) throws RemoteException {
            if (m_serial != null) {
                UMDB db = new UMDB();
                db.setValue(key, value);
                updateDB(db);
            }
        }

        @Override
        public void updateDB(UMDB db) throws RemoteException {
            if (m_serial != null) {
                onSerialPreWrite(db);
                StatusManager.getUIStatus().flushDB(db, false, StatusManager.FROM_UI);
                m_serial.write(db);
            }
        }

        @Override
        public void getAll(UMDB db) throws RemoteException {
            db.clear();
            db.From(StatusManager.getUIStatus().GetCurrentStatus());
        }

        @Override
        public String getValue(String key) throws RemoteException {
            return StatusManager.getUIStatus().GetCurrentStatus().getValue(key);
        }

        @Override
        public int[] getSendBytes() throws RemoteException {
            if (m_serial != null) {
                return m_serial.getSendBytes();
            }
            return new int[1];
        }

        @Override
        public int[] getRecvBytes() throws RemoteException {
            if (m_serial != null) {
                return m_serial.getRecvBytes();
            }
            return new int[1];
        }

        @Override
        public void start() throws RemoteException {
            if (m_serial == null) {
                m_serial = new SerialPort(getSerialProtocol());
                m_serial.setReceiveListener(m_receiverListener);
                m_serial.start();
            }
        }

        @Override
        public void stop() throws RemoteException {
            if (m_serial != null) {
                m_serial.stop();
                m_serial = null;
            }
        }
    };

}
