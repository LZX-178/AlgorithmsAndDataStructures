package com.lzx.leetCode.chapter10_sort.bucketSort.radixSort;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-12 15:12:22
 * 164. 最大间距
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 *
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 *
 * 桶排序后再求间距, 效率带改进!!!
 *
 */
public class Code_0164_maximumGap {

    @Test
    public void test_maximumGap() {
        int[] arr =  {3,6,9,1};
        int i = maximumGap(arr);
        System.out.println(i);
    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2){
            return 0;
        }

        int[] temp = new int[nums.length];
        int digit;

        int maxDigit = getMaxDigit(nums);

        for (int i = 1; i <= maxDigit; i++) {
            // 1
            int[] count = new int[10];
            for (int j = 0; j < nums.length; j++) {
                digit = getDigit(nums[j], i);
                count[digit]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j-1];
            }

            // 2
            for (int j = nums.length-1; j >= 0; j--) {
                digit = getDigit(nums[j], i);
                temp[--count[digit]] = nums[j];
            }

            // 3
            int[] t = nums;
            nums = temp;
            temp = t;
        }

        int maxMum = nums[1] - nums[0];
        for (int i = 2; i < nums.length; i++) {
            maxMum = Math.max(maxMum, nums[i] - nums[i-1]);
        }

        return maxMum;
    }

    private int getDigit(int num, int i) {
        int pow = (int) Math.pow(10, i-1);
        return (num / pow) % 10;
    }

    private int getMaxDigit(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        int res = 0;
        while (max > 0){
            max /= 10;
            res++;
        }
        return res;
    }
}
