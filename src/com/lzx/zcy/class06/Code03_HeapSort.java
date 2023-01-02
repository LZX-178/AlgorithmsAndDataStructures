package com.lzx.zcy.class06;

import com.lzx.utils.*;

/**
 * @author LZX
 * @code @create 2022-09-11 14:05:35
 */
public class Code03_HeapSort {
    public void heapSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }

        // 1 将原数组调整成一个大顶堆
        // 方法一, 依次将元素加入堆 O( n * log n)
//        for (int i = 1; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        // 方法二, 将数组看成一个堆, 自底向上调整至合法  O( n )
        // 只需要从第一个 非叶子节点开始即
        //      2i+1 <= len-1
        // 解得   i <= (len-2) / 2
        for (int i = (arr.length-2)>>1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }

        // 2 排序, 依次从堆中取出元素放至数组最后  O( n * log n)
        // 可以看出, 如果只是堆排序的话, 是只需要 heapify 方法的
        for (int i = arr.length - 1; i >= 1; i--) {
            ArrayUtils.swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    // 将index位置的数向上浮, 默认 index 和它的子节点已经合法,
    // 只需要检验 index 和 其父节点 之间是否合法
    // 所以该结点移动到0位置，或者比父节点小, 停止移动
    private void heapInsert(int[] heap, int index){
        while (heap[index] > heap[(index-1)/2]){
            ArrayUtils.swap(heap, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    // 堆化 : 将 index 位置的数下沉, 默认 index 的子节点已经合法, 忽略 index 的父节点和 index 的合法性
    // 只需要检验 index 和 它的两个子节点 之间的合法性
    // 从 index 和 它的两个子节点 中选出最大的节点
    private void heapify(int[] heap, int heapSize, int index){
        int max = (index << 1) | 1;
        while (max <= heapSize - 1) {
            if (max+1 < heapSize && heap[max] < heap[max+1]){
                max++;
            }
            if (heap[index] >= heap[max]) {
                break;
            }
            ArrayUtils.swap(heap, index, max);
            index = max;
            max = (index << 1) | 1;
        }
    }
}
