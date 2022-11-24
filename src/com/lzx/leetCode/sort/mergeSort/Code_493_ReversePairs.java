package com.lzx.leetCode.sort.mergeSort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-24 16:17:31
 * 493. 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 返回给定数组中的重要翻转对的数量。
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 */
public class Code_493_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        return reversePairs(nums, 0, nums.length-1);
    }

    private int reversePairs(int[] nums, int start, int end) {
        if (start >= end){
            return 0;
        }

        int mid = start + ((end-start)>>1);

        return  reversePairs(nums, start, mid) +
                reversePairs(nums, mid+1, end) +
                merge(nums, start, mid, end);
    }

    // 默认 start < end
    private int merge(int[] nums, int start, int mid, int end) {
        int ans = 0;
        int i = start, j = mid + 1, index = 0;
        int[] arr = new int[end - start + 1];

        while (i <= mid && j <= end){
            if (nums[i] <= nums[j]){
                arr[index++] = nums[i];
                if (nums[i] < 0){
                    for (int jj = j; jj <= end; jj++) {
                        if (nums[i]/2.0 > nums[jj]){
                            ans++;
                        }
                    }
                }
                i++;
            }else {
                arr[index++] = nums[j];
                int ii;
                for (ii = i; ii <= mid; ii++) {
                    if (nums[ii]/2.0 > nums[j]){
                        break;
                    }
                }
                ans += mid - ii + 1;
                j++;
            }
        }
        while (i <= mid){
            arr[index++] = nums[i++];
        }
        while (j <= end){
            arr[index++] = nums[j++];
        }
        System.arraycopy(arr, 0, nums, start, arr.length);
        return ans;
    }

    @Test
    public void test_reversePairs() {
        int[] arr = {1,3,2,3,1};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        int ans = reversePairs(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_1() {
        for (int i = 0; i < 33; i++) {
            System.out.println("i = " + i + "\t" + (-111 >> i));
        }
    }
}
