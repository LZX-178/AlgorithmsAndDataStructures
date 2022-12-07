package com.lzx.zcy.class12;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-12-07 14:58:49
 *
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 *      给你每一个项目开始的时间和结束的时间
 *      安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 *      返回最多的宣讲场次。
 *      会议的开始时间和结束时间，都是数值，不会 < 0
 */
public class Code02_BestArrange {
    private static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    // 贪心策略, 每次选择结束时间最小的会议
    // 证明 :
    //      假设 a 会议的结束时间最早, b 会议为其余会议中的一个
    //      显而易见, 选择 a 和选择 b, 在其余条件相同的情况下, 选选择 a 之后, 后续的过程可选择的余地更大
    public int bestArrange1(Program[] programs){
        PriorityQueue<Program> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.end));
        queue.addAll(Arrays.asList(programs));

        int cur = 0;
        int ans = 0;
        while (!queue.isEmpty()){
            Program program = queue.poll();
            if (cur <= program.start){
                ans++;
                cur = program.end;
            }
        }
        return ans;
    }

    // 对数器, 暴力递归
    public int bestArrange2(Program[] programs){
        Arrays.sort(programs, Comparator.comparingInt(p -> p.start));

        return bestArrange2(programs, 0);
    }
    private int bestArrange2(Program[] programs, int index) {
        // index 表示需要从哪个会议开始往后遍历
        if (index == programs.length-1){
            return 1;
        }

        // 要么选择当前会议,
        int i, count=1;
        int cur = programs[index].end;
        for (i = index + 1; i < programs.length; i++) {
            if (cur <= programs[i].start){
                break;
            }
        }
        if (i < programs.length){
            count += bestArrange2(programs, i);
        }
        // 要么不选
        count = Math.max(count, bestArrange2(programs, index+1));
        return count;
    }

    @Test
    public void test_bestArrange1() {
        for (int j = 0; j < 5; j++) {
            System.out.println("***** " + "test" + " *****");
            Program[] programs = new Program[NumberUtils.getRandomInt(3, 6)];
            for (int i = 0; i < programs.length; i++) {
                int start = NumberUtils.getRandomInt(0, 20);
                int end = start + NumberUtils.getRandomInt(1, 10);
                programs[i] = new Program(start, end);
            }

            for (int i = 0; i < programs.length; i++) {
                System.out.println("programs[" + i + "] = " + programs[i]);
            }

            int ans1 = bestArrange1(programs);
            System.out.println("ans1 = " + ans1);

            for (int i = 0; i < programs.length; i++) {
                System.out.println("programs[" + i + "] = " + programs[i]);
            }
            int ans2 = bestArrange2(programs);
            System.out.println("ans2 = " + ans2);
        }
    }
    @Test
    public void test_bestArrange2() {
        Program[] programs = {
                new Program(4, 12),
                new Program(19, 29),
                new Program(15, 23)
        };

        for (int i = 0; i < programs.length; i++) {
            System.out.println("programs[" + i + "] = " + programs[i]);
        }
        int ans1 = bestArrange1(programs);
        System.out.println("ans1 = " + ans1);

        for (int i = 0; i < programs.length; i++) {
            System.out.println("programs[" + i + "] = " + programs[i]);
        }
        int ans2 = bestArrange2(programs);
        System.out.println("ans2 = " + ans2);
    }
    @Test
    public void test_bestArrange3() {
        for (int j = 0; j < 500000; j++) {
            Program[] programs = new Program[NumberUtils.getRandomInt(3, 6)];
            for (int i = 0; i < programs.length; i++) {
                int start = NumberUtils.getRandomInt(0, 20);
                int end = start + NumberUtils.getRandomInt(1, 10);
                programs[i] = new Program(start, end);
            }

            int ans1 = bestArrange1(programs);
            int ans2 = bestArrange2(programs);

            if (ans1 != ans2){
                System.out.println("ans1 = " + ans1);
                for (int i = 0; i < programs.length; i++) {
                    System.out.println("programs[" + i + "] = " + programs[i]);
                }
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
