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

    // 返回由 26 个小写字母组成的字符串
    public static String getRandomString(int minLength, int maxLength){
        if (minLength <= 0 || maxLength < minLength){
            throw new RuntimeException("error");
        }
        char[] chars = new char[NumberUtils.getRandomInt(minLength, maxLength)];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('a' + NumberUtils.getRandomInt(0, 25));
        }
        return new String(chars);
    }
}
