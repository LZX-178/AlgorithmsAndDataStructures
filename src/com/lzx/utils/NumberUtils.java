package com.lzx.utils;

/**
 * @author LZX
 * @code @create 2022-09-08 13:38:50
 */
public class NumberUtils {
    // 返回 [start, end] 之间的随机整数, 正负随意, 但必须 start <= end
    public static int getRandomInt(int start, int end){
        // random() 返回 [0, 1) 之间的随机数
        return (int) (Math.random() * (end - start + 1))+ start;
    }
}
