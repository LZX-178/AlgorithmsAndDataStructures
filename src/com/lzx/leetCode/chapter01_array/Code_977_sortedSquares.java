package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-20 20:05:58
 * 977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，
 * 返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
public class Code_977_sortedSquares {
    // 双指针, 从中间向两边
    public int[] sortedSquares(int[] nums) {
        int right = 0;
        int l = nums.length;
        while (right < l && nums[right] < 0){
            nums[right] *= nums[right];
            right++;
        }
        int left = right-1;
        int[] ans = new int[l];

        int i = 0;
        while (left >= 0 && right < l){
            ans[i++] = nums[left] < nums[right] * nums[right] ? nums[left--] : nums[right] * nums[right++];
        }
        while (left >= 0){
            ans[i++] = nums[left--];
        }
        while (right < l){
            ans[i++] = nums[right] * nums[right++];
        }

        return ans;
    }

    // 双指针, 从两边向中间
    public int[] sortedSquares2(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0, right = nums.length-1;
        int leftNum = nums[left]*nums[left];
        int rightNum = nums[right]*nums[right];

        // 注意, 这里少遍历一个元素, 是为了防止 left++ 后 nums[left] 越界( right 同理)
        for (int i = nums.length-1; i >= 1; i--) {
            if (leftNum > rightNum){
                ans[i] = leftNum;
                left++;
                leftNum = nums[left]*nums[left];
            }else {
                ans[i] = rightNum;
                right--;
                rightNum = nums[right]*nums[right];
            }
        }
        ans[0] = leftNum;
        return ans;
    }

    @Test
    public void test_sortedSquares() {
        int[] arr = {-4,-1,0,3,10};
        int[] arr2 = {1};
        int[] ans2 = sortedSquares2(arr2);
        int[] ans = sortedSquares2(arr);
        System.out.println("ans = " + Arrays.toString(ans2));
    }
}
