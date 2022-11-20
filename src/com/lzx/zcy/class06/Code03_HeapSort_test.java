package com.lzx.zcy.class06;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-11 14:24:23
 */
public class Code03_HeapSort_test {
    @Test
    public void test_heapSort() {
        Code03_HeapSort heapSort = new Code03_HeapSort();

        for (int i = 0; i < 10; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(20, -100, 100);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            System.out.println("**************************");
            System.out.println("array      = " + Arrays.toString(array1));
            heapSort.heapSort(array1);
            System.out.println("heapSort   = " + Arrays.toString(array1));
            Arrays.sort(array2);
            System.out.println("comparator = " + Arrays.toString(array2));

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }
    }

    @Test
    public void test_heapSort2() {
        Code03_HeapSort heapSort = new Code03_HeapSort();

        for (int i = 0; i < 1000000; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(100, -100, 100);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            heapSort.heapSort(array1);
            Arrays.sort(array2);

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }
        System.out.println("ok");
    }

}
