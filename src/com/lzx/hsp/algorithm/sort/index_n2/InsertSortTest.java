package com.lzx.hsp.algorithm.sort.index_n2;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-28 17:00
 */
public class InsertSortTest {
    private final int[] array = {9, 5, 6, 7, 1, 8, 2, 0, 4, 3};

    @Test
    public void test_sort() {
        InsertSort sort = new InsertSort();
        System.out.println(Arrays.toString(array));
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void test_bigArray() {
        InsertSort sort = new InsertSort();
        int[] bigArray = new int[80000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (int) (Math.random() * 80000) + 1;
        }
        long t1 = System.currentTimeMillis();
        sort.sort(bigArray);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 1009
    }
}
