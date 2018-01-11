package com.unilife.common.managers;

import com.unilife.common.entities.UMDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by nicholasyu on 7/8/15.
 */
public class StatusManager {

    public static final int FROM_UI = 0;
    public static final int FROM_SERVER = 1;
    public static final int FROM_SERIAl = 2;

    /**
     *  mark value initialized status
     */
    private boolean m_initedValue = false;

    private List<String> m_changedKeys = new ArrayList<>();

    private StatusManager(){
        m_localdb = new UMDB();
        synchronized (m_localdb)
        {
            m_localdb.initDefault();
            initDBValues();
        }
    }

    /**
     * initialize local db value, only one time
     */
    private void initDBValues() {
        if (m_statusListener != null && !m_initedValue) {
            m_statusListener.onInitValue(m_localdb);
            m_initedValue = true;
        }
    }

    /**
     * ui status
     */
    private static StatusManager _uiStatus;

    /**
     * serial port status
     */
    private static StatusManager _hwStatus;

    public static synchronized StatusManager getUIStatus(){
        if (_uiStatus == null)
        {
            _uiStatus = new StatusManager();
        }
        return _uiStatus;
    }

    public static synchronized StatusManager getInstance(){
        if (_hwStatus == null)
        {
            _hwStatus = new StatusManager();
        }
        return _hwStatus;
    }

    UMDB m_localdb = null;

    public UMDB GetCurrentStatus(){
        synchronized (m_localdb)
        {
            return m_localdb;
        }
    }

    public String GetKeyValue(String key) {
        synchronized (m_localdb)
        {
            return m_localdb.getValue(key);
        }
    }

    IStatusManagerListener m_statusListener = null;

    public void setStatusManagerListener(IStatusManagerListener listener)
    {
        m_statusListener = listener;
        initDBValues();
    }

    public synchronized boolean flashDB(String key, String value, int from)
    {
        boolean dirty = false;

        synchronized (m_localdb)
        {
            if (!value.equals(m_localdb.getValue(key)))
            {
                m_localdb.setValue(key, value);
                dirty = true;
            }
        }

        if (dirty && m_statusListener != null)
        {
            m_statusListener.onChanged(m_localdb, from);
        }
        return dirty;
    }
    /**
     * flush new db to local
     * @param newdb
     * @return
     */
    public synchronized boolean flushDB(UMDB newdb, boolean forceFlush, int from)
    {
        boolean dirty = forceFlush;

        if (m_localdb == null) {
            m_localdb = new UMDB();
            synchronized (m_localdb)
            {
                m_localdb.getDb().putAll(newdb.getDb());
                initDBValues();
            }
            return true;
        }

        m_changedKeys.clear();

        Iterator it=newdb.getDb().entrySet().iterator();
        String key;
        String value;
        String lvalue;
        synchronized (m_localdb)
        {
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
                key=entry.getKey().toString();
                value = (String)entry.getValue();

                lvalue = m_localdb.getValue(key);
                if (lvalue != value && !lvalue.equals(value)) {
                    m_localdb.addValue(key, value);
                    if (!key.equals(UMDB.CommCount)) {
                        dirty = true;
                        m_changedKeys.add(key);
                    }
                }
            }
        }
        if (dirty && m_statusListener != null)
        {
            m_statusListener.onChanged(m_localdb, from);
        }

        return dirty;
    }

    public final List getChangedKeys() {
        return m_changedKeys;
    }

    public interface IStatusManagerListener {
        int onChanged(UMDB db, int from);
        int onInitValue(UMDB db);
    }

    //private static UMDB _total = new UMDB();
    public static UMDB getTotalStatus(){
        UMDB _total = new UMDB();
        // merge display status
        _total.From(getUIStatus().GetCurrentStatus());
        // merge pcb status
        _total.From(getInstance().GetCurrentStatus());
        return _total;
    }
}
