package com.lzx.leetCode.chapter10_sort.mergeSort;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-25 15:36:05
 *
 * 327. 区间和的个数
 *
 *      给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 *      求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 *      区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 *      题目数据保证
 *          答案是一个 32 位 的整数
 *          lower <= upper
 *
 *
 */
public class Code_377_CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int counts = 0;
        long[] arr = new long[nums.length];


        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j < arr.length; j++) {
                arr[j] += nums[i];
                if (arr[j] <= upper && arr[j] >= lower){
                    counts++;
                }
            }
        }

        return counts;
    }

    @Test
    public void test_method() {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int lower = -564;
        int upper = 3864;
    }
}
