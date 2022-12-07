package com.lzx.zcy.class12;

import com.lzx.utils.NumberUtils;
import org.junit.Test;
import sun.security.krb5.internal.crypto.Aes128;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-12-07 19:47
 *
 * 输入正数数组 costs、正数数组 profits、正数 K、正数 M
 * costs[i] 表示 i 号项目的花费
 * profits[i] 表示 i 号项目在扣除花费之后还能挣到的钱（利润〕
 * K 表示你只能串行的最多做 k 个项目
 * M 表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出。你最后获得的最大钱数。
 */
public class Code04_IPO {
    // 贪心策略 : 每次选择资金范围内利润最大的项目
    public int findMaximizedCapital1(int K, int W, int[] profits, int[] costs) {
        // 约定以一个一维数组来 表示项目, 第一位为 花费, 第二位为 利润
        PriorityQueue<int[]> queue1 = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> queue2 = new PriorityQueue<>(Comparator.comparingInt(a -> -a[1]));
//        PriorityQueue<int[]> queue2 = new PriorityQueue<>((a1, a2) -> a2[1] - a1[1]);
        for (int i = 0; i < profits.length; i++) {
            int[] program = {costs[i], profits[i]};
            queue1.add(program);
        }
        for (int i = 0; i < K; i++) {
            while (!queue1.isEmpty() && queue1.peek()[0] <= W){
                queue2.add(queue1.poll());
            }
            if (queue2.isEmpty()){
                break;
            }
            W += queue2.poll()[1];
        }
        return W;
    }

    // 对数器
    public int findMaximizedCapital2(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
    public class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
    public class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }
    public class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }


    @Test
    public void test_findMaximizedCapital1() {
        for (int j = 0; j < 500000; j++) {

            int K = NumberUtils.getRandomInt(3, 5);
            int W = NumberUtils.getRandomInt(5, 10);
            int[] costs = new int[NumberUtils.getRandomInt(K, K+K)];
            int[] profits = new int[costs.length];

            for (int i = 0; i < costs.length; i++) {
                costs[i] = NumberUtils.getRandomInt(1, 10);
                profits[i] = NumberUtils.getRandomInt(5, 10);
            }

            int ans1 = findMaximizedCapital1(K, W, profits, costs);
            int ans2 = findMaximizedCapital2(K, W, profits, costs);

            if (ans1 != ans2){
                System.out.println("K = " + K);
                System.out.println("W = " + W);
                System.out.println("Arrays.toString(costs) = " + Arrays.toString(costs));
                System.out.println("Arrays.toString(profits) = " + Arrays.toString(profits));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }

        }

        System.out.println("OK");
    }
}
