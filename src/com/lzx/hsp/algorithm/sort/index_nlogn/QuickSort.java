package com.lzx.hsp.algorithm.sort.index_nlogn;

/**
 * @author LZX
 * @code @create 2022-07-28 16:48
 * 快速排序
 */
public class QuickSort {
    public void sort(int[] array){
        sort(array, 0, array.length-1);
    }
    public void sort2(int[] array){
        sort2(array, 0, array.length-1);
    }
    // 写法一 : 以最后一个数为界
    public void sort(int[] array, int start, int end){
        if (start >= end){
            return;
        }
        // 约定 : i 之前的元素都小于或等于minValue, j 之后的元素都大于minValue
        int i = start;
        int j = end-1;
        while (i < j){
            while (array[i] <= array[end] && i < j){
                i++;
            }
            while (array[j] > array[end] && i < j){
                j--;
            }
            if (i == j){
                break;
            }
            swap(array, i, j);
            i++;
            j--;
        }
        if (array[i] <= array[end]){
            i = i+1;
        }
        if (i != end){
            swap(array, i, end);
        }
        sort(array, start, i-1);
        sort(array, i+1, end);
    }
    // 写法二 : 以中间某个数为界
    public void sort2(int[] array, int start, int end){
        if (start >= end){
            return;
        }
        int i = start;
        int j = end;
        int pivotIndex = start + ((end-start)>>1);
        int pivot = array[pivotIndex];
        while (i < j){
            while (array[i] < pivot){
                i++;
            }
            while (array[j] > pivot){
                j--;
            }
            if (i == j){
                break;
            }
            swap(array, i, j);
            if (array[i] == pivot){
                j--;
            }
            if (i == j){
                break;
            }
            if (array[j] == pivot){
                i++;
            }
        }
        sort2(array, start, i-1);
        sort2(array, i+1, end);
    }
    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
