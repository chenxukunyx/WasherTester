package com.unilife.variety.service;

import android.os.RemoteException;

import com.unilife.common.entities.UMDB;
import com.unilife.common.receiver.BaseReceiver;
import com.unilife.common.sender.IUMSender;
import com.unilife.common.serials.SerialProtocol;
import com.unilife.common.services.UMBaseControlService;
import com.unilife.variety.config.AppConfig;
import com.unilife.variety.protocol.gelanshi.GeLanshiProtocol;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/29
 * @time: 上午9:40
 */

public class TesterService extends UMBaseControlService {

    private static final String TAG = "TesterService";

    @Override
    protected IUMSender initSvrSender() {
        return null;
    }

    @Override
    protected SerialProtocol getSerialProtocol() {
        if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_GELANSHI)) {
            return new GeLanshiProtocol();
        }
        return null;
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
    public String getValue(String key) throws RemoteException {
        return null;
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
}
