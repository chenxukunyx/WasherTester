package com.miracle.app.config;

import com.miracle.app.R;
import com.miracle.app.protocol.galanz.GalanzProtocol;
import com.miracle.app.protocol.galanz.GalanzRecvProtocol;
import com.miracle.app.protocol.galanz.GalanzSendProtocol;
import com.miracle.app.protocol.galanz.GalanzSerialConfig;
import com.miracle.app.protocol.jide.JiDeProtocol;
import com.miracle.app.protocol.jide.JiDeRecvProtocol;
import com.miracle.app.protocol.jide.JiDeSendProtocol;
import com.miracle.app.protocol.jide.JiDeSerialConfig;
import com.miracle.um_base_common.base.BaseWasherConfig;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:48
 */

public class JiDeWasherConfig extends BaseWasherConfig {

    public JiDeWasherConfig() {
        setMotorBrand(BrandConfig.MODEL_JIDE);
        setBrandChinese(BrandConfig.MODEL_JIDE_CHINESE);
        setLayoutId(R.layout.activity_main_wolong);
        setSerialConfig(new JiDeSerialConfig());
        setM_serialProtocol(JiDeProtocol.class);
        setM_clsSendProtocol(JiDeSendProtocol.class);
        setM_clsRecvProtocol(JiDeRecvProtocol.class);
    }
}
