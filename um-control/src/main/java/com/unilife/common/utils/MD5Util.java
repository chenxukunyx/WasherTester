package com.unilife.common.utils;

/**
 * Created by Kevin on 2015/7/16.
 */
import java.security.MessageDigest;
public class MD5Util {
    final static char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public final static byte[] MD5Bytes(byte[] btInput)
    {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            return md;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public final static String MD5(byte[] btInput)
    {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public final static String MD5(String s) {
         byte[] btInput = s.getBytes();
         return MD5(btInput);
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("20121221"));
        System.out.println(MD5Util.MD5("加密"));
    }
}