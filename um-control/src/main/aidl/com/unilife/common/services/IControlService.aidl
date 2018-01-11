// IControlService.aidl
package com.unilife.common.services;
import com.unilife.common.entities.UMDB;

// Declare any non-default types here with import statements

interface IControlService {
    void update(String key, String value);
    void updateDB(in UMDB db);
    void getAll(out UMDB db);
    String getValue(in String key);
    int[] getSendBytes();
    int[] getRecvBytes();
    void start();
    void stop();
}
