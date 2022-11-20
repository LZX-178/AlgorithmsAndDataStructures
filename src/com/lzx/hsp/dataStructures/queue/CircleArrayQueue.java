package com.lzx.hsp.dataStructures.queue;

/**
 * @author LZX
 * @code @create 2022-07-25 14:45
 */
public class CircleArrayQueue{
    // 约定:
    //      front = -1 表示队列为空且此时 rear 失去意义

    //队列头指针，指向头元素
    private int front;
    //队列尾指针，指向尾元素
    private int rear;
    //队列体
    private final int[] array;
    //队列最大容积
    private final int capacity;

    public CircleArrayQueue(int size) {
        this.capacity = size;
        array = new int[size];
        //两个指针的初始值
        front = -1;
        rear = -1;
    }
    // 入队方法
    public void enQueue(int data){
        if (isFull()){
            throw new RuntimeException("queue full");
        }
        //尾指针移动至“下一个”
        rear = (rear+1)%capacity;
        array[rear] = data;
        if (isEmpty()){
            //初始情况下，要移动头指针
            front = rear;
        }
    }
    // 出队方法
    public int deQueue(){
        if (isEmpty()){
            throw new RuntimeException("queue empty");
        }
        int data = array[front];
        if (front == rear){
            //只有一个元素时，队列置空
            front = -1;
        }else {
            //否则头指针移动至“下一个”
            front = (front+1)%capacity;
        }
        return data;
    }
    //判断队列是否满了：rear的 “下一个” 位置为front
    public boolean isFull(){
        return (rear + 1)%capacity == front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front == -1;
    }
    //获取有效元素个数：注意，队列满时 size 要手动改
    public int getQueueSize(){
        if (isEmpty()){
            return 0;
        }
        int size = (rear + capacity - front + 1)%capacity;

        return size == 0? capacity : size;
    }
    // 打印队列
    public void showQueue(){
        if (isEmpty()){
            System.out.println("    empty queue");
        }else {
            int size = getQueueSize();
            System.out.print("index:\t");
            for (int i = front; i < size + front; i++) {
                int index = i%capacity;
                System.out.printf(index + "  ");
            }
            System.out.print("\ndata:\t");
            for (int i = front; i < size + front; i++) {
                int index = i%capacity;
                int data = array[index];
                System.out.printf(data + "  ");
            }
            System.out.println("\nsize = " + size);
            System.out.println();
        }
    }
}

