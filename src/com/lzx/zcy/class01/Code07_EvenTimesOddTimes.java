package com.lzx.zcy.class01;

/**
 * @author LZX
 * @code @create 2022-07-22 8:49
 */
public class Code07_EvenTimesOddTimes {
    //一个数组中一个数出现了奇数次，其他数出现了偶数次，找到这个数
    public static void printOddTimesNum1(int[] arr){
        int num = 0;
        for (int i : arr) {
            num = num ^ i;
        }
        System.out.println(num);
    }
    //一个数组中两个数出现了奇数次，其他数出现了偶数次，找到这个两个数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        // 先得到 a^b
        for (int i : arr) {
            eor= eor ^ i;
        }
        // a和b必定在某一位是不同的 假设 a 在这一位为 1
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
    // 这个 1 的右边全部是 0 , 把 " 10000... " 先取反, 再加一, 这部分不变, 而 这个1 的前面会取反
    public static int getFirst_1(int a){
        return a&(-a); //a&(~a + 1) = a&(-a)
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
