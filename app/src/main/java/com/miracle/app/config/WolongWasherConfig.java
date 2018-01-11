package com.miracle.app.config;

import com.miracle.app.R;
import com.miracle.app.protocol.wolong.WolongProtocol;
import com.miracle.app.protocol.wolong.WolongRecvProtocol;
import com.miracle.app.protocol.wolong.WolongSendProtocol;
import com.miracle.app.protocol.wolong.WolongSerialConfig;
import com.miracle.um_base_common.base.BaseWasherConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:22
 */

public class WolongWasherConfig extends BaseWasherConfig {

    public WolongWasherConfig() {
        setMotorBrand(BrandConfig.MODEL_WOLONG);
        setBrandChinese(BrandConfig.MODEL_WOLONG_CHINESE);
        setLayoutId(R.layout.activity_main_wolong);
        setSerialConfig(new WolongSerialConfig());

        setM_serialProtocol(WolongProtocol.class);
        setM_clsSendProtocol(WolongSendProtocol.class);
        setM_clsRecvProtocol(WolongRecvProtocol.class);
    }
}
