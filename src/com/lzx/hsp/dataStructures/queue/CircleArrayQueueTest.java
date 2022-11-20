package com.lzx.hsp.dataStructures.queue;

import java.util.Scanner;

/**
 * @author LZX
 * @code @create 2022-07-22 15:12
 * 使用数组模拟实现循环队列
 */
public class CircleArrayQueueTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the queue size");
        int size = scanner.nextInt();
        CircleArrayQueue queue = new CircleArrayQueue(size);

        char key;
        while (true){
            System.out.println();
            System.out.println("**********************************************");
            queue.showQueue();
            System.out.println("a for add");
            System.out.println("g for get");
            System.out.println("e for exit");
            System.out.println("**********************************************");
            System.out.println();

            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    if (queue.isFull()) {
                        System.out.println("queue is full");
                    } else {
                        System.out.println("enter the number");
                        int data = scanner.nextInt();
                        queue.enQueue(data);
                    }
                    break;
                case 'g':
                    if (queue.isEmpty()){
                        System.out.println("queue is empty");
                    }else {
                        int data = queue.deQueue();
                        System.out.println("data = " + data);
                    }
                    break;
                case 'e':
                    return;
                default:
                    System.out.println("unknown operate");
            }
        }

    }
}

