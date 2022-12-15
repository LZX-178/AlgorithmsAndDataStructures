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
    // 情况划分 :
    //      包括 start,
    //      不包括 start
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
            // 没有 和 chars[start] 相同的字符
            // 只能不包括 start
            return Math.max(1, longestPalindromeSubseq(chars, start+1, end));
        }else {
            // 包括 start, 或者不包括 start
            return Math.max(
                    longestPalindromeSubseq(chars, start+1, i-1)+2,
                    longestPalindromeSubseq(chars, start+1, end)
            );
        }
    }

    // 方法2 : 将 方法1 改为 记忆化搜索
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

    // 方法3 : str 和 它的逆序串 的最长公共子序列 即为 str的 最长回文子序列

    // 方法4 : 暴力递归
    // 情况划分 :
    //      包括 start 和 end
    //      不包括 start 和 end
    //      包括 start 或 end
    public int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return longestPalindromeSubseq4(s.toCharArray(), 0, s.length()-1);
    }
    private int longestPalindromeSubseq4(char[] chars, int start, int end) {
        if (start == end){
            return 1;
        }else if (start > end){
            return 0;
        }

        if (chars[start] == chars[end]){
            return 2 + longestPalindromeSubseq4(chars, start+1, end-1);
        }
        return Math.max(
                longestPalindromeSubseq4(chars, start+1, end),
                longestPalindromeSubseq4(chars, start, end-1)
        );
    }

    // 方法5 : 方法4 改动态规划
    public int longestPalindromeSubseq5(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[][] dp = new int[chars.length][chars.length];

        for (int start = chars.length-1; start >= 0; start--) {
            dp[start][start] = 1;
            for (int end = start+1; end < chars.length; end++) {
                if (chars[start] == chars[end]){
                    dp[start][end] = 2 + dp[start+1][end-1];
                }else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }

        return dp[0][chars.length-1];
    }

    // 方法6 : 方法5 优化成一维
    public int longestPalindromeSubseq6(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        // old 用于记录 当前值的左下角的值
        int old;
        // temp 用于更新 old
        int temp;

        for (int start = chars.length-1; start >= 0; start--) {
            dp[start] = 1;
            old = 0;
            for (int end = start+1; end < chars.length; end++) {
                temp = dp[end];
                if (chars[start] == chars[end]){
                    dp[end] = 2 + old;
                }else {
                    dp[end] = Math.max(dp[end], dp[end-1]);
                }
                old = temp;
            }
        }

        return dp[chars.length-1];
    }


    @Test
    public void test_() {
        String s = "bbbab";

        int ans = longestPalindromeSubseq(s);
        int ans4 = longestPalindromeSubseq4(s);
        int ans5 = longestPalindromeSubseq5(s);

        System.out.println("ans = " + ans);
        System.out.println("ans4 = " + ans4);
        System.out.println("ans5 = " + ans5);
    }
}
