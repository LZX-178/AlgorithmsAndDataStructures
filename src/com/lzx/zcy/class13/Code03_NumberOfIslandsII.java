package com.lzx.zcy.class13;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-12-13 13:53:09
 * 305. 岛屿数量 II
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，0 表示水，1 表示陆地。
 * 最初，grid 中的所有单元格都是水单元格（即，所有单元格都是 0）。
 *
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组 positions ，
 * 其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位置 (ri, ci) 。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换为陆地后，地图中岛屿的数量。
 *
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。
 * 你可以假设地图网格的四边均被无边无际的「水」所包围。
 */
public class Code03_NumberOfIslandsII {
    int count = 0;
    int[][] grid;
    int[] parents;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        grid = new int[m][n];
        parents = new int[m*n];
        List<Integer> ans = new ArrayList<>(positions.length);

        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];

            if (grid[i][j] == 1){
                ans.add(count);
                continue;
            }
            grid[i][j] = 1;
            parents[i*n+j] = i*n+j;
            count++;

            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                union(i * n + j, i * n + j - n);
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                union(i * n + j, i * n + j - 1);
            }
            if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                union(i * n + j, i * n + j + n);
            }
            if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                union(i * n + j, i * n + j + 1);
            }
            ans.add(count);
        }
        return ans;
    }

    private void union(int i, int j) {
        int f1 = getFather(i);
        int f2 = getFather(j);

        if (f1 != f2){
            parents[f1] = f2;
            count--;
        }
    }

    private int getFather(int i) {
        int c = i;
        int f = parents[i];
        while (c != f){
            c = f;
            f = parents[c];
        }

        c = parents[i];
        while (c != i){
            parents[i] = f;
            i = c;
            c = parents[i];
        }
        return f;
    }

    @Test
    public void test_num() {
        int[][] positions = {{0,0},{0,1},{1,2},{1,2}};
        List<Integer> ans = numIslands2(3, 3, positions);
        System.out.println("ans = " + ans);
    }
}
