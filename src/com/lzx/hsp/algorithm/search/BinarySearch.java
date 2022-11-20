package com.lzx.hsp.algorithm.search;

/**
 * @author LZX
 * @code @create 2022-08-02 7:04
 * 二分法查找
 */
public class BinarySearch {
    public int count;

    // 非递归二分查找, 未找到返回 -1, 找到返回索引
    public int searchNonRecursion(int[] array, int findValue){
        if (array == null || array.length < 1 ||
            findValue < array[0] || findValue > array[array.length-1]){
            return -1;
        }

        int i = 0;
        int j = array.length-1;
        int mid;
        while (i <= j){
            mid = i + ((j-i)>>1);
            System.out.println( " searchNonRecursion " + ++count +
                    " findValue = " + findValue +
                    " array[start] = " + array[i] +
                    " array[end] = " + array[j] +
                    " start = " + i +
                    " end = " + j +
                    " mid = " + mid);
            if (array[mid] == findValue) {
                return mid;
            }
            if (array[mid] > findValue){
                j = mid - 1;
            }else {
                i = mid + 1;
            }
        }
        return -1;
    }

    // 递归二分查找, 未找到返回 -1, 找到返回索引
    public int search(int[] array, int findValue){
        if (array == null || array.length < 1 ||
            findValue < array[0] || findValue > array[array.length-1]){
            return -1;
        }
        count = 0;
        return search(array, findValue, 0, array.length-1);
    }
    public int search(int[] array, int findValue, int start, int end){
        if (start > end){
            return -1;
        }
        int mid = start + ((end - start)>>1);
        System.out.println( " search " + ++count +
                " findValue = " + findValue +
                " array[start] = " + array[start] +
                " array[end] = " + array[end] +
                " start = " + start +
                " end = " + end +
                " mid = " + mid);
        int midValue = array[mid];
        if (midValue == findValue){
            return mid;
        }else if (midValue > findValue){
            return search(array, findValue, start, mid-1);
        }else {
            return search(array, findValue, mid+1, end);
        }
    }
}
