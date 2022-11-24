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
            // mid 总是 "更靠近" L
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num){
                return true;
            }else if (sortedArr[mid] > num){
                // 更新后
                // R 可能越界, L 不变
                R = mid - 1;
            }else{
                // 更新后
                // 由于 mid 不可能和 R 重合, L 总是 <= R , 不会越界
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
