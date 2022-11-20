package com.lzx.hsp.dataStructures.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-07-26 15:33
 * 实现计算器
 * 可以包含 - + * / (  )  以及 一元运算的减法
 */
public class Calculator {
    private final ArrayStack numStack = new ArrayStack(50);
    private final ArrayStack operateStack = new ArrayStack(50);
    private final Map<Character, Integer> priority = new HashMap<>();

    public int calculate(String expression){

        numStack.clear();
        operateStack.clear();

        StringBuilder number = new StringBuilder();
        // 下一个运算符可能是一元的
        boolean isUnaryOperator = true;

        //遍历表达式
        for (int i = 0; i < expression.length(); i++) {
            char temp = expression.charAt(i);
            // 获取到数字
            if (temp <= '9' && temp >= '0'){
                number.append(temp);
                // 获取完整的数字
                for (i = i + 1; i < expression.length(); i++) {
                    temp = expression.charAt(i);
                    if (temp <= '9' && temp >= '0'){
                        number.append(temp);
                    }else {
                        break;
                    }
                }
                numStack.push(Integer.parseInt(number.toString()));
                number.delete(0, number.length());
                // 下一个运算符不是一元的
                isUnaryOperator = false;
            }
            // 获取到运算符
            if (isOperate(temp)){
                // 满足以下条件之一 运算符直接入栈
                    // 1 符号栈为空
                    // 2 符号栈前一个元素为 '('
                    // 3 当前运算符优先级更高 (如果是一元减法, 会符合前两种情况)
                            // 如果当前的操作符的优先级小于或者等于栈中的操作符, 代表栈顶的 两个操作数 和 一个操作符 是顺序合法的,
                            // 则计算之(计算到栈为空 或者下一个 '(' ), 若不计算, 对于不满足交换律的运算符会出错
                            // 如果当前的操作符的优先级更高, 代表栈顶的 一个操作数 和 当前操作符 和 下一个入栈的操作数 是顺序合法的
                // 否则, "运算" 至顺序合法
                while (
                        !operateStack.isEmpty() &&
                        (char)operateStack.peek() != '(' &&
                        priority.get(temp) <= priority.get((char)operateStack.peek())
                ){
                    int result = calculate((char) operateStack.pop());
                    numStack.push(result);
                }
                // 入栈运算符
                // 一元运算的减法和二元运算的减法不一样
                if (isUnaryOperator && temp == '-'){
                    operateStack.push('_');
                }else {
                    operateStack.push(temp);
                }
                continue;
            }
            // 获取到括号
            if (temp == '('){
                // 遇到 '(' 直接入栈
                operateStack.push(temp);
                // 下一个运算符可能是一元的
                isUnaryOperator = true;
            }else if (temp == ')'){
                // 遇到 ')' 则 "运算" 至下一个 '('
                char op = (char) operateStack.pop();
                while (op != '('){
                    int result = calculate(op);
                    numStack.push(result);
                    op = (char) operateStack.pop();
                }
            }
        }
        // 此时 两个栈 的运算顺序是合法的, 运算之即可
        while (!operateStack.isEmpty()){
            int result = calculate((char) operateStack.pop());
            numStack.push(result);
        }
        return numStack.pop();
    }

    //约定: 数字越大, 优先级越高
    public Calculator() {
        priority.put('_', 2);
        priority.put('*', 1);
        priority.put('/', 1);
        priority.put('+', 0);
        priority.put('-', 0);
    }
    //运算
    private int calculate(char operate, int a, int b){
        switch (operate){
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return b / a;
            default:
                throw new RuntimeException("operate error");
        }
    }
    private int calculate(char operate){
        if (operate == '_'){
            return -numStack.pop();
        }else {
            return calculate(operate, numStack.pop(), numStack.pop());
        }
    }
    // 检查是否是运算符
    private boolean isOperate(char operate){
        return operate == '+' || operate == '-' || operate == '*' || operate == '/';
    }
}

