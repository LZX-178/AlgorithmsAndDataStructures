package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-20 17:53:47
 * 给定一个 n 个元素有序的（升序）整型数组
 * nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，
 * 如果目标值存在返回下标，否则返回 -1
 */
public class Code_704_BinarySearch {
    // 递归版本
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return search(nums, target, 0, nums.length - 1);
    }

    // 由于 mid 永远 "更靠近" start, 所以其向左移动时不会越界, 向右移动才可能越界
    // 即 start 永远不越界
    public int search(int[] nums, int target, int start, int end) {
        if (end <= start) {
            return nums[start] == target ? start : -1;
        }
        int mid = start + ((end - start) >> 2);
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return search(nums, target, start, mid - 1);
        } else {
            return search(nums, target, mid + 1, end);
        }
    }

    // 非递归版本
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start < end) {
            mid = start + ((end - start) >> 2);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nums[start] == target ? start : -1;
    }

    @Test
    public void test_search() {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int ans = search(arr, target);
        System.out.println("ans = " + ans);
    }
}
