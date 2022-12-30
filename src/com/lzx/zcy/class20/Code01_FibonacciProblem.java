package com.lzx.zcy.class20;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-30 13:57:29
 * 求 斐波那契数列 的第  n 项,
 *      1, 1, 2, 3, 5...
 */
public class Code01_FibonacciProblem {
    // 方法1 : 暴力递归, O( 2 ^ n )
    int count = 0;
    public int f1(int n) {
        count++;
        if (n < 1){
            return 0;
        }else if (n == 1 || n == 2){
            return 1;
        }else {
            return f1(n-1) + f1(n-2);
        }
    }

    // 方法2 : 动态规划, O( n )
    public int f2(int n) {
        if (n < 1){
            return 0;
        }else if (n == 1 || n == 2){
            return 1;
        }else {
            int[] arr = new int[n];

            arr[0] = arr[1] = 1;

            for (int i = 2; i < n; i++) {
                arr[i] = arr[i-1] + arr[i-2];
            }

            return arr[n-1];
        }
    }

    // 方法3 : 数的快速幂/矩阵的快速幂, O( log N )
    // 由 f(n) = f(n-1) + f(n-2), 得
    //      [f(n), f(n-1)] = [f(n-1), f(n-2)] * A
    //      其中 A = {
    //                 {1, 1},
    //                 {1, 0},
    //               }
    // 递推得 :
    //      [f(n), f(n-1)] = [f(2), f(1)] * (A ^ n-2)


    public int f3(int n) {

        if (n < 1){
            return 0;
        }else if (n == 1 || n == 2){
            return 1;
        }else {
            int[][] base = {
                    {1, 1},
                    {1, 0},
            };

            base = matrixPower(base, n-2);
            return base[0][0] + base[1][0];
        }


    }

    // 求一个方阵 m 的 i 次幂, i >= 1
    // 将 i 拆分成二进制, 假设最高位为 2 ^ l
    // 则依次求
    //      m 的 1 次幂, m 的 2 次幂, m 的 4 次幂, m 的 8 次幂 ... m 的 l 次幂
    // 取 i 中为 1 的位对应的 m 的幂, 将这些幂相乘即可
    private int[][] matrixPower(int[][] m, int i) {
        int k = m.length;
        int[][] res = new int[k][k];

        if (i % 2 == 1){
            for (int j = 0; j < k; j++) {
                System.arraycopy(m[j], 0, res[j], 0, k);
            }
        }else {
            for (int j = 0; j < k; j++) {
                res[j][j] = 1;
            }
        }
        i >>= 1;
        while (i > 0){
            m = matrixMul(m, m);
            if ((i & 1) == 1){
                res = matrixMul(res, m);
            }
            i >>= 1;
        }

        return res;
    }
    // 两个矩阵相乘
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
    public void test_f1() {
        // count 用来测试 f1 的复杂度
        count = 0;
        int ans1_1 = f1(1);
        System.out.println("count = " + count);

        count = 0;
        int ans1_2 = f1(2);
        System.out.println("count = " + count);

        count = 0;
        int ans1_3 = f1(3);
        System.out.println("count = " + count);

        count = 0;
        int ans1_4 = f1(4);
        System.out.println("count = " + count);

        count = 0;
        int ans1_5 = f1(5);
        System.out.println("count = " + count);

        count = 0;
        int ans1_19 = f1(19);
        System.out.println("count = " + count);

        System.out.println("***** " + "f1" + " *****");
        System.out.println("ans1_1 = " + ans1_1);
        System.out.println("ans1_2 = " + ans1_2);
        System.out.println("ans1_3 = " + ans1_3);
        System.out.println("ans1_4 = " + ans1_4);
        System.out.println("ans1_5 = " + ans1_5);
        System.out.println("ans1_19 = " + ans1_19);
    }

    @Test
    public void test_f2() {
        int ans2_1 = f2(1);
        int ans2_2 = f2(2);
        int ans2_3 = f2(3);
        int ans2_4 = f2(4);
        int ans2_5 = f2(5);
        int ans2_19 = f2(19);
        int ans2_29 = f2(29);

        System.out.println("***** " + "f2" + " *****");
        System.out.println("ans2_1 = " + ans2_1);
        System.out.println("ans2_2 = " + ans2_2);
        System.out.println("ans2_3 = " + ans2_3);
        System.out.println("ans2_4 = " + ans2_4);
        System.out.println("ans2_5 = " + ans2_5);
        System.out.println("ans2_19 = " + ans2_19);
        System.out.println("ans2_29 = " + ans2_29);

        int ans3_1 = f3(1);
        int ans3_2 = f3(2);
        int ans3_3 = f3(3);
        int ans3_4 = f3(4);
        int ans3_5 = f3(5);
        int ans3_19 = f3(19);
        int ans3_29 = f3(29);

        System.out.println("***** " + "f3" + " *****");
        System.out.println("ans3_1 = " + ans3_1);
        System.out.println("ans3_2 = " + ans3_2);
        System.out.println("ans3_3 = " + ans3_3);
        System.out.println("ans3_4 = " + ans3_4);
        System.out.println("ans3_5 = " + ans3_5);
        System.out.println("ans3_19 = " + ans3_19);
        System.out.println("ans3_29 = " + ans3_29);
    }
}
