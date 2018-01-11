package com.unilife.common.ui.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;

import com.unilife.common.entities.UMDB;
import com.unilife.common.sender.UISender;


/**
 * Created by Kevin on 2015/7/16.
 */
public class UMUIReceiver extends BroadcastReceiver{
    public final static int UMDB_UPDATE_MESSAGE = 0x1;
    public final static int UI_REFRESH_MESSAGE = 0x2;
    final static int OTA_FORCE_UPDATE_MESSAGE = 0x03;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (UISender.UI_REFRESH_ACTION.equals(action))
        {
            int moduleId = intent.getIntExtra("moduleId", 0);
            m_handler.dispatchMessage(Message.obtain(m_handler, UI_REFRESH_MESSAGE, moduleId));
        }
        else if (UISender.UMDB_UPDATE_ACTION.equals(action))
        {
            UMDB db = (UMDB) intent.getExtras().get("umdb");
            m_handler.dispatchMessage(Message.obtain(m_handler, UMDB_UPDATE_MESSAGE, db));
        }
    }
    private Context m_context;
    private Handler m_handler;
    public void start(Context context, Handler handler)
    {
        m_context = context;
        m_handler = handler;
        IntentFilter itf = new IntentFilter();
        itf.addAction(UISender.UMDB_UPDATE_ACTION);
        itf.addAction(UISender.UI_REFRESH_ACTION);
        context.registerReceiver(this, itf);
    }

    public void stop()
    {
        m_context.unregisterReceiver(this);
    }
}
