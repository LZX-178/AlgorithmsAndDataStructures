package com.lzx.zcy.class18;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author LZX
 * @code @create 2022-12-25 13:52:47
 *
 * 给定一个整型数组 arr，和一个整数 num, 返回arr中达标子数组的数量
 * 某个 arr 中的子数组 sub，如果满足
 *      sub中最大值 - sub中最小值 <= num
 * 则为达标的
 */
public class Code02_AllLessNumSubArray {

    // 对数器
    public int num0(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            count += R - L;
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return count;
    }


    // 引理 :
    //      1 如果 [start, end] 是达标的, 那么它的子区间也是达标的
    //      2 如果 [start, end] 是不达标的, 那么包含它的区间也是不达标的
    // 策略
    //      对于 [start, end] 区间, 依次统计 以 start 开头的达标子区间数量,
    //      根据引理, 只需要找到最大的那个 end 即可, 达标数量为 end-start+1
    public int num1(int[] arr, int num) {
        int count = 0;
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();

        // 遍历 以 start 开头的子区间
        for (int start = 0, end = -1; start < arr.length; start++) {
            // 先尝试 end 尽量往后扩
            while (
                    qMax.isEmpty() ||
                    qMin.isEmpty() ||
                    end+1 < arr.length &&
                    arr[qMax.peekFirst()] - arr[end+1] <= num &&
                    arr[end+1] - arr[qMin.peekFirst()] <= num
            ){
                end++;

                // end 纳入 [start, end] 最大值队列
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[end]){
                    qMax.removeLast();
                }
                qMax.addLast(end);

                // end 纳入 [start, end] 最小值队列
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[end]){
                    qMin.removeLast();
                }
                qMin.addLast(end);
            }

            // 统计达标子区间数量
            count += end-start+1;

            // start++后, 最大值队列 和 最小值队列 可能需要更新
            if (qMax.get(0) == start){
                qMax.removeFirst();
            }
            if (qMin.get(0) == start){
                qMin.removeFirst();
            }
        }

        return count;
    }

    @Test
    public void test_num() {
        int[] arr = {1, 5, 7, 6, 8, 3, 2};
        int num = 4;

        int ans0 = num0(arr, num);
        int ans1 = num1(arr, num);

        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
    }

    @Test
    public void test_num1() {
        for (int i = 0; i < 500000; i++) {
            int[] arr = ArrayUtils.generateRandomArray(10, 60, 1, 20);
            int num = NumberUtils.getRandomInt(5, 15);

            int ans0 = num0(arr, num);
            int ans1 = num1(arr, num);

            if (ans0 != ans1){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                throw new RuntimeException("error");
            }
        }

        System.out.println("OK");
    }
}
