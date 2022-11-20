package com.lzx.hsp.algorithm.dynamicProgram;


/**
 * @author LZX
 * @code @create 2022-08-17 14:50:41
 * 分数背包问题
 * 目标 : 装入的背包的总价值最大， 并且重量不超出
 *       01 背包 : 装入的物品不能重复
 *       完全背包 : 装入的物品可以重复
 */
public class KnapsackFraction {
    // 01 背包 的解, weight 为物品的重量, value 为物品的价值
    public int solution(int[] weight, int[] value, int capacity){


        if (weight.length < 1 || weight.length != value.length || capacity < 0){
            throw new RuntimeException("solution error");
        }
        // count 表示物品的数量
        int count = weight.length;
        // result[i][j] 表示背包容量为 j 时, 可选物品为 1-i 时, 问题的最优解
        // i 为物品的编号, 取值范围: 1-count
        int[][] result = new int[count + 1][capacity + 1];
        // 记录物品放入的路径
        int[][] path = new int[count + 1][capacity + 1];
        for (int i = 1; i <= count; i++) {
            // i 从 1 开始, 没有可选物品时, 最优解为 0 (默认值)
            // j 从 1 开始, 背包容量为 0, 最优解为 0 (默认值)
            for (int j = 1; j <= capacity; j++) {
                if (weight[i-1] > j){   // 如果当前物品重量大于背包容量, 当前物品不可能放进背包, 相当于可选物品为 : 1 至 i-1
                    result[i][j] = result[i-1][j];
                }else { // 如果当前物品重量大于背包容量, 最优解为以下两种情况的较大值
                    int r1 = value[i-1] + result[i-1][j - weight[i-1]]; // 把当前物品放入背包, 剩余容量取最优解
                    int r2 = result[i-1][j]; // 不把当前物品放入背包, 相当于可选物品为 : 1 至 i-1

                    if (r1 > r2){
                        result[i][j] = r1;
                        path[i][j] = 1;
                    }else {
                        result[i][j] = r2;
                    }
                }
            }
        }

        // 寻找背包放入的商品
        int tempIndex = count;
        int tempCapacity = capacity;
        while (tempIndex > 0 && tempCapacity > 0){
            if (path[tempIndex][tempCapacity] == 1){
                System.out.println("put " + tempIndex);
                tempCapacity -= weight[tempIndex-1];
            }
            tempIndex--;
        }

        return result[count][capacity];
    }
}
