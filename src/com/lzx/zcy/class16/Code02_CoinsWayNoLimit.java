package com.lzx.zcy.class16;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-16 15:22:40
 * 给定 一个数组 arr, 代表一组货币的面值, 面值为正数且无重复值且能重复使用
 * 返回用 arr 货币表示 aim 金额的方法数量
 */
public class Code02_CoinsWayNoLimit {

    // 对数器
    public int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }
    public int process(int[] arr, int index, int rest) {
        if (index == arr.length) { // 没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    public int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return coinsWay1(arr, 0, aim);
    }
    // 只使用 [index, end] 货币时, 表示 aim 的方法数量
    private int coinsWay1(int[] arr, int index, int aim) {
        if (aim == 0){
            return 1;
        }

        if (index == arr.length-1){
            return aim % arr[index] == 0 ? 1 : 0;
        }

        int res = 0;
        for (int num = 0; num * arr[index] <= aim; num++) {
            res += coinsWay1(arr, index+1, aim - num * arr[index]);
        }
        return res;
    }

    // 将 coinsWay1 改成动态规划
    public int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                // 将情况分为
                // 1 不使用 index 货币
                dp[index][rest] = dp[index+1][rest];

                // 2 至少使用一次 index 货币, 相当于 rest-arr[index] 后, 回到原始情况
                int onceUsedRest = rest-arr[index];
                if (onceUsedRest >= 0){
                    dp[index][rest] += dp[index][onceUsedRest];
                }
            }
        }
        return dp[0][aim];
    }


        @Test
    public void test_coinsWay() {
        for (int i = 0; i < 200; i++) {
            int[] arr = ArrayUtils.generateRandomArray(2, 5, 1, 10);
            int aim = NumberUtils.getRandomInt(10, 100);
            int res = coinsWay(arr, aim);
            int res1 = coinsWay1(arr, aim);
            int res2 = coinsWay2(arr, aim);
            if (res1 != res || res2 != res1){
                System.out.println(Arrays.toString(arr));
                System.out.println("aim = " + aim);
                System.out.println("res = " + res);
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
