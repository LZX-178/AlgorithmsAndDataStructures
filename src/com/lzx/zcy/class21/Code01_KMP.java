package com.lzx.zcy.class21;

import com.lzx.utils.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-12-31 11:00:36
 */
public class Code01_KMP {

    // 对数器
    public int[] getNextArray0(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // 当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
    public int getIndexOf0(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        // O(M) m <= n
        int[] next = getNextArray0(str2);
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // y == 0
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    // 方法1
    // 如果 s1 的某个字串和 s2 匹配, 返回这个字串第一个字符的索引
    // 否则, 返回 -1
    public int getIndexOf1(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() == 0 || s1.length() < s2.length()) {
            return -1;
        }

        int[] next = getNextArray1(s2);
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int i1 = 0;
        int i2 = 0;

        while (i1 < c1.length) {
            if (c1[i1] == c2[i2]) {
                if (i2 == c2.length - 1) {
                    return i1 - c2.length + 1;
                }
                i1++;
                i2++;
            } else if (i2 == 0) {
                // 如果 c2[0] 匹配失败, 则 i1 递增
                i1++;
            } else {
                // 如果 c2[i2] (i2 > 0) 匹配失败,
                // 则 i2 跳到 next[i2],
                //      next[i2] 的含义是, c2 的子串 [0, i2-1] 的前后缀中, 相等的前后缀 中的最大长度
                //          以下将 c2 的子串 [0, i2-1] 简称为 c2
                //          假设 这个前后缀 的为 x (长度为 next[i2] ), 需要证明两件事
                //              1, 直接假定 前缀x 和 i1前面的next[i2]个字符 匹配是可行的,
                //              现在只需要验 前缀x的下一个字符(next[i2]) 和 i1 即可
                //                  因为 前缀x 和 后缀x 是相等的,
                //                  而 后缀x 在 c2[i2]匹配失败时 已经和 i1前面的next[i2]个字符 验过是相等的
                //              2, 如果 经过命题1之后, c2 和 c1 匹配成功, 得到的结果为 index
                //              需要证明 : i2 跳到 next[i2] 时, 不会漏掉一个 比 index 更小的解
                //                  假设存在一个比 index 更小的解, index0
                //                  则 [index0, i1-1] 和 c2 的某个前缀 y 是匹配的, 且 y 的长度大于 x
                //                  而在 c2[i2]匹配失败时, [index0, i1-1] 又和 c2 的后缀 y 匹配成功
                //                  这 和 x 是 最大的相等前后缀矛盾
                i2 = next[i2];
            }
        }
        return -1;
    }
    // 获取 next 数组
    //      对于 s 中的每一个字符 c, 计算 c 之前的字符串 相等前后缀 的最大长度
    // 约定 next[0] = -1
    // next[1] 一定为 0
    public int[] getNextArray1(String s) {
        int length = s.length();

        if (length == 1) {
            return new int[]{-1};
        } else if (length == 2) {
            return new int[]{-1, 0};
        }

        char[] chars = s.toCharArray();

        int[] next = new int[length];
        // 维护一个 temp 数组, 从大到小 记录每一个位置 所有的 相等前后缀 的长度
        // temp 的更新策略 :
        //      来到下一个位置 i 时,
        //          1 依次验证 chars[i] 和 temp 中 所有的 相等前后缀 能否续上,
        //          2 如果 chars[i] 和 chars[0] 相等, 还需要添加一个 长度为 1 的相等前后缀
        int[] temp = new int[length - 2];
        next[0] = -1;

        for (int i = 2, index = 0; i < length; i++) {
            int newIndex = 0;
            for (int j = 0; j < index; j++) {
                if (chars[temp[j]] == chars[i - 1]) {
                    temp[newIndex++] = temp[j] + 1;
                }
            }
            if (chars[i - 1] == chars[0]) {
                temp[newIndex++] = 1;
            }
            index = newIndex;
            next[i] = index == 0 ? 0 : temp[0];
        }
        return next;
    }

    // 方法2 :
    //      改进分支结构
    //      改进获取 next 数组
    public int getIndexOf2(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() == 0 || s1.length() < s2.length()) {
            return -1;
        }

        int[] next = getNextArray2(s2);
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int i1 = 0;
        int i2 = 0;

        while (true) {
            if (c1[i1] == c2[i2]) {
                i1++;
                i2++;
                if (i2 == c2.length) {
                    return i1 - c2.length;
                }
            } else{
                if (i2 == 0) {
                    i1++;
                } else {
                    i2 = next[i2];
                }

                if (c1.length - i1 < c2.length - i2){
                    break;
                }
            }

        }
        return -1;
    }
    // 获取 next 数组
    // 约定 next[0] = 0,
    // next[1] 一定为 0
    public int[] getNextArray2(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int[] next = new int[length];

        for (int i = 2; i < length; i++) {
            int temp = next[i-1];

            // 如果 chars[temp] 和 chars[i-1] 匹配失败
            // 则一直向前跳
            while (chars[temp] != chars[i-1]){
                if (temp == 0){
                    temp = -1;
                    break;
                }
                temp = next[temp];
            }
            next[i] = temp+1;
        }
        return next;
    }


    @Test
    public void test_getNextArray1() {
        String s = "abcaaa";
        int[] next = getNextArray2(s);
        System.out.println("s = " + s);
        System.out.println("Arrays.toString(next) = " + Arrays.toString(next));

        s = "abdcabdef";
        next = getNextArray2(s);
        System.out.println("s = " + s);
        System.out.println("Arrays.toString(next) = " + Arrays.toString(next));

        s = "abababe";
        next = getNextArray2(s);
        System.out.println("s = " + s);
        System.out.println("Arrays.toString(next) = " + Arrays.toString(next));

        s = "aaaaa";
        next = getNextArray2(s);
        System.out.println("s = " + s);
        System.out.println("Arrays.toString(next) = " + Arrays.toString(next));

    }

    @Test
    public void test_getNextArray2() {
        for (int j = 0; j < 5000000; j++) {
            String s = StringUtils.getRandomString(6, 16);

            int[] ans0 = getNextArray0(s.toCharArray());
            int[] ans1 = getNextArray1(s);
            int[] ans2 = getNextArray2(s);

            if (ans0.length != ans1.length || ans2.length != ans0.length) {
                throw new RuntimeException("error");
            }

            for (int i = 1; i < ans0.length; i++) {
                if (ans0[i] != ans1[i] || ans2[i] != ans0[i]) {
                    System.out.println("s = " + s);
                    System.out.println("Arrays.toString(ans0) = " + Arrays.toString(ans0));
                    System.out.println("Arrays.toString(ans1) = " + Arrays.toString(ans1));
                    System.out.println("Arrays.toString(ans2) = " + Arrays.toString(ans2));
                    throw new RuntimeException("error");
                }
            }
        }

        System.out.println("ok");
    }

    @Test
    public void test_getIndexOf1() {
        String s1 = "xux";
        String s2 = "ux";

        int ans0 = getIndexOf0(s1, s2);
        int ans1 = getIndexOf1(s1, s2);

        if (ans0 != ans1) {
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
            System.out.println("Arrays.toString(ans0) = " + ans0);
            System.out.println("Arrays.toString(ans1) = " + ans1);
            throw new RuntimeException("error");
        }
    }

    @Test
    public void test_getIndexOf2() {
        for (int j = 0; j < 5000000; j++) {
            String s1 = StringUtils.getRandomString(1, 50);
            String s2 = StringUtils.getRandomString(1, 30);

            int ans0 = getIndexOf0(s1, s2);
            int ans1 = getIndexOf1(s1, s2);
            int ans2 = getIndexOf2(s1, s2);

            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
                System.out.println("Arrays.toString(ans0) = " + ans0);
                System.out.println("Arrays.toString(ans1) = " + ans1);
                System.out.println("Arrays.toString(ans2) = " + ans2);
                throw new RuntimeException("error");
            }

        }

        System.out.println("ok");
    }
}
