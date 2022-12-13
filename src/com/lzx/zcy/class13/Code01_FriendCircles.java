package com.lzx.zcy.class13;

/**
 * @author LZX
 * @code @create 2022-12-13 13:52:49
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，
 *      其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 *      而 isConnected[i][j] = 0 表示二者不直接相连。
 *      返回矩阵中 省份 的数量。
 *
 */
public class Code01_FriendCircles {
    int count;

    public int findCircleNum(int[][] isConnected) {
        // 用数组做并查集
        int[] unionFind = new int[isConnected.length];
        // 一开始, 所有节点的父亲指向自己,
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }
        count = unionFind.length;
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1){
                    // 尝试合并两个节点
                    union(i, j, unionFind);
                }
            }
        }

        return count;
    }

    private void union(int i, int j, int[] unionFind) {
        int f1 = getFather(i, unionFind);
        int f2 = getFather(j, unionFind);

        if (f1 != f2){
            // 每次成功合并时, 集合数量-1
            count--;
            unionFind[f1] = f2;
        }
    }

    private int getFather(int i, int[] unionFind) {
        int c = i;
        int f = unionFind[c];
        while (f != c){
            c = f;
            f = unionFind[c];
        }

        c = unionFind[i];
        while (c != f){
            unionFind[i] = f;
            i = c;
            c = unionFind[i];
        }

        return f;
    }
}
