package com.lzx.zcy.class15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-11-13 19:05:53
 * 汉诺塔问题
 */
public class Code02_hanota {
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
    // 过程中 B 是可以用的
    private void hanota(List<Integer> A, List<Integer> B, List<Integer> C, int num){
        if (num == 1){
            C.add(A.remove(A.size()-1));
            printHanota();
        }else {
            // 当 num 不是1 时,  默认 B, C 上的盘子 在移动这 num 个盘子时是 "足够大" 的
            // 并且在移动过程中也维护这个规则
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
