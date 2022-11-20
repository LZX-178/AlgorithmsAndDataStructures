package com.lzx.zcy.class16;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-11-15 17:06:25
 * 假设有排成一行的 N 个位置，记为 1-N，N >= 2
 * 开始时机器人在其中的 M 位置上
 *      如果机器人来到 1 位置，那么下一步只能往右来到 2 位置，
 *      如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置，
 *      如果机器人来到中间位置，那么下一步可以往左走或者往右走，
 * 规定机器人必须走 K 步，最终能来到 P 位置的方法有多少种
 * 给定四个参数 N, M, P, K, 返回方法数。
 */
public class Code01_RobotWalk {

    // 给定四个参数 N, M, P, K, 返回方法数。
    public int ways1(int N, int M, int P, int K){
        int distance = Math.abs(P - M);
        if (distance == K){
            return 1;
        }else if (distance > K){
            return 0;
        }
        int ways = 0;
        // 分成前一步走到 P-1 还是 P+1 进行情况划分
        if (P-1 >= 1){
            ways += ways1(N, M, P-1, K-1);
        }
        if (P+1 <= N){
            ways += ways1(N, M, P+1, K-1);
        }
        return ways;
    }

    // 在 ways1 的基础上建立缓存
    public int ways2(int N, int M, int P, int K){
        HashMap<Integer, Integer> record = new HashMap<>();
        return ways2(N, M, P, K, record, K);
    }
    public int ways2(int N, int M, int P, int K, HashMap<Integer, Integer> record, int Kconst){
        int distance = Math.abs(P - M);
        if (distance == K){
            return 1;
        }else if (distance > K){
            return 0;
        }
        int ways = 0;
        // 分成前一步走到 P-1 还是 P+1 进行情况划分
        if (P-1 >= 1){
            Integer re = record.get((P - 1) * (Kconst + 1) + K - 1);
            if (re == null){
                ways += ways2(N, M, P-1, K-1, record, Kconst);
            }else {
                System.out.println("命中 : " + "P = " + (P-1) + ", K = " + (K-1) + ", re = " + re);
                ways += re;
            }
        }
        if (P+1 <= N){
            Integer re = record.get((P + 1) * (Kconst + 1) + K - 1);
            if (re == null) {
                ways += ways2(N, M, P+1, K-1, record, Kconst);
            }else {
                System.out.println("命中 : " + "P = " + (P+1) + ", K = " + (K-1) + ", re = " + re);
                ways += re;
            }
        }

        record.put(P*(Kconst+1)+ K, ways);
        System.out.println("缓存 : " + "P = " + (P) + ", K = " + (K) + ", re = " + ways);
        return ways;
    }

    // 将 ways1 改成动态规划
    public int ways3(int N, int M, int P, int K){
        int[][] result = new int[K+1][N];
        result[0][M-1] = 1;
        for (int k = 1; k < result.length; k++) {
            result[k][0] = result[k-1][1];
            for (int p = 1; p <= N-2; p++) {
                result[k][p] = result[k-1][p-1] + result[k-1][p+1];
            }
            result[k][N-1] = result[k-1][N-2];
        }
        return result[K][P-1];
    }

    @Test
    public void test_ways() {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
