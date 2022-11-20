package com.lzx.zcy.class15;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-15 9:33:51
 * 规定 1 和 A 对应, 2 和 B 对应, 3 和 C 对应 ... 26 和 Z 对应
 * 那么一个数字字符串比如 "111" 就可以转化为
 * "AAA", "KA', 'AK'
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * 01 的结果 为 0
 */
public class Code06_ConvertToLetterString {
    // 方法 1
    // 约定, str 只包含 0-9
    public int number1(String str) {
        if (str != null && str.length() > 0 ){
            return number1(str.toCharArray(), 0);
        }
        return 0;
    }
    // 返回 [index, end] 上有多少种转化方式
    private int number1(char[] chars, int index) {
        // 指针移动到 length 处, 说明之前的转换合法, 记一次
        if (index == chars.length){
            return 1;
        }
        // 任何以 '0' 开头的字符串都不能进行转换
        if (chars[index] == '0'){
            return 0;
        }
        // 存在 '1' 作为一位数转换和两位数转换 两种可能
        if (chars[index] == '1'){
            int res1 = number1(chars, index+1);
            int res2 = 0;
            if (index < chars.length-1){
                res2 = number1(chars, index+2);
            }
            return res1 + res2;
        }
        // 存在 '2' 作为一位数转换和两位数转换 两种可能
        if (chars[index] == '2'){
            int res1 = number1(chars, index+1);
            int res2 = 0;
            if (index < chars.length-1 && chars[index+1] <= '6'){
                res2 = number1(chars, index+2);
            }
            return res1 + res2;
        }
        return number1(chars, index+1);
    }

    // 方法 2
    // 对 number1 的改进
    public int number2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }
    public int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    // 方法 3
    // 将 number2 改为动态规划
    public int number3(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] result = new int[str.length() + 1];

        result[str.length()] = 1;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (chars[i] == '0'){
                result[i] = 0;
            }else {
                result[i] = result[i+1];
                if ( i<str.length()-1 && (chars[i]-'0')*10 + chars[i+1]-'0' < 27){
                    result[i] += result[i+2];
                }
            }
        }

        return result[0];
    }


    @Test
    public void test_number1() {
        String[] strings = {"7256", "1", "11", "01", "123", "122"};

        for (String s : strings) {
            int res1 = number1(s);
            int res2 = number3(s);
            System.out.println("string = " + s + ", result1 = " + res1);
            System.out.println("string = " + s + ", result2 = " + res2);
        }
    }

    @Test
    public void test_number2() {
        int N = 5;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number1(s);
            int ans1 = number2(s);
            int ans2 = number3(s);
            if (ans0 != ans1 || ans1 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("errors! string = " + s);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }
}
