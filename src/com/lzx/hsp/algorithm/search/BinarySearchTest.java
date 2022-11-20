package com.lzx.hsp.algorithm.search;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-02 7:10
 */
public class BinarySearchTest {
    private final int[] array = {8888, 9, 5, 555, 6, 7, 1, 8, 436, 2, 11, 0, 4, 3, 53, 3, 542, 748, 14, 214};
    @Test
    public void test_search() {
        Arrays.sort(array);
        BinarySearch binarySearch = new BinarySearch();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
        int[] findValues = {8888, 9, 5, 555, 6, 7, 1, 8, 436, 2, 11, 0, 4, 3, 53, 3, 542, 748, 14, 214, 22, 45136};
        for (int findValue : findValues) {
            int index = binarySearch.search(array, findValue);
            System.out.println("findValue = " + findValue + "\tindex = " + index);
        }

    }
    @Test
    public void test_searchNonRecursion() {
        Arrays.sort(array);
        BinarySearch binarySearch = new BinarySearch();
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
        int[] findValues = {8888, 9, 5, 555, 6, 7, 1, 8, 436, 2, 11, 0, 4, 3, 53, 3, 542, 748, 14, 214, 22, 45136};
        for (int findValue : findValues) {
            int index = binarySearch.searchNonRecursion(array, findValue);
            System.out.println("findValue = " + findValue + "\tindex = " + index);
        }

    }
}
