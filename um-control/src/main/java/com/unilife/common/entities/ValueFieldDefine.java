package com.unilife.common.entities;

import com.unilife.common.converter.IDisplayConverter;

/**
 * Created by Kevin on 2015/7/14.
 */
public class ValueFieldDefine extends EntityDefine{
    public Object getValueDefine() {
        return m_valueDefine;
    }

    public void setValueDefine(Object valueDefine) {
        this.m_valueDefine = valueDefine;
    }

    private Object m_valueDefine;

    public ValueFieldDefine(Object valueDefine, String UMDBKey, IDisplayConverter converter) {
        super(UMDBKey, converter);
        setValueDefine(valueDefine);
    }

    public ValueFieldDefine(Object valueDefine, String UMDBKey) {
        super(UMDBKey);
        setValueDefine(valueDefine);
    }
}