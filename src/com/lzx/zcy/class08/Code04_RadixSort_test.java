package com.lzx.zcy.class08;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-12 13:56:51
 */
public class Code04_RadixSort_test {

    private final Code04_RadixSort sort = new Code04_RadixSort();

    @Test
    public void test_radixSort1() {
        for (int i = 0; i < 10; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(20, 0, 200);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            System.out.println("array      = " + Arrays.toString(array1));
            sort.radixSort(array1);
            System.out.println("countSort  = " + Arrays.toString(array1));
            Arrays.sort(array2);
            System.out.println("comparator = " + Arrays.toString(array2));
            System.out.println();

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }
    }
    @Test
    public void test_radixSort2() {
        for (int i = 0; i < 1000000; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(200, 0, 1000);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            sort.radixSort(array1);
            Arrays.sort(array2);

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }

        System.out.println("ok");
    }
}
