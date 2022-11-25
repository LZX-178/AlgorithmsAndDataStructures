package com.lzx.leetCode.chapter05_stack;

import org.junit.Test;

import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-07-27 10:21
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 1 <= s.length <= 3 * 10^5
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1"和 "+(2 + 3)"无效)
 * '-' 可以用作一元运算(即 "-1"和 "-(2 + 3)"是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 */
public class Code_0224_BasicCalculator {
    @Test
    public void test_() {
        String[] expressions ={
                "1 + 1",
                "2 - 3",
                " 2-1 + 2 ",
                "(1+(4+5+2)-3)+(6+8)",
                "-1 + 2",
                "-(1+2)",
                "-1",
                "(-1)",
//                "1-(-2)"
        };
        for (String expression : expressions) {
            int result = calculate(expression);
            System.out.println("expression : " + expression + " = "  + result);
        }
    }

    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operateStack = new Stack<>();
        StringBuilder number = new StringBuilder();
        boolean isUnaryOperator = true;

        //遍历表达式
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            //获取数字
            if (temp <= '9' && temp >= '0'){
                number.append(temp);
                continue;
            }
            if (number.length() > 0){
                numStack.push(Integer.parseInt(number.toString()));
                number.delete(0, number.length());
                isUnaryOperator = false;
            }
            //获取操作符
            if (temp == '+' || temp == '-'){
                // 处理一元减法的思路: 一元减法只可能在表达式一开始 或 '(' 一开始出现, 用标志位 isUnaryOperator 标注
                while (!operateStack.isEmpty() && operateStack.peek() != '('){
                    Character operate = operateStack.pop();
                    int result;
                    // 一元减法的运算和 二元的不同
                    if (operate == '_'){
                        result = -numStack.pop();
                    }else {
                        result = calculate(operate, numStack.pop(), numStack.pop());
                    }
                    numStack.push(result);
                }
                // 一元减法操作符入栈
                if (isUnaryOperator && temp == '-'){
                    operateStack.push('_');
                }else {
                    operateStack.push(temp);
                }
                // 处理括号
            }else if (temp == '('){
                operateStack.push(temp);
                isUnaryOperator = true;
            }else if (temp == ')'){
                //计算至下一个'('
                char operate = operateStack.pop();
                while (operate != '('){
                    int result;
                    if (operate == '_'){
                        result = -numStack.pop();
                    }else {
                        result = calculate(operate, numStack.pop(), numStack.pop());
                    }
                    numStack.push(result);
                    operate = operateStack.pop();
                }
            }
        }
        if (number.length() > 0){
            numStack.push(Integer.parseInt(number.toString()));
            number.delete(0, number.length());
        }
        // 此时 两个栈 的运算顺序是合法的, 运算之即可
        while (!operateStack.isEmpty()){
            Character operate = operateStack.pop();
            int result;
            if (operate == '_'){
                result = -numStack.pop();
            }else {
                result = calculate(operate, numStack.pop(), numStack.pop());
            }
            numStack.push(result);
        }
        return numStack.pop();
    }

    //运算
    private int calculate(char operate, int a, int b){
        switch (operate){
            case '+':
                return a + b;
            case '-':
                return b - a;
            default:
                System.out.println(operate);
                throw new RuntimeException("operate error");
        }
    }

}
