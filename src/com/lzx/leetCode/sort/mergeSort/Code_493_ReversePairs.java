package com.lzx.leetCode.sort.mergeSort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-24 16:17:31
 * 493. 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 返回给定数组中的重要翻转对的数量。
 *
 * 给定数组的长度不会超过 50000。
 * 输入数组中的所有数字都在 32 位整数的表示范围内。
 */
public class Code_493_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        return reversePairs(nums, 0, nums.length-1);
    }

    private int reversePairs(int[] nums, int start, int end) {
        if (start >= end){
            return 0;
        }

        int mid = start + ((end-start)>>1);

        // 引理, 一个数组的翻转对数量 = 左数组的翻转对数量 + 右数组的翻转对数量 + merge过程中产生的翻转对数量
        return  reversePairs(nums, start, mid) +
                reversePairs(nums, mid+1, end) +
                merge2(nums, start, mid, end);
    }
    // 方法1 : 默认 start < end
    private int merge1(int[] nums, int start, int mid, int end) {
        int ans = 0;
        int i = start, j = mid + 1, index = 0;
        int[] arr = new int[end - start + 1];

        while (i <= mid && j <= end){

            if (nums[i] <= nums[j]){
                // 如果 nums[i] 为最小数,
                //      当 nums[i] < 0 时, 依然可能存在 nums[i] > 2*nums[j],
                //      因为 nums[j] 可能为负数
                arr[index++] = nums[i];
                if (nums[i] < 0){
                    for (int jj = j; jj <= end; jj++) {
                        if (nums[i]/2.0 > nums[jj]){
                            ans++;
                        }
                    }
                }
                i++;
            }else {
                // 如果 nums[j] 为最小数,
                //      去左数组寻找 第一个大于 2*nums[j] 的 nums[i]
                arr[index++] = nums[j];
                int ii;
                for (ii = i; ii <= mid; ii++) {
                    if (nums[ii]/2.0 > nums[j]){
                        break;
                    }
                }
                ans += mid - ii + 1;
                j++;
            }
        }
        while (i <= mid){
            arr[index++] = nums[i++];
        }
        while (j <= end){
            arr[index++] = nums[j++];
        }
        System.arraycopy(arr, 0, nums, start, arr.length);
        return ans;
    }

    // 方法 2 : 考察 方法 1,
    //      每次处理最小数时, 都要去从头遍历另一个子数组
    //      没有用到当前子数组的 有序性, 先遍历两个子数组一遍, 将翻转对找出来
    //      和处理 逆序对 的不同之处在于
    //          1 逆序对只有在 右数组输出 nums[j] 才会产生
    //          2 逆序对不必检查另一个子数组元素的合法性
    private int merge2(int[] nums, int start, int mid, int end) {
        int ans = 0;
        int i = start, j = mid + 1, index = 0;
        int[] arr = new int[end - start + 1];

        while (i <= mid && j <= end){
//            if (nums[i]/2.0 <= nums[j]){
            if (!myCompare(nums[i], nums[j])){
                i++;
            }else {
                ans += mid-i+1;
                j++;
            }
        }

        i = start;
        j = mid + 1;
        while (i <= mid && j <= end){
            if (nums[i] <= nums[j]){
                arr[index++] = nums[i++];
            }else {
                arr[index++] = nums[j++];
            }
        }
        while (i <= mid){
            arr[index++] = nums[i++];
        }
        while (j <= end){
            arr[index++] = nums[j++];
        }
        System.arraycopy(arr, 0, nums, start, arr.length);
        return ans;
    }

    // i 和 j 是 int 范围的整数, 判断 i > 2*j
    private boolean myCompare(int i, int j) {

//        if (i>>1 > j){
//            return true;
//        }else if (i>>1 == j){
//            return (i&1) == 1;
//        }else {
//            return false;
//        }
        // 化简
        //return i>>1 > j ? true : i>>1 == j ? (i&1) == 1 : false;
        // 再化简
        return i >> 1 > j || (i >> 1 == j && (i & 1) == 1);
    }

    @Test
    public void test_reversePairs() {
        int[] arr = {1,3,2,3,1};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        int ans = reversePairs(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("ans = " + ans);
    }

    @Test
    public void test_1() {
        int n1 = Integer.MIN_VALUE;
        int n2 = Integer.MIN_VALUE >> 1;
        int n3 = (Integer.MIN_VALUE >> 1) + 1;
        int n4 = (Integer.MIN_VALUE >> 1) - 1;
        int n5 = n3 >> 1;


        System.out.printf("n1 =  %xd,  %d\n", n1, n1);
        System.out.println(Integer.toBinaryString(n1));
        System.out.println();
        System.out.printf("n2 =  %xd,  %d\n", n2, n2);
        System.out.println(Integer.toBinaryString(n2));
        System.out.println();
        System.out.printf("n3 =  %xd,  %d\n", n3, n3);
        System.out.println(Integer.toBinaryString(n3));
        System.out.println();
        System.out.printf("n4 =  %xd,  %d\n", n4, n4);
        System.out.println(Integer.toBinaryString(n4));
        System.out.println();
        System.out.printf("n5 =  %xd,  %d\n", n5, n5);
        System.out.println(Integer.toBinaryString(n5));

    }
}
