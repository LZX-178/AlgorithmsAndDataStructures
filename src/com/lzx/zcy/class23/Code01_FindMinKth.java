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

    // *************************** 方法1 ***************************
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
        if (i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // *************************** 方法2 ***************************
    // 改写快排，时间复杂度O(N)
    public int minKth2(int[] arr, int k){
        // k 的意义变更为 arr 排序后 索引为 k 的数
        return process(arr, 0, arr.length-1, k-1);
    }
    private int process(int[] arr, int start, int end, int k) {
        if (start == end){
            return arr[k];
        }

        swap(arr, start, NumberUtils.getRandomInt(start, end));

        int[] res = partition(arr, start, end);
        int l = res[0];
        int r = res[1];

        if (k < l){
            return process(arr, start, l-1, k);
        }else if (k > r){
            return process(arr, r+1, end, k);
        }else {
            return arr[k];
        }
    }
    private int[] partition(int[] arr, int start, int end) {

        int l = start, m = start+1, r = end+1;

        while (m < r){
            if (arr[m] == arr[start]){
                m++;
            }else if (arr[m] < arr[start]){
                swap(arr, ++l, m++);
            }else {
                swap(arr, m, --r);
            }
        }
        swap(arr, start, l);
        return new int[]{l, r-1};
    }

    // *************************** 方法3 ***************************
    // 在方法2的基础上, 使用bfprt算法
    public int minKth3(int[] array, int k) {
        int[] arr = Arrays.copyOf(array, array.length);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }
    // arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
    public int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // L...R  每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition3(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }
    public int[] partition3(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }
    // arr[L...R]  五个数一组
    // 每个小组内部排序
    // 每个小组中位数领出来，组成marr
    // marr中的中位数，返回
    public int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            // L ... L + 4
            // L +5 ... L +9
            // L +10....L+14
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        // marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }



    @Test
    public void test_minKth0() {
        int[] arr0 = {-26, 87, 64};
        System.out.println("Arrays.toString(arr0) = " + Arrays.toString(arr0));
        int[] arr1 = {-26, 87, 64};
        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
        int[] arr2 = {-26, 64,  87,};
        System.out.println("Arrays.toString(arr2) = " + Arrays.toString(arr2));

        int k = 2;

        int ans0 = minKth0(arr0, k);
        int ans1 = minKth1(arr1, k);
        int ans2 = minKth2(arr2, k);

        System.out.println("k = " + k);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
        System.out.println("ans2 = " + ans2);
    }

    @Test
    public void test_minKth1() {
        for (int i = 0; i < 2000000; i++) {
            int[] arr0 = ArrayUtils.generateRandomArray(1, 3, -100, 100);
            int[] arr1 = Arrays.copyOf(arr0, arr0.length);
            int[] arr2 = Arrays.copyOf(arr0, arr0.length);
            int[] arr3 = Arrays.copyOf(arr0, arr0.length);


            int k = NumberUtils.getRandomInt(1, arr0.length);

            int ans0 = minKth0(arr0, k);
            int ans1 = minKth1(arr1, k);
            int ans2 = minKth2(arr2, k);
            int ans3 = minKth3(arr3, k);

            if (ans0 != ans2){
                System.out.println("Arrays.toString(arr0) = " + Arrays.toString(arr0));
                System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
                System.out.println("Arrays.toString(ans2) = " + Arrays.toString(arr2));
                System.out.println("Arrays.toString(ans3) = " + Arrays.toString(arr3));
                System.out.println("k = " + k);
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
