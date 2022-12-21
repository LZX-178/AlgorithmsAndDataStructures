package com.lzx.zcy.class16;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-21 12:09:05
 * 给定一个正数 1，裂开的方法有一种，(1)
 * 给定一个正数 2，裂开的方法有两种，(1、1)、(2)
 * 给定一个正数 3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
 * 给定一个正数 4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4)
 * 给定一个正数 n，求裂开的方法数。
 */
public class Code09_SplitNumber {
    // 对数器
    public int ways0(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    // 方法1
    public int ways1(int n) {
         return ways1(1, n);
    }
    private int ways1(int i, int n) {
        if (i == n || n == 0){
            return 1;
        }
        int count = 0;
        for (int a = i; a <= n; a++) {
            count += ways1(a, n-a);
        }
        return count;
    }

    // 方法2 : 将方法1 改为动态规划
    public int ways2(int n) {
        int[][] dp = new int[n+1][n+1];

        // 遍历对角线
        for (int sum = 2; sum <= n + 1; sum++) {
            // 从左下角的元素向右上角的元素遍历
            int ii = (sum+1)/2;
            dp[ii][sum-ii] = ii == sum-ii ? 1 : 0;
            for (int iii = ii-1; iii >= 1; iii--) {
                int nnn = sum - iii;
                for (int a = iii; a < nnn; a++) {
                    dp[iii][nnn] += dp[a][nnn-a];
                }
                dp[iii][nnn]++;
            }
        }
        return dp[1][n];
    }

    // 方法3 : 改变方法2 的遍历方式
    public int ways3(int n) {
        int[][] dp = new int[n+1][n+1];

        // 遍历竖线
        for (int nn = 1; nn <= n; nn++) {
            // 从主对角线的元素向上遍历
            dp[nn][nn] = 1;
            for (int ii = nn-1; ii >= 1; ii--) {
                dp[ii][nn] = 1;
                for (int a = ii; a < nn; a++) {
                    dp[ii][nn] += dp[a][nn-a];
                }
            }
        }
        return dp[1][n];
    }

    // 方法4 : 优化方法 3
    public int ways4(int n) {
        int[][] dp = new int[n+1][n+1];

        // 遍历竖线
        dp[1][1] = 1;
        for (int nn = 2; nn <= n; nn++) {
            // 从主对角线的元素向上遍历
            dp[nn][nn] = 1;

            for (int ii = nn-1; ii >= 1; ii--) {
                // 注意这里不用加 1, 因为 dp[ii+1][nn] 加过了
                dp[ii][nn] = dp[ii][nn-ii] + dp[ii+1][nn];
            }
        }
        return dp[1][n];
    }



    @Test
    public void test_ways() {
        for (int i = 0; i < 500000; i++) {
            int n = NumberUtils.getRandomInt(10, 50);
            int ans0 = ways0(n);
            int ans1 = ways4(n);
            if (ans0 != ans1){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
    @Test
    public void test_ways2() {
            int n = 3;
            int ans0 = ways3(n);
            int ans1 = ways4(n);
            System.out.println("ans0 = " + ans0);
            System.out.println("ans1 = " + ans1);
    }
}
