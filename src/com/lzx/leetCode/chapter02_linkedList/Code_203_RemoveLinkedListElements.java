package com.lzx.leetCode.chapter02_linkedList;

/**
 * @author LZX
 * @code @create 2022-11-22 16:57:02
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，
 * 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class Code_203_RemoveLinkedListElements {
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

    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        ListNode temp = head;
        while (temp.next != null){
            if (temp.next.val == val){
                temp.next = temp.next.next;
            }else {
                temp = temp.next;
            }
        }

        if (head.val == val){
            head = head.next;
        }
        return head;
    }
}
