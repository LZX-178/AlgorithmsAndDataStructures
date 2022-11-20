package com.lzx.hsp.algorithm.sort.index_nlogn;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-28 17:00
 */
public class RadixSortTest {
    private final int[] array = {8888, 9, 5, 555, 6, 7, 1, 8, 45136, 2, 11, 0, 4, 3, 53, 3, 542, 748, 14, 214};

    @Test
    public void test_sort() {
        RadixSort sort = new RadixSort();
        System.out.println(Arrays.toString(array));
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void test_bigArray() {
        RadixSort sort = new RadixSort();
        int[] bigArray = new int[80000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (int) (Math.random() * 80000) + 1;
        }
        long t1 = System.currentTimeMillis();
        sort.sort(bigArray);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 15   397
    }
}
