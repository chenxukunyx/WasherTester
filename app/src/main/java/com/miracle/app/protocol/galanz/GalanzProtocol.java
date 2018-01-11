package com.miracle.app.protocol.galanz;

import android.support.annotation.Nullable;
import android.util.Log;

import com.miracle.app.base.BaseProtocol;
import com.miracle.app.model.GalanzModel;
import com.miracle.um_base_common.checkcrc.BaseCheck;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.serials.comfifo;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/28
 * @time: 下午5:53
 */

public class GalanzProtocol extends BaseProtocol {

    private static final String TAG = "GalanzProtocol";
    private int[] lastSendData;

    @Override
    protected int calcVarietyChksum(int[] data, int size) {
        int crc = BaseCheck.getCRC16(data, 0, size - 2) & 0xffff;
        data[size - 2] = crc & 0xff;
        data[size - 1] = (crc & 0xff00) >> 8;
        return crc;
    }

    @Override
    protected boolean chkVarietyChksum(int[] data, int size) {
        int crc = BaseCheck.getCRC16(data, 0, size - 2);
        int crc1 = crc & 0xff;
        int crc2 = (crc & 0xff00) >> 8;
        return crc1 == data[size - 2] && crc2 == data[size - 1];
    }

    @Override
    protected int parseVarietyFrame(comfifo frame, int[] data, int size) {
        Integer len = parseGeLanshi(frame, data, size);
        if (len != null) {
            return len;
        }
        return 0;
    }

    @Nullable
    private Integer parseGeLanshi(comfifo frame, int[] data, int size) {
        int dtz = frame.getDataSize();
        if (size == 20) { //写   WasherSerialConfig中定义

            int index = -1;//数据开始位
            for (int i = 0; i < dtz; i++) {
                //0 开始位  1 源地址 2 目标地址
                if (frame.get(i) == StringUtils.hexStringToLong(GalanzModel.CMD_STX) &&
                        frame.get(i + 1) == StringUtils.hexStringToLong(GalanzModel.CMD_SRC_ADDR) &&
                        frame.get(i + 2) == StringUtils.hexStringToLong(GalanzModel.CMD_DEST_ADDR)) {
                    index = i;
                    Log.d(TAG, "--->start found:index=" + index);
                    break;
                }
            }
            if (index != -1) { //有符合的
                for (int i = index + 3; i < dtz; i++) {
                    if (frame.get(i) == StringUtils.hexStringToLong(GalanzModel.CMD_ETX)) {
                        Log.d(TAG, "--->end found:index=" + i);
                        //找到一条完整的数据
                        int len = i - index + 3;
                        if (len > size) {
                            return 0;
                        }
                        frame.seek(index);
                        frame.read(data, len);
                        lastSendData = new int[len];
                        for (int q = 0; q < len; q++) {
                            lastSendData[q] = data[q];
                        }
                        return len;
                    }
                }
            } else if (lastSendData != null && lastSendData.length > 0) {
                int lastSendDataLength = lastSendData.length;
                for (int i = 0; i < lastSendDataLength; i++) {
                    data[i] = lastSendData[i];
                }
                return lastSendDataLength;
            }
        } else if (size == 21) {//读   WasherSerialConfig中定义
            int index = -1;//数据开始位
            for (int i = 0; i < dtz; i++) {
                //0 开始位  1 源地址 2 目标地址
                if (frame.get(i) == StringUtils.hexStringToLong(GalanzModel.CMD_STX) &&
                        i + 2 < dtz &&
                        frame.get(i + 1) == StringUtils.hexStringToLong(GalanzModel.CMD_DEST_ADDR) &&
                        frame.get(i + 2) == StringUtils.hexStringToLong(GalanzModel.CMD_SRC_ADDR)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) { //没有符合的
                for (int i = index + 3; i < dtz; i++) {
                    if (frame.get(i) == StringUtils.hexStringToLong(GalanzModel.CMD_ETX)) {
                        //找到一条完整的数据
                        int len = i - index + 3;
                        if (len > size) {
                            return 0;
                        }
                        frame.seek(index);
                        frame.read(data, len);
                        if (!chkChksum(data, len)) {
                            frame.reset();
                            return 0;
                        }
                        return len;
                    }
                }
            }
        }
        return null;
    }
}
