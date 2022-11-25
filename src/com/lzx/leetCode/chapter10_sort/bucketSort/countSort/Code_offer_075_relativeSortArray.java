package com.lzx.leetCode.chapter10_sort.bucketSort.countSort;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-09-12 14:38:33
 * 剑指 Offer II 075. 数组相对排序
 * 给定两个数组，arr1 和arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 */
public class Code_offer_075_relativeSortArray {
    @Test
    public void test_relativeSortArray() {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        new Solution().relativeSortArray(arr1, arr2);
        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
    }

    private static class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int[] count = new int[arr2.length];
            for (int k : arr1) {
                int j;
                for (j = 0; j < arr2.length; j++) {
                    if (k == arr2[j]) {
                        count[j]++;
                        break;
                    }
                }
                if (j == arr2.length) {
                    heap.add(k);
                }
            }

            int u = 0;
            for (int i = 0; i < arr2.length; i++) {
                while (count[i]-- > 0){
                    arr1[u++] = arr2[i];
                }
            }
            while (u < arr1.length){
                arr1[u++] = heap.poll();
            }

            return arr1;
        }
    }
}
