package com.lzx.hsp.algorithm.recursion;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-28 15:11
 */
public class eightQueenTest {
    @Test
    public void test_solution() {
        eightQueen eightQueen = new eightQueen();
        eightQueen.solution(0);
        int count = eightQueen.getCount();
        System.out.println("count = " + count);
    }
}
