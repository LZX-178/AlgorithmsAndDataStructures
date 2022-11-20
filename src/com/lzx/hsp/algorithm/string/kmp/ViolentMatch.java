package com.lzx.hsp.algorithm.string.kmp;

/**
 * @author LZX
 * @code @create 2022-08-19 8:39:31
 * 暴力匹配法
 */
public class ViolentMatch {
    public int match(String str1, String str2){
        if (str1 == null || str2 == null || str2.length() > str1.length()){
            return -1;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < c1.length){
            if (c1[i] == c2[j]){
                if (j == c2.length -1){
                    return i - j;
                }
                i++;
                j++;
            }else { // 若当前字符匹配失败, i 和 j 需要回溯
                i = i - j + 1;
                j = 0;
            }
        }
        return -1;
    }
}
