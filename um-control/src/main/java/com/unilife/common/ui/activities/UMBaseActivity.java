package com.unilife.common.ui.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.unilife.common.entities.UMDB;
import com.unilife.common.services.IControlService;
import com.unilife.common.ui.receivers.UMUIReceiver;

import java.util.HashMap;


/**
 * Created by Kevin on 2015/7/16.
 */
public abstract class UMBaseActivity extends Activity {
    public final static String TAG = "UMBaseActivity";
    /**
     * 模块ID 主程序
     */
    public final static int MODULE_ID_MAIN = 0x00200000;

    /**
     * 响应控制服务过来的使用UMDB的数据刷新界面（控制部分）
     *
     * @param db
     */
    protected abstract void onNewUMDbData(UMDB db);

    protected abstract void onNewUpdateRequest(int moduleId);


    Handler m_handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case UMUIReceiver.UI_REFRESH_MESSAGE: {
                    break;
                }
                case UMUIReceiver.UMDB_UPDATE_MESSAGE: {
                    UMDB umdb = (UMDB) msg.obj;
                    if (umdb != null) {
                        onNewUMDbData(umdb);
                    }
                    break;
                }
            }
        }
    };
    private IControlService m_remoteServer;

    public UMBaseActivity() {
    }

    UMUIReceiver receiver = new UMUIReceiver();
    ;

    @Override
    protected void onResume() {
        super.onResume();
        //bindService(new Intent("com.unilife.fridge.suning.services.SuningControlService"), conn, Service.BIND_AUTO_CREATE);
        Intent intent = new Intent(IControlService.class.getName());
        intent.setPackage(getPackageName());
        if (!bindService(intent, conn, Context.BIND_AUTO_CREATE)) {
            Log.e(TAG, "BindServer failed!");
        }
        receiver.start(this, m_handler);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            m_remoteServer = IControlService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            m_remoteServer = null;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(conn);

        receiver.stop();
    }

    public int[] getSendBytes() {
        try {
            return m_remoteServer.getSendBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    public int[] getRecvBytes() {
        try {
            return m_remoteServer.getRecvBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    public void update(String key, Object newValue) {
        try {
            m_remoteServer.update(key, (newValue == null) ? "0" : newValue.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDb(UMDB umdb) {
        try {
            m_remoteServer.updateDB(umdb == null ? new UMDB() : umdb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(UMDB db) {
        try {
            m_remoteServer.updateDB(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HashMap<String, Object> newValues) throws Exception {
        throw new Exception("Not implemented");
    }

    private static UMDB m_db = new UMDB();

    public final UMDB getStatusAll() {
        m_db.clear();
        if (m_remoteServer != null) {
            try {
                m_remoteServer.getAll(m_db);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return m_db;
    }

    protected abstract int hasModuleId();

    public void startActivity(Class<? extends Activity> atyClass) {
        Intent it = new Intent(this, atyClass);
        startActivity(it);
    }


    public String getValue(String key) {
        try {
            return m_remoteServer.getValue(key);
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }
}
