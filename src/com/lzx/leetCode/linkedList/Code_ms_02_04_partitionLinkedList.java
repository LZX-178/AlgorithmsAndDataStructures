package com.lzx.leetCode.linkedList;

/**
 * @author LZX
 * @code @create 2022-09-13 15:29:01
 * 面试题 02.04. 分割链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你不需要保留每个分区中各节点的初始相对位置。
 *
 */
public class Code_ms_02_04_partitionLinkedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    // 维护两个链表即可
    // 改成带头结点的链表会更简洁
    public ListNode partition(ListNode head, int x) {
        if (head == null){
            return null;
        }

        ListNode sH = null; // small head
        ListNode sT = null; // small tail
        ListNode mH = null; // big head
        ListNode mT = null; // big tail

        while (head != null){
            if (head.val < x){
                if (sH == null){
                    sH = sT = head;
                }else {
                    sT.next = head;
                    sT = head;
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

        if (sH != null){
            sT.next = mH;
        }

        if (mT != null){
            mT.next = null;
        }
        return sH != null? sH : mH;

    }
}
