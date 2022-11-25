package com.lzx.leetCode.chapter11_DP;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-11-13 18:11:19
 * 面试题 08.06. 汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 *      (1) 每次只能移动一个盘子;
 *      (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 *      (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *
 * 你需要原地修改栈。
 *
 */
public class Code_ms_0806_hanota {
    List<Integer> A;
    List<Integer> B;
    List<Integer> C;

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if (A.size() > 0){
            printHanota();
            hanota(A, B, C, A.size());
        }
    }

    // 将 A 的柱子上面的 num 个盘子, 移动到 C,
    private void hanota(List<Integer> A, List<Integer> B, List<Integer> C, int num){
        if (num == 1){
            C.add(A.remove(A.size()-1));
            printHanota();
        }else {
            //当 num 不是1 时,  默认 B, C 上的盘子 在移动这 num 个盘子时是 "足够大" 的
            hanota(A, C, B, num-1);
            hanota(A, B, C, 1);
            hanota(B, A, C, num-1);
        }
    }

    private void printHanota(){
        int maxHeight = Math.max(A.size(), Math.max(B.size(), C.size()));
        System.out.println("***** " + "process" + " *****");
        System.out.println();
        for (int i = maxHeight - 1; i >= 0; i--) {
            if (i < A.size()){
                System.out.print(A.get(i) + "\t");
            }else {
                System.out.print("\t");
            }
            if (i < B.size()){
                System.out.print(B.get(i) + "\t");
            }else {
                System.out.print("\t");
            }
            if (i < C.size()){
                System.out.print(C.get(i) + "\t");
            }else {
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("A\tB\tC\t");
    }

    @Test
    public void test_hanota() {
        A = new ArrayList<>();
        B = new ArrayList<>();
        C = new ArrayList<>();
        A.add(3);
        A.add(2);
        A.add(1);
        A.add(0);
        hanota(A, B, C);
    }
}
