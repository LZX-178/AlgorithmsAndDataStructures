package com.lzx.zcy.class23;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2023-01-02 16:37:18
 *
 * 在一个无序数组(长度为 n)中求一个第 k 小的数
 * 默认
 *      1 <= k <= n,
 */
public class Code01_FindMinKth {

    // 对数器
    public int minKth0(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k-1];
    }

    // 方法1
    // 利用大根堆，时间复杂度O(n *log k)
    public int minKth1(int[] arr, int k) {
        int len = arr.length;
        if (len == 1){
            return arr[0];
        }

        for (int i = (len-2)>>1 ; i >= 0; i--) {
            heapify(arr, i, len);
        }
        for (int i = 0; i < k - 1; i++) {
            swap(arr, 0, --len);
            heapify(arr, 0, len);
        }
        return arr[0];
    }
    public void heapify(int[] arr, int index, int length){
        int min = (index << 1) | 1;
        while (min < length){
            if (min+1 < length && arr[min] > arr[min+1]){
                min++;
            }
            if (arr[index] <= arr[min]){
                break;
            }
            swap(arr, index, min);
            index = min;
            min = (index << 1) | 1;
        }
    }
    private void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }




    @Test
    public void test_minKth0() {
        int[] arr0 = {0, 1};
        System.out.println("Arrays.toString(arr0) = " + Arrays.toString(arr0));
        int[] arr1 = {0, 1};
        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));

        int k = 2;

        int ans0 = minKth0(arr0, k);
        int ans1 = minKth1(arr1, k);

        System.out.println("k = " + k);
        System.out.println("ans0 = " + ans0);
        System.out.println("arr1 = " + ans1);
    }

    @Test
    public void test_minKth1() {
        for (int i = 0; i < 2000000; i++) {
            int[] arr0 = ArrayUtils.generateRandomArray(1, 50, -100, 100);
            int[] arr1 = Arrays.copyOf(arr0, arr0.length);


            int k = NumberUtils.getRandomInt(1, arr0.length);

            int ans0 = minKth0(arr0, k);
            int ans1 = minKth1(arr1, k);

            if (ans0 != ans1){
                System.out.println("Arrays.toString(arr0) = " + Arrays.toString(arr0));
                System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
                System.out.println("k = " + k);
                System.out.println("ans0 = " + ans0);
                System.out.println("arr1 = " + ans1);
            }
        }
        System.out.println("OK");
    }
}
