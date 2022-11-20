package com.lzx.hsp.dataStructures.stack;

import java.util.*;

/**
 * @author LZX
 * @code @create 2022-07-27 14:52
 * 逆波兰计算器 相当于将 Calculator 类的功能拆分为  将中缀表达式转换为后缀表达式 + 计算后缀表达式结果
 */
public class reversePolishNotationCalculator {
    private final Stack<Integer> numbersStack = new Stack<>();
    private final Map<Character, Integer> priority = new HashMap<>();

    public reversePolishNotationCalculator() {
        priority.put('_', 2);
        priority.put('*', 1);
        priority.put('/', 1);
        priority.put('+', 0);
        priority.put('-', 0);
    }
    //将中缀表达式转换为后缀表达式
    public List<String> toSuffixExpression(String infixExpression){

        Stack<Character> operateStack = new Stack<>();
        // 注意 此处 number栈 可以直接用 list 代替, 因为 number栈 没有出过栈
        ArrayList<String> suffixExpression = new ArrayList<>();

        StringBuilder numberString = new StringBuilder();
        // 下一个运算符可能是一元的
        boolean isUnaryOperator = true;

        for (int i = 0; i < infixExpression.length(); i++) {
            char temp = infixExpression.charAt(i);
            // 获取到数字
            if (temp <= '9' && temp >= '0'){
                numberString.append(temp);
                // 获取完整的数字
                for (i = i + 1; i < infixExpression.length(); i++) {
                    temp = infixExpression.charAt(i);
                    if (temp <= '9' && temp >= '0'){
                        numberString.append(temp);
                    }else {
                        break;
                    }
                }
                suffixExpression.add(numberString.toString());
                numberString.delete(0, numberString.length());
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
                    operateStack.peek() != '(' &&
                    priority.get(temp) <= priority.get(operateStack.peek())
                ){
                    suffixExpression.add(operateStack.pop().toString());
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
                Character operate = operateStack.pop();
                while (operate != '('){
                    suffixExpression.add(operate.toString());
                    operate = operateStack.pop();
                }
            }
        }

        // 此时 两个栈 的运算顺序是合法的, 运算之即可
        while (!operateStack.isEmpty()){
            suffixExpression.add(operateStack.pop().toString());
        }

        System.out.println(suffixExpression);
        return suffixExpression;
    }
    // 输入一个逆波兰表达式(后缀表达式) 计算其结果
    public int calculate(String infixExpression){
        List<String> stringList = toSuffixExpression(infixExpression);
        for (String string : stringList) {
            // 遇到数字直接入栈, 遇到运算符则从栈中弹出两个数运算 再入栈
            if (string.matches("\\d+")){
                numbersStack.push(Integer.parseInt(string));
            }else {
                int result = calculate(string.charAt(0));
                numbersStack.push(result);
            }
        }
        return numbersStack.pop();
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
            return - numbersStack.pop();
        }else {
            return calculate(operate, numbersStack.pop(), numbersStack.pop());
        }
    }
    // 检查是否是运算符
    private boolean isOperate(char operate){
        return operate == '+' || operate == '-' || operate == '*' || operate == '/';
    }
}
