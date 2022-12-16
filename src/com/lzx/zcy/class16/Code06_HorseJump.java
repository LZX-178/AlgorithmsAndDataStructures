package com.lzx.zcy.class16;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-16 2:04:53
 *
 * 有一个象棋棋盘 (9*10), 马从 [0,0] 位置开始跳, 跳到 [a,b] 位置, 必须跳 k 步, 有多少种方法
 */
public class Code06_HorseJump {
    // 对数器
    public int jump0(int a, int b, int s) {
        int[][][] dp = new int[10][9][s + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= s; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }
    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }


    // 方法1 : 暴力递归
    public int jump1(int a, int b, int k) {
        return jump1(0, 0, k, a, b);
    }
    // 从 [x,y] 跳到 [a,b]
    private int jump1(int x, int y, int k, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        if (k == 0){
            return x == a && y == b ? 1 : 0;
        }
        return
                jump1(x+2, y+1, k-1, a, b) +
                jump1(x+1, y+2, k-1, a, b) +
                jump1(x-2, y+1, k-1, a, b) +
                jump1(x-1, y+2, k-1, a, b) +
                jump1(x+2, y-1, k-1, a, b) +
                jump1(x+1, y-2, k-1, a, b) +
                jump1(x-2, y-1, k-1, a, b) +
                jump1(x-1, y-2, k-1, a, b);
    }

    // 方法2 : 将方法1 改为 动态规划
    public int jump2(int a, int b, int k) {
        int[][][] dp = new int[k + 1][14][13];

        // 扩展边界, 避免大量的 if 判断
        dp[0][a+2][b+2] = 1;

        for (int i = 1; i <= k - 1; i++) {
            for (int j = 2; j <= 11; j++) {
                for (int l = 2; l <= 10; l++) {
                     dp[i][j][l] =
                             dp[i-1][j+2][l+1] +
                             dp[i-1][j+1][l+2] +
                             dp[i-1][j-2][l+1] +
                             dp[i-1][j-1][l+2] +
                             dp[i-1][j+2][l-1] +
                             dp[i-1][j+1][l-2] +
                             dp[i-1][j-2][l-1] +
                             dp[i-1][j-1][l-2];
                }
            }
        }
        dp[k][2][2] = dp[k-1][4][3] + dp[k-1][3][4];
        return dp[k][2][2];
    }

    @Test
    public void test_jump() {
        for (int i = 0; i < 500000; i++) {
            int a = NumberUtils.getRandomInt(0, 9);
            int b = NumberUtils.getRandomInt(0, 8);
            int k = NumberUtils.getRandomInt(1, 8);


            int ans0 = jump0(a, b, k);
            //        int ans1 = jump1(a, b, k);
            int ans2 = jump2(a, b, k);

            if (ans0 != ans2){
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("k = " + k);

                System.out.println("ans0 = " + ans0);
                //        System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
