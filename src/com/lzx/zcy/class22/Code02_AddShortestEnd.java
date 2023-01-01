package com.lzx.zcy.class22;

import com.lzx.utils.StringUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-01 13:36:40
 * <p>
 * 给定一个字符串 s, 在 s 后面添加若干个字符, 使得 s 是回文串
 * 求添加的最短字符
 */
public class Code02_AddShortestEnd {

    // 对数器
    public String shortestEnd0(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }
    public char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // 方法1
    // 相当于 在manacher算法的过程中 求 r 第一次到达 s 结尾位置时的 l
    public String shortestEnd1(String s) {
        char[] chars = insertChar(s);
        int length = chars.length;

        // 记录回文半径
        int[] radius = new int[length];
        int r = -1, c = 0, distance, ii;

        for (int i = 0; i < length; i++) {
            if (i <= r) {
                distance = r - i + 1;
                ii = (c << 1) - i;

                if (radius[ii] != distance) {
                    radius[i] = Math.min(radius[ii], distance);
                    continue;
                }

                radius[i] = distance;
            } else {
                radius[i] = 1;
            }

            while (i + radius[i] < length && i - radius[i] >= 0 && chars[i + radius[i]] == chars[i - radius[i]]) {
                radius[i]++;
            }

            r = i + radius[i] - 1;
            c = i;
            if (r == length - 1) {
                break;
            }
        }

        int len = ((c << 1) - r) >> 1;
        char[] temp = new char[len];
        for (int i = 0; i < len; i++) {
            temp[i] = chars[((len - i) << 1) - 1];
        }
        return new String(temp);
    }

    // 方法2 : 对方法1 的改进,
    // r 第一次到达 s 结尾位置时, c 必然过半了, 直接从中间位置开始即可
    public String shortestEnd2(String s) {
        char[] chars = insertChar(s);
        int length = chars.length;

        // 记录回文半径
        int[] radius = new int[length];
        int r = -1, c = 0, distance, ii;

        // 字符串加工过后长度必然为奇数, 从最中间开始找即可
        for (int i = (length>>1) +1; i < length; i++) {
            if (i <= r) {
                distance = r - i + 1;
                ii = (c << 1) - i;

                if (radius[ii] != distance) {
                    radius[i] = Math.min(radius[ii], distance);
                    continue;
                }

                radius[i] = distance;
            } else {
                radius[i] = 1;
            }

            while (i + radius[i] < length && i - radius[i] >= 0 && chars[i + radius[i]] == chars[i - radius[i]]) {
                radius[i]++;
            }

            r = i + radius[i] - 1;
            c = i;
            if (r == length - 1) {
                break;
            }
        }

        int len = ((c << 1) - r) >> 1;
        char[] temp = new char[len];
        for (int i = 0; i < len; i++) {
            temp[i] = chars[((len - i) << 1) - 1];
        }
        return new String(temp);
    }

    private char[] insertChar(String s) {
        char[] chars = new char[(s.length() << 1) + 1];
        int i = 1;
        chars[0] = '#';

        for (char c : s.toCharArray()) {
            chars[i++] = c;
            chars[i++] = '#';
        }
        return chars;
    }

    @Test
    public void test_shortestEnd1() {
        String s = "ggg";

        String ans0 = shortestEnd0(s);
        String ans1 = shortestEnd1(s);

        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
    }

    @Test
    public void test_shortestEnd2() {
        for (int i = 0; i < 500000; i++) {
            String s = StringUtils.getRandomString(1, 300);
            String ans0 = shortestEnd0(s);
            String ans1 = shortestEnd1(s);
            String ans2 = shortestEnd2(s);

            if (!ans0.equals(ans1)) {
                System.out.println("s = " + s);
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }
}
