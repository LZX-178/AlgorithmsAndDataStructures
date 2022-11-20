package com.lzx.zcy.class09;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-13 15:01:07
 * 将链表划分为 小于 等于 大于 三段
 */
public class Code03_SmallerEqualBigger {
    private static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    @Test
    public void test_listPartition() {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition(head1, 5);
        printLinkedList(head1);
    }

    public static Node listPartition(Node head, int pivot){
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail

        while (head != null){
            if (head.value < pivot){
                if (sH == null){
                    sH = sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mH == null){
                    mH = mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }

            head = head.next;
        }

        if (sT != null){
            sT.next = eH;
            eT = eT != null ? eT : sT;
        }
        if (eT != null){
            eT.next = mH;
        }
        if (mT != null){
            mT.next = null;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
}
