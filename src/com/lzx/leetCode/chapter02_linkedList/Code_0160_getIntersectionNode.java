package com.lzx.leetCode.chapter02_linkedList;

/**
 * @author LZX
 * @code @create 2022-09-13 17:59:07
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
 * 题目数据 保证 整个链式结构中不存在环
 */
public class Code_0160_getIntersectionNode {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode end1 = headA, end2 = headB;
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
            end1 = headA;
            headA = headB;
            headB = end1;
            delta = size2 - size1;
        }else {
            delta = size1 - size2;
        }
        end1 = headA;
        end2 = headB;
        for (int i = 0; i < delta; i++) {
            end1 = end1.next;
        }
        while (end1 != end2){
            end1 = end1.next;
            end2 = end2.next;
        }

        return end1;

    }
}
