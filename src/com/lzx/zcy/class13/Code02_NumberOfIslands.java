package com.lzx.zcy.class13;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author LZX
 * @code @create 2022-12-13 13:53:03
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 */
public class Code02_NumberOfIslands {
    // 方法1 : 深度优先搜索
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    diffusion(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }
    private void diffusion(char[][] grid, int i, int j) {
        grid[i][j] = '2';
        if (i-1 >=0 && grid[i-1][j] == '1'){
            diffusion(grid, i-1, j);
        }
        if (i+1 < grid.length && grid[i+1][j] == '1'){
            diffusion(grid, i+1, j);
        }
        if (j-1 >=0 && grid[i][j-1] == '1'){
            diffusion(grid, i, j-1);
        }
        if (j+1 < grid[i].length && grid[i][j+1] == '1'){
            diffusion(grid, i, j+1);
        }
    }

    // 方法2 : 并查集
    // 可以用数组替代 哈希表, 因为矩阵的长宽不超过 300
    HashMap<Integer, Integer> fathers = new HashMap<>();

    public int numIslands2(char[][] grid){
        int col = grid[0].length;
        int count = 0;

        if (grid[0][0] == '1'){
            count++;
            fathers.put(0, 0);
        }
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1'){
                if (grid[0][i-1] == '1'){
                    fathers.put(i, getFather(i-1));
                }else {
                    count++;
                    fathers.put(i, i);
                }
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1'){
                if (grid[i-1][0] == '1'){
                    fathers.put(i* col, getFather((i-1)* col));
                }else {
                    count++;
                    fathers.put(i* col, i* col);
                }
            }

            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    if (grid[i-1][j] == '1' && grid[i][j-1] == '1'){
                        Integer f1 = getFather(i* col + j - 1);
                        Integer f2 = getFather((i-1)* col +j);
                        // 此处不要用 !=
                        if (!Objects.equals(f1, f2)){
                            fathers.put(f1, f2);
                            count--;
                        }
                        fathers.put(i* col +j, f2);
                    }else if (grid[i-1][j] == '1'){
                        fathers.put(i* col +j, getFather((i-1)* col + j));
                    }else if (grid[i][j-1] == '1'){
                        fathers.put(i* col +j, getFather(i* col + j - 1));
                    }else {
                        fathers.put(i* col +j, i* col +j);
                        count++;
                    }
                }
            }
        }

        return count;
    }
    private Integer getFather(int i) {
        Integer f = fathers.get(i);
        while (f != i){
            i = f;
            f = fathers.get(i);
        }
        return i;
    }

    // 方法3 : 可以用数组替代 哈希表, 因为矩阵的长宽不超过 300
    public int numIslands3(char[][] grid){
        int[] parents = new int[grid.length * grid[0].length];
        int col = grid[0].length;
        int count = 0;

        if (grid[0][0] == '1'){
            count++;
            parents[0] = 0;
        }
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1'){
                if (grid[0][i-1] == '1'){
                    parents[i] = getFather(i-1, parents);
                }else {
                    count++;
                    parents[i] = i;
                }
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1'){
                if (grid[i-1][0] == '1'){
                    parents[i* col] = getFather((i-1)* col, parents);
                }else {
                    count++;
                    parents[i*col] = i*col;
                }
            }

            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    if (grid[i-1][j] == '1' && grid[i][j-1] == '1'){
                        Integer f1 = getFather(i* col + j - 1, parents);
                        Integer f2 = getFather((i-1)* col +j, parents);
                        // 此处不要用 !=
                        if (!Objects.equals(f1, f2)){
                            parents[f1] = f2;
                            count--;
                        }
                        parents[i*col + j] = f2;
                    }else if (grid[i-1][j] == '1'){
                        parents[i* col +j] = getFather((i-1)* col + j, parents);
                    }else if (grid[i][j-1] == '1'){
                        parents[i* col +j] = getFather(i* col + j - 1, parents);
                    }else {
                        parents[i* col +j] = i* col +j;
                        count++;
                    }
                }
            }
        }

        return count;
    }
    private Integer getFather(int i, int[] parents) {
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
        char[][] grid1 = {
                {'1'},
                {'1'}
        };
        char[][] grid2 = {
                {'1', '1', '1', '0', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid3 = {
                            {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                            {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                            {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                            {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                            {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                            {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                            {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                            {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                            {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                            {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
                        };

        int i = numIslands2(grid1);
        System.out.println("i = " + i);
    }
}
