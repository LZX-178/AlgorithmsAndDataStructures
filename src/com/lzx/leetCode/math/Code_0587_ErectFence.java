package com.lzx.leetCode.math;

import org.junit.Test;

import java.util.HashSet;

//程序效率待改进!!!

/**
 * @author LZX
 * @create 2022-04-23 16:52
 *
 * 587. 安装栅栏
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 *
 * 示例 1:
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 *
 * 注意:
 *
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 *
 *
 *
 */
public class Code_0587_ErectFence {
    @Test
    public void myTest(){
        int[][] trees1 = {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}};
        show(trees1);

        int[][] result1 = outerTrees(trees1);
        show(result1);

        System.out.println();

        int[][] trees2 = {{1, 2}, {2, 2}, {4, 2}};
        show(trees2);

        int[][] result2 = outerTrees(trees2);
        show(result2);
    }

    public int[][] outerTrees(int[][] trees) {
        HashSet<int[]> result = new HashSet<>();

        //找到最右边的树
        int start = findStart(trees);
        System.out.println("start:" + "["+ trees[start][0] +","+ trees[start][1] +"]");

        //记录当前找到的边界树
        int now = start;

        while (true){
            //按逆时针方向寻找下一棵边界树
            int next = findNext(trees, now, result);

            //将找到的边界树加入list
            result.add(trees[next]);

            if (next == start){
                break;
            }else {
                now = next;
            }
        }

        return result.toArray(new int[0][]);
    }

    private int findNext(int[][] trees, int now, HashSet<int[]> result) {
        //从剩余的点中随机选取一个初始边界点
        int next = now;
        if (result.isEmpty()){
            for (int i = 0; i < trees.length; i++) {
                if (i != now){
                    next = i;
                    break;
                }
            }
        }else {
            for (int i = 0; i < trees.length; i++) {
                if (!result.contains(trees[i])){
                    next = i;
                    break;
                }
            }
        }


        //记录当前的两个点及其直线方程
        int x1 = trees[now][0];
        int y1 = trees[now][1];
        int x2 = trees[next][0];
        int y2 = trees[next][1];


        int A = y2 - y1;
        int B = x1 - x2;
        int C = x1*A + y1*B;

        for (int i = 0; i < trees.length; i++) {
            if (result.contains(trees[i]) || i == next){
                continue;
            }

            int x = trees[i][0];
            int y = trees[i][1];

            //如果当前点在当前直线的顺时针方向,则当前点更优,更新next
            if (A * x + B * y < C){
                next = i;
                x2 = trees[next][0];
                y2 = trees[next][1];
                A = y2 - y1;
                B = x1 - x2;
                C = x1*A + y1*B;
            }else if (A * x + B * y == C){//如果当前点在当前直线上,且在next和now两点之间,则当前点更优,更新next
                if (x1 == x2){
                    if (y1 > y2){
                        if (y >= y1 || y <= y2){
                            continue;
                        }
                    }else {
                        if (y <= y1 || y >= y2){
                            continue;
                        }
                    }
                }else if (x1 > x2){
                    if (x >= x1 || x <= x2){
                        continue;
                    }
                }else {
                    if (x <= x1 || x >= x2){
                        continue;
                    }
                }

                next = i;
                x2 = trees[next][0];
                y2 = trees[next][1];
            }
        }

        return next;
    }

    private int findStart(int[][] trees) {
        int start = 0;
        int max = trees[0][0];

        for (int i = 1; i < trees.length; i++) {
            if (trees[i][0] > max){
                max = trees[i][0];
                start = i;
            }
        }

        return start;
    }

    public void show(int[][] arr){

        System.out.print("[");
        if (arr.length > 0) {
            System.out.print("["+ arr[0][0] +","+ arr[0][1] +"]");
            for (int i = 1; i < arr.length; i++) {
                System.out.print(",["+ arr[i][0] +","+ arr[i][1] +"]");
            }
        }
        System.out.println("]");

    }
}
