package com.unilife.variety.utils;

/**
 * 功能：
 * 作者： 万启林
 * 时间：16/5/16
 */
public class StringUtils {
    /**
     * 将16进制的字符串转为long类型
     * @param value
     * @return
     */
    public static Long hexStringToLong(String value) {
        try{
            return Long.parseLong(value, 16);
        }catch (Exception e) {
            return 0l;
        }
    }

    public static int parseInteger(String value) {
        try{
            return Integer.parseInt(value);
        }catch (Exception e) {
            return 0;
        }
    }
}
