package com.lzx.leetCode.chapter02_linkedList;

import java.util.TooManyListenersException;

/**
 * @author LZX
 * @code @create 2022-11-23 13:31
 * 面试题 02.07. 链表相交
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */
public class Code_ms_2_7_getIntersectionNode {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode tempA = headA, tempB = headB;
        int lengthA = 1, lengthB = 1;

        while (tempA.next != null){
            tempA = tempA.next;
            lengthA++;
        }
        while (tempB.next != null){
            tempB = tempB.next;
            lengthB++;
        }
        if (tempA != tempB){
            return null;
        }

        int n = lengthA - lengthB;
        if (n < 0){
            n = -n;
            tempA = headA;
            headA = headB;
            headB = tempA;
        }

        tempA = headA;
        tempB = headB;
        for (int i = 0; i < n; i++) {
            tempA = tempA.next;
        }

        while (tempA != tempB){
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA;
    }
}
