package com.lzx.zcy.class19;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-12-30 12:21:05
 *
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 * 例
 *      输入：arr = [3,1,2,4]
 *      输出：17
 *      解释：
 *          子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 *          最小值为 3，   1，  2，  4，  1，    1，    2，    1，      1，      1，和为 17。
 *  1 <= arr[i] <= 3 * 10^4
 */
public class Code06_SumOfSubarrayMinimums {

    // 考察 每个最小值 i , 只需要计算以 i 为最小值的子数组的个数即可
    // 即 遍历 arr, 对于每一个 i, 找到其左右两边最近的 比 i 小的数的索引 start, end,
    // 计算 [start+1, end-1] 上有多少个子数组
    public int sumSubarrayMins(int[] arr) {
        int[] stack = new int[arr.length + 2];
        int index = -1;
        long ans = 0;
        int start, end;

        int[] newArr = new int[arr.length + 2];
        System.arraycopy(arr, 0, newArr, 1, arr.length);

        stack[++index] = 0;

        for (int i = 1; i < newArr.length; i++) {
            while (newArr[stack[index]] > newArr[i]){
                int t = stack[index--];

                // 相等数的处理, 等价于如下问题
                //      在 数组 [stack[index]+1  ... t2 ... t1 ... t ... i-1] 上
                //      寻找 至少包含一个 t 的子数组的个数
                //  方法1 :
                //      对子数组 [start, end], 考察 start 的范围,
                //          start 在 [t+1, i-1], 不合法
                //          start 在 [t1 + 1 , t], 则 end 必须在 [t, i-1];
                //          start 在 [t2 + 1 , t1], 则 end 必须在 [t1, i-1];
                //  方法2 :
                //      求补集, 答案 = 所有子数组的个数 - 不包含 t 的子数组的个数
                while (newArr[stack[index]] == newArr[t]){
                    start = t - stack[index];
                    end = i - t;
                    ans +=  (long) start * end * newArr[t];
                    t = stack[index--];
                }
                start = t - stack[index];
                end = i - t;
                ans += (long) start * end * newArr[t];
            }
            stack[++index] = i;
        }

        return (int) (ans % 1000000007);
    }

    //  方法2 :
    //      求补集, 答案 = 所有子数组的个数 - 不包含 t 的子数组的个数
    //  长度为 n (n >= 0) 的数组的子数组个数为 : n + ... + 2 + 1 = n(n+1) / 2,
    public int sumSubarrayMins1(int[] arr) {
        int[] stack = new int[arr.length + 2];
        int index = -1;
        long ans = 0;
        int temp;

        int[] newArr = new int[arr.length + 2];
        System.arraycopy(arr, 0, newArr, 1, arr.length);

        stack[++index] = 0;

        for (int i = 1; i < newArr.length; i++) {
            while (newArr[stack[index]] > newArr[i]){
                int t = stack[index--];

                temp = ((i - t - 1) * (i - t)) >> 1;

                //  方法2 :
                //      求补集, 答案 = 所有子数组的个数 - 不包含 t 的子数组的个数
                while (newArr[stack[index]] == newArr[t]){
                    temp += ((t- stack[index] - 1) * (t- stack[index])) >> 1;
                    t = stack[index--];
                }
                temp += ((t- stack[index] - 1) * (t- stack[index])) >> 1;
                ans += (((long) (i - stack[index] - 1) * (i-stack[index]) >> 1) - temp) * newArr[t];
            }
            stack[++index] = i;
        }

        return (int) (ans % 1000000007);
    }

    @Test
    public void test_sumSubarrayMins() {
        int[] arr = {3, 1, 2, 4};

        int ans = sumSubarrayMins(arr);
        int ans1 = sumSubarrayMins1(arr);
        System.out.println("ans = " + ans);
        System.out.println("ans1 = " + ans1);
    }
}
