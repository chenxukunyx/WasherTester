package com.unilife.common.managers;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by Kevin on 2015/7/16.
 */
public class DeviceManager {
    private DeviceManager(){}

    private static DeviceManager _instance;
    public static DeviceManager getInstance()
    {
        if (_instance == null)
        {
            _instance = new DeviceManager();
        }
        return _instance;
    }
    Context m_context;
    public void initContext(Context c){
        if (m_context != c)
        {
            m_context = c;
        }
    }

    public String getMac(){
        WifiManager wifi = (WifiManager) m_context.getSystemService(Context.WIFI_SERVICE);
        String mac = wifi.getConnectionInfo().getMacAddress();
        return mac.replace(":", "");
    }

    public long getMacLong(){
        return Long.parseLong(getMac(), 16);
    }
}
