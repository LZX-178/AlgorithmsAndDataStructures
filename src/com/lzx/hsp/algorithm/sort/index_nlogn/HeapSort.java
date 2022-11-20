package com.lzx.hsp.algorithm.sort.index_nlogn;

/**
 * @author LZX
 * @code @create 2022-08-12 15:06:07
 * 堆排序
 */
public class HeapSort {
    public void sort(int[] array){
        // 将数组调整为一个大顶堆, 从最后一个非叶子结点( 最后一个节点的父节点 ) 开始, 依次向前进行 向下渗透 即可
        int length = array.length;
        for (int i = (length-1) / 2; i >=0 ; i--) {
            percolateDown(array, length, i);
        }
        // 拿出堆中的最大节点后, 重新调整堆
        for (int i = length - 1; i >= 1; i--) {
            swap(array, 0 , i);
            length--;
            percolateDown(array, length, 0);
        }
    }

    // index 为要向下渗透的元素(约定, index 下的两颗子树已经符合大顶堆), length 为数组的当前长度
    public void percolateDown(int[] array, int length, int index){
        // 假定 index 位置为空
        int temp = array[index];
        // maxChild 表示最大的子节点, 非叶子节点的左孩子一定存在
        int child = getLeftChild(index);
        // 找到正确的index位置, 以放置 temp
        while (child < length) {
            // 找到最大的子节点
            if (child + 1 < length && array[child] < array[child+1]){
                child++;
            }
            // 如果子节点较小, 则渗透完毕, index位置正确
            if (array[child] < temp){
                break;
            }
            // 否则, index位置下移
            array[index] = array[child];
            index = child;
            child = getLeftChild(index);
        }
        // 放置 temp
        array[index] = temp;
    }

    public int getLeftChild(int i){
        return i * 2 + 1;
    }

    public void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
