package com.lzx.hsp.algorithm.sort.index_n2;

/**
 * @author LZX
 * @code @create 2022-07-28 16:48
 * 插入排序
 */
public class InsertSort {
    public void sort(int[] array){
        if (array.length < 2){
            return;
        }
        // 移位法: 向后移位
//        for (int i = 1; i < array.length; i++) {
//            int temp = array[i];
//            int j;
//            for (j = i; j >= 1; j--) {
//                if (array[j-1] > temp){
//                    array[j] = array[j-1];
//                }else {
//                    break;
//                }
//            }
//            array[j] = temp;
//        }
        // 交换法: 向前冒泡 速度较慢
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j-1]){
                    swap(array, j, j-1);
                }else {
                    break;
                }
            }
        }
    }
    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
