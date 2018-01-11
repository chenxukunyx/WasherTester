package com.unilife.common.receiver;

import com.unilife.common.entities.UMDB;

/**
 * Created by Kevin on 2015/7/9.
 */
public interface IReceiverListener {
    void OnReceive(Object from, UMDB db);
    void OnUIUpdateRequest(int moduleId);
}
