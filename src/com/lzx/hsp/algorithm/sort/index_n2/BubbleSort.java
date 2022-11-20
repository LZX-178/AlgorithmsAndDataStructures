package com.lzx.hsp.algorithm.sort.index_n2;

/**
 * @author LZX
 * @code @create 2022-07-28 15:58
 * 冒泡排序
 */
public class BubbleSort {
    public void sort(int[] array){
        if (array.length < 2){
            return;
        }
        //记录是否进行过交换
        boolean flag = false;
        for (int i = array.length - 1; i >= 1; i--) {
            for (int j = 1; j < i + 1; j++) {
                if (array[j] < array[j-1]){
                    swap(array, j-1, j);
                    flag = true;
                }
            }
            if (!flag){
                break;
            }else {
                flag = false;
            }
        }
    }
    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
