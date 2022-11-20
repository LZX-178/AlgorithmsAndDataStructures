package com.lzx.hsp.dataStructures.stack;

/**
 * @author LZX
 * @code @create 2022-07-26 15:01
 * 使用数组模拟栈
 */
public class ArrayStack {
    private final int[] stack;
    //栈的容量
    private final int maxSize;
    //栈顶元素, -1 表示栈空
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("stack is full");
            return;
        }
        stack[++top] = value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        return stack[top--];
    }
    //显示栈顶元素
    public int peek(){
        return stack[top];
    }
    //清空栈
    public void clear(){
        this.top = -1;
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        System.out.print("stack : ");
        for (int i = top; i > -1; i--) {
            System.out.print(stack[i] + "  ");
        }
        System.out.println();
    }
    public void listAsChar(){
        if (isEmpty()){
            System.out.println("stack is empty");
            return;
        }
        System.out.print("stack : ");
        for (int i = top; i > -1; i--) {
            System.out.print((char) stack[i] + "  ");
        }
        System.out.println();
    }
}
