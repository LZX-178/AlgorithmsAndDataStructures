package com.lzx.zcy.class12;

import com.lzx.utils.NumberUtils;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-12-07 15:56:50
 *      一块金条切成两半，需要花费和长度数值一样的铜板。
 *      比如长度为 20 的金条，不管怎么切，都要花费 20 个铜板。
 *      给定数组{10，20，30}，整块金条长度为60，金条要分成 10，20，30 三个部分。
 *      如果先把长度 60 的金条分成 10 和 50，花费 60, 再把长度 50 的金条分成 20 和 30，花费 50，一共花费 110。
 *      如果先把长度 60 的金条分成 30 和 30，花费 60, 再把长度 30 的金条分成 10 和 20，花费 30，一共花费 90。
 * 输入一个数组，返回分割的最小代价。
 */
public class Code03_LessMoneySplitGold {

    // 首先, 切的次数是固定的 n-1,
    // 整个过程可以看成各个子段合并成原金条, 每次选择两个子段进行合并, 代价为两个子段的长度和
    // 贪心策略为 : 每次选择最小的两个子段进行合并
    // 说明 : 如果把某个两个子段进行合并成 M, 后续 合并 M 时都要记入 两个子段 的代价, 所以越大的段越晚合并越好
    // 证明 :
    //      该证明过程等同于证明 霍夫曼编码的最优性
    //          1，最优编码下，频率越小的字符编码必然越长
    //          2，最优编码下，频率最小的那两个字符的编码长度相等

    // 方法1 : 在原数组上建堆实现
    private int length;
    private int[] heap;
    public int lessMoney1(int[] arr) {
        if (arr == null || arr.length < 2){
            return 0;
        }
        length = arr.length;
        heap = arr;
        for (int i = arr.length-1; i >= 0; i--) {
            heapify(i);
        }
        int ans = 0;
        while (length >= 2){
            int n = pop();
            n += pop();
            ans += n;
            heap[length] = n;
            heapInsert(length++);
        }
        return ans;
    }
    public int pop(){
        if (length == 1){
            length--;
            return heap[0];
        }
        swap(heap, 0, --length);
        heapify(0);
        return heap[length];
    }
    public void heapify(int index){
        int leftChild = (index << 1) | 1;

        while (leftChild < length){
            if (leftChild + 1 < length && heap[leftChild+1] < heap[leftChild]){
                leftChild++;
            }
            if (heap[index] <= heap[leftChild]){
                break;
            }
            swap(heap, index, leftChild);
            index = leftChild;
            leftChild = (index << 1) | 1;
        }
    }
    public void heapInsert(int index){
        int father = (index - 1) / 2;
        while (heap[index] < heap[father]){
            swap(heap, index, father);
            index = father;
            father = (index - 1) / 2;
        }
    }
    private void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 方法2 : 对数器
    public int lessMoney2(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }


    @Test
    public void test_lessMoney1() {
        int[] arr = {20, 30, 10};
        int ans1 = lessMoney1(arr);
        System.out.println("ans1 = " + ans1);
    }

    @Test
    public void test_lessMoney2() {
        for (int j = 0; j < 500000; j++) {

            int[] arr1 = new int[NumberUtils.getRandomInt(5, 15)];
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = NumberUtils.getRandomInt(10, 100);
            }
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);

            int ans1 = lessMoney1(arr1);
            int ans2 = lessMoney2(arr2);

            if (ans1 != ans2){
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                throw new RuntimeException("error");
            }
        }
        System.out.println("OK");
    }

}
