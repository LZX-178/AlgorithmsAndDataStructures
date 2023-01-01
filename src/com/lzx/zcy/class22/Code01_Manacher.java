package com.lzx.zcy.class22;

import com.lzx.utils.StringUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-01 8:34:25
 * <p>
 * manacher (马拉车算法) 求解最大回文字串
 * 返回最大回文子串的长度
 */
public class Code01_Manacher {

    // 对数器
    public int manacher0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
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
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
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

    // 方法1, 最快!!!
    public int manacher1(String s) {
        char[] chars = insertChar(s);
        int length = chars.length;
        // 记录各位置的回文半径 (包括回文中心)
        int[] radius = new int[length];
        // r 记录着回文子串到达过的最右边界, c 记录到达 r 时的回文中点
        int r = -1, c = 0, max = 2, ii, distance;

        for (int i = 0; i < length; i++) {
            // radius[i] 先赋一个初始值
            if (i <= r) {
                // 如果i 落在 [2c-r,r] 范围内, 不需要做 [2c-r,r]内 的比较操作
                // ii 为 i 关于 c 的对称点,
                //      如果 ii 的最大回文串也在 [2c-r,r] 范围内, 则 radius[i] = radius[ii]
                //      否则 radius[i] = distance, 根据对称关系可得
                ii = c + c - i;
                distance = r - i + 1;
                if (radius[ii] != distance){
                    radius[i] = Math.min(radius[ii], distance);
                    continue;
                }
                radius[i] = distance;
            }else {
                // 如果i 落在 [2c-r,r] 范围外, 只能 radius[i] 从 1 开始扩张
                radius[i] = 1;
            }

            // 尝试扩张 radius[i]
            while (i - radius[i] >= 0 && i + radius[i] < length && chars[i - radius[i]] == chars[i + radius[i]]) {
                radius[i]++;
            }
            max = Math.max(max, radius[i]);
            c = i;
            r = i + radius[i] - 1;
        }

        return max-1;
    }

    // 方法2 : 方法1 的优雅版, 但效率不如 方法1,
    //      因为把很多分支都合并了, 但最后的结果又需要把这些分支分开
    //      不要一味地追求代码的体积短
    public int manacher2(String s) {
        char[] chars = insertChar(s);
        int length = chars.length;
        // 记录各位置的回文半径 (包括回文中心)
        int[] radius = new int[length];
        // r 记录着回文子串到达过的最右边界, c 记录到达 r 时的回文中点
        int r = -1, c = 0;
        int max = 2;

        for (int i = 0; i < length; i++) {

            // radius[i] 先赋一个初始值
            //      如果 i 被 r 包含, 则 找到对称点的radius 和 i到r的距离, 两者的最小值是不用验的
            //      否则, radius[i] 从 1 开始往后验
            radius[i] = i <= r ? Math.min(radius[(c<<1) - i], r - i + 1) : 1;

            // 尝试扩张 radius[i]
            while (i + radius[i] < length && i - radius[i] >= 0){
                if (chars[i + radius[i]] == chars[i - radius[i]]){
                    radius[i]++;
                }else {
                    break;
                }
            }

            // 如果 radius[i] 扩张到了更远的位置, 更新 r 和 c
            if (i + radius[i] - 1 > r){
                r = i + radius[i] - 1;
                c = i;
            }

            // 更新max
            max = Math.max(max, radius[i]);
        }


        return max - 1;
    }

    private char[] insertChar(String s) {
        char[] chars = new char[s.length() * 2 + 1];
        chars[0] = '#';
        char[] sArr = s.toCharArray();

        for (int i = 0, j = 1; i < sArr.length; i++) {
            chars[j++] = sArr[i];
            chars[j++] = '#';
        }
        return chars;
    }

    @Test
    public void test_insertChar() {
        String s = "mm";
        char[] chars = insertChar(s);
        String s2 = new String(chars);
        System.out.println("s = " + s);
        System.out.println("s2 = " + s2);
    }

    @Test
    public void test_manacher() {

        int len = 500000;
        String[] strings = new String[len];
        int[] ans0 = new int[len];
        int[] ans1 = new int[len];
        int[] ans2 = new int[len];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = StringUtils.getRandomString(1000, 2000);
        }

        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < strings.length; i++) {
            ans1[i] = manacher1(strings[i]);
        }
        long endTime1 = System.currentTimeMillis();

        long startTime0 = System.currentTimeMillis();
        for (int i = 0; i < strings.length; i++) {
            ans0[i] = manacher0(strings[i]);
        }
        long endTime0 = System.currentTimeMillis();



        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < strings.length; i++) {
            ans2[i] = manacher2(strings[i]);
        }
        long endTime2 = System.currentTimeMillis();

        for (int i = 0; i < strings.length; i++) {
            if (ans0[i] != ans1[i] || ans0[i] != ans2[i]) {
                System.out.println("s = " + strings[i]);
                System.out.println("ans0 = " + ans0[i]);
                System.out.println("ans1 = " + ans1[i]);
                System.out.println("ans2 = " + ans2[i]);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
        System.out.println("time0 = " + (endTime0-startTime0));
        System.out.println("time1 = " + (endTime1-startTime1));
        System.out.println("time2 = " + (endTime2-startTime2));
    }

    @Test
    public void test_manacher1() {

        String s = "mm";

        int ans0 = manacher0(s);
        int ans1 = manacher1(s);
        int ans2 = manacher2(s);

        if (ans0 != ans1 || ans0 != ans2) {
            System.out.println("s = " + s);
            System.out.println("ans0 = " + ans0);
            System.out.println("ans1 = " + ans1);
            System.out.println("ans2 = " + ans2);
            throw new RuntimeException("error");
        }
    }
}
