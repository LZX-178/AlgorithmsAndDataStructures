package com.lzx.zcy.class09;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-13 14:07:04
 * 判断一个链表是否是回文的
 */
public class Code02_IsPalindromeList {
    private static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 判断一个链表是否是回文的
    public static boolean isPalindrome(Node head){
        if (head == null){
            return true;
        }else if (head.next == null){
            return true;
        }else if (head.next.next == null){
            return head.value == head.next.value;
        }else if (head.next.next.next == null){
            return head.value == head.next.next.value;
        }

        Node p1 = head;
        Node mid = head.next;
        Node p2 = mid.next;
        Node end = p2.next;

        p1.next = null;

        while (true){
            // 总链表的长度为偶数
            if (end.next == null){
                end = p2;
                break;
            }
            // 移动尾指针
            end = end.next;
            // 总链表的长度为奇数
            if (end.next == null){
                end = p2.next;
                break;
            }
            // 移动尾指针
            end = end.next;
            // 链表反转
            mid.next = p1;
            // 移动中指针
            p1 = mid;
            mid = p2;
            p2 = p2.next;
        }

        boolean flag = true;

        while (p1 != null){
            if (flag && (mid.value != end.value)){
                flag = false;
            }

            end = end.next;

            p2 = mid;
            mid = p1;
            p1 = p1.next;
            mid.next = p2;
        }

        if (flag && (mid.value != end.value)){
            flag = false;
        }

        return flag;
    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Test
    public void test_isPalindrome() {
        Node head = null;
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
