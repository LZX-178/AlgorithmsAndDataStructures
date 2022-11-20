package com.lzx.hsp.algorithm.sort.index_nlogn;

/**
 * @author LZX
 * @code @create 2022-07-28 16:49
 * 希尔排序
 * 把 array 按下标的 某个增量 进行分组， 对每组使用直接插入排序算法排序；
 * 当增量减至 1 时，算法退化为插入排序, 但此时大部分元素需要移动的次数不会很多
 */
public class ShellSort {
    public void sort(int[] array){
        // 增量由 array.length 开始, 多次减半后为 1
        int gap = array.length / 2;
        while (gap > 0 ){
            sort(array, gap);
            gap /= 2;
        }
    }
    // 对array中同一组的元素进行排序, 每个元素索引相差 gap
    public void sort(int[] array, int gap){
        if (array.length < 2){
            return;
        }
        // startIndex 表示每一组的起始索引
        for (int startIndex = 0; startIndex < gap; startIndex++) {
            // 对每一组的元素进行插入排序
            for (int j = startIndex + gap; j < array.length; j += gap) {
                int insertValue = array[j];
                int insertIndex = j;
                // 注意: 这里的两个条件的先后顺序不能颠倒, 否则索引溢出
                while (insertIndex > startIndex && array[insertIndex - gap] > insertValue){
                    array[insertIndex] = array[insertIndex - gap];
                    insertIndex -= gap;
                }
                array[insertIndex] = insertValue;
            }
        }
    }
}
