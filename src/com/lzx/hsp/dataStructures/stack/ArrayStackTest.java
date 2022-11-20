package com.lzx.hsp.dataStructures.stack;

import java.util.Scanner;

/**
 * @author LZX
 * @code @create 2022-07-26 15:13
 */
public class ArrayStackTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the stack size");
        int size = scanner.nextInt();
        ArrayStack stack = new ArrayStack(size);

        char key;
        while (true){
            System.out.println();
            System.out.println("**********************************************");
            stack.list();
            System.out.println("a for push");
            System.out.println("g for pop");
            System.out.println("e for exit");
            System.out.println("**********************************************");
            System.out.println();

            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    if (stack.isFull()) {
                        System.out.println("stack is full");
                    } else {
                        System.out.println("enter the number");
                        int data = scanner.nextInt();
                        stack.push(data);
                    }
                    break;
                case 'g':
                    if (stack.isEmpty()){
                        System.out.println("stack is empty");
                    }else {
                        int data = stack.pop();
                        System.out.println("data = " + data);
                    }
                    break;
                case 'e':
                    return;
                default:
                    System.out.println("unknown operate");
            }
            scanner.close();
        }
    }
}
