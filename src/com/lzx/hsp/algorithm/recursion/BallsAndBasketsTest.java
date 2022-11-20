package com.lzx.hsp.algorithm.recursion;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-28 8:27
 */
public class BallsAndBasketsTest {
    @Test
    public void test_C() {
        BallsAndBaskets ballsAndBaskets = new BallsAndBaskets();
        int a = ballsAndBaskets.A(5, 3);
        int c = ballsAndBaskets.C(5, 3);
        System.out.println("a = " + a);
        System.out.println("c = " + c);
    }

    @Test
    public void test_countWays() {
        BallsAndBaskets ballsAndBaskets = new BallsAndBaskets();
        int[][] test  = {
                {2, 20, 2, 3},
                {3, 20, 1, 3},
                {3, 20, 2, 6},
                {1, 5, 10, 0},
                {4, 5, 10, 146},
        };
        for (int[] ints : test) {
            int baskets=ints[0];
            int capacity=ints[1];
            int balls=ints[2];
            int key=ints[3];
            int result=ballsAndBaskets.countWays(baskets,capacity,balls);
            boolean success = key == result;
            if (!success){
                throw new RuntimeException("error");
            }
            System.out.print("baskets = " + baskets + "\t");
            System.out.print("capacity = " + capacity + "\t");
            System.out.print("balls = " + balls + "\t");
            System.out.print("key = " + key + "\t");
            System.out.print("result = " + result + "\t");
            System.out.println("success = " + success + "\t");
        }

    }
}
