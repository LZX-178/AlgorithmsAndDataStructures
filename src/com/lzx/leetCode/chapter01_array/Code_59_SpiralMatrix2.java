package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-22 16:16:43
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *      1  2  3
 *      8  9  4
 *      7  6  5
 */
public class Code_59_SpiralMatrix2 {


    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int num = 1;
        int i=0, k, j = 0;
        // 将矩阵按圈划分
        // 每一圈又划分为4条边
        for (i = 0; i < n/2; i++) {
            j = n - i - 1;
            for (k = i; k < j; k++) {
                arr[i][k] = num++;
            }
            for (k = i; k < j; k++) {
                arr[k][j] = num++;
            }
            for (k = j; k > i; k--) {
                arr[j][k] = num++;
            }
            for (k = j; k > i; k--) {
                arr[k][i] = num++;
            }
        }
        if (n % 2 == 1){
            arr[i][i] = num;
        }
        return arr;
    }

    @Test
    public void test_generateMatrix() {
        int[][] res = generateMatrix(5);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
