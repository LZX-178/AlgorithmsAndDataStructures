package com.lzx.zcy.class16;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-21 14:58:43
 * 给定一个正数数组
 * 请把数组中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 *
 * 待改进以及加入数量平衡限制的进阶题
 */
public class Code10_SplitArraySumClosed {
    // 对数器
    public int splitArray0(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    // 方法1 : 暴力递归
    public int splitArray1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        int temp = splitArray1(arr, 0, 0, 0);
        return (sum-temp)/2;
    }
    private int splitArray1(int[] arr, int index, int sum1, int sum2) {
        if (index == arr.length-1){
            return Math.min(
                    Math.abs(sum1+arr[index]-sum2),
                    Math.abs(sum1-arr[index]-sum2)
            );
        }

        return Math.min(
                splitArray1(arr, index+1, sum1+arr[index], sum2),
                splitArray1(arr, index+1, sum1, sum2+arr[index])
        );
    }

    @Test
    public void test_splitArray() {
        for (int i = 0; i < 50000; i++) {
            int[] array = ArrayUtils.generateRandomArray(10, 20, 1, 100);

            int ans0 = splitArray0(array);
            int ans1 = splitArray1(array);

            if (ans0 != ans1){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
