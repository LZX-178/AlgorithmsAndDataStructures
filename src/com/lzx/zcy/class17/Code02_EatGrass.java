package com.lzx.zcy.class17;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-25 15:16:16
 * 给定一个正整数N，表示有 N 份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是
 * 1，4，16，64等（4的某次方）
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢都会做出理性的决定
 * 根据唯一的参数N返回谁会赢
 */
public class Code02_EatGrass {

    public boolean cowWin1(int N){
        if (N < 0){
            return false;
        }
        if (N < 7){
            return N != 2 && N != 5;
        }
        // 穷举牛吃的个数的每一种可能性 : i = 1, 4, 16, 64 ...., 则羊面对的是 N-i 堆草
        // 问题转化为 牛在面对 N-i 时是否必输
        for (int i = 1; i < N; i*=4) {
            if (!cowWin1(N-i)){
                return true;
            }
            if (i > N/4){
                break;
            }
        }
        return false;
    }

    // 先用暴力解寻找规律
    // 发现 : 在 2 以后每 5 个数一个循环
    // 可以根据数学归纳法证明 :
    //      4的幂 一定是 以 1或6或4 结尾, 4的幂 模5 一定为 1 或 4
    public boolean cowWin2(int N){
        if (N < 3){
            return N != 2;
        }
        int temp = (N-3) % 5;
        return temp != 2 && temp != 4;
    }

    @Test
    public void test_cowWin() {
        for (int i = 0; i < 50; i++) {
            boolean res1 = cowWin1(i);
            boolean res2 = cowWin2(i);
            System.out.println(i + " : " + res1);
            System.out.println(i + " : " + res2);
            if (res1 != res2){
                throw new RuntimeException("error");
            }
        }
    }

    @Test
    public void test_mod() {
        int[][] inPut = {
                {-7, 4},
                {7, 4},
        };
        for (int[] ints : inPut) {
            System.out.println(ints[0] + " % " + ints[1] + " = " + ints[0] % ints[1]);
        }
    }


}
