package com.lzx.hsp.algorithm.string.kmp;

/**
 * @author LZX
 * @code @create 2022-08-19 9:57:06
 */
public class KMPMatch {
    public int match(String str1, String str2){
        if (str1 == null || str2 == null || str2.length() == 0 || str1.length() == 0 || str2.length() > str1.length()){
            return -1;
        }
        int[] next = getNext(str2);
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < c1.length && j < c2.length){
            if (j == -1 || c1[i] == c2[j]){ // 如果匹配成功, 或者上一次匹配失败且 j 回溯到 -1
                i++;
                j++;
            }else { // 若当前字符匹配失败, j 需要回溯
                j = next[j];
            }
        }
        if (j == c2.length){
            return i - j;
        }else {
            return -1;
        }
    }
    // 获取 next 数组, next 的含义是, 当 str2[j] 失配时, 指针 j 应该回溯到哪一个位置( next[j] )
    // next[j] 的值为 str2中j之前的字符 的 最大公共前后缀的长度
    public int[] getNext(String str) {
        char[] chars = str.toCharArray();
        int[] next = new int[str.length()];
        next[0] = -1;
        // 方法一 : 暴力计算
//        for (int j = 1; j < next.length; j++) {
//            next[j] = getMaxPublicLength(str.substring(0, j));
//            if (next[j] != -1 && str.charAt(next[j])  == str.charAt(j)){
//                next[j] = next[next[j]];
//            }
//        }
        // 方法二 : 递推计算
//        for (int j = 1; j < next.length; j++) {
//            if (next[j-1] == -1){
//                // 如果 前一个字符的 next 是 -1, 说明前一个字符 是 位置0
//                next[j] = 0;
//                continue;
//            }
//            if (chars[next[j-1]] == chars[j-1]){
//                // 如果前一个字符 等于 前一个字符的 next
//                // 说明 当前字符 的 最大公共前后缀的长度 = 前一个字符 的 最大公共前后缀的长度 + 1
//                next[j] = next[j - 1] + 1;
//            }else {
//                // 如果前一个字符 不等于 前一个字符的 next
//                // 在这种情况下, 可以肯定的是 next[j] 不会 超过 next[j-1], 否则 next[j-1] 应该更大
//                // 将 0 - next[j-1] 看成一个串, 再去检验 chars[next[next[j-1]]] 和 chars[j-1] 是否相等
//                int temp = next[j-1];
//                while (chars[temp] != chars[j-1]){
//                    temp = next[temp];
//                    // 如果回溯到了第一个字符也不符合chars[j-1], 则 说明 当前字符 的 最大公共前后缀的长度 = 0
//                    if (temp == -1){
//                        break;
//                    }
//                }
//                next[j] = temp+1;
//            }
//        }
        // 方法三 : 方法二 的 简写
        // 0-j 为已经计算好 next 的字符
//        int j = 0;
//        while(j < next.length-1) {
//            if (next[j] == -1){
//                next[++j] = 0;
//            }else {
//                int temp = next[j];
//                while (temp != -1 && chars[temp] != chars[j]){
//                    temp = next[temp];
//                }
//                next[++j] = temp+1;
//            }
//        }
        // 方法四 : 方法三 的 简写
        // 0-j 为已经计算好 next 的字符
//        int j = 0;
//        // temp 为 next[j] 或 next[temp], 表示可能找到了 0-j 的 最大公共前后缀的长度
//        int temp = next[j];
//        while(j < next.length-1) {
//            if (temp == -1 || chars[temp] == chars[j]){
//                next[++j] = ++temp;
//            }else {
//                temp = next[temp];
//            }
//        }
        // 方法五 : 方法四 的 改进
        // 0-j 为已经计算好 next 的字符
        int j = 0;
        // temp 为 next[j] 或 next[temp], 表示可能找到了 0-j 的 最大公共前后缀的长度
        int temp = next[j];
        while(j < next.length-1) {
            if (temp == -1 || chars[temp] == chars[j]){
                j++;
                temp++;
                next[j] = temp;
            }else {
                temp = next[temp];
            }
        }
        for (int jj = 1; jj < next.length; jj++) {
            if (chars[next[jj]] == chars[jj]){
                next[jj] = next[next[jj]];
            }
        }

        return next;
    }
    // 获取一个字符串的最大公共前后缀的长度
    public int getMaxPublicLength(String str) {
        if (str.length() == 1){
            return 0;
        }
        int maxLength = 0;
        // 遍历每一个前缀 : 0-i
        for (int i = 0; i < str.length() - 1; i++) {
            int j;
            for (j = 0; j <= i; j++) {
                if (str.charAt(j) != str.charAt(str.length() - i - 1 + j)){
                    break;
                }
            }
            // 如果当前的这个前缀也是后缀, 则更新 maxLength
            if (j == i + 1){
                maxLength = Math.max(maxLength, j);
            }
        }
        return maxLength;
    }
}
