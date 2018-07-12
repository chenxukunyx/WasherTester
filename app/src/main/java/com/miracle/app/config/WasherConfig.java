package com.miracle.app.config;

import android.util.Log;

import com.miracle.app.protocol.wolong.WolongProtocol;
import com.miracle.um_base_common.base.BaseWasherConfig;
import com.unilife.common.serials.SerialProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:17
 */

public class WasherConfig extends BaseWasherConfig {

    private static final String TAG = "WasherConfig";

    static BaseWasherConfig mWasherConfig = new WolongWasherConfig();
    static List<BaseWasherConfig> mWasherConfigList;

    static {
        mWasherConfigList = new ArrayList<>();

        mWasherConfigList.add(new WolongWasherConfig());
        mWasherConfigList.add(new GalanzWasherConfig());
        mWasherConfigList.add(new JiDeWasherConfig());
        mWasherConfigList.add(new WeiLiWasherConfig());

        setWasherConfig(BrandConfig.MODEL_JIDE);
    }

    /**
     * 根据brand配置洗衣机协议
     * @param brand motor品牌
     */
    public static void setWasherConfig(String brand) {
        Log.i(TAG, "----->>brand: " + brand);

        if (!brand.equals(mWasherConfig.getMotorBrand())) {
            for (BaseWasherConfig config : mWasherConfigList) {
                if (config.getMotorBrand().equals(brand)) {
                    synchronized (mWasherConfig) {
                        mWasherConfig = config;
                    }
                    break;
                }
            }
        }
    }

    public static BaseWasherConfig getWasherConfig() {
        return mWasherConfig;
    }

    /**
     * 获取串口解析协议，处理数据
     * @return
     */
    public static SerialProtocol getSerialProtocol() {
        SerialProtocol protocol = null;
        try {
            protocol = mWasherConfig.getM_serialProtocol().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (protocol == null) {
                protocol = new WolongProtocol();
            }
        }
        return protocol;
    }
}
