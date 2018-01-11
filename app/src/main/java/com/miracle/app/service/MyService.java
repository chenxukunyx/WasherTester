package com.miracle.app.service;

import android.os.RemoteException;

import com.miracle.app.config.WasherConfig;
import com.unilife.common.entities.UMDB;
import com.unilife.common.receiver.BaseReceiver;
import com.unilife.common.sender.IUMSender;
import com.unilife.common.serials.SerialProtocol;
import com.unilife.common.services.UMBaseControlService;

public class MyService extends UMBaseControlService {
    final static String TAG = "MyService";
    @Override
    protected IUMSender initSvrSender() {
        return null;
    }

    @Override
    protected SerialProtocol getSerialProtocol() {
        return WasherConfig.getSerialProtocol();
    }

    @Override
    protected BaseReceiver initReceiver() {
        return null;
    }

    @Override
    public void update(String key, String value) throws RemoteException {

    }

    @Override
    public void updateDB(UMDB db) throws RemoteException {

    }

    @Override
    public void getAll(UMDB db) throws RemoteException {

    }

    @Override
    public int[] getSendBytes() throws RemoteException {
        return new int[0];
    }

    @Override
    public int[] getRecvBytes() throws RemoteException {
        return new int[0];
    }

    @Override
    public void start() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public String getValue(String key) throws RemoteException {
        return null;
    }
}
