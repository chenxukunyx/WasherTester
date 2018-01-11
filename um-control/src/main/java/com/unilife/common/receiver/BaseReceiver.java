package com.unilife.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.unilife.common.entities.UMDB;

/**
 * Created by nicholasyu on 7/8/15.
 */
public abstract class BaseReceiver extends BroadcastReceiver implements IUMReceiver {

    protected IReceiverListener m_listener;

    public void setReceiverListener(IReceiverListener listener){
        m_listener = listener;
    }

    public void startReceive(Context context)
    {
        IntentFilter filter = new IntentFilter();
        for (String messageId : getActionId())
        {
            filter.addAction(messageId);
        }
        context.registerReceiver(this, filter);
    }

    protected abstract String[] getActionId();

    protected abstract UMDB receiveMsgToDB(String actionId, Context context, Intent intent);

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UMDB db = receiveMsgToDB(action, context, intent);
        if (db != null && !db.IsEmpty() && m_listener != null)
        {
            m_listener.OnReceive(this, db);
        }
    }
}
