package com.unilife.common.converter;

import com.unilife.common.serials.comfifo;

/**
 * Created by nicholas on 2015/7/17.
 */
public interface IDataFrameConverter {
    int calcChksum(int[] data, int size);
    boolean chkChksum(int[] data, int size);
    int simulate(int[] dst, int[] src);
    int parseFrame(comfifo frame, int[] data, int size);
    int getFrameLength(int[] data);
    long getFrameHead();
}
