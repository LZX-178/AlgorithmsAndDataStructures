package com.lzx.zcy.class18;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author LZX
 * @code @create 2022-12-26 13:17:51
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 *
 * 举例
 *      输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *      输出: 3
 * 解释:
 *      从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 *      开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 *      开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 *      开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 *      开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 *      开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 *      因此，3 可为起始索引。
 *
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 */
public class Code03_GasStation {

    // 从 index = 0 出发, 依次检验当前加油站是否合格
    // end 记录来到的加油站
    // curGas 记录当前加油站去到下一加油站后剩余的油
    //      如果 curGas >= 0, 则继续去下一个加油站
    //      否则, index 不合格, 且 [index, end] 范围的加油站均不合格,
    //          因为到达这些加油站时, curGas >= 0 ( 如果就是从这些加油站出发, curGas == 0, 就更不合格了  )
    //      index 来到 end+1
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length, index = 0, end = 0, curGas;

        while (index < n){
            curGas = gas[index] - cost[index];
            while (curGas >= 0){
                if ((++end)%n == index){
                    return index;
                }
                curGas += gas[end%n] - cost[end%n];
            }
            index = ++end;
        }
        return -1;
    }

    // 对数器
    public boolean[] canCompleteCircuitAll0(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }

    // 修改题目, 返回所有合格的加油站
    // 使用 gas-cost, 得到 remain 数组, 元素个数为 n
    // 原问题转变为
    //      遍历 remain 每一个元素 i
    //      检查 i 的 n 个前缀数组的和, 如果都大于 0, 则 i 是合法的
    // 构造一个 2n 长度的前缀和数组 prefixSum,
    // 元素 i 的 n 个前缀数组的和 为 = prefixSum[i, i+n-1] 的 n 个元素减 prefixSum[i-1]
    public boolean[] canCompleteCircuitAll1(int[] gas, int[] cost) {

        int n = gas.length;
        boolean[] ans = new boolean[n];

        // 构造 prefixSum 数组
        int[] prefixSum = new int[n + n];
        prefixSum[0] = gas[0] - cost[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1] + gas[i] - cost[i];
        }
        for (int i = 0; i < n; i++) {
            prefixSum[i+n] = prefixSum[i+n-1] + gas[i] - cost[i];
        }

        // 先将窗口内的元素放入队列
        LinkedList<Integer> qMin = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!qMin.isEmpty() && prefixSum[qMin.peekLast()] >= prefixSum[i]){
                qMin.removeLast();
            }
            qMin.addLast(i);
        }

        // 窗口移动
        int start = 0, end = n-1, offset = 0;
        while (true){
            // 检验窗口内最小值 和 offset 的大小
            if (prefixSum[qMin.get(0)] >= offset){
                ans[start] = true;
            }

            if (start == n-1){
                break;
            }

            // 更新 offset
            offset = prefixSum[start];

            // 尝试弹出队列头
            if (qMin.get(0) == start++){
                qMin.removeFirst();
            }

            // 窗口后移后添加队列尾
            end++;
            while (!qMin.isEmpty() && prefixSum[qMin.peekLast()] >= prefixSum[end]){
                qMin.removeLast();
            }
            qMin.addLast(end);
        }

        return ans;
    }

    @Test
    public void test_canCompleteCircuit() {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};

        int ans = canCompleteCircuit(gas, cost);

        System.out.println("ans = " + ans);
    }

    @Test
    public void test_canCompleteCircuitAll() {
        for (int j = 0; j < 5000000; j++) {
            int[] gas = ArrayUtils.generateRandomArray(5, 20, 1, 10);
            int[] cost = ArrayUtils.generateRandomArray(gas.length, gas.length, 1, 10);

            boolean[] ans0 = canCompleteCircuitAll0(gas, cost);
            boolean[] ans1 = canCompleteCircuitAll1(gas, cost);

            for (int i = 0; i < ans0.length; i++) {
                if (ans0[i] != ans1[i]){
                    System.out.println("Arrays.toString(gas) = " + Arrays.toString(gas));
                    System.out.println("Arrays.toString(cost) = " + Arrays.toString(cost));
                    System.out.println("Arrays.toString(ans0) = " + Arrays.toString(ans0));
                    System.out.println("Arrays.toString(ans1) = " + Arrays.toString(ans1));
                    throw new RuntimeException("error");
                }
            }
        }
        System.out.println("OK");
    }
}
