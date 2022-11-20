package com.lzx.hsp.algorithm.recursion;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-07-28 14:51
 * 八皇后问题， 是一个古老而著名的问题， 是回溯算法的典型案例。 该问题是国际西洋棋棋手马克斯· 贝瑟尔于
 * 1848 年提出： 在 8× 8 格的国际象棋上摆放八个皇后， 使其不能互相攻击， 即： 任意两个皇后都不能处于同一行、
 * 同一列或同一斜线上， 问有多少种摆法(92)。
 */
public class eightQueen {
    private final int maxSize = 8;
    private final int[] array = new int[maxSize];
    private int count = 0;
    // 摆放 curQueen 位置的皇后
    public void solution(int curQueen){
        // 如果所有位置的皇后已经摆放完成
        if (curQueen == maxSize){
            System.out.println(Arrays.toString(array));
            count++;
            return;
        }
        // 否则, 摆放当前的皇后
        for (int i = 0; i < maxSize; i++) {
            // 如果 i 位置是可以摆放的, 则摆放当前皇后 并 进行下一个皇后的摆放
            if (isLegal(curQueen, i)){
                array[curQueen] = i;
                solution(curQueen+1);
            }
        }
    }
    // 判断 array[index] = location 和之前的皇后的位置关系是否合法
    private boolean isLegal(int index, int location){
        for (int i = 0; i < index; i++) {
            int curLocation = array[i];
            // 是否在同一列
            if (curLocation == location){
                return false;
            }
            // 是否在同一对角线
            if ((index - i) == (location - curLocation) || (index - i) == (curLocation - location)){
                return false;
            }
        }
        return true;
    }
    public int getCount() {
        return count;
    }
}
