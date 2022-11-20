package com.lzx.hsp.algorithm.string.kmp;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-19 8:49:20
 */
public class ViolentMatchTest {
    @Test
    public void test_match() {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你2好";
        String str2 = "尚硅谷你尚硅你2";
        int index = new ViolentMatch().match(str1, str2);
        for (int i = 0; i < str1.length(); i++) {
            System.out.printf("%d:%2c\n",i , str1.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < str2.length(); i++) {
            System.out.printf("%d:%2c\n",i , str2.charAt(i));
        }
        System.out.println("index = " + index);
    }
}
