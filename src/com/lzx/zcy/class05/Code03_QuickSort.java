package com.lzx.zcy.class05;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;

/**
 * @author LZX
 * @code @create 2022-09-10 12:12:23
 */
public class Code03_QuickSort {
    // 荷兰国旗问题
    // 在数组的 [l, r] 范围上以 arr[r] 为标准进行划分, 划分为 小于区域, 等于区域, 大于区域,
    // 返回值为 等于区域 的左右边界
    // 默认 l 和 r 的值合法, 且 l < r
    public int[] netherlandsFlag(int[] arr, int l, int r){

        int less = l-1;
        int more = r;
        int i = l;

        while (i < more){
            if (arr[i] == arr[r]){
                i++;
            }else if (arr[i] < arr[r]){
                ArrayUtils.swap(arr, ++less, i++);
            }else {
                ArrayUtils.swap(arr, i, --more);
            }
        }
        ArrayUtils.swap(arr, r, more);

        return new int[]{less+1, more};
    }
    // 快速排序
    public void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r){
            return;
        }
        // 如果不做随机交换的话
        // 最坏时间复杂度是 O( n * n )
        int randomIndex = NumberUtils.getRandomInt(l, r);
        ArrayUtils.swap(arr, r, randomIndex);
        int[] res = netherlandsFlag(arr, l, r);
        quickSort(arr, l, res[0]-1);
        quickSort(arr, res[1]+1, r);
    }
}
