package com.lzx.utils;

/**
 * @author LZX
 * @code @create 2022-09-12 9:42:48
 */
public class StringUtils {
    public static boolean isEmptyString(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmptyString(String str){
        return !isEmptyString(str);
    }
}
