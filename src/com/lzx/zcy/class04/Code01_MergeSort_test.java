package com.lzx.zcy.class04;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-09-08 13:18:41
 *
 */
public class Code01_MergeSort_test {
    public Code01_MergeSort mergeSort = new Code01_MergeSort();

    @Test
    public void test_mergeSort1() {
        for (int i = 0; i < 10; i++) {
            int[] array = ArrayUtils.generateRandomArray(20, -10, 10);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            System.out.println("*********************************************");
            System.out.println("array      = " + Arrays.toString(array));
            mergeSort.mergeSort(array);
            System.out.println("mergeSort  = " + Arrays.toString(array));
            Arrays.sort(arrayCopy);
            System.out.println("comparator = " + Arrays.toString(arrayCopy));
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            System.out.println("isEqual = " + isEqual);
            if (!isEqual){
                System.out.println("array      = " + Arrays.toString(array));
                System.out.println("mergeSort  = " + Arrays.toString(array));
                System.out.println("comparator = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
    }
    @Test
    public void test_mergeSort2() {
        for (int i = 0; i < 1000; i++) {
            int[] array = ArrayUtils.generateRandomArray(200, -100, 100);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            mergeSort.mergeSort(array);
            Arrays.sort(arrayCopy);
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            if (!isEqual){
                System.out.println("array      = " + Arrays.toString(array));
                System.out.println("mergeSort  = " + Arrays.toString(array));
                System.out.println("comparator = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
        System.out.println("ok");
    }

    @Test
    public void test_mergeSortNotRecursion1() {
        for (int i = 0; i < 10; i++) {
            int[] array = ArrayUtils.generateRandomArray(15, -10, 10);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            System.out.println("*********************************************");
            System.out.println("array                  = " + Arrays.toString(array));
            mergeSort.mergeSortNonRecursion(array);
            System.out.println("mergeSortNotRecursion  = " + Arrays.toString(array));
            Arrays.sort(arrayCopy);
            System.out.println("comparator             = " + Arrays.toString(arrayCopy));
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            System.out.println("isEqual = " + isEqual);
            if (!isEqual){
                System.out.println("array                  = " + Arrays.toString(array));
                System.out.println("mergeSortNotRecursion  = " + Arrays.toString(array));
                System.out.println("comparator             = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
    }

    @Test
    public void test_mergeSortNotRecursion2() {
        for (int i = 0; i < 5000; i++) {
            int[] array = ArrayUtils.generateRandomArray(150, -100, 100);
            int[] arrayCopy = new int[array.length];
            System.arraycopy(array, 0, arrayCopy, 0, array.length);

            mergeSort.mergeSortNonRecursion(array);
            Arrays.sort(arrayCopy);
            boolean isEqual = ArrayUtils.IntArrayIsEqual(array, arrayCopy);
            System.out.println(i + " + " + isEqual);
            if (!isEqual){
                System.out.println("array      = " + Arrays.toString(array));
                System.out.println("mergeSort  = " + Arrays.toString(array));
                System.out.println("comparator = " + Arrays.toString(arrayCopy));
                throw new RuntimeException("error");
            }
        }
        System.out.println("ok");
    }
}
