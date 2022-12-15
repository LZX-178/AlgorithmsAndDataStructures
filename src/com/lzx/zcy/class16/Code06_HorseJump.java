package com.lzx.zcy.class16;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-16 2:04:53
 *
 * 有一个象棋棋盘, 马从 [0,0] 位置开始跳, 跳到 [a,b] 位置, 必须跳 k 步, 有多少种方法
 */
public class Code06_HorseJump {
    // 方法1 : 暴力递归
    public int jump1(int a, int b, int k) {
        return jump1(0, 0, k, a, b);
    }
    // 从 [x,y] 跳到 [a,b]
    private int jump1(int x, int y, int k, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        if (k == 0){
            return x == a && y == b ? 1 : 0;
        }
        return
                jump1(x+2, y+1, k-1, a, b) +
                jump1(x+1, y+2, k-1, a, b) +
                jump1(x-2, y+1, k-1, a, b) +
                jump1(x-1, y+2, k-1, a, b) +
                jump1(x+2, y-1, k-1, a, b) +
                jump1(x+1, y-2, k-1, a, b) +
                jump1(x-2, y-1, k-1, a, b) +
                jump1(x-1, y-2, k-1, a, b);
    }

    // 方法2 : 将方法1 改为 动态规划
    public int jump2(int a, int b, int k) {
        return 0;
    }


    // 对数器
    public int jump0(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }
    public int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    @Test
    public void test_jump() {
//        int a = NumberUtils.getRandomInt(0, 8);
//        int b = NumberUtils.getRandomInt(0, 9);
//        int k = NumberUtils.getRandomInt(1, 8);
        int a = 5;
        int b = 0;
        int k = 7;


        int ans0 = jump0(a, b, k);
        int ans1 = jump1(a, b, k);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("k = " + k);

        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
    }
}
