package com.unilife.common.converter;

/**
 * Created by Kevin on 2015/7/9.
 */
public interface IDisplayConverter {
    String toDisplay(long value);
    long toValue(String display);
}
