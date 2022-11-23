package com.lzx.leetCode.chapter01_array;

/**
 * @author LZX
 * @code @create 2022-11-23 12:32
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 默认 n 一定合法
 */
public class Code_19_RemoveNthNodeFromEndOfList {
    private static class ListNode {
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

    // 双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return null;
        }
        ListNode listNode = new ListNode();
        listNode.next = head;
        ListNode cur = listNode;
        for (int i = 0; i < n-1; i++) {
            head = head.next;
        }
        while (head.next != null){
            head = head.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return listNode.next;
    }
}
