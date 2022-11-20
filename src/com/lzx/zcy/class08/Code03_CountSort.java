package com.lzx.zcy.class08;

/**
 * @author LZX
 * @code @create 2022-09-12 10:25:03
 *
 * 计数排序
 */
public class Code03_CountSort {
    // 约定 arr 的元素是在 [0, 200] 范围的
    // 统计原数组中各个数出现的次数, 再将出现过的数排列出来(每个数排列几次依据统计的次数)
    public void countSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] temp = new int[max + 1];

        for (int j : arr) {
            temp[j]++;
        }

        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            while (temp[i]-- != 0){
                arr[index++] = i;
            }
        }
    }
}
