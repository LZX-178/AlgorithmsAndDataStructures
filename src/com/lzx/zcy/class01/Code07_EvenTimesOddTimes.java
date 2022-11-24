package com.lzx.zcy.class01;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-22 8:49
 * 异或的运用
 *      异或相当于 无进位的相加
 *      0 ^ N = N
 * 		N ^ N = 0
 */
public class Code07_EvenTimesOddTimes {
    // 一个数组中一个数出现了奇数次，其他数出现了偶数次，找到这个数
    public static void printOddTimesNum1(int[] arr){
        int num = 0;
        for (int i : arr) {
            num = num ^ i;
        }
        System.out.println(num);
    }
    // 一个数组中两个数出现了奇数次，其他数出现了偶数次，找到这个两个数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        // 先得到 a^b
        for (int i : arr) {
            eor= eor ^ i;
        }
        // a 和 b 必定在某一位(第 i 位)是不同的 假设 a 在第 i 位为 1
        // rightOne 第 i 位 为 1, 其余位为 0, ( 去记录 i 是很傻的 !!! )
        int rightOne = getFirst_1(eor);
        int onlyone = 0;
        for (int i : arr) {
            // 把数分成两类：一类在该位为 1 ；另一类为 0，a和b 必定分别属于这两类 题目简化为 printOddTimesNum1
            if ((rightOne & i) != 0){
                onlyone = onlyone ^ i;
            }
        }
        eor = onlyone ^ eor;
        System.out.println("num1 = " + eor);
        System.out.println("onlyone = " + onlyone);
    }

    // 获取一个整数最右侧的 1
    // 这个 1 的右边全部是 0 , 把 " 10000... " 先取反, 再加一, 这部分不变, 而 这个 1 的前面会取反
    public static int getFirst_1(int a){
        return a & (-a); // a & (~a + 1) = a & (-a)
    }

    // 一个数组中
    //      一个数(记为 x )出现了 K (K >= 1)次，
    //      其他数出现了 M (M > 1)次，
    // 找到这个数, M 是已知的, 且 K != M, 且 K 不是 M 的倍数
    public static void printOddTimesNum3(int[] arr, int M) {
        int[] counts = new int[32];

        // 用 counts 累加每个数每一位
        for (int num : arr) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>= 1;
            }
        }

        // 考察 x 的每一位 i,
        //      如果 x 在这一位为 0, counts[i] 为 M 的整数倍
        //      否则 counts[i] 不能被 M 整除
        int result = 0;
        for (int i = 31; ; i--) {
                result |= counts[i] % M == 0 ? 0 : 1; // 这里可以把 + 改成 |
                if (i == 0) break;
                result <<= 1;
        }
        System.out.println("result = " + result);
    }

    @Test
    public void test_printOddTimesNum3() {
        int[] arr = {5, 4, 5, 4, 10, 3, 3, 10, 777, 777, 999, 999, 999};
        printOddTimesNum3(arr, 2);
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }
}
