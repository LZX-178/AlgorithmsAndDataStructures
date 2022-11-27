package com.lzx.leetCode.chapter10_sort.mergeSort;

import org.junit.Test;

import java.util.Arrays;

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
public class Code_327_CountOfRangeSum {
    // 遍历所有 子区间 O(n2)
    public int countRangeSum0(int[] nums, int lower, int upper) {
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

    // 引入排序
    public int[] nums;
    public long[] sumsLeft;
    public long[] sumsRight;

    public int count;
    public long lower;
    public long upper;

    public int countRangeSum1(int[] nums, int lower, int upper) {
        this.nums = nums;
        this.sumsLeft = new long[nums.length];

        this.count = 0;
        this.lower = lower;
        this.upper = upper;

        for (int i = 0; i < sumsLeft.length; i++) {
            sumsLeft[i] = nums[i];
        }
        this.sumsRight = Arrays.copyOf(sumsLeft, sumsLeft.length);

        mergeAndCount(0, nums.length-1);

        return this.count;
    }

    private long mergeAndCount(int start, int end) {
        if (start == end){
            if (nums[start] <= upper && nums[start] >= lower){
                count++;
            }
            return nums[start];
        }

        int mid = start + ((end - start)>>1);
        long sumLeft = mergeAndCount(start, mid);
        long sumRight = mergeAndCount(mid+1, end);

        int indexL = end, indexR = end;
        for (int i = mid; i >= start; i--) {
            indexR = binarySearchRight(mid + 1, indexR, upper - sumsRight[i]);
            if (indexR == -1){
                break;
            }
            indexL = binarySearchLeft(mid + 1, indexL, lower - sumsRight[i]);
            if (indexL == -1){
                indexL = end;
                continue;
            }
            count += indexR - indexL + 1;
        }


        int i = start, j = mid+1, index = 0;
        long[] tempL = new long[end-start+1];
        while (i <= mid && j <= end){
            tempL[index++] = sumsLeft[i] < sumsLeft[j] + sumLeft ? sumsLeft[i++] : sumsLeft[j++] + sumLeft;
        }
        while (i <= mid){
            tempL[index++] = sumsLeft[i++];
        }
        while (j <= end){
            tempL[index++] = sumsLeft[j++] + sumLeft;
        }
        System.arraycopy(tempL, 0, sumsLeft, start, end-start+1);


        long[] tempR = new long[end-start+1];
        i = start;
        j = mid+1;
        index = 0;
        while (i <= mid && j <= end){
            tempR[index++] = sumsRight[i] + sumRight >= sumsRight[j] ? sumsRight[i++] + sumRight : sumsRight[j++];
        }
        while (i <= mid){
            tempR[index++] = sumsRight[i++] + sumRight;
        }
        while (j <= end){
            tempR[index++] = sumsRight[j++];
        }
        System.arraycopy(tempR, 0, sumsRight, start, end-start+1);


        return sumLeft + sumRight;
    }

    // 计算 sumsLeft[start, end] 范围上  大于等于 lower 的 index,
    // start < end
    // 如果不存在则返回 -1
    private int binarySearchLeft(int start, int end, long lower) {
        if (sumsLeft[start] >= lower){
            return start;
        }
        if (sumsLeft[end] < lower){
            return -1;
        }
        int index = end;
        int mid;
        while (start <= end){
            mid = start + ((end-start)>>1);
            if (sumsLeft[mid] >= lower){
                index = mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return index;
    }
    // 计算 sums[start, end] 范围上  小于等于 upper 的 index,
    // start < end
    // 如果不存在则返回 -1
    private int binarySearchRight(int start, int end, long upper) {
        if (sumsLeft[end] <= upper){
            return end;
        }
        if (sumsLeft[start] > upper){
            return -1;
        }
        int index = start;
        int mid;
        while (start <= end){
            mid = start + ((end-start)>>1);
            if (sumsLeft[mid] <= upper){
                index = mid;
                start = mid+1;
            }else {
                end = mid-1;
            }
        }
        return index;
    }

    @Test
    public void test_method() {
        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int lower = -564;
        int upper = 3864;

        int sum1 = countRangeSum0(arr, lower, upper);
        int sum2 = countRangeSum1(arr, lower, upper);
        System.out.println("sum1 = " + sum1);
        System.out.println("sum2 = " + sum2);
    }
    @Test
    public void test_method2() {
        int[] arr = {-2, 5, -1, 5, -6};
//        int[] arr = {-2, 5, -1};
        int lower = -2;
        int upper = 2;

        int sum1 = countRangeSum0(arr, lower, upper);
        int sum2 = countRangeSum1(arr, lower, upper);
        System.out.println("sum1 = " + sum1);
        System.out.println("sum2 = " + sum2);
        System.out.println("Arrays.toString(sumsLeft) = " + Arrays.toString(sumsLeft));
        System.out.println("Arrays.toString(sumsRight) = " + Arrays.toString(sumsRight));
    }
}
