package com.lzx.hsp.algorithm.sort.index_nlogn;

/**
 * @author LZX
 * @code @create 2022-07-28 16:49
 * 基数排序
 */
public class RadixSort {
    public void sort(int[] array){
        // 10 个桶
        int[][] bucket = new int[10][array.length];
        // 记录各个桶中的元素个数
        int[] counts = new int[10];
        int offset = 1;
        while (true){
            // 把数放入桶中
            for (int k : array) {
                // index 表示应该放入哪个桶中
                int index = (k / offset) % 10;
                bucket[index][counts[index]++] = k;
            }
            // 判断是否所有数都放在同一个桶中
            if (counts[0] == array.length){
                break;
            }
            // 把数从桶中取出来
            // 遍历 10 个桶
            for (int i = 0, u = 0; i < counts.length; i++) {
                // 遍历每个桶
                for (int j = 0; j < counts[i]; j++) {
                    array[u++] = bucket[i][j];
                }
                // 注意 : 取完数后要将 counts 置零, 否则下一轮循环要出错
                counts[i] = 0;
            }

            offset *= 10;
        }
    }
}
