package com.lzx.zcy.class19;

import org.junit.Test;

import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-29 16:23:30
 * 1504. 统计全 1 子矩形
 * <p>
 * 给你一个 m x n 的二进制矩阵 mat ，
 * 请你返回有多少个 子矩形 的元素全部都是 1 。
 * <p>
 * 输入：mat = [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * 输出：13
 * 解释：
 * 有 6个 1x1 的矩形。
 * 有 2 个 1x2 的矩形。
 * 有 3 个 2x1 的矩形。
 * 有 1 个 2x2 的矩形。
 * 有 1 个 3x1 的矩形。
 * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13。
 * <p>
 * 1 <= m, n <= 150
 * mat[i][j] 仅包含 0 或 1
 */
public class Code05_CountSubMatricesWithAllOnes {
    public int num;

    public int numSubmat(int[][] mat) {
        num = 0;
        int rows = mat.length;
        int cols = mat[0].length;
        int[] heights = new int[cols];
        int max;

        // 遍历 rows 行
        // 对每一行 i, 统计以 i 为底的直方图
        // 求直方图的中所有以 i 为底的子矩形的个数, 问题回到 Code03_LargestRectangleInHistogram
        System.arraycopy(mat[0], 0, heights, 0, cols);
        largestRectangleArea2(heights);
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            largestRectangleArea2(heights);
        }
        return num;
    }

    public void largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        heights = newHeights;

        stack.push(0);
        for (int i = 1; i < newHeights.length; i++) {
            while (heights[stack.peek()] > heights[i]) {
                Integer t = stack.pop();

                while (heights[stack.peek()] == heights[t]) {
                    stack.pop();
                }
                // 弹出 t 时, 可以得到一个 高度为 heights[t], 宽度为 w (尽量往两边扩) 的矩形
                // 在 w 维度, 有 (w + 1) * w / 2) 种划分 (子矩形宽度 从 1 到 w)
                // 在 h 维度, 有 heights[t] 种划分 ( 必须以 i 为底),
                //      但是 heights[t] 不能全算进去,
                //      需要 减去 左右两边的最大高度 Math.max(heights[i], heights[stack.peek()])
                //      这样, 在 弹出 左右两边时才不会算重
                int w = i - stack.peek() - 1;
                int h = heights[t] - Math.max(heights[i], heights[stack.peek()]);
                num += ((w + 1) * w / 2) * h;
            }
            stack.add(i);
        }
    }

    @Test
    public void test_numSubmat() {
        int[][] mat = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };

        int ans = numSubmat(mat);
        System.out.println("ans = " + ans);
    }
}
