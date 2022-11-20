package com.lzx.leetCode.linkedList;

/**
 * @author LZX
 * @code @create 2022-09-13 14:49:07
 * 剑指 Offer II 027. 回文链表
 *
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 *
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 */
public class Code_offer2_027_isPalindrome {


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }else if (head.next == null){
            return true;
        }else if (head.next.next == null){
            return head.val == head.next.val;
        }else if (head.next.next.next == null){
            return head.val == head.next.next.val;
        }

        ListNode p1 = head;
        ListNode mid = head.next;
        ListNode p2 = mid.next;
        ListNode end = p2.next;

        p1.next = null;

        while (true){
            // 总链表的长度为偶数
            if (end.next == null){
                end = p2;
                break;
            }
            // 移动尾指针
            end = end.next;
            // 总链表的长度为奇数
            if (end.next == null){
                end = p2.next;
                break;
            }
            // 移动尾指针
            end = end.next;
            // 链表反转
            mid.next = p1;
            // 移动中指针
            p1 = mid;
            mid = p2;
            p2 = p2.next;
        }

        boolean flag = true;

        while (p1 != null){
            if (flag && (mid.val != end.val)){
                flag = false;
            }

            end = end.next;

            p2 = mid;
            mid = p1;
            p1 = p1.next;
            mid.next = p2;
        }

        if (flag && (mid.val != end.val)){
            flag = false;
        }

        return flag;
    }

}
