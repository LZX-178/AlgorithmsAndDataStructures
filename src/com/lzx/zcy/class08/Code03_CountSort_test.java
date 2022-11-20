package com.lzx.zcy.class08;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-12 10:39:25
 *
 *
 */
public class Code03_CountSort_test {
    private final Code03_CountSort sort = new Code03_CountSort();

    @Test
    public void test_countSort1() {
        for (int i = 0; i < 10; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(20, 0, 200);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            System.out.println("array      = " + Arrays.toString(array1));
            sort.countSort(array1);
            System.out.println("countSort  = " + Arrays.toString(array1));
            Arrays.sort(array2);
            System.out.println("comparator = " + Arrays.toString(array2));

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }
    }

    @Test
    public void test_countSort2() {
        for (int i = 0; i < 1000000; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(200, 0, 200);
            int[] array2 = Arrays.copyOf(array1, array1.length);

            sort.countSort(array1);
            Arrays.sort(array2);

            if (!ArrayUtils.IntArrayIsEqual(array1, array2)){
                throw new RuntimeException("error");
            }
        }

        System.out.println("ok");
    }
}
