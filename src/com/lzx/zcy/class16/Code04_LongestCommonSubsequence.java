package com.lzx.zcy.class16;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-18 12:10:25
 * 给定两个字符串text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class Code04_LongestCommonSubsequence {
    public int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        char[] c2 = s2.toCharArray();
        int[][] dp = new int[s1.length()][];

        dp[0] = new int[s2.length()];
        int index = s2.indexOf(s1.charAt(0));
        if (index != -1) {
            for (int i = index; i < s2.length(); i++) {
                dp[0][i] = 1;
            }
        }
        // 讨论 s1.charAt(index1) 是否为 最长公共子序列的最后一个
        //      1 如果不是, 问题等价于上面的格子
        //      2 如果是, 找到 s2 中 s1.charAt(index1) 最后出现的 位置, 问题等价于 该格子左边的格子
        for (int index1 = 1; index1 < s1.length(); index1++) {
            ArrayList<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < s2.length(); i++) {
                if (s2.charAt(i) == s1.charAt(index1)) {
                    indexes.add(i);
                }
            }
            dp[index1] = Arrays.copyOf(dp[index1 - 1], s2.length());
            if (indexes.size() > 0) {
                indexes.add(s2.length());
                Integer start = indexes.get(0);
                Integer end = indexes.get(1);
                int i = 0;
                if (start == 0){
                    for (int j = start; j < end; j++) {
                        dp[index1][j] = Math.max(dp[index1][j], 1);
                    }
                    i++;
                }
                for (; i < indexes.size() - 1; i++) {
                    start = indexes.get(i);
                    end = indexes.get(i + 1);
                    for (int j = start; j < end; j++) {
                        dp[index1][j] = Math.max( dp[index1][j],  dp[index1-1][start-1]+1);
                    }
                }
            }
        }

        return dp[s1.length() - 1][s2.length() - 1];
    }

    // 还能再改进, 因为只需要用到当前行和上一行
    public int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s1.length()][s2.length()];

        dp[0][0] = s1.charAt(0) == s2.charAt(0)? 1 : 0;
        for (int i = 1; i < s2.length(); i++) {
            dp[0][i] = Math.max(dp[0][i-1], s1.charAt(0) == s2.charAt(i)? 1 : 0);
        }
        for (int i = 1; i < s1.length(); i++) {
            dp[i][0] = Math.max(dp[i-1][0], s2.charAt(0) == s1.charAt(i)? 1 : 0);
        }
        
        // 讨论 s1.charAt(index1) 和 s2.charAt(index1) 是否为 最长公共子序列的最后一个
        //      1 如果不是, 那么至少可以去掉其中一个, 问题等价于 上面的格子 和 左边的格子
        //      2 如果是, 问题等价于 左上角的格子
        for (int index1 = 1; index1 < s1.length(); index1++) {
            for (int index2 = 1; index2 < s2.length(); index2++) {
                dp[index1][index2] = Math.max(dp[index1-1][index2], dp[index1][index2-1]);
                
                if (s1.charAt(index1) == s2.charAt(index2)){
                    dp[index1][index2] = Math.max(dp[index1][index2], dp[index1-1][index2-1]+1);
                }
            }
        }

        return dp[s1.length() - 1][s2.length() - 1];
    }

    @Test
    public void test_1() {
        String[] text1 = {
                "ezupkr",
                "bsbininm",
                "abcde",
                "abc",
                "abc"
        };
        String[] text2 = {
                "ubmrapg",
                "jmjkbkjkv",
                "ace",
                "abc",
                "def"
        };

        for (int i = 0; i < text1.length; i++) {
            int result1 = longestCommonSubsequence1(text1[i], text2[i]);
            int result2 = longestCommonSubsequence2(text1[i], text2[i]);
            System.out.println("***** " + "longestCommonSubsequence1" + " *****");
            System.out.println("text1[i] = " + text1[i]);
            System.out.println("text2[i] = " + text2[i]);
            System.out.println("result1 = " + result1);
            System.out.println("result2 = " + result2);
            if (result1 != result2){
                throw new RuntimeException("error");
            }
        }
    }
}
