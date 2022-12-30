package com.lzx.zcy.class19;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-27 18:56:53
 * *********************** 待优化 : 哨兵 + 数组实现栈 ***********************
 *
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *  例 :
 *      输入：heights = [2,1,5,6,2,3]
 *      输出：10
 *      解释：最大的矩形为图中红色区域 ( 5 + 6 )，面积为 10
 */
public class Code03_LargestRectangleInHistogram {


    public int largestRectangleArea1(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < heights.length; i++) {

            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                Integer t = stack.pop();

                while (!stack.isEmpty() && heights[stack.peek()] == heights[t]){
                    stack.pop();
                }
                int l = stack.isEmpty()? 0 : stack.peek()+1;
                max = Math.max(max, (i-l) * heights[t]);
            }

            stack.add(i);
        }

        while (!stack.isEmpty()){
            Integer t = stack.pop();

            while (!stack.isEmpty() && heights[stack.peek()] == heights[t]){
                stack.pop();
            }
            int l = stack.isEmpty()? 0 : stack.peek()+1;
            max = Math.max(max, (heights.length-l) * heights[t]);
        }

        return max;
    }

    // 使用哨兵进行优化 在 heights 前后分别添加一个高度为 0 的矩形
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

    // 不知名优化
    public int largestRectangleArea3(int[] heights) {
        int maxArea = 0, top = 0, topValue = 0;
        int[] stack = new int[heights.length + 1];
        int[] index = new int[heights.length + 1];

        for (int i = 0; i < heights.length; i++) {
            while (heights[i] < stack[top]) {
                topValue = stack[top];
                while (stack[--top] == topValue) {}
                maxArea = Math.max(maxArea, topValue * (i - index[top]));
            }
            stack[++top] = heights[i];
            index[top] = i + 1;
        }
        while (stack[top] > 0) {
            topValue = stack[top];
            while (stack[--top] >= topValue){}
            maxArea = Math.max(maxArea, topValue * (heights.length - index[top]));
        }
        return maxArea;
    }

    // 官方解
    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }



    @Test
    public void test_largestRectangleArea() {
        int[] heights = {2,1,5,6,2,3};
        int ans = largestRectangleArea2(heights);
        System.out.println("ans = " + ans);
    }
}
