package com.lzx.hsp.dataStructures.Utils;

/**
 * @author LZX
 * @code @create 2022-08-15 14:11:59
 */
public class NumberUtils {
    // 交换两个变量的值, 但 i 和 j 不能相等, 否则出大问题
    public static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
