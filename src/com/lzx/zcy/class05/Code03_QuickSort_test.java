package com.lzx.zcy.class05;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-10 12:46:20
 */
public class Code03_QuickSort_test {
    private final Code03_QuickSort quickSort = new Code03_QuickSort();

    @Test
    public void test_quickSort1() {
        for (int i = 0; i < 10; i++) {
            int[] array = ArrayUtils.generateRandomArray(20, -10, 10);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            System.out.println("*********************************************");
            System.out.println("array      = " + Arrays.toString(array));
            quickSort.quickSort(array);
            System.out.println("quickSort  = " + Arrays.toString(array));
            Arrays.sort(arrayCopy);
            System.out.println("comparator = " + Arrays.toString(arrayCopy));
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            System.out.println("isEqual = " + isEqual);
            if (!isEqual){
                System.out.println("array      = " + Arrays.toString(array));
                System.out.println("quickSort  = " + Arrays.toString(array));
                System.out.println("comparator = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
    }

    @Test
    public void test_quickSort2() {
        for (int i = 0; i < 100000; i++) {
            int[] array = ArrayUtils.generateRandomArray(200, -100, 100);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            quickSort.quickSort(array);
            Arrays.sort(arrayCopy);
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            if (!isEqual){
                System.out.println("array      = " + Arrays.toString(array));
                System.out.println("quickSort  = " + Arrays.toString(array));
                System.out.println("comparator = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
        System.out.println("ok");
    }
}
