package com.miracle.app.protocol.wolong;

import com.miracle.app.base.BaseProtocol;
import com.miracle.um_base_common.checkcrc.BaseCheck;
import com.unilife.common.serials.comfifo;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/12/21
 * @time: 上午11:50
 */

public class WolongProtocol extends BaseProtocol {

    private int[] lastSendData;


    @Override
    public int calcVarietyChksum(int[] data, int size) {
        int crc16 = BaseCheck.crc_16_CCITT_False(data, size - 2) & 0xffff;
        data[size - 1] = crc16 & 0xff;
        data[size - 2] = (crc16 & 0xff00) >> 8;
        return crc16;
    }

    @Override
    public boolean chkVarietyChksum(int[] data, int size) {
        int crc16 = BaseCheck.crc_16_CCITT_False(data, size - 2) & 0xffff;
        int crcLow = crc16 & 0xff;
        int crcHigh = (crc16 & 0xff00) >> 8;
        return data[size - 2] == crcHigh && data[size - 1] == crcLow;
    }

    @Override
    public int parseVarietyFrame(comfifo frame, int[] data, int size) {
        int dsz = frame.getDataSize();
        if (size == 20) {
            for (int i = 0; i < dsz; i++) {
                if (frame.get(i) == 0xb2 && frame.get(i + 1) == 0x01) {
                    int len = frame.get(i + 3) + 6;
                    if (len + i > dsz) {
                        return 0;
                    }
                    if (len > size) {
                        frame.seek(i + len);
                        return 0;
                    }
                    frame.seek(i);
                    frame.read(data, len);
                    lastSendData = new int[len];
                    for (int j = 0; j < len; j++) {
                        lastSendData[j] = data[j];
                    }
                    return len;
                }
            }
            if (lastSendData != null && lastSendData.length > 0) {
                for (int i = 0; i < lastSendData.length; i++) {
                    data[i] = lastSendData[i];
                }
                return lastSendData.length;
            }
            return 0;
        } else if (size == 21) {
            for (int i = 0; i < dsz; i++) {
                if (frame.get(i) == 0xb2 && frame.get(i + 1) == 0x01) {
                    int len = frame.get(i + 3) + 6;
                    if (len + i > dsz) {
                        return 0;
                    }
                    if (len > size) {
                        frame.seek(i + len);
                        return 0;
                    }
                    frame.seek(i);
                    frame.read(data, len);
                    return len;
                }
            }
        }
        return 0;
    }
}
