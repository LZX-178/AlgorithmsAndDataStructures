package com.lzx.leetCode.chapter03_hashTable;

import java.util.ArrayList;

/**
 * @author LZX
 * @code @create 2022-11-23 17:09
 * 349. 两个数组的交集
 *      给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
 *      输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 */
public class Code_349_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] flags = new boolean[1000];
        for (int j : nums1) {
            flags[j-1] = true;
        }
        ArrayList<Integer> result = new ArrayList<>(Math.min(nums1.length, nums2.length));

        for (int j : nums2) {
            if (flags[j-1]) {
                result.add(j);
                flags[j-1] = false;
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
