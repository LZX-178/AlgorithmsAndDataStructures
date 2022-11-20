package com.lzx.leetCode.sort.mergeSort;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-08 17:42:05
 * 剑指 Offer 51. 数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 */
public class Code_offer_051_reversePairs {
    @Test
    public void test_reversePairs() {
        int[] array =
//                {7,5,6,4};
                {2,4,3,5,1};

        int res = reversePairs(array);
        System.out.println("res = " + res);
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        return reversePairs(nums, 0 , nums.length-1);
    }

    private int reversePairs(int[] nums, int start, int end) {
        if (start == end){
            return 0;
        }
        int mid = start + ((end - start)>>1);
        return reversePairs(nums, start, mid) + reversePairs(nums, mid+1, end) + merge(nums, start, mid, end);
    }

    private int merge(int[] nums, int start, int mid, int end) {
        int count = 0;
        int i = start;
        int j = mid + 1;
        int[] temp = new int[end - start + 1];
        int t = 0;
        while (i <= mid && j <= end){
            if (nums[i] <= nums[j]){
                temp[t++] = nums[j++];
            }else {
                count += end - j + 1;
                temp[t++] = nums[i++];
            }
        }
        while (i <= mid){
            temp[t++] = nums[i++];
        }
        while (j <= end){
            temp[t++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, start, temp.length);
        return count;
    }
}
