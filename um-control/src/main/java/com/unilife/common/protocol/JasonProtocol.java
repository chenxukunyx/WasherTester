package com.unilife.common.protocol;

import com.unilife.common.entities.EntityDefine;
import com.unilife.common.entities.UMDB;

/**
 * Created by Kevin on 2015/7/10.
 */
public class JasonProtocol extends IBaseProtocol<EntityDefine> {
    @Override
    public UMDB toUMDB(Object obj) {
        return null;
    }

    @Override
    public Object toBuffer(UMDB db) {
        return null;
    }

    @Override
    public void initDefines() {

    }
}
