package com.lzx.zcy.class16;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-19 12:37:34
 * 最小路径累加和
 *
 * 给定一个二维数组 m
 *      从左上角出发走到右下角
 *      只能向下或者向右走
 *      返回其路径上的元素的累加和的最小值
 */
public class Code08_MinPathSum {
    // 对数器
    public int minPathSum0(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // 方法1 : 暴力递归
    public int N;
    public int M;
    public int[][] matrix;
    public int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0){
            return 0;
        }
        N = m.length;
        M = m[0].length;
        matrix = m;
        return minPathSum1(0, 0);
    }
    private int minPathSum1(int n, int m) {
        if (n == N-1 && m == M-1){
            return matrix[n][m];
        }

        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (n < N-1){
            p1 = minPathSum1(n+1, m);
        }
        if (m < M-1){
            p2 = minPathSum1(n, m+1);
        }
        return matrix[n][m] + Math.min(p1, p2);
    }

    // 方法2 : 将方法1 改为动态规划
    public int minPathSum2(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[N-1][M-1] = matrix[N-1][M-1];
        for (int i = M - 2; i >= 0; i--) {
            dp[N-1][i] = matrix[N-1][i] + dp[N-1][i+1];
        }
        for (int i = N - 2; i >= 0; i--) {
            dp[i][M-1] = matrix[i][M-1] + dp[i+1][M-1];
        }

        for (int n = N - 2; n >= 0; n--) {
            for (int m = M - 2; m >= 0; m--) {
                dp[n][m] = matrix[n][m] + Math.min(dp[n+1][m], dp[n][m+1]);
            }
        }
        return dp[0][0];
    }

    // 方法3 : 将方法2 改为一维的
    public int minPathSum3(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[] dp = new int[M];
        dp[M-1] = matrix[N-1][M-1];
        for (int i = M - 2; i >= 0; i--) {
            dp[i] = matrix[N-1][i] + dp[i+1];
        }
        for (int n = N - 2; n >= 0; n--) {
            dp[M-1] += matrix[n][M-1];
            for (int m = M - 2; m >= 0; m--) {
                dp[m] = matrix[n][m] + Math.min(dp[m], dp[m+1]);
            }
        }
        return dp[0];
    }

    @Test
    public void test_minPathSum() {
        for (int u = 0; u < 50000; u++) {
            int N = NumberUtils.getRandomInt(5, 10);
            int M = NumberUtils.getRandomInt(5, 10);
            int[][] m = new int[N][M];
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    m[i][j] = NumberUtils.getRandomInt(5, 30);
                }
            }
            int ans0 = minPathSum0(m);
            int ans1 = minPathSum1(m);
            int ans2 = minPathSum2(m);
            int ans3 = minPathSum3(m);

            if (ans0 != ans1 || ans2 != ans0 || ans3 != ans0){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
