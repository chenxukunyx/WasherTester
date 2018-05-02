package com.miracle.app.config;

import com.miracle.app.R;
import com.miracle.app.protocol.galanz.GalanzProtocol;
import com.miracle.app.protocol.galanz.GalanzRecvProtocol;
import com.miracle.app.protocol.galanz.GalanzSendProtocol;
import com.miracle.app.protocol.galanz.GalanzSerialConfig;
import com.miracle.app.protocol.weili.WeiLiProtocol;
import com.miracle.app.protocol.weili.WeiLiRecvProtocol;
import com.miracle.app.protocol.weili.WeiLiSendProtocol;
import com.miracle.app.protocol.weili.WeiLiSerialConfig;
import com.miracle.um_base_common.base.BaseWasherConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2018/4/24
 * @time: 下午2:55
 */

public class WeiLiWasherConfig extends BaseWasherConfig{

    public WeiLiWasherConfig() {
        setMotorBrand(BrandConfig.MODEL_WEILI);
        setBrandChinese(BrandConfig.MODEL_WEILI_CHINESE);
        setLayoutId(R.layout.activity_main);
        setSerialConfig(new WeiLiSerialConfig());
        setM_serialProtocol(WeiLiProtocol.class);
        setM_clsSendProtocol(WeiLiSendProtocol.class);
        setM_clsRecvProtocol(WeiLiRecvProtocol.class);
    }
}
