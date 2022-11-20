package com.lzx.zcy.class09;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author LZX
 * @code @create 2022-09-13 8:51:01
 * 链表中点问题
 */
public class Code01_LinkedListMid {

    @Test
    public void test_LinkedListMid() {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        System.out.println("midOrUpMidNode");
        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        System.out.println("midOrDownMidNode");
        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        System.out.println("midOrUpMidPreNode");
        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        System.out.println("midOrDownMidPreNode");
        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
    }

    private static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 输入头结点, 奇数长度返回中点, 偶数长度返回上中点
    public static Node midOrUpMidNode(Node head){
        if (head == null || head.next == null){
            return head;
        }

        Node mid = head;
        Node end = head.next;

        while (end.next != null){
            end = end.next;
            mid = mid.next;
            if (end.next == null){
                break;
            }
            end = end.next;
        }

        return mid;
    }
    // 输入头结点, 奇数长度返回中点, 偶数长度返回下中点
    public static Node midOrDownMidNode(Node head){
        if (head == null){
            return null;
        }

        Node mid = head;
        Node end = head;

        while (end.next != null){
            end = end.next;
            mid = mid.next;
            if (end.next == null){
                break;
            }
            end = end.next;
        }

        return mid;
    }
    // 输入头结点, 奇数长度返回中点前一个, 偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }

        Node mid = head;
        Node end = head.next.next;

        while (end.next != null){
            end = end.next;
            if (end.next == null){
                break;
            }
            mid = mid.next;
            end = end.next;
        }

        return mid;

    }
    // 输入头结点, 奇数长度返回中点前一个, 偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }

        Node mid = head;
        Node end = head.next.next;

        while (end.next != null){
            end = end.next;
            mid = mid.next;
            if (end.next == null){
                break;
            }
            end = end.next;
        }

        return mid;
    }

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }
}
