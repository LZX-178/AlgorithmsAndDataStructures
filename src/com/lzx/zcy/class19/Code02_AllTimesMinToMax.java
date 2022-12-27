package com.lzx.zcy.class19;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-27 18:03:45
 * 给定一个只包含正数的数组 arr，对于 arr 中任何一个子数组 sub，
 * 计算 aim = (sub累加和) * (sub中的最小值)
 * 求 aim 的最大值 maxAim
 */
public class Code02_AllTimesMinToMax {
    // 对数器
    public int max0(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    // 对于需要求的 maxAim, 一定来自 某个子数组的累加和 * 这个子数组的最小值
    // 遍历 arr 的所有元素 i,
    //      将 i 当成某个子数组的最小值, 求这个子数组的最大累加和
    //      找到 i 左右两边离它最近的 小于i 的元素, 即可得到这个子数组
    //      使用单调栈即可完成这件事
    public int max1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // [i, j] 的累加和 = prefixSum[j+1] - prefixSum[i]
        int[] prefixSum = new int[arr.length + 1];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i-1] + arr[i-1];
        }

        int maxAim = Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                Integer t = stack.pop();

                while (!stack.isEmpty() && arr[stack.peek()] == arr[t]){
                    stack.pop();
                }

                int l = stack.isEmpty() ? 0 : stack.peek()+1;
                maxAim = Math.max(maxAim, arr[t] * (prefixSum[i] - prefixSum[l]));
            }

            stack.add(i);
        }

        while (!stack.isEmpty()){
            Integer t = stack.pop();

            while (!stack.isEmpty() && arr[stack.peek()] == arr[t]){
                stack.pop();
            }

            int l = stack.isEmpty() ? 0 : stack.peek()+1;
            maxAim = Math.max(maxAim, arr[t] * (prefixSum[arr.length] - prefixSum[l]));
        }

        return maxAim;
    }

    @Test
    public void test_max1() {
        for (int i = 0; i < 500000; i++) {
            int[] arr = ArrayUtils.generateRandomArray(10, 50, 5, 20);

            int ans0 = max0(arr);
            int ans1 = max1(arr);

            if (ans0 != ans1){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
