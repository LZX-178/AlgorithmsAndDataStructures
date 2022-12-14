package com.lzx.zcy.class16;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-14 22:50:32
 * 516. 最长回文子序列
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * s 仅由小写英文字母组成
 */
public class Code05_LongestPalindromeSubseq {
    // 方法1 : 暴力递归
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return longestPalindromeSubseq(s.toCharArray(), 0, s.length()-1);
    }
    private int longestPalindromeSubseq(char[] chars, int start, int end) {
        if (start == end){
            return 1;
        }else if (start > end){
            return 0;
        }

        int i;
        for (i = end; i >= start; i--) {
            if (chars[i] == chars[start]){
                break;
            }
        }
        if (i == start){
            return Math.max(1, longestPalindromeSubseq(chars, start+1, end));
        }else {
            return Math.max(
                    longestPalindromeSubseq(chars, start+1, i-1)+2,
                    longestPalindromeSubseq(chars, start+1, end)
            );
        }
    }

    // 方法2 : 记忆化搜索
    int[][] dp;
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        dp = new int[chars.length][chars.length];

        return longestPalindromeSubseq2(s.toCharArray(), 0, s.length()-1);
    }
    private int longestPalindromeSubseq2(char[] chars, int start, int end) {
        if (start == end){
            return 1;
        }else if (start > end){
            return 0;
        }
        if (dp[start][end] != 0){
            return dp[start][end];
        }

        int i;
        for (i = end; i >= start; i--) {
            if (chars[i] == chars[start]){
                break;
            }
        }

        int ans;
        if (i == start){
            ans = longestPalindromeSubseq2(chars, start + 1, end);
        }else {
            ans = Math.max(
                    longestPalindromeSubseq2(chars, start + 1, i - 1) + 2,
                    longestPalindromeSubseq2(chars, start + 1, end)
            );
        }
        dp[start][end] = ans;
        return ans;
    }


    @Test
    public void test_() {
        String s = "bbbab";

        int ans = longestPalindromeSubseq(s);

        System.out.println("ans = " + ans);
    }
}
