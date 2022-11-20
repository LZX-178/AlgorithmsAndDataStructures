package com.lzx.zcy.class06;

import com.lzx.utils.*;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author LZX
 * @code @create 2022-09-11 14:00:50
 */
public class Code02_Heap_test {
    @Test
    public void test_PriorityQueue() {
        // 大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }

    @Test
    public void test_MyMaxHeap() {
        int value = 1000;
        for (int i = 0; i < 500000; i++) {
            int size = NumberUtils.getRandomInt(1, 100);
            Code02_Heap.MyMaxHeap my = new Code02_Heap.MyMaxHeap(size);
            Code02_Heap.RightMaxHeap test = new Code02_Heap.RightMaxHeap(size);
            int operateTimes = NumberUtils.getRandomInt(1, size);
            for (int j = 0; j < operateTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
