package com.unilife.common.protocol;

import com.unilife.common.entities.EntityDefine;
import com.unilife.common.entities.UMDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kevin on 2015/7/10.
 */
public abstract class IBaseProtocol<T extends EntityDefine> {
    public IBaseProtocol(){
        initDefines();
    }
    public abstract UMDB toUMDB(Object obj);
    abstract Object toBuffer(UMDB db);
    public abstract void initDefines();

    private  List<T> m_defineLst = new ArrayList<>();

    protected void addDefine(T define)
    {
        m_defineLst.add(define);
    }

    public List<T> getDefineLst(){
        return m_defineLst;
    }

    public void sortDefine(){
        Collections.sort(m_defineLst);
    }
    public String getDisplayValue(EntityDefine define, int value) {
        String display = String.valueOf(value);
        if (define.getConverter() != null)
        {
            display = define.getConverter().toDisplay(value);
        }
        return display;
    }
}
