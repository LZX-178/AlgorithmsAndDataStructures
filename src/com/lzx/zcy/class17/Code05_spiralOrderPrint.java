package com.lzx.zcy.class17;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-10-25 19:55:12
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Code05_spiralOrderPrint {


    @Test
    public void test_spiralOrder() {
        int[][] matrix1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6}, {7, 8, 9} };
        int[][] matrix3 = { { 1}, { 4}, {7} };
        int[][] matrix4 = { { 1, 2}, { 4, 5}};
        int[] ints1 = spiralOrder(matrix1);
        System.out.println(Arrays.toString(ints1));
        int[] ints2 = spiralOrder(matrix2);
        System.out.println(Arrays.toString(ints2));
        int[] ints3 = spiralOrder(matrix3);
        System.out.println(Arrays.toString(ints3));
        int[] ints4 = spiralOrder(matrix4);
        System.out.println(Arrays.toString(ints4));
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int[] res = new int[height * width];
        int n = 0;

        for (int i = 0; i <= Math.min(height-1,width-1)/2; i++) {
            // x 坐标的范围为 [i, height-1-i]
            // y 坐标的范围为 [i, width-1-i]
            int countY = 0, countX = 0;
            for (int y = i; y <= width-i-1; y++) {
                res[n++] = matrix[i][y];
                countY++;
            }

            for (int x = i+1; x <= height-i-1; x++) {
                res[n++] = matrix[x][width-1-i];
                countX++;
            }

            if (countY == 1 || countX == 0){
                break;
            }

            for (int y = width-2-i; y >= i+1 ; y--) {
                res[n++] = matrix[height-1-i][y];
            }

            for (int x = height-1-i; x >= i+1; x--) {
                res[n++] = matrix[x][i];
            }
        }
        return res;
    }

}
