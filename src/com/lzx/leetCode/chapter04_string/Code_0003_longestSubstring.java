package com.lzx.leetCode.chapter04_string;

import org.junit.Test;

//程序效率待改进!!!

/**
 * @author LZX
 * @create 2022-04-11 16:25
 *
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 程序效率待改进!!!
 */
public class Code_0003_longestSubstring {
    @Test
    public void test1(){
        String s1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s1));
        String s2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s2));
        String s3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s3));
        String s4 = "cdd";
        System.out.println(lengthOfLongestSubstring(s4));
        String s5 = "abcb";
        System.out.println(lengthOfLongestSubstring(s5));
    }

    public int lengthOfLongestSubstring(String s) {
        return findLongestSubstring(s, 0, s.length());
    }

    public int findLongestSubstring(String s, int startIndex, int endIndex){

        if (endIndex == startIndex){
            return 0;
        }

        if (endIndex == startIndex+1){
            return 1;
        }

        int i;
        int j=0;

lable:  for (i = startIndex; i < endIndex-1; i++) {
            for (j = i + 1; j < endIndex; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    break lable;
                }
            }
        }

        if (i == endIndex-1 && j == endIndex){
            return endIndex - startIndex;
        }else {
            return Math.max(findLongestSubstring(s, startIndex, j), findLongestSubstring(s, i+1, endIndex));
        }

    }
}
