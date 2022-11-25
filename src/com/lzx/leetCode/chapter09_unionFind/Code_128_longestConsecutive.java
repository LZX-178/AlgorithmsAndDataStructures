package com.lzx.leetCode.chapter09_unionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-26 15:40:44
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，
 * 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 其实不用并集查集会更快
 *
 */
public class Code_128_longestConsecutive {

    public HashSet<Integer> nodes = new HashSet<>();
    public HashMap<Integer, Integer> parents = new HashMap<>();
    public HashMap<Integer, Integer> sizeMap = new HashMap<>();
    public int maxSize = 1;

    private Integer findParent(Integer value){
        Stack<Integer> path = new Stack<>();

        Integer parent = parents.get(value);
        while (!Objects.equals(parent, value)){
            path.push(value);
            value = parent;
            parent = parents.get(value);
        }

        while (!path.isEmpty()){
            parents.put(path.pop(), value);
        }

        return value;
    }

    private void union(Integer left, Integer right) {
        Integer headLeft = findParent(left);
        Integer headRight = findParent(right);

        Integer sizeLeft = sizeMap.get(headLeft);
        Integer sizeRight = sizeMap.get(headRight);

        if (sizeLeft < sizeRight){
            parents.put(headLeft, headRight);
            sizeMap.put(headRight,sizeLeft + sizeRight);
            maxSize = Math.max(sizeLeft + sizeRight, maxSize);
            sizeMap.remove(headLeft);
        }else {
            parents.put(headRight, headLeft);
            sizeMap.put(headLeft, sizeLeft + sizeRight);
            maxSize = Math.max(sizeLeft + sizeRight, maxSize);
            sizeMap.remove(headRight);
        }
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 1){
            return 0;
        }
        for (int value : nums) {
            if (nodes.contains(value)){
                continue;
            }

            nodes.add(value);
            parents.put(value,value);
            sizeMap.put(value, 1);

            if (nodes.contains(value-1)){
                union(value-1, value);
            }
            if (nodes.contains(value+1)){
                union(value+1, value);
            }
        }
        return maxSize;
    }
}
