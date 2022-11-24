package com.lzx.leetCode.chapter03_hashTable;


/**
 * @author LZX
 * @code @create 2022-11-23 16:47
 * 242. 有效的字母异位词
 * 
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 */
public class Code_242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        int l = s.length();
        if (t.length() != l){
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < l; i++) {
            arr[s.charAt(i)-'a']++;
        }
        // 如果是 有效的字母异位词, arr 一定全为 0,
        // 否则 一定有 正数 和 负数
        for (int i = 0; i < l; i++) {
            if (--arr[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
