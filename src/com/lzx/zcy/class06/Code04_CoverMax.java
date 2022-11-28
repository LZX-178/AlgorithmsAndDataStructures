package com.lzx.zcy.class06;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-11-28 16:58:57
 * 线段最大重叠问题
 *      给定一组线段 int[][] lines
 *      统计这些线段的重叠区域中, 各区域重叠次数的最大值
 *          lines[i][0] lines[i][1] 分别代表线段的数轴上端点(开区间), 即 : 如果两个线段只有端点重叠, 并不计重叠区域
 */
public class Code04_CoverMax {

    // 对数器
    public int maxCover0(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] line : lines) {
            min = Math.min(min, line[0]);
            max = Math.max(max, line[1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int[] line : lines) {
                if (line[0] < p && line[1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // 方法1 : 暴力遍历所有区域
    public int maxCover1(int[][] lines){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int ans = 0;

        for (int[] line : lines) {
            min = Math.min(min, line[0]);
            max = Math.max(max, line[1]);
        }

        // 遍历 (i, i+1) 区间, 计算重叠数, 取最大值
        for (int i = min; i < max; i++) {
            int count = 0;
            for (int[] line : lines) {
                if (line[0] <= i && line[1] >= i+1){
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }

        return ans;
    }

    // 方法2 : 每个区域 (i, i+1) 在上一个区域 (i-1, i) 的基础上进行判断
    //      假设上一次的重叠数为 n
    //      如果有线段在 i 位置开始, 重叠数+1
    //      如果有线段在 i 位置结束, 重叠数-1
    //      否则 当前区域和上一个区域可以视为同一区域
    //      综上, 换一个角度, 遍历所有端点也可以实现
    public int maxCover2(int[][] lines){
        // 记录所有端点上的 "出入度", 有线段以该端点开始计+1, 以该端点结束计-1
        HashMap<Integer, Integer> map = new HashMap<>();
        // 按顺序记录 端点
        PriorityQueue<Integer> points = new PriorityQueue<>();

        for (int[] line : lines) {
            Integer count = map.get(line[0]);
            if (count == null){
                map.put(line[0], 1);
                points.add(line[0]);
            }else {
                map.put(line[0], count+1);
            }

            count = map.get(line[1]);
            if (count == null){
                map.put(line[1], -1);
                points.add(line[1]);
            }else {
                map.put(line[1], count-1);
            }
        }

        int ans = 0;
        int count = 0;
        while (!points.isEmpty()){
            Integer point = points.poll();
            count += map.get(point);
            ans = Math.max(ans, count);
        }

        return ans;
    }



    @Test
    public void test_1() {
        int[][] lines = new int[100][2];
        for (int i = 0; i < lines.length; i++) {
            lines[i][0] = NumberUtils.getRandomInt(-100, 100);
            lines[i][1] = lines[i][0] + NumberUtils.getRandomInt(1, 50);
            System.out.println(lines[i][0] + "\t" + lines[i][1]);
        }
        int ans1 = maxCover0(lines);
        int ans2 = maxCover1(lines);
        System.out.println("ans1 = " + ans1);
        System.out.println("ans2 = " + ans2);

    }

    @Test
    public void test_2() {
        for (int j = 0; j < 50000; j++) {
            int[][] lines = new int[50][2];
            for (int i = 0; i < lines.length; i++) {
                lines[i][0] = NumberUtils.getRandomInt(-100, 100);
                lines[i][1] = lines[i][0] + NumberUtils.getRandomInt(1, 50);
            }
            int ans0 = maxCover0(lines);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans0 != ans1 || ans1 != ans2){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
