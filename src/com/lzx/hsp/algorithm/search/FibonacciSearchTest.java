package com.lzx.hsp.algorithm.search;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-03 9:09
 */
public class FibonacciSearchTest {
    private final int[] array = {88, 9, 5, 55, 6, 7, 1, 8, 43, 2, 11, 0, 4, 3, 53, 3, 52, 74, 14, 24};
    @Test
    public void test_search() {
        Arrays.sort(array);
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
        int[] findValues = {5, 11, 22, 555, 1, 45136, 8888, 0};

        for (int findValue : findValues) {
            int search = fibonacciSearch.search(array, findValue);
            System.out.println("findValue = " + findValue + "\tsearch = " + search);
        }
    }
}
