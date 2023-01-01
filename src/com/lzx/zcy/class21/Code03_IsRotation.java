package com.lzx.zcy.class21;

import com.lzx.utils.StringUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-01 16:09:35
 *
 * 给定字符串 s1 和 s2
 * 判断 s1 和 s2 是否互为旋转子串
 *      如果 s1 = s11 + s12, s2 = s12 + s11, 则 s1 和 s2 互为旋转子串
 *      例 a bcdef 和 bcdef a 互为旋转子串
 */
public class Code03_IsRotation {
    // 对数器
    public boolean isRotation0(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }
    public int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }
    public int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    // 方法1 , 将 s1 加工成 s3 = s1+s1, 判断 s2 是否为 s3 的子串即可
    public boolean isRotation1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s1.length() != s2.length()){
            return false;
        }
        char[] c1 = process(s1);
        char[] c2 = s2.toCharArray();

        int[] nexts = getNexts(c2);
        int i1 = 0, i2 = 0, len1 = c1.length, len2 = c2.length;

        while (true){
            if (c1[i1] == c2[i2]){
                i1++;
                i2++;
                if (i2 == len2){
                    return true;
                }
            }else{
                if (i2 == 0){
                    i1++;
                }else {
                    i2 = nexts[i2];
                }

                if (len1 - i1 < len2 - i2){
                    break;
                }
            }
        }
        return false;
    }
    private int[] getNexts(char[] chars) {
        int length = chars.length;
        int[] nexts = new int[length];

        for (int i = 2, temp; i < length; i++) {
            temp = nexts[i-1];
            while (chars[temp] != chars[i-1]){
                if (temp == 0){
                    temp = -1;
                    break;
                }
                temp = nexts[temp];
            }
            nexts[i] = temp+1;
        }

        return nexts;
    }
    private char[] process(String s) {
        char[] chars = s.toCharArray();
        char[] ans = new char[chars.length + chars.length - 1];
        System.arraycopy(chars, 0, ans, 0, chars.length);
        System.arraycopy(chars, 0, ans, chars.length, chars.length-1);

        return ans;
    }

    @Test
    public void test_process() {
        String s = "aacc";
        char[] chars = process(s);
        String ss = new String(chars);
        System.out.println("ss = " + ss);
    }

    @Test
    public void test_isRotation1() {
        String str1 = "abc";
        String str2 = "cab";
        boolean ans0 = isRotation0(str1, str2);
        boolean ans1 = isRotation1(str1, str2);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
    }

    @Test
    public void test_isRotation2() {
        for (int i = 0; i < 500000; i++) {
            String str1 = StringUtils.getRandomString(1, 5);
            String str2 = StringUtils.getRandomString(1, 5);;
            boolean ans0 = isRotation0(str1, str2);
            boolean ans1 = isRotation1(str1, str2);
            if (ans0 != ans1){
                System.out.println("str1 = " + str1);
                System.out.println("str2 = " + str2);
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
