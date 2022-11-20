package com.lzx.hsp.algorithm.recursion;

/**
 * @author LZX
 * @code @create 2022-07-28 8:20
 * 你有几个同样的球，你希望把它放到几个篮子里。
 * 每个篮子有相同的容量。
 * 给出int 型的baskets,代表篮子的数量。
 * 给出int型的 capacity，代表每个篮子的最大容量。
 * 给出int型的balls,表示归类到篮子里的球的数量。
 * 返回值是把球归类到篮子里的方式的数量。如果不能完全存放到篮子中，无法划分，返回0。
 * 篮子互不同，所有的球相同。因此，如果2个球放到2个篮子里，你可以采用3种方式，即(0, 2), (1, 1), 或 (2, 0)
 */
public class BallsAndBaskets {
    // 计算分球的方法个数, 允许有篮子为空
    public int countWays(int baskets, int capacity, int balls){
        // 篮子放不下, 返回 0
        if (balls > baskets * capacity){
            return 0;
        }
        // 球已经分完, 返回 1
        if (balls == 0){
            return 1;
        }
        // 根据 空着的篮子 的个数进行分类
        int sum  = 0;
        // curBaskets 表示可以保证最多有多少个篮子非空, 从它开始循环递减
        int curBaskets = Math.min(baskets, balls);
        while (curBaskets >=1) {
            sum += C(baskets, curBaskets) * countWaysNotEmpty(curBaskets, capacity, balls);
            curBaskets--;
        }
        return sum;
    }
    // 计算分球的方法个数, 不允许有篮子为空
    public int countWaysNotEmpty(int baskets, int capacity, int balls){
        // 篮子放不下, 返回 0
        if (balls > baskets * capacity){
            return 0;
        }
        // 否则 每个篮子 "垫一层" 再调用 countWays
        return countWays(baskets, capacity-1, balls-baskets);
    }

    public int A(int a, int b){
        int result = 1;
        for (int i = a - b + 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }
    public int C(int a, int b){
        int ans = 1;
        for (int i = 1; i < b + 1; i++) {
            // 注意 : 这里的 i 不要 写成了 b !!!!!!!!!!!!
            ans *= i;
        }
        return A(a,b) / ans;
    }
}
