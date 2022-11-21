package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-21 19:23
 *
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和  >= target 的长度最小的 连续子数组
 *      [n, n+1, .... n+m]
 *      并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 */
public class Code_209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int ans = minSubArrayLen(target, nums, nums.length-1);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private int minSubArrayLen(int target, int[] nums, int index) {
        if (index == 0){
            return nums[index] >= target ? 1 : Integer.MAX_VALUE;
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = index, sum = 0; i >= 0; i--) {
            sum += nums[i];
            if (sum >= target){
                minLength = index - i + 1;
                break;
            }
        }
        return Math.min(minLength, minSubArrayLen(target, nums, index-1));
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = nums[0] >= target ? 1 : Integer.MAX_VALUE;
        int minLength;

        for (int i = 1; i < ans.length; i++) {
            minLength = Integer.MAX_VALUE;
            for (int j = i, sum = 0; j >= 0; j--) {
                sum += nums[j];
                if (sum >= target){
                    minLength = i - j + 1;
                    break;
                }
            }
            ans[i] = Math.min(minLength, ans[i-1]);
        }
        return ans[nums.length-1] == Integer.MAX_VALUE ? 0 : ans[nums.length-1];
    }

    @Test
    public void test_ans() {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        int ans = minSubArrayLen(target, nums);
        System.out.println("ans = " + ans);
    }
}
