package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-20 17:53:47
 * 给定一个 n 个元素有序的（升序）整型数组
 * nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，
 * 如果目标值存在返回下标，否则返回 -1
 *
 */
public class Code_704_BinarySearch {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        return search(nums, target, 0, nums.length-1);
    }

    public int search(int[] nums, int target, int start, int end){
        if (end <= start){
            return nums[start] == target ? start : -1;
        }
        int mid = start + ((end-start)>>2) ;
        if (nums[mid] == target){
            return mid;
        }else if (nums[mid] > target){
            return search(nums, target, start, mid-1);
        }else {
            // 由于 mid 永远 "更靠近" start,
            return search(nums, target, mid+1, end);
        }
    }

    @Test
    public void test_search() {
        int[] arr = {-1,0,3,5,9,12};
        int target = 9;
        int ans = search(arr, 9);
        System.out.println("ans = " + ans);
    }
}
