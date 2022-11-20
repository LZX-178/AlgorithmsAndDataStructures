package com.lzx.zcy.class01;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-21 17:24
 */
public class Code02_BubbleSort {
    //冒泡排序
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = arr.length - 2; i >= 0 ; i--) {
            for (int j = 0; j <= i; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {8, 5, 6, 4, 7, 55, 0};

        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
