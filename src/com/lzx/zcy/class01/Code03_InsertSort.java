package com.lzx.zcy.class01;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-21 17:55
 */
public class Code03_InsertSort {
    //插入排序
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i;j > 0 && arr[j] < arr[j-1]; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean isSuccess = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr = getRandomArray(maxSize, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);

            insertSort(arr1);
            comparator(arr2);

            if (!isEqual(arr1, arr2)) {
                isSuccess = false;
                break;
            }
        }

        if (!isSuccess){
            System.out.println("error");
        }else {
            System.out.println("success");
        }
    }

    private static int[] getRandomArray(int maxSize, int maxValue) {
        int length = (int) (Math.random() * maxSize);
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }
}
