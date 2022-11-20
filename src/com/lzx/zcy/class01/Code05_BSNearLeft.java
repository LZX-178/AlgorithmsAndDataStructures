package com.lzx.zcy.class01;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-21 20:15
 */
public class Code05_BSNearLeft {
    // 二分法的变形
    // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int value) {
        Arrays.sort(arr);

        int L = 0;
        int R = arr.length -1;
        int index = -1;

        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value){
                index = mid;
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }

        return index;
    }
}
