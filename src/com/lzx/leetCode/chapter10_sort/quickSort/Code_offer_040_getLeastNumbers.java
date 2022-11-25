package com.lzx.leetCode.chapter10_sort.quickSort;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-10 12:54:10
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入 4、5、1、6、2、7、3、8 这8个数字，则最小的 4 个数字是 1、2、3、4。
 */
public class Code_offer_040_getLeastNumbers {
    private int limit;
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0){
            return new int[0];
        }
        limit = k;

        quickSort(arr, 0, arr.length-1);

        return Arrays.copyOf(arr, k);
    }
    public void quickSort(int[] arr, int l, int r){
        if (l >= r){
            return;
        }

        int[] res = netherLandsFlag(arr, l, r);

        if (limit < res[0]){
            quickSort(arr, l, res[0]-1);
        }else if (limit > res[1]){
            quickSort(arr, res[1]+1, r);
        }
    }

    private int[] netherLandsFlag(int[] arr, int l, int r) {
        int less = -1;
        int more = r;
        int i = 0;

        while (i < more){
            if (arr[i] < arr[r]){
                swap(arr, ++less, i++);
            }else if (arr[i] == arr[r]){
                i++;
            }else {
                swap(arr, i, --more);
            }
        }
        swap(arr, r, more);
        return new int[]{less+1, more};
    }

    public void swap(int[] arr, int i, int j){
        if (i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
