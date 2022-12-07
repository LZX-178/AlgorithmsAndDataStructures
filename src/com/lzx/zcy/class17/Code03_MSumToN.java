package com.lzx.zcy.class17;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-25 17:17:40
 *
 *     题目三
 *     定义一种数，可以表示成若干（数量>1）连续正数和的数
 *     比如．
 *              5=2+3，5就是这样的数
 *              12=3+4+5，12就是这样的数
 *              1不是这样的数，因为要求数量大于1个、连续正数和
 *              2=1+1，2也不是因为等号右边不是连续正数
 *     给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class Code03_MSumToN {
    // 当 输入的数 >= 3 时
    //      1 所有的奇数是 合法 的
    //      2 对于一个偶数 N 来说, 如果它能分解为 N = m * n, 其中 m 为偶数, n 为奇数( n >= 3 ), 它也是合法的
    //          可以从两个方面证明 :
    //              (1) m > (n-1)/2 时 : 可分解为 n 个 以 m 为中心的数
    //              (2) m <= (n-1)/2 时 : 可分解为 m/2 个 以 n 为中心的数
    //      3 综上所述, 只有 非正数 和 2的幂 为非法的数 : 1,2,4,8....
    public boolean isMSum(int num) {
        if(num < 1){
            return false;
        }
        return (num & (num - 1)) != 0;
    }

    @Test
    public void test_isMsum() {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum(num));
        }
    }
}
