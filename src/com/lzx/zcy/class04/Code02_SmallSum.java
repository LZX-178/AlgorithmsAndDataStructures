package com.lzx.zcy.class04;

/**
 * @author LZX
 * @code @create 2022-09-08 13:18:41
 * 小和问题
 * 在一个数组中, 一个数左边比它小的数的总和, 叫这个数的小和,
 * 数组中所有数的小和之和, 叫该数组的小和
 * 例 : [1,3,4,2,5]
 * 小和为 : () + (1) +(1 + 3) +(1) +(1 + 3 + 4 + 2) = 16
 *
 * 等价为 :
 *      设 smallSum = 0;
 *      遍历数组, 对每一个元素 array[i],
 *          计算它右边有多少个数比它大 (count),
 *          smallSum += array[i] * count
 */
public class Code02_SmallSum {
    // 求一个数组的小和
    public int smallSum(int[] arr){
        if (arr == null || arr.length ==1){
            return 0;
        }
        return smallSum(arr, 0, arr.length-1);
    }
    // 求 [l, r] 之间的小和
    // 引理 :
    //      两个数组( L 和 R ) merge 成一个新数组 ( N ), N 的小和 = L 和 R 的小和 + merge 过程中产生的小和
    //      merge 过程中只有 L 中的元素会产生小和,
    //      对于 L 中某个元素来说, 对同为 L 组的元素来说, 已经累计过次数了, 只需要对 R 组中比它大的元素累计次数
    private int smallSum(int[] arr, int l, int r) {
        if (l == r){
            return 0;
        }
        int m = l + ((r-l)>>1);
        return smallSum(arr, l, m) + smallSum(arr, m+1, r) + merge(arr, l, m, r);
    }

    private int merge(int[] arr, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int[] temp = new int[r - l + 1];
        int t = 0;
        int smallSum = 0;
        while (i <= m && j <= r){
            if (arr[i] < arr[j]){
                smallSum += arr[i] * (r-j+1);
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= m){
            temp[t++] = arr[i++];
        }
        while (j <= r){
            temp[t++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, l, temp.length);
        return smallSum;
    }

    // 对数器
    public int comparator(int[] array){
        if (array == null || array.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                res += array[j] < array[i] ? array[j] : 0;
            }
        }
        return res;
    }
}
