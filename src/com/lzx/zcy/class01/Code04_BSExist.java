package com.lzx.zcy.class01;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-21 19:47
 */
public class Code04_BSExist {
    //二分查找一个数在有序数组中是否存在
    public static boolean exist(int[] sortedArr, int num){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }

        int L = 0;
        int R = sortedArr.length -1;
        int mid;

        while (L < R){
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num){
                return true;
            }else if (sortedArr[mid] > num){
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }

        return sortedArr[L] == num;
    }

    public static void main(String[] args) {
        int[] arr = {8, 5, 6, 4, 7, 55, 0};

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        boolean isExist = exist(arr, 1);
        System.out.println("isExist = " + isExist);
    }
}
