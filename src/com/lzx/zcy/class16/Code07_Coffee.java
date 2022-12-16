package com.lzx.zcy.class16;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-12-16 16:46:27
 *
 * 数组 arr 代表每一个咖啡机冲一杯咖啡的时间
 * 现在有 n 个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为 a，
 * 任何一个杯子自然挥发干净的时间为 b。
 * 四个参数：arr, n, a, b
 * 假设时间点从 0 开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 *
 * 待改进 :
 *      1 比较两种制作咖啡的算法 (用堆)
 *      2 改动态规划
 */
public class Code07_Coffee {
    // 对数器
    public class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }
    public class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }

    }
    public int minTime0(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return bestTime(drinks, a, b, 0, 0);
    }
    public int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // index号杯子 决定洗
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime(drinks, wash, air, index + 1, selfClean1);
        int p1 = Math.max(selfClean1, restClean1);

        // index号杯子 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }


    // 方法1
    public int minTime1(int[] arr, int n, int a, int b){
        int[] coffee = makeCoffee1(arr, n);
        return washCoffee1(coffee, a, b);
    }
    // 方法1 : 制作 n 杯 咖啡
    // 每个咖啡机先制作若干杯咖啡, 再从中挑出 n 杯最早的
    private int[] makeCoffee1(int[] arr, int n) {
        // 按咖啡机能力强弱排序
        Arrays.sort(arr);
        int[][] coffee = new int[arr.length][];

        // 最强的咖啡机制作 n 杯, 最后一杯的时间为 coffee[0][n-1]
        coffee[0] = new int[n];
        coffee[0][0] = arr[0];
        for (int i = 1; i < coffee[0].length; i++) {
            coffee[0][i] = coffee[0][i-1] + arr[0];
        }

        // 之后的咖啡机制作 coffee[0][n-1] / arr[i] 杯
        for (int i = 1; i < coffee.length; i++) {
            int length = coffee[0][n-1] / arr[i];
            if (length == 0){
                continue;
            }
            coffee[i] = new int[length];
            coffee[i][0] = arr[i];
            for (int j = 1; j < coffee[i].length; j++) {
                coffee[i][j] = coffee[i][j-1] + arr[i];
            }
        }
        // 归并所有咖啡机
        for (int i = 1; i < coffee.length; i++) {
            if (coffee[i] != null){
                coffee[0] = merge(coffee[0], coffee[i]);
            }
        }
        return coffee[0];
    }
    private int[] merge(int[] a, int[] b) {

        int[] temp = new int[a.length];

        int l = 0, r = 0;
        int i = 0;
        while (l < a.length && r < b.length && i < temp.length){
            temp[i++] = a[l] <= b[r] ? a[l++] : b[r++];
        }

        while (i < temp.length){
            temp[i++] = a[l++];
        }

        return temp;
    }
    // 方法1 : 洗 n 杯 咖啡
    private int washCoffee1(int[] coffee, int a, int b) {
        if (a >= b){
            return coffee[coffee.length-1] + b;
        }
        return washCoffee1(coffee, 0, 0, a, b);
    }
    // 假设 index 以前的杯子已经洗干净了
    // 只需要返回 index及以后的杯子 弄干净的最早时间
    // timeCoffee 为咖啡机的可用时间
    private int washCoffee1(int[] coffee, int index, int timeCoffee, int a, int b) {
        if (index == coffee.length-1){
            return Math.min(coffee[index]+b, Math.max(timeCoffee, coffee[index])+a);
        }
        // 如果晾干 index
        int p1 = Math.max(
                coffee[index]+b,                                        // index 干净的时间
                washCoffee1(coffee, index + 1,timeCoffee, a, b)  // index 以后杯子干净的时间
        );
        // 如果咖啡机洗 index
        int newTimeCoffee = Math.max(timeCoffee, coffee[index]) + a;
        int p2 = Math.max(
                newTimeCoffee,                 // index 干净的时间
                washCoffee1(coffee, index + 1,newTimeCoffee, a, b) // index 以后杯子干净的时间
        );
        return Math.min(p1,p2);
    }


    // 方法2 : 将方法1 的洗杯子改成记忆化搜索
    public int minTime2(int[] arr, int n, int a, int b){
        int[] coffee = makeCoffee1(arr, n);
        return washCoffee2(coffee, a, b);
    }
    HashMap<String, Integer> map;
    private int washCoffee2(int[] coffee, int a, int b) {
        if (a >= b){
            return coffee[coffee.length-1] + b;
        }
        map = new HashMap<>();
        return washCoffee2(coffee, 0, 0, a, b);
    }
    private int washCoffee2(int[] coffee, int index, int timeCoffee, int a, int b) {
        int ans;
        int p1;
        int p2;
        String key = index + "_" + timeCoffee;
        Integer value = map.get(key);
        if (value != null) {
            return value;
        }

        if (index == coffee.length-1){
            p1 = coffee[index] + b;
            p2 = Math.max(timeCoffee, coffee[index]) + a;
        }else {
            // 如果晾干 index
            p1 = Math.max(
                    coffee[index]+b,                                        // index 干净的时间
                    washCoffee2(coffee, index + 1,timeCoffee, a, b)  // index 以后杯子干净的时间
            );
            // 如果咖啡机洗 index
            int newTimeCoffee = Math.max(timeCoffee, coffee[index]) + a;
            p2 = Math.max(
                    newTimeCoffee,                 // index 干净的时间
                    washCoffee2(coffee, index + 1,newTimeCoffee, a, b) // index 以后杯子干净的时间
            );
        }
        ans = Math.min(p1, p2);
        map.put(key, ans);
        return ans;
    }


    @Test
    public void test_makeCoffee() {
        int[] coffee = makeCoffee1(new int[]{2, 7, 5}, 10);
        System.out.println("Arrays.toString(coffee) = " + Arrays.toString(coffee));
    }

    @Test
    public void test_minTime() {
        for (int i = 0; i < 1000000; i++) {

//            int[] arr0 = {9, 3};
//            int[] arr1 = Arrays.copyOf(arr0, arr0.length);
//            int n = 3;
//            int a = 2;
//            int b = 6;

            int[] arr0 = ArrayUtils.generateRandomArray(2, 6, 1, 10);
            int[] arr1 = Arrays.copyOf(arr0, arr0.length);
            int[] arr2 = Arrays.copyOf(arr0, arr0.length);
            int n = NumberUtils.getRandomInt(5, 15);
            int a = NumberUtils.getRandomInt(2, 6);
            int b = NumberUtils.getRandomInt(4, 10);

            int ans0 = minTime0(arr0, n, a, b);
            int ans1 = minTime1(arr1, n, a, b);
            int ans2 = minTime2(arr2, n, a, b);

            if (ans0 != ans1 || ans0 != ans2){
                System.out.println("Arrays.toString(arr0) = " + Arrays.toString(arr0));
                System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
                System.out.println("n = " + n);
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }

        }
        System.out.println("OK");
    }
}
