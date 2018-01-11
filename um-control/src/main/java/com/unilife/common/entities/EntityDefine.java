package com.unilife.common.entities;

import com.unilife.common.converter.IDisplayConverter;

/**
 * Created by Kevin on 2015/7/10.
 */
public class EntityDefine implements Comparable<EntityDefine> {

    public EntityDefine(String UMDBKey, IDisplayConverter converter)
    {
        m_UMDBKey = UMDBKey;
        m_converter = converter;
    }

    public EntityDefine(String UMDBKey)
    {
        this(UMDBKey, null);
    }

    //public EntityDefine(){}

    public String getUMDBKey() {
        return m_UMDBKey;
    }

    public void setUMDBKey(String m_UMDBKey) {
        this.m_UMDBKey = m_UMDBKey;
    }

    public IDisplayConverter getConverter() {
        return m_converter;
    }

    public void setConverter(IDisplayConverter m_converter) {
        this.m_converter = m_converter;
    }

    private String m_UMDBKey;
    private IDisplayConverter m_converter;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o instanceof EntityDefine)
        {
            return compareTo((EntityDefine)o) == 0;
        }
        return false;
    }

    @Override
    public int compareTo(EntityDefine another) {
        return m_UMDBKey.compareTo(another.m_UMDBKey);
    }
}
