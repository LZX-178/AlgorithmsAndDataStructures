package com.lzx.zcy.class12;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-25 18:40:57
 * 蛇形打印矩阵
 * 如 :
 *      1   2   3   4
 *      5   6   7   8
 *      9   10  11  12
 *  输出 :
 *      1 2 5 9 6 3 4 7 10 11 8 12
 *
 *  注意 坐标轴的名称,方向 和 矩阵的两个索引的顺序
 */
public class Code04_ZigZagPrintMatrix {

    public void printMatrixZigZag(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1){
            return;
        }
        int height = matrix.length - 1;
        int width = matrix[0].length - 1;

        int ax;
        int ay;
        int bx;
        int by;

        for (int i = 0; i <= height+width; i++) {
           if (i <= height){
               ay = 0;
               ax = i;
           }else {
               ay = i - height;
               ax = height;
           }

           if (i <= width){
               by = i;
               bx = 0;
           }else {
               by = width;
               bx = i - width;
           }

           if (i%2 == 0){
               while (ax >= bx){
                   System.out.println(matrix[ax--][ay++]);
               }
           }else {
               while (ax >= bx){
                   System.out.println(matrix[bx++][by--]);
               }
           }
        }
    }

    @Test
    public void test_printMatrixZigZag() {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);
    }

}
