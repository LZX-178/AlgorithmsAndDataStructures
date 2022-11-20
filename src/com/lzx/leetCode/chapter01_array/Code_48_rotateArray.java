package com.lzx.leetCode.chapter01_array;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-26 10:36:31
 * 48. 旋转图像
 * 给定一个 n×n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Code_48_rotateArray {

    @Test
    public void test_rotate() {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6}, {7, 8, 9} };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        rotate(matrix);
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix) {

        int n = matrix.length;
        int temp;

        for (int i = 0; i < n/2; i++) {
            // (i, i)
            // (n-1-i, i)
            // (n-1-i, n-1-i)
            // (i, n-1-i)

            for (int j = 0; j <= n-i-i-2; j++) {
                temp = matrix[i][i+j];
                matrix[i][i+j] = matrix[n-1-i-j][i];
                matrix[n-1-i-j][i] = matrix[n-1-i][n-1-i-j];
                matrix[n-1-i][n-1-i-j] = matrix[i+j][n-1-i];
                matrix[i+j][n-1-i] = temp;
            }
        }

    }
}
