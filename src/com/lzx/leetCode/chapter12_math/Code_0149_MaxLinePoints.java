package com.lzx.leetCode.chapter12_math;

import org.junit.Test;

/**
 * @author LZX
 * @create 2022-05-02 17:34
 *
 * 149. 直线上最多的点数
 *
 * 给你一个数组 points
 * 其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * points 中的所有点互不相同
 */
public class Code_0149_MaxLinePoints {
    @Test
    public void test_maxPoints(){
        int[][] ints1 = {
                {1,1},
                {2,2},
                {3,3}
        };
        int[][] ints2 = {
                {1,1},
                {3,2},
                {5,3},
                {4,1},
                {2,3},
                {1,4}
        };
        System.out.println(maxPoints(ints1));
        System.out.println(maxPoints(ints2));
    }

    @Test
    public void test_IsOn(){
        System.out.println(isOn(new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 4}));
    }

    public int maxPoints(int[][] points) {
        if (points.length == 1){
            return 1;
        }
        if (points.length == 2){
            return 2;
        }
        int max = 2;
        for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < i; j++) {
                int count = count(i, j, points);
                max = Math.max(max, count);
            }
        }

        return max;
    }

    public boolean isOn(int[] p1, int[] p2, int[] p3){
        int x12 = p1[0] - p2[0];
        int y12 = p1[1] - p2[1];
        int x23 = p2[0] - p3[0];
        int y23 = p2[1] - p3[1];

        return x12 * y23 == x23 * y12;
    }

    public int count(int p1, int p2, int[][] points){
        int res = 2;
        for (int[] point : points) {
            if (point == points[p1] || point == points[p2]){
                continue;
            }
            if (isOn(points[p1],points[p2],point)){
                res++;
            }
        }
        return res;
    }
}
