package com.miracle.app.protocol.jide;

import android.support.annotation.Nullable;
import android.util.Log;

import com.miracle.app.base.BaseProtocol;
import com.miracle.app.model.JiDeModel;
import com.miracle.um_base_common.checkcrc.BaseCheck;
import com.miracle.um_base_common.util.StringUtils;
import com.unilife.common.serials.comfifo;

import java.util.Arrays;

/**
 * Created by 万启林 on 2015/7/30.
 */
public class JiDeProtocol extends BaseProtocol {
    private final String TAG = getClass().getSimpleName();
    private int[] lastSendData;

    @Override
    protected int calcVarietyChksum(int[] data, int size) {
        for(int i=3; i<size-2; i++) {
            data[i] = BaseCheck.GetVRC(data[i], 7);
        }
        //lrc校验
        data[size-1] = BaseCheck.GetLRC(Arrays.copyOfRange(data, 1, size-1), size-2);
        return 0;
    }

    @Override
    protected boolean chkVarietyChksum(int[] data, int size) {
        int chksum = BaseCheck.GetLRC(Arrays.copyOfRange(data, 1, size-1), size-2);
        int chksumd = data[size-1];

//            for(int i=0; i<size; i++) {
//                //data[i] = data[i] & 0x7f;
//            }
        return chksum == chksumd;
    }

    @Override
    protected int parseVarietyFrame(comfifo frame, int[] data, int size) {
        Integer len = parseHaixin(frame, data, size);
        if (len != null) {
            return len;
        }
        return 0;
    }

    @Nullable
    private Integer parseHaixin(comfifo frame, int[] data, int size) {
        int dtz = frame.getDataSize();
        if(size == 20) { //写   WasherSerialConfig中定义

            int index = -1;//数据开始位
            for(int i=0; i<dtz; i++) {
                //0 开始位  1 源地址 2 目标地址
                if(frame.get(i) == StringUtils.hexStringToLong(JiDeModel.CMD_STX) &&
                        frame.get(i+1) == StringUtils.hexStringToLong(JiDeModel.CMD_SRC_ADDR) &&
                        frame.get(i+2) == StringUtils.hexStringToLong(JiDeModel.CMD_DEST_ADDR)) {
                    index = i;
                    Log.d(TAG, "--->start found:index=" + index);
                    break;
                }
            }
            if(index != -1) { //有符合的
                for(int i=index+3; i<dtz; i++) {
                    if(frame.get(i) == StringUtils.hexStringToLong(JiDeModel.CMD_ETX)) {
                        Log.d(TAG, "--->end found:index="+i);
                        //找到一条完整的数据
                        int len = i-index+2;
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
                if(frame.get(i) == StringUtils.hexStringToLong(JiDeModel.CMD_STX) &&
                        i+2<dtz &&
                        frame.get(i+1) == StringUtils.hexStringToLong(JiDeModel.CMD_DEST_ADDR) &&
                        frame.get(i+2) == StringUtils.hexStringToLong(JiDeModel.CMD_SRC_ADDR)) {
                    index = i;
                    break;
                }
            }
            if(index != -1) { //没有符合的
                for(int i=index+3; i<dtz; i++) {
                    if(frame.get(i) == StringUtils.hexStringToLong(JiDeModel.CMD_ETX)) {
                        //找到一条完整的数据
                        int len = i-index+2;
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
