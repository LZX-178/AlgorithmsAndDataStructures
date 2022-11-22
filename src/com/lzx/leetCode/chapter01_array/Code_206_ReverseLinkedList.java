package com.lzx.leetCode.chapter01_array;

/**
 * @author LZX
 * @code @create 2022-11-22 22:17
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Code_206_ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }else if (head.next == null){
            return head;
        }else if (head.next.next == null){
            ListNode temp = head.next;
            temp.next = head;
            head.next = null;
            return temp;
        }

        ListNode mid = head.next;
        ListNode end = mid.next;
        head.next = null;
        mid.next = head;

        while (end.next != null){
            head = mid;
            mid = end;
            end = end.next;
            mid.next = head;
        }
        end.next = mid;
        return end;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }else if (head.next == null){
            return head;
        }

        ListNode mid = head.next;
        ListNode end = mid.next;
        head.next = null;

        while (end != null){
            mid.next = head;
            head = mid;
            mid = end;
            end = end.next;
        }
        mid.next = head;
        return mid;
    }
}
