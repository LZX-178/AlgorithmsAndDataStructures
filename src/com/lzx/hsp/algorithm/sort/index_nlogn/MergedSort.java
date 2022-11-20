package com.lzx.hsp.algorithm.sort.index_nlogn;

/**
 * @author LZX
 * @code @create 2022-07-28 16:48
 * 归并排序
 */
public class MergedSort {
    public void sort(int[] array){
        int mid = (array.length)>>1;
        sort(array, 0, mid, array.length-1);
    }
    public void sort(int[] array, int start, int mid, int end){
        if (start >= end){
            return;
        }

        sort(array, start, start + ((mid-start)>>1), mid);
        sort(array, mid+1, mid + 1 + ((end-mid-1)>>1), end);

        int[] tempArr = new int[end - start + 1];
        // i 为第一个数组下一个待拷贝的元素
        int i = start;
        // j 为第二个数组下一个待拷贝的元素
        int j = mid+1;
        // tempArr的索引
        int u = 0;
        int k;
        while (true) {
            if (array[i] <= array[j]){
                tempArr[u++] = array[i++];
            }else {
                tempArr[u++] = array[j++];
            }
            if (i > mid){
                k = j;
                break;
            }
            if (j > end){
                k = i;
                break;
            }
        }
        // 有待改进 : array的元素不用移动, 或者直接后移
        for(; u < tempArr.length; u++){
            tempArr[u] = array[k];
            k++;
        }
        System.arraycopy(tempArr, 0, array, start, tempArr.length);
    }
}
