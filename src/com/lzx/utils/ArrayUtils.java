package com.lzx.utils;

/**
 * @author LZX
 * @code @create 2022-09-08 13:37:30
 */
public class ArrayUtils {
    // 返回一个随机数组
    public static int[] generateRandomArray(int maxSize,int minValue, int maxValue){
        int[] array = new int[NumberUtils.getRandomInt(1, maxSize)];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtils.getRandomInt(minValue, maxValue);
        }
        return array;
    }
    // 返回一个随机数组, minSize<=maxSize, minValue<=maxValue
    public static int[] generateRandomArray(int minSize, int maxSize,int minValue, int maxValue){
        int[] array = new int[NumberUtils.getRandomInt(minSize, maxSize)];
        for (int i = 0; i < array.length; i++) {
            array[i] = NumberUtils.getRandomInt(minValue, maxValue);
        }
        return array;
    }
    // 判断两个数组的元素是否完全一致
    public static boolean IntArrayIsEqual(int[] array1, int[] array2){
        if ((array1 == null && array2 != null) || (array1 != null && array2 == null)) {
            return false;
        }else if (array1 == null){
            return true;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    // 交换数组中两个变量的值, i 和 j 相等时, 不交换
    public static void swap(int[] arr, int i, int j){
        if (i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
