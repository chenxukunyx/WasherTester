package com.unilife.variety.protocol.gelanshi;

import android.support.annotation.Nullable;
import android.util.Log;

import com.unilife.common.serials.SerialProtocol;
import com.unilife.common.serials.comfifo;
import com.unilife.variety.config.AppConfig;
import com.unilife.variety.model.GeLanshiModel;
import com.unilife.variety.protocol.BaseCheck;
import com.unilife.variety.utils.StringUtils;

/**
 * Created with Android Studio
 *
 * @fuction:
 * @author: chenxukun
 * @data: 2017/11/28
 * @time: 下午5:53
 */

public class GeLanshiProtocol extends SerialProtocol {

    private static final String TAG = "GeLanshiProtocol";
    private int[] lastSendData;
    public GeLanshiProtocol()
    {
        m_serial = new GeLanshiSerialConfig();
        m_send = new GeLanshiSendProtocol();
        m_recv = new GeLanshiRecvProtocol();
    }

    @Override
    public int calcChksum(int[] data, int size) {
        if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_GELANSHI)) {
            int crc = BaseCheck.getCRC16(data, 0, size - 2) & 0xffff;
            data[size - 2] = crc & 0xff;
            data[size - 1] = (crc & 0xff00) >> 8;
            return crc;
        }
        return 0;
    }

    @Override
    public boolean chkChksum(int[] data, int size) {
        if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_GELANSHI)) {
            int crc = BaseCheck.getCRC16(data, 0, size - 2);
            int crc1 = crc & 0xff;
            int crc2 = (crc & 0xff00) >> 8;
            return crc1 == data[size - 2] && crc2 == data[size - 1];
        }
        return true;
    }

    @Override
    public int parseFrame(comfifo frame, int[] data, int size) {
        if (AppConfig.DEVICE_MODEL.equals(AppConfig.MODEL_GELANSHI)) {
            Integer len = parseGeLanshi(frame, data, size);
            if (len != null)
                return len;
        }
        return 0;
    }

    @Nullable
    private Integer parseGeLanshi(comfifo frame, int[] data, int size) {
        int dtz = frame.getDataSize();
        if(size == 20) { //写   WasherSerialConfig中定义

            int index = -1;//数据开始位
            for(int i=0; i<dtz; i++) {
                //0 开始位  1 源地址 2 目标地址
                if(frame.get(i) == StringUtils.hexStringToLong(GeLanshiModel.CMD_STX) &&
                        frame.get(i+1) == StringUtils.hexStringToLong(GeLanshiModel.CMD_SRC_ADDR) &&
                        frame.get(i+2) == StringUtils.hexStringToLong(GeLanshiModel.CMD_DEST_ADDR)) {
                    index = i;
                    Log.d(TAG, "--->start found:index=" + index);
                    break;
                }
            }
            if(index != -1) { //有符合的
                for(int i=index+3; i<dtz; i++) {
                    if(frame.get(i) == StringUtils.hexStringToLong(GeLanshiModel.CMD_ETX)) {
                        Log.d(TAG, "--->end found:index="+i);
                        //找到一条完整的数据
                        int len = i-index+3;
                        if(len > size) {
                            return 0;
                        }
                        frame.seek(index);
                        frame.read(data, len);
                        lastSendData = new int[len];
                        for(int q=0; q<len; q++) {
                            lastSendData[q] = data[q];
                        }
                        return len;
                    }
                }
            }else if(lastSendData != null && lastSendData.length > 0) {
                int lastSendDataLength = lastSendData.length;
                for (int i=0; i<lastSendDataLength; i++) {
                    data[i] = lastSendData[i];
                }
                return lastSendDataLength;
            }
        }else if(size == 21) {//读   WasherSerialConfig中定义
            int index = -1;//数据开始位
            for(int i=0; i<dtz; i++) {
                //0 开始位  1 源地址 2 目标地址
                if(frame.get(i) == StringUtils.hexStringToLong(GeLanshiModel.CMD_STX) &&
                        i+2<dtz &&
                        frame.get(i+1) == StringUtils.hexStringToLong(GeLanshiModel.CMD_DEST_ADDR) &&
                        frame.get(i+2) == StringUtils.hexStringToLong(GeLanshiModel.CMD_SRC_ADDR)) {
                    index = i;
                    break;
                }
            }
            if(index != -1) { //没有符合的
                for(int i=index+3; i<dtz; i++) {
                    if(frame.get(i) == StringUtils.hexStringToLong(GeLanshiModel.CMD_ETX)) {
                        //找到一条完整的数据
                        int len = i-index+3;
                        if(len > size) {
                            return 0;
                        }
                        frame.seek(index);
                        frame.read(data, len);
                        if(!chkChksum(data, len)) {
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
