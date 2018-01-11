package com.unilife.common.entities;

/**
 * Created by Kevin on 2015/7/18.
 */
public interface IBuffer {
    void addValue(int start, int length, Object value);
    int getInt32Value(int start, int length);
    long getInt64Value(int start, int length);
    Object getObjValue(int start, int length);
    String getString(int start, int length);

    void from(Object obj);
    Object getBuffer();
}
