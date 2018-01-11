package com.miracle.app.config;

import com.miracle.app.R;
import com.miracle.app.protocol.galanz.GalanzProtocol;
import com.miracle.app.protocol.galanz.GalanzRecvProtocol;
import com.miracle.app.protocol.galanz.GalanzSendProtocol;
import com.miracle.app.protocol.galanz.GalanzSerialConfig;
import com.miracle.um_base_common.base.BaseWasherConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:48
 */

public class GalanzWasherConfig extends BaseWasherConfig {

    public GalanzWasherConfig() {
        setMotorBrand(BrandConfig.MODEL_GALANZ);
        setBrandChinese(BrandConfig.MODEL_GALANZ_CHINESE);
        setLayoutId(R.layout.activity_main);
        setSerialConfig(new GalanzSerialConfig());
        setM_serialProtocol(GalanzProtocol.class);
        setM_clsSendProtocol(GalanzSendProtocol.class);
        setM_clsRecvProtocol(GalanzRecvProtocol.class);
    }
}
