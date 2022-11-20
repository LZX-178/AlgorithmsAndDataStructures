package com.lzx.hsp.algorithm.sort.index_nlogn;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-12 15:06:17
 */
public class HeapSortTest {
    private final int[] array = {9, 5, 6, 7, 1, 8, 2, 0, 4, 3};
    private final int[] array2 = {4, 6, 8, 5, 9};

    @Test
    public void test_sort() {
        HeapSort sort = new HeapSort();
        System.out.println(Arrays.toString(array));
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }
    @Test
    public void test_sort2() {
        HeapSort sort = new HeapSort();
        System.out.println(Arrays.toString(array2));
        sort.sort(array2);
        System.out.println(Arrays.toString(array2));
    }

    @Test
    public void test_bigArray() {
        HeapSort sort = new HeapSort();
        int[] bigArray = new int[80000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = (int) (Math.random() * 80000) + 1;
        }
        long t1 = System.currentTimeMillis();
        sort.sort(bigArray);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1); // 18   2427
    }

    @Test
    public void test_percolateDown() {
        HeapSort sort = new HeapSort();

        System.out.println(Arrays.toString(array2));
        sort.percolateDown(array2, array2.length, 1);
        System.out.println(Arrays.toString(array2));
        sort.percolateDown(array2, array2.length, 0);
        System.out.println(Arrays.toString(array2));

    }
}
