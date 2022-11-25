package com.lzx.leetCode.chapter10_sort.heapSort;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-09-11 14:42:47
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值,
 * 那么中位数就是所有数值排序之后位于中间的数值
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 */
public class Code_offer_041_MedianFinder {

    @Test
    public void test_MedianFinder() {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        double m1 = obj.findMedian();
        System.out.println("m1 = " + m1);
        obj.addNum(3);
        double m2 = obj.findMedian();
        System.out.println("m2 = " + m2);
    }



    // 一个大顶堆, 一个小顶堆
    private static class MedianFinder {


        private final PriorityQueue<Integer> bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        private final PriorityQueue<Integer> smallHeap = new PriorityQueue<>();


        /** initialize your data structure here. */
        public MedianFinder() {

        }

        // 维护 bigHeap 的数量始终和 smallHeap 相等, 或者比 smallHeap 多一个
        public void addNum(int num) {
            // 添加前两个元素
            if (bigHeap.size() == 0){
                bigHeap.add(num);
                return;
            }else if (smallHeap.size() == 0){
                if (bigHeap.peek() > num){
                    smallHeap.add(bigHeap.poll());
                    bigHeap.add(num);
                }else {
                    smallHeap.add(num);
                }
                return;
            }

            Integer n1 = bigHeap.peek();
            Integer n2 = smallHeap.peek();
            if (bigHeap.size() == smallHeap.size()){
                if (num < n2){
                    bigHeap.add(num);
                }else {
                    smallHeap.add(num);
                    bigHeap.add(smallHeap.poll());
                }
            }else {
                if (num <= n1){
                    bigHeap.add(num);
                    smallHeap.add(bigHeap.poll());
                }else {
                    smallHeap.add(num);
                }
            }
        }

        public double findMedian() {
            if (bigHeap.size() == smallHeap.size()){
                return (bigHeap.peek() + smallHeap.peek()) / 2.0;
            }else {
                return bigHeap.peek();
            }
        }
    }
}
