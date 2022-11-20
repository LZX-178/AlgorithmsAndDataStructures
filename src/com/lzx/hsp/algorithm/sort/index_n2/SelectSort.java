package com.lzx.hsp.algorithm.sort.index_n2;

/**
 * @author LZX
 * @code @create 2022-07-28 16:47
 * 选择排序
 */
public class SelectSort {
    public void sort(int[] array){
        if (array.length < 2){
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                swap(array, i, minIndex);
            }
        }
    }
    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
