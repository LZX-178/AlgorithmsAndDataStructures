package com.lzx.zcy.class10;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author LZX
 * @code @create 2022-09-13 16:44:22
 *
 * 有两个可能有环也可能无环的链表, 如果两个链表相交, 返回第一个相交的节点, 否则返回 null
 */
public class Code01_FindFirstIntersectNode {
    private static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    @Test
    public void test_getIntersectNode() {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }

    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        // 首先判断两个链表是否有环
        Node loop1 = getLoopNode2(head1);
        Node loop2 = getLoopNode2(head2);

        if (loop1 == null && loop2 == null){                    // 如果都无环
            return noLoop(head1, head2);
        }else if (loop1 != null && loop2 != null){              // 如果都有环
            return bothLoop(head1, loop1, head2, loop2);
        }else {                                                 // 一个有环一个无环, 不可能相交
            return null;
        }
    }

    // 找到链表第一个入环节点，如果无环，返回null
    // 方法一 : 使用 set
    public static Node getLoopNode1(Node head){
        HashSet<Node> set = new HashSet<>();
        Node temp = head;
        while (temp != null){
            if (set.contains(temp)) {
                return temp;
            }else {
                set.add(temp);
            }
            temp = temp.next;
        }
        return null;
    }
    // 方法二 : 使用 Floyd判环算法
    public static Node getLoopNode2(Node head){
        if (head == null){
            return null;
        }

        Node f = head;
        Node s = head;
        do {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            f = f.next.next;
            s = s.next;
        } while (s != f);

        f = head;
        while (f != s){
            s = s.next;
            f = f.next;
        }
        return s;
    }

    // 已知两个链表都无环且非空，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2){
        Node end1 = head1, end2 = head2;
        int size1 = 1, size2 = 1;
        while (end1.next != null){
            end1 = end1.next;
            size1++;
        }
        while (end2.next != null){
            end2 = end2.next;
            size2++;
        }
        if (end1 != end2){
            return null;
        }

        int delta;
        if (size2 > size1){
            end1 = head1;
            head1 = head2;
            head2 = end1;
            delta = size2 - size1;
        }else {
            delta = size1 - size2;
        }
        end1 = head1;
        end2 = head2;
        for (int i = 0; i < delta; i++) {
            end1 = end1.next;
        }
        while (end1 != end2){
            end1 = end1.next;
            end2 = end2.next;
        }

        return end1;
    }

    // 两个有环链表，返回第一个相交节点，如果不相交返回null
    // loop1 和 loop2 为入环点
    // 如果两个有环链表相交, 只有两种情况
    //      1 入环点相同, 求解过程与 noLoop() 类似
    //      2 两个不同的入环点, 返回任意一个即可
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        if (loop1 == loop2){
            Node cur1 = null;
            Node cur2 = null;
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        Node temp = loop1;
        do {
            if (temp == loop2){
                return loop2;
            }
            temp = temp.next;
        }while (temp != loop1);
        return null;
    }
}
