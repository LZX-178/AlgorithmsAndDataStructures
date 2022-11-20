package com.lzx.zcy.class15;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-15 10:29:20
 * 给定两个长度都为 N 的数组 weights 和 values
 * weights 和 values 分别代表 i 号物品的重量和价值, 都为正数
 * 给定一个正数 bag，表示一个载重 bag 的袋子，
 * 你装的物品不能超过这个重量
 * 返回你能装下最多的价值是多少？
 */
public class Code07_Knapsack {
    public int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag <= 0) {
            return 0;
        }
        return maxValue1(w, v, bag, 0);
    }
    private int maxValue1(int[] w, int[] v, int bag, int index) {
        // 返回 只从 [index, end] 上做选择时, 能装下的最大价值, bag 表示剩余的容量
        // 由于之前选择的货物已经 把其价值加在结果上了, 此处返回 0
        if (index == w.length){
            return 0;
        }
        // 当前货物的重量太大, 只能放弃
        if (w[index] > bag){
            return maxValue1(w, v, bag, index+1);
        }else {
            // 取两种情况的最大值
            //      装当前货物, 或者放弃当前货物
            return Math.max(
                    maxValue1(w, v, bag, index+1),
                    maxValue1(w, v, bag-w[index], index+1)+v[index]);
        }
    }

    // 对数器
    public int maxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }
    public int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    // 将 maxValue1 改成动态规划
    public int maxValue3(int[] w, int[] v, int bag){
        int[][] result = new int[w.length][bag + 1];
        int end = w.length-1;
        if (w[end] <= bag){
            for (int i = w[end]; i <= bag; i++) {
                result[end][i] = v[end];
            }
        }
        for (int i = end - 1; i >= 0 ; i--) {
            for (int b = 1; b <= bag; b++) {
                if (w[i] > b){
                    result[i][b] = result[i+1][b];
                }else {
                    result[i][b] = Math.max(result[i+1][b], result[i+1][b-w[i]] + v[i]);
                }
            }
        }
        return result[0][bag];
    }

    @Test
    public void test_maxValue() {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
        System.out.println(maxValue3(weights, values, bag));
    }

    @Test
    public void test_maxValue2() {
        for (int i = 0; i < 500000; i++) {
            int[] weights = ArrayUtils.generateRandomArray(7, 7, 1, 19);
            int[] values = ArrayUtils.generateRandomArray(7, 7, 1, 19);
            int r1 = maxValue1(weights, values, 50);
            int r2 = maxValue2(weights, values, 50);
            int r3 = maxValue3(weights, values, 50);
            if (r1 != r2 || r2 != r3){
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
