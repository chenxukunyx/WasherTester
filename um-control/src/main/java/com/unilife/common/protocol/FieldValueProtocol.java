package com.unilife.common.protocol;

import android.util.Pair;

import com.unilife.common.converter.IDisplayConverter;
import com.unilife.common.entities.UMDB;
import com.unilife.common.entities.ValueFieldDefine;

/**
 * Created by Kevin on 2015/7/14.
 */
public abstract class FieldValueProtocol extends IBaseProtocol<ValueFieldDefine> {
    @Override
    public UMDB toUMDB(Object obj) {

        Pair<Object, Integer> pair = null;
        if (obj instanceof char[])
        {
            char[] buffer = (char[])obj;
            pair = new Pair<>((Object)buffer[0], (int)buffer[1]);
        }
        else if (obj instanceof byte[])
        {
            byte[] buffer = (byte[])obj;
            pair = new Pair<>((Object)buffer[0], (int)buffer[1]);
        }
        else if (obj instanceof Pair)
        {
            pair = (Pair<Object, Integer>) obj;
        }
        if (pair == null)
        {
            throw new IllegalArgumentException("param of value-field must be pair");
        }
        Object field = pair.first;
        int value = pair.second;

        UMDB db = new UMDB();


        for (ValueFieldDefine define : getDefineLst())
        {
            if (field == define.getValueDefine() || field.equals(define.getValueDefine()))
            {
                String strValue = getDisplayValue(define, value);
                db.setValue(define.getUMDBKey(), strValue);
                break;
            }
        }
        return db;
    }



    @Override
    Object toBuffer(UMDB db) {
        return null;
    }

    protected  void addDefine(Object valueDefine, String UMDBKey, IDisplayConverter converter)
    {
        ValueFieldDefine define = new ValueFieldDefine(valueDefine, UMDBKey, converter);
        addDefine(define);
    }

    protected  void addDefine(Object valueDefine, String UMDBKey)
    {
        addDefine(valueDefine, UMDBKey, null);
    }
}
