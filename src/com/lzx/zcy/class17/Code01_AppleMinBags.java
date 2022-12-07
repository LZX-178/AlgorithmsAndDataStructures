package com.lzx.zcy.class17;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-25 14:30:53
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量
 *      1）能装下6个苹果的袋子
 *      2）能装下8个苹果的袋子
 *      小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，
 *      他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满
 * 给定一个正整数N，返回至少使用多少袋子。
 * 如果N无法让使用的每个袋子必须装满，返回一1
 */
public class Code01_AppleMinBags {
    public int minBags1(int apple){
        int maxBigBags = apple >>> 3;
        int remaining = apple - (maxBigBags << 3);

        for (int i = maxBigBags; i >= 0; i--) {

            if (remaining % 6 == 0){
                return i + remaining / 6;
            }
            remaining += 8;
        }
        return -1;
    }
    // 先用暴力解寻找规律
    // 发现 : 在 16 以后每 4 个偶数一个循环
    public int minBags2(int apple){
        if (apple < 1 || apple % 2 == 1){
            return -1;
        }
        if (apple < 18){
            return (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple-18) / 8 +3;
    }

    @Test
    public void test_minBags() {
        for (int i = 1; i < 1000; i++) {
            int res1 = minBags1(i);
            int res2 = minBags2(i);
            if (res1 != -1 || res2 != -1){
                System.out.print(i);
                System.out.println(" : " + res1);
                System.out.print(i);
                System.out.println(" : " + res2);
                if (res1 != res2){
                    throw new RuntimeException("error");
                }
            }
        }
    }
}
