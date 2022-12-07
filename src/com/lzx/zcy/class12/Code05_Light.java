package com.lzx.zcy.class12;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author LZX
 * @code @create 2022-12-07 20:15
 *      给定一个字符串 str,只由'X'和'.'两种字符构成。
 *      'X'表示墙，不能放灯，也不需要点亮
 *      表示居民点，可以放灯，需要点亮
 *      如果灯放在 i 位置，可以让 i一1，i 和 i+1 三个位置被点亮
 *      返回如果点亮 str 中所有需要点亮的位置，至少需要几盏灯
 */
public class Code05_Light {

    // 遍历数组 当前位置是 i
    // 如果是 X 跳过
    // 如果是 . , 灯数+1
    //      如果是 .x, 下一个位置跳到 i+2
    //      如果是 .., 下一个位置跳到 i+3
    public int minLight1(String road) {
        if (road == null || road.length() == 0){
            return 0;
        }
        int ans = 0;
        char[] array = road.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '.'){
                ans++;
                i++;
                if (i< array.length && array[i] == '.'){
                    i++;
                }
            }
        }
        return ans;
    }

    // 更简洁的解法
    // 两个X之间，数一下.的数量，然后除以3，向上取整
    // 把灯数累加
    public int minLight2(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    @Test
    public void test_minLight() {
        for (int j = 0; j < 5000000; j++) {

            StringBuilder s = new StringBuilder();
            int length = NumberUtils.getRandomInt(10, 50);

            for (int i = 0; i < length; i++) {
                if (Math.random() < 0.5) {
                    s.append('X');
                }else{
                    s.append('.');
                }
            }

            int ans1 = minLight1(s.toString());
            int ans2 = minLight2(s.toString());

            if (ans1 != ans2){
                System.out.println("s = " + s);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }

        }
        System.out.println("OK");
    }
}
