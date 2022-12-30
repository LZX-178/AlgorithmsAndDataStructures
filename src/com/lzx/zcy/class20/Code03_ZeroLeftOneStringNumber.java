package com.lzx.zcy.class20;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-30 16:08:34
 *
 * 对于一个只由 0 和 1 两种字符组成的字符串
 * 如果该字符串中任何 0 字符的左边都有 1 紧挨着，认为这个字符串达标
 * 返回长度为 n 的达标的字符串的数量
 */
public class Code03_ZeroLeftOneStringNumber {

    // 对数器
    public int getNum0(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }


    // 可以认为该字符串只由 "1" 和 "10" 组成
    // 且任意一个达标字符串可以由 "1" 和 "10" 唯一地表示, 没有歧义 ( "1", "10", "101" 则不行)
    // 所以 f(n) = f(n-1) + f(n-2)
    //   f(1) = 1
    //   f(2) = 2 ("10", "11")
    // 由递推式 :
    //      [f(n), f(n-1)] = [f(n-1), f(n-2)] * A
    //      [f(n), f(n-1)] = [f(2), f(1)] * (A ^ n-2)
    public int getNum1(int n) {
        if (n < 1){
            return 0;
        }else if (n <= 2){
            return n;
        }else {
            int[][] A = {
                    {1, 1},
                    {1, 0}
            };

            A = matrixPower(A, n-2);
            return 2 * A[0][0] + A[1][0] ;
        }
    }
    private int[][] matrixPower(int[][] m, int i) {
        int k = m.length;
        int[][] res = new int[k][k];

        if (i % 2 == 1) {
            for (int j = 0; j < k; j++) {
                System.arraycopy(m[j], 0, res[j], 0, k);
            }
        } else {
            for (int j = 0; j < k; j++) {
                res[j][j] = 1;
            }
        }
        i >>= 1;
        while (i > 0) {
            m = matrixMul(m, m);
            if ((i & 1) == 1) {
                res = matrixMul(res, m);
            }
            i >>= 1;
        }

        return res;
    }
    private int[][] matrixMul(int[][] m, int[][] n) {
        int[][] res = new int[m.length][n[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                for (int k = 0; k < m[i].length; k++) {
                    res[i][j] += m[i][k] * n[k][j];
                }
            }
        }
        return res;
    }

    @Test
    public void test_getNum1() {
        for (int i = 1; i < 40; i++) {
            int ans0 = getNum0(i);
            int ans1 = getNum1(i);
            System.out.println("***** " + i + " *****");
            System.out.println("ans0 = " + ans0);
            System.out.println("ans1 = " + ans1);

            if (ans0 != ans1){
                throw new RuntimeException("error");
            }
        }
    }
}
