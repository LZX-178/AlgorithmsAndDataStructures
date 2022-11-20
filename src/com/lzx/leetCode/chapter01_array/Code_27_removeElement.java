package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-20 19:18:29
 * 27. 移除元素
 * 给你一个数组 nums和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class Code_27_removeElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            while ( start <= end && nums[start] != val) {
                start++;
            }
            while (start <= end && nums[end] == val) {
                end--;
            }
            if (start <= end) {
                nums[start] = nums[end];
                start++;
                end--;
            }
        }
        return start;
    }

    @Test
    public void test_find() {
        int[] arr = {1};
        int val = 1;
//        int[] arr = {3,2,2,3};
//        int val = 3;
//        int[] arr = {4, 5};
//        int val = 4;
        int res = removeElement(arr, val);
        System.out.println("res = " + res);
    }
}
