package com.lzx.zcy.class04;


/**
 * @author LZX
 * @code @create 2022-09-08 13:18:41
 *
 */
public class Code01_MergeSort {

    /************ 递归方法实现归并排序 ************/
    public void mergeSort(int[] arr){
        if (arr == null || arr.length ==1){
            return;
        }
        mergeSort(arr, 0, arr.length-1);
    }
    public void mergeSort(int[] arr, int L, int R){
        if (L == R){
            return;
        }
        int M = L + ((R - L) >> 1);
        mergeSort(arr, L, M);
        mergeSort(arr, M+1, R);
        merge(arr, L, M, R);
    }
    public void merge(int[] arr, int L, int M, int R){
        int l = L;
        int r = M+1;
        int[] temp = new int[R - L + 1];
        int i = 0;

        while (l <= M && r <= R){
            temp[i++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
        }
        while (l <= M){
            temp[i++] = arr[l++];
        }
        while (r <= R){
            temp[i++] = arr[r++];
        }
        System.arraycopy(temp, 0, arr, L, temp.length);
    }

    /************ 非递归方法实现归并排序 ************/
    public void mergeSortNonRecursion(int[] arr){
        if (arr == null || arr.length ==1){
            return;
        }
        // size 表示每次 merge 时, 两个子数组的大小, 从 1 开始, 到 arr.length / 2 结束
        int size = 1;
        // 以 size 为步长, 对 array 进行 merge
        while (size < arr.length){
            // 最后一次 merge 时, 可能有两种情况
            //      情况1 : M >= arr.length-1
            //          不需要 merge, 因为最后一段的长度小于等于 size, 这种长度的字串已经有序
            //      情况2 : M < arr.length-1 && R >= arr.length-1
            //          R 要取 arr.length-1
            int L = 0;
            int M = L + size - 1;
            int R = Math.min(arr.length-1, M + size);
            while (L < arr.length){
                merge(arr, L, M, R);
                L = R + 1;
                // 注意要 减1
                M = L + size - 1;
                // 情况1
                if (M >= arr.length-1){
                    break;
                }
                R = Math.min(arr.length-1, M + size);
            }
            // 防止溢出 当 size 超过 10.5 亿时, 再执行 size <<= 1 可能会溢出
            if (size > (arr.length>>1)){
                break;
            }
            size <<= 1;
        }
    }

}
