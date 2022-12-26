package com.lzx.zcy.class18;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * 窗口最大值的更新结构, 假设窗口为 [start, end]
 *      使用双端队列存索引
 *      每次窗口右移时
 *          1 尝试将 end 加入队列尾, 加入之前需要弹出 那些比 arr[end] 小的元素的索引
 *          2 如果队列头元素已经不在窗口中了, 需要弹出队列头元素
 *          3 队列头元素即为当前窗口的最大值
 * 注意 : start 和 end 是不能回退的
 */

/**
 * @author LZX
 * @code @create 2022-12-25 11:10:42
 * 假设一个固定大小为 w 的窗口，依次划过 arr，
 * 返回每一次滑出窗口的最大值
 * 例如，arr=[4，3，5，4，3，3，6，7]，W=3
 * 返回：[5，5，5，4，6，7]
 */
public class Code01_SlidingWindowMaxArray {
    // 对数器
    public int[] getMaxWindow0(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - w) {
                qmax.pollFirst();
            }
            if (R >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    // 方法1
    public int[] getMaxWindow1(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w > arr.length) {
            return null;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int[] ans = new int[arr.length - w + 1];
        int end = 0, start = 0;

        // 现将 w 窗口内的元素加入队列
        while (end < w-1){
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[end]){
                queue.removeLast();
            }
            queue.addLast(end++);
        }

        // 将 w 窗口移动至数组末尾
        for (; end < arr.length; end++) {
            // 对于每次移动
            // 先尝试将 end 加入队列尾
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[end]){
                queue.removeLast();
            }
            queue.addLast(end);

            // 获取窗口的最大值
            ans[start++] = arr[queue.get(0)];

            // 处理过期值
            if (queue.get(0) < start){
                queue.removeFirst();
            }
        }

        return ans;
    }

    @Test
    public void test_getMaxWindow() {
        int[] arr={4, 41, 24, 32, 17};
        int w = 3;

        int[] ans0 = getMaxWindow0(arr, w);
        int[] ans1 = getMaxWindow1(arr, w);


        System.out.println("Arrays.toString(ans) = " + Arrays.toString(ans0));
        System.out.println("Arrays.toString(ans) = " + Arrays.toString(ans1));
    }

    @Test
    public void test_getMaxWindow1() {
        for (int i = 0; i < 500000; i++) {
            int[] arr = ArrayUtils.generateRandomArray(10, 50, 1, 50);
            int w = NumberUtils.getRandomInt(2, 6);

            int[] ans0 = getMaxWindow0(arr, w);
            int[] ans1 = getMaxWindow1(arr, w);

            for (int j = 0; j < ans0.length; j++) {
                if (ans0[j] != ans1[j]){

                    System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
                    System.out.println("Arrays.toString(ans0) = " + Arrays.toString(ans0));
                    System.out.println("Arrays.toString(ans1) = " + Arrays.toString(ans1));

                    throw new RuntimeException("error");
                }
            }

        }

        System.out.println("OK");
    }
}
