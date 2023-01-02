package com.lzx.zcy.class23;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2023-01-02 18:56:56
 * 在一个无序数组(长度为 n)中求前 k 大的数
 * 默认
 *      1 <= k <= n,
 */
public class Code02_MaxTopK {

    // 方法1 : 对 arr 排序后返回 前 k 个数
    // 时间复杂度O(n * log n)
    public int[] maxTopK1(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[arr.length-i-1];
        }
        return result;
    }

    // 方法2 : 在 arr 上建堆 ( O(n) ), 拿出( O(log n) ) k个数( O(k) )返回
    // 时间复杂度O(n + k *log n)
    public int[] maxTopK2(int[] arr, int k) {
        int length = arr.length;
        int[] result = new int[k];

        for (int i = (length-2)>>1; i >= 0; i--) {
            heapify(arr, i, length);
        }

        for (int i = 0; i < k; i++) {
            result[i] = arr[0];
            swap(arr, 0, --length);
            heapify(arr, 0, length);
        }
        return result;
    }
    public void heapify(int[] arr, int index, int length){
        int max = (index << 1) | 1;

        while (max < length){
            if (max+1 < length && arr[max+1] > arr[max]){
                max++;
            }
            if (arr[index] >= arr[max]){
                break;
            }
            swap(arr, index, max);
            index = max;
            max = (index << 1) | 1;
        }

    }
    private void swap(int[] arr, int i, int j) {
        if (i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 方法3 : 改写快排, 使用快排找到 第 k 大的数 ( O(n) ), 对 [0, k-1] 范围的数排序 ( O(k * log k) )
    // 即 对于 [k, end] 范围内的数只需找出来即可, 不必进行排序
    // 时间复杂度O(n + k * log k)
    public int[] maxTopK3(int[] arr, int k) {
        process(arr, 0, arr.length-1, k-1);
        int[] result = new int[k];
        System.arraycopy(arr, 0, result, 0, k);
        return result;
    }
    private void process(int[] arr, int start, int end, int k) {
        if (start >= end || start > k){
            return;
        }
        int[] res = partition(arr, start, end);

        int l = res[0];
        int r = res[1];

        process(arr, start, l-1, k);
        process(arr, r+1, end, k);
    }
    private int[] partition(int[] arr, int start, int end) {
        int l = start, m = start+1, r = end+1;

        while (m < r){
            if (arr[m] > arr[start]){
                swap(arr, ++l, m++);
            }else if (arr[m] == arr[start]){
                m++;
            }else {
                swap(arr, m, --r);
            }
        }
        swap(arr, start, l);
        return new int[]{l, r-1};
    }

    @Test
    public void test_maxTopK1() {
        for (int i = 0; i < 500000; i++) {
            int[] arr1 = ArrayUtils.generateRandomArray(1, 50, -100, 100);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            int[] arr3 = Arrays.copyOf(arr1, arr1.length);

            int k = NumberUtils.getRandomInt(1, arr1.length);

            int[] r1 = maxTopK1(arr1, k);
            int[] r2 = maxTopK2(arr2, k);
            int[] r3 = maxTopK3(arr3, k);

            for (int j = 0; j < k; j++) {
                if (r1[j] != r2[j] || r2[j] != r3[j]){
                    System.out.println("k = " + k);
                    System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
                    System.out.println("Arrays.toString(arr2) = " + Arrays.toString(arr2));
                    System.out.println("Arrays.toString(arr3) = " + Arrays.toString(arr3));
                    System.out.println("Arrays.toString(r1) = " + Arrays.toString(r1));
                    System.out.println("Arrays.toString(r2) = " + Arrays.toString(r2));
                    System.out.println("Arrays.toString(r3) = " + Arrays.toString(r3));
                    throw new RuntimeException("error");
                }

            }
        }
        System.out.println("OK");
    }
}
