package com.lzx.hsp.algorithm.sort.index_nlogn;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-28 17:00
 */
public class QuickSortTest {
    private final int[] array = {9, 5, 6, 7, 1, 8, 2, 0, 4, 3};

    @Test
    public void test_sort() {
        QuickSort sort = new QuickSort();
        System.out.println(Arrays.toString(array));
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void test_sort2() {
        QuickSort sort = new QuickSort();
        System.out.println(Arrays.toString(array));
        sort.sort2(array);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void test_bigArray() {
        QuickSort sort = new QuickSort();
        int[] bigArray = new int[8000000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (int) (Math.random() * 8000000) + 1;
        }
        long t1 = System.currentTimeMillis();
        sort.sort(bigArray);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 23  1050
    }
}
