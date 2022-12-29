package com.lzx.zcy.class19;

import org.junit.Test;

import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-29 12:15:42
 * 85. 最大矩形
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 */
public class Code04_MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int max;

        // 遍历 rows 行
        // 对每一行 i, 统计以 i 为底的直方图
        // 求直方图的最大矩形, 问题回到 Code03_LargestRectangleInHistogram
        for (int i = 0; i < cols; i++) {
            heights[i] = matrix[0][i] - '0';
        }
        max = largestRectangleArea2(heights);
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == '0'? 0 : heights[j]+1;
            }
            max = Math.max(max, largestRectangleArea2(heights));
        }
        return max;
    }
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int len = heights.length;
        int[] newHeights = new int[len+2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        heights = newHeights;

        stack.push(0);
        for (int i = 1; i < newHeights.length; i++) {
            while (heights[stack.peek()] > heights[i]){
                Integer t = stack.pop();

                while (heights[stack.peek()] == heights[t]){
                    stack.pop();
                }
                int l = stack.peek()+1;
                max = Math.max(max, (i-l) * heights[t]);
            }
            stack.add(i);
        }
        return max;
    }

    @Test
    public void test_maximalRectangle() {
        char[][] matrix = {
                {'0', '1'},
                {'0', '1'}
        };
        int ans = maximalRectangle(matrix);
        System.out.println("ans = " + ans);
    }
}
