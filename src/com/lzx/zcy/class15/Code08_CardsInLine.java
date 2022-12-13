package com.lzx.zcy.class15;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-15 11:01:27
 * 给定一个整型数组 arr，代表数值不同的纸牌排成一条线，
 * 玩家 A 和玩家 B 依次拿走每张纸牌，
 * 规定玩家 A 先拿，玩家 B 后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家 A 和玩家 B 都绝顶聪明。请返回最后获胜者的分数
 */
public class Code08_CardsInLine {
    public int win1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(
                win1First(arr, 0, arr.length-1),
                win1Second(arr, 0, arr.length-1));
    }
    // 在 [start, end] 范围上 先手选择时, 能获得的最大分数
    private int win1First(int[] arr, int start, int end) {
        if (start == end){
            return arr[start];
        }
        return Math.max(
                arr[start] + win1Second(arr, start+1, end),
                arr[end] + win1Second(arr, start, end-1));
    }
    // 在 [start, end] 范围上 后手选择时, 能获得的最大分数
    private int win1Second(int[] arr, int start, int end) {
        if (start == end){
            return 0;
        }
        // 因为总分不变, 后手越少, 则先手越多
        return Math.min(
                win1First(arr, start+1, end),
                win1First(arr, start, end-1));
    }

    // 将 win1 改为动态规划
    public int win2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[][] winFirst = new int[arr.length][arr.length];
        int[][] winSecond = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            winFirst[i][i] = arr[i];
        }

        int start, end;
        for (int u = 1; u < arr.length; u++) {
            for (int v = u; v < arr.length; v++) {
                start = v - u;
                end = v;
                winFirst[start][end] = Math.max(
                        arr[start] + winSecond[start+1][end],
                        arr[end] + winSecond[start][end-1]
                );
                winSecond[start][end] = Math.min(
                        winFirst[start+1][end],
                        winFirst[start][end-1]
                );
            }
        }

        return Math.max(
                winFirst[0][arr.length-1],
                winSecond[0][arr.length-1]);
    }

    @Test
    public void test_win() {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
