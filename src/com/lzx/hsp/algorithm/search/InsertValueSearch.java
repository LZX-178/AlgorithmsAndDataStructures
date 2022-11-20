package com.lzx.hsp.algorithm.search;

/**
 * @author LZX
 * @code @create 2022-08-02 16:46
 * 差值查找
 */
public class InsertValueSearch {
    public int i;
    public int search(int[] array, int findValue){
        if (findValue > array[array.length-1] || findValue < array[0]){
            return -1;
        }
        i = 0;
        return search(array, findValue, 0, array.length-1);
    }
    public int search(int[] array, int findValue, int start, int end){
        if (start > end){
            return -1;
        }
        if (array[end] == array[start]){
            if (array[start] == findValue){
                return start;
            }else {
                return -1;
            }
        }
        // 在二分法的基础上改进 mid 的取值
        int mid = start + ((end - start) * (findValue - array[start]) / (array[end] - array[start])) ;
        System.out.println( " search " + ++i +
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
