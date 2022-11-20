package com.lzx.zcy.class15;

import org.junit.Test;

import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-11-13 19:37:42
 * 将一个栈逆序
 * 不能申请额外的数据结构, 可以使用递归
 */
public class Code05_ReverseStack {
    public void reverseStack1(Stack<Integer> stack){
        for (int i = 1; i < stack.size(); i++) {
            reverseStack1(stack, i);
        }
    }
    // 将 stack 栈底元素上移至 第 i 个位置 (栈顶元素为第一个)
    public void reverseStack1(Stack<Integer> stack, int i){
        Integer num = stack.pop();
        if (stack.isEmpty()){
            stack.push(num);
        }else {
            if (i == 1) {
                reverseStack1(stack, 1);
                Integer temp = stack.pop();
                stack.push(num);
                stack.push(temp);
            }else {
                reverseStack1(stack, i-1);
                stack.push(num);
            }
        }
    }

    public void reverseStack2(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getLast(stack);
        reverseStack2(stack);
        stack.push(i);
    }
    // 取出栈底元素并返回
    public int getLast(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getLast(stack);
            stack.push(result);
            return last;
        }
    }

    @Test
    public void test_reverseStack1() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        reverseStack1(stack);
        System.out.println(stack);
    }
}
