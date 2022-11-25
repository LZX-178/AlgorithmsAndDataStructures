package com.lzx.leetCode.chapter12_math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author LZX
 * @create 2022-05-02 14:07
 *
 * 60. 排列序列
 *
 * 给出集合[1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * 给定 n 和 k，返回第 k 个排列。
 *
 */
public class Code_0060_PermutationSequence {
    @Test
    public void test1(){
        String permutation1 = getPermutation(3, 3);
        String permutation2 = getPermutation(4, 9);
        String permutation3 = getPermutation(3, 1);
        String permutation4 = getPermutation(2, 2);
        String permutation5 = getPermutation(3, 2);
        String permutation6 = getPermutation(1, 1);
        System.out.println(permutation1);
        System.out.println(permutation2);
        System.out.println(permutation3);
        System.out.println(permutation4);
        System.out.println(permutation5);
        System.out.println(permutation6);
    }

    public String getPermutation(int n, int k) {
        //记录结果
        StringBuilder reslut = new StringBuilder(n);
        //记录未插入的数字
        LinkedList<Integer> notUsed = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            notUsed.add(i+1);
        }
        // m 表示比当前的结果小的排列的个数, 当 m = 0 时,求解完成
        int m = k - 1;
        // listNum 记录各阶乘大小
        ArrayList<Integer> listNum = new ArrayList<>(n);

        listNum.add(1);
        for (int i = 0; i < n-2; i++) {
            listNum.add(listNum.get(i) * (i+2));
        }

        for (int i = listNum.size()-1; i >= 0; i--) {
            int q = m / listNum.get(i);
            m = m % listNum.get(i);
            reslut.append(notUsed.remove(q));
        }

        if (n != 1){
            reslut.append(notUsed.getFirst());
        }
        return reslut.toString();
    }
}


