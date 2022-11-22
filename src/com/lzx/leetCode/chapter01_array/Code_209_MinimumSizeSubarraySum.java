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
    public final int anInt = 0;

    // 方法1 : 暴力递归, 超时
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

    // 方法2 : 把 方法1 改为动态规划, 超时
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

    // 方法3 : 改进的动态规划,  beat 5%
    //      不是每次从 index 往前累加, 而是将前一次的 累加和 记录起来
    public int minSubArrayLen3(int target, int[] nums) {
        int minLength = nums.length+1;

        // 第一个维度
        //      索引 index
        // 第二个维度
        //      第一位记录 从 index 往前的累加和
        //      第二位记录 累加终止时的索引,
        //          全加起来也不足 target, 则用 -1 表示
        //          否则表示 累加和大于 target 时的最小长度
        int[][] ans = new int[nums.length][2];

        ans[0][0] = nums[0];
        if (nums[0] < target){
            ans[0][1] = -1;
        }else {
            return 1;
        }

        for (int i = 1; i < nums.length; i++) {

            ans[i][0] = ans[i-1][0] + nums[i];

            // 根据 前一次累加和 是否大于 target 决定当前 index 的策略
            if (ans[i-1][1] == -1){
                if (ans[i][0] >= target){
                    ans[i][1] = 0;
                    minLength = Math.min(minLength, i-ans[i][1]+1);
                }else {
                    ans[i][1] = -1;
                    continue;
                }
            }else {
                ans[i][1] = ans[i-1][1];
            }

            // 由于累加和变大了, ans[i][1] 可能不是最优的, 需要优化
            for (int j = ans[i][1]; j < i; j++) {
                int newSum = ans[i][0] - nums[j];
                if (newSum >= target){
                    ans[i][0] = newSum;
                    ans[i][1]++;
                    minLength = Math.min(minLength, i-ans[i][1]+1);
                }else {
                    break;
                }
            }
        }
        return minLength == nums.length+1 ? 0 : minLength;
    }

    // 方法4 : 滑动窗口
    //      考察 方法3 的 ans 数组
    //          只访问 ans[i] 和 ans[i-1], 可优化
    public int minSubArrayLen4(int target, int[] nums) {
        int minLength = nums.length+1;

        int start = 0;
        int sum = nums[start];
        if (sum >= target){
            return 1;
        }

        for (int end = 1; end < nums.length; end++) {
            sum += nums[end];
            if (sum >= target){
                minLength = Math.min(minLength, end-start+1);
                while (start < end){
                    int newSum = sum - nums[start];
                    if (newSum >= target){
                        sum = newSum;
                        start++;
                        minLength = Math.min(minLength, end-start+1);
                    }else {
                        break;
                    }
                }
            }
        }

        return minLength == nums.length + 1 ? 0 : minLength;
    }

    // 方法5 : 方法4 的优雅版
    public int minSubArrayLen5(int s, int[] nums) {
        int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length) {
            sum += nums[hi++];
            while (sum >= s) {
                min = Math.min(min, hi - lo);
                sum -= nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int climb(int n){
        if (n == 1){
            return 1;
        }else if (n == 2){
            return 2;
        }
        return climb(n-1) + climb(n-2);
    }

    @Test
    public void test_climb() {
        int c1 = climb(1);
        int c2 = climb(2);
        int c3 = climb(3);
        int c4 = climb(4);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c4 = " + c4);

    }


    @Test
    public void test_ans() {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        int ans = minSubArrayLen4(target, nums);
        System.out.println("ans = " + ans);
    }
    @Test
    public void test_ans2() {
        int[] nums = {1,2,3,4,5};
        int target = 15;
        int ans = minSubArrayLen4(target, nums);
        System.out.println("ans = " + ans);
    }
    @Test
    public void test_ans3() {
        int[] nums = {1,1,1,1,1,1,1,1};
        int target = 11;
        int ans = minSubArrayLen4(target, nums);
        System.out.println("ans = " + ans);
    }
}
