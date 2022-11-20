package com.lzx.hsp.algorithm.string.kmp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-19 10:27:11
 */
public class KMPMatchTest {
    @Test
    public void test_getNext() {
        KMPMatch match = new KMPMatch();

        String str = "尚硅谷你尚硅你2";
        int[] next = match.getNext(str);
        System.out.println("str = " + str);
        System.out.println("next = " + Arrays.toString(next));
    }

    @Test
    public void test_match() {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你2好";
        String str2 = "尚硅谷你尚硅你2";
        int index = new KMPMatch().match(str1, str2);
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
