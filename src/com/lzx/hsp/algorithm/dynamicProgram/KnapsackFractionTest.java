package com.lzx.hsp.algorithm.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-17 15:14:38
 */
public class KnapsackFractionTest {
    @Test
    public void test_solution() {
        int[] weight = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int capacity = 4;

        KnapsackFraction knapsackFraction = new KnapsackFraction();
        int result = knapsackFraction.solution(weight, value, capacity);
        System.out.println("weight = " + Arrays.toString(weight));
        System.out.println("value = " + Arrays.toString(value));
        System.out.println("capacity = " + capacity);
        System.out.println("result = " + result);
    }
}
