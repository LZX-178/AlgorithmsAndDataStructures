package com.lzx.zcy.class23;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-02 20:27:29
 *
 * 假设有一个池子可以放球, 也可以把池子中的任意球抛弃, 池子的容量为 n,
 * 现有一个管道, 不断地输出小球 ( 1, 2, 3 ... 无穷),
 * 假设 目前输出 球的编号为 i,
 * 请给出一种策略, 使得 [1, i] 号小球出现在池子里的概率是相等的
 */
public class Code03_ReservoirSampling {

    /*
    * 假设 i号球 以 f(i) 的概率放入池子
    * 对于 [1, i-1] 来说, 他们目前在池子的概率是均等的, 均为 f(i-1)
    * i号球放入后, i-1 存在于池子的概率应该也变成 f(i), 则有
    *       f(i) = f(i-1)( 1-f(i) + f(i)*(n-1)/n )
    *       分 i号球 进袋和不进袋讨论 :
    *           不进 : i-1 留下的概率为 1-f(i)
    *           进 : i-1 留下的概率为   f(i)*(n-1)/n ,    (n-1)/n 表示需要踢走一个球
    * 利用倒数技巧化简得
    *       1/f(i) - 1/f(i-1) = 1/n
    * 注意初始条件的选择
    *       由于以上的推导过程假定了池子是满的
    *       对于 [1, n], 直接放入池子, 把池子填满, 即 f[1, n] = 1,
    *       对于 [i+1, ...],
    *           1/f(i) - 1/f(n) = (i-n)/n
    * 综上
    *       1 <= i <= n 时,
    *           f(i) = 1
    *       n < i 时,
    *           f(i) = n/i
    * */
    private static class RandomBox {
        // 池子
        private final int[] bag;
        private final int n;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            n = capacity;
            count = 0;
        }

        public void add(int i) {
            if (count < n){
                bag[count++] = i;
            }else {
                int isAdd = NumberUtils.getRandomInt(1, i);
                if (isAdd <= n){
                    bag[isAdd-1] = i;
                }
            }
        }

        public int[] getBag() {
            return bag;
        }
    }


    @Test
    public void test_RandomBox2() {
        // 蓄水池的容量
        int n = 30;
        // 放出的球数
        int m = 31;
        // 测试次数
        int time = 2000;
        // 统计每次测试的结果 count[i] 应该为
        //      1, 如果 m > n
        //          n/m * time
        //      2, 否则
        //          time
        int[] count = new int[m];

        for (int j = 0; j < time; j++) {
            RandomBox box = new RandomBox(n);
            for (int i = 1; i <= m; i++) {
                box.add(i);
            }
            int[] bag = box.getBag();
            for (int k : bag) {
                if (k != 0){
                    count[k - 1]++;
                }
            }
        }

        for (int i = 0; i < count.length; i++) {
            System.out.print(count[i] + "\t");
            if ((i+1) % 15 == 0){
                System.out.println();
            }
        }

    }
}
