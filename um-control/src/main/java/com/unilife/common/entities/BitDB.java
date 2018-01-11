package com.unilife.common.entities;

import com.unilife.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicholasyu on 7/9/15.
 */
public class BitDB{

    public static final int UPDATE_TO_UI = 1;
    public static final int UPDATE_TO_SERVER = 2;
    public static final int REPORT_FROM_SERIAL = 4;
    public static final int REPORT_FROM_SERVER = 8;

    protected HashMap<String, String> m_db = new HashMap<String, String>();
    protected static HashMap<String, Integer> m_updateTo = new HashMap<String, Integer>();

    public boolean containValue(String name)
    {
        return m_db.containsKey(name);
    }
    public boolean isEmpty(){
        return m_db == null || m_db.isEmpty();
    }

    public static boolean isUpdateToEmpty() {
        return m_updateTo==null || m_updateTo.isEmpty();
    }

    public static int setUpdateTo(String name, int value) {
        m_updateTo.put(name, value);
        return value;
    }

    public static int getUpdateTo(String name) {
        if (m_updateTo.containsKey(name))
            return m_updateTo.get(name);
        else
            return 0;
    }
    /**
     * add key/value to db
     * @param name : key name
     * @param value : value string
     * @return
     */
    public int addValue(String name, String value)
    {
        m_db.put(name, value);
        return 0;
    }

    /**
     * add key/value to db
     * @param name : key name
     * @param value : value string
     * @return
     */
    public int addValue(String name, long value)
    {
        m_db.put(name, ""+value);
        return 0;
    }

    /**
     * add key/value to db
     * @param name : key name
     * @param value : value string
     * @return
     */
    public int addValue(String name, float value)
    {
        m_db.put(name, String.valueOf(value));
        return 0;
    }

    /**
     * remove key/value from db
     * @param name : key name
     * @return
     */
    public int removeValue(String name)
    {
        if (m_db.remove(name) == null)
            return -1;
        return 0;
    }

    /**
     * set new value
     * @param name : key name
     * @param value : value string
     * @return
     */
    public int setValue(String name, String value)
    {
        /**
        if (m_db.containsKey(name)) {
            m_db.put(name, value);
            return 0;
        } else {
            return -1;
        }*/
        m_db.put(name, value);
        return 1;
    }

    public int setValue(String name, long value)
    {
        return setValue(name, "" + value);
    }

    public int setValue(String name, float value)
    {
        return setValue(name, String.valueOf(value));
    }

    /**
     * get value from UMDB map
     * @param name : key name
     * @return : value string
     */
    public String getValue(String name)
    {
        if (m_db.containsKey(name)) {
            return m_db.get(name);
        } else {
            return "";
        }
    }

    /**
     * get value from UMDB map
     * @param name : key name
     * @return : value string
     */
    public long getIntValue(String name)
    {
        if (m_db.containsKey(name))
            return (long) StringUtils.parseFloat(m_db.get(name));
        else
            return -1;
    }

    public int getIntegerValue(String name) {
        if (m_db.containsKey(name))
            return StringUtils.parseInt(m_db.get(name));
        else
            return -1;
    }

    /**
     * get value from UMDB map
     * @param name : key name
     * @return : value string
     */
    public float getFloatValue(String name)
    {
        if (m_db.containsKey(name)) {
            return StringUtils.parseFloat(m_db.get(name));
        } else {
            return -1;
        }
    }

    /**
     * get value in db and compare with input value
     * @param name
     * @param value
     * @return true/false
     */
    public boolean equalValue(String name, String value) {
        if (m_db.containsKey(name)) {
            if (m_db.get(name).equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get db map
     * @return
     */
    public Map<String,String> getDb()
    {
        return m_db;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof BitDB)
        {
            return equals((BitDB)o);
        }
        return false;
    }

    public boolean equals(BitDB other)
    {
        for (String key : m_db.keySet() )
        {
            if (other.getValue(key) == null && getValue(key) == null)
            {
                continue;
            }
            if (!other.getValue(key).equals(this.getValue(key)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean contains(BitDB other, boolean autoSet)
    {
        boolean ret = true;
        for (String key : other.m_db.keySet() )
        {
            if (other.getValue(key) == null && getValue(key) == null)
            {
                continue;
            }
            if (!other.getValue(key).equals(this.getValue(key)))
            {
                if (autoSet)
                    other.setValue(key, this.getValue(key));
                ret = false;
            }
        }
        return ret;
    }

    public boolean IsEmpty(){
        return m_db == null || m_db.isEmpty();
    }

    public BitDB getUpdateDB(int updateTo, BitDB db) {
        for (String key : m_db.keySet() )
        {
            if ((getUpdateTo(key) & updateTo) == updateTo)
            {
                db.addValue(key, getValue(key));
            }
        }
        return db;
    }

    public void From(BitDB other)
    {
        m_db.putAll(other.m_db);
    }

    public void From(BitDB other, BitDB update, int updateTo)
    {
        m_db.putAll(other.getUpdateDB(updateTo, update).getDb());
    }

    public boolean MergeFrom(BitDB other, boolean bOverWrite)
    {
        if (other == null || other.m_db == null || other.m_db.isEmpty())
        {
            return false;
        }
        boolean bMerged = false;
        for (String key : other.m_db.keySet())
        {
            if (!this.m_db.containsKey(key))
            {
                bMerged = true;
                this.m_db.put(key, other.m_db.get(key));
            }
            else if (!this.m_db.get(key).equals(other.m_db.get(key)) && bOverWrite)
            {
                bMerged = true;
                this.m_db.put(key, other.m_db.get(key));
            }
        }

        return bMerged;
    }

    public boolean isEqualExisted(BitDB other)
    {
        if (other == null)
        {
            return false;
        }
        for (String key : other.m_db.keySet())
        {
            if (!m_db.containsKey(key) || !m_db.get(key).equals(other.m_db.get(key)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean isEqual(BitDB other, List<String> maskKeys)
    {
        for (String key : maskKeys)
        {
            if (!other.m_db.containsKey(key) || !m_db.get(key).equals(other.m_db.get(key)))
            {
                return false;
            }
        }
        return true;
    }

    public void mergeTo(BitDB other, List<String> maskKeys)
    {
        for (String key : maskKeys)
        {
            if (m_db.containsKey(key))
            {
                other.setValue(key, getValue(key));
            }
        }
    }

    public void removeValues(List<String> maskKeys)
    {
        for (String key : maskKeys)
        {
            if (m_db.containsKey(key))
            {
                removeValue(key);
            }
        }
    }

    public void clear(){
        m_db.clear();
    }
}
