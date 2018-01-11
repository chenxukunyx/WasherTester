package com.miracle.app.logic;

import com.unilife.common.serials.SerialTransmitter;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/13
 * @time: 下午6:44
 */

public class TransmatterLogic {

    private static TransmatterLogic transmatterLogic;

    private SerialTransmitter mTransmitter;

    private TransmatterLogic() {
        if (mTransmitter == null) {
            mTransmitter = new SerialTransmitter();
            mTransmitter.init(4800, 8, 2, 1, 0, false);
        }
    }

    public static TransmatterLogic getTransmatterLogic() {
        if (transmatterLogic == null) {
            synchronized (TransmatterLogic.class) {
                if (transmatterLogic == null) {
                    transmatterLogic = new TransmatterLogic();
                }
            }
        }
        return transmatterLogic;
    }

    public SerialTransmitter getTransmitter() {
        return mTransmitter;
    }
}
