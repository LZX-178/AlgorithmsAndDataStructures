package com.lzx.zcy.class20;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-30 15:42:29
 * <p>
 * 第一年农场有 1 只成熟的母牛 A，往后的每年
 * 1）每一只成熟的母牛都会生一只母牛
 * 2）每一只新出生的母牛都在出生的第三年成熟 (2 -> 5)
 * 3）每一只母牛永远不会死
 * 返回第 n 年牛的数量
 */
public class Code02_NumOfCow {

    // 对数器
    public int numOfCow0(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    // 由题意 :
    //      n   num
    //      1   1
    //      2   2
    //      3   3
    //      4   4
    //      5   6
    //      6   9
    //  f(n) = f(n-1) + f(n-3)
    //  [f(n), f(n-1), f(n-2)] = [f(n-1), f(n-2), f(n-3)] * A
    //  A =
    //       {1, 1, 0},
    //       {0, 0, 1},
    //       {1, 0, 0}
    //  [f(n), f(n-1), f(n-2)] = [f(3), f(2), f(1)] * (A ^ n-3)
    public int numOfCow(int n) {
        if (n < 1) {
            return 0;
        } else if (n <= 3) {
            return n;
        } else {
            int[][] base = {
                    {1, 1, 0},
                    {0, 0, 1},
                    {1, 0, 0}
            };
            base = matrixPower(base, n - 3);
            return 3 * base[0][0] + 2 * base[1][0] + base[2][0];
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
    public void test_numOfCow() {
        for (int i = 1; i < 30; i++) {
            int ans0 = numOfCow0(i);
            int ans1 = numOfCow(i);

            System.out.println("***** " + i + " *****");
            System.out.println("ans0 = " + ans0);
            System.out.println("ans1 = " + ans1);

            if (ans0 != ans1){
                throw new RuntimeException("error");
            }
        }
    }
}
