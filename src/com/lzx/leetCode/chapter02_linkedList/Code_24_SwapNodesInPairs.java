package com.lzx.leetCode.chapter02_linkedList;

import java.util.jar.JarEntry;

/**
 * @author LZX
 * @code @create 2022-11-23 12:04
 * 24. 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class Code_24_SwapNodesInPairs {
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

    // 方法1
    public ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }else if (head.next == null){
            return head;
        }
        ListNode pre = head, cur = head.next;
        head = cur;
        pre.next = cur.next;
        cur.next = pre;
        cur = pre.next;

        while (cur != null && cur.next != null){
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;

            pre = cur;
            cur = cur.next;
        }
        return head;
    }

    // 方法2 : 创建头结点可以省去对边界条件的判断
    public ListNode swapPairs2(ListNode head){
        ListNode headNode = new ListNode();
        headNode.next = head;
        ListNode pre = headNode;
        while (head != null && head.next != null){
            // 进行交换操作
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = head;
            // 更新两个指针的值
            pre = head;
            head = head.next;
        }
        return headNode.next;
    }
}
