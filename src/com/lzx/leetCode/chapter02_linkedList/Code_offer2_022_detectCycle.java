package com.lzx.leetCode.chapter02_linkedList;

import java.util.HashSet;

/**
 * @author LZX
 * @code @create 2022-09-13 16:51:46
 * 剑指 Offer II 022. 链表中环的入口节点
 * <p>
 * 给定一个链表，返回链表开始入环的第一个节点。
 * 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。
 * 如果链表无环，则返回 null
 */
public class Code_offer2_022_detectCycle {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 方法一 : 使用 set
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = head;
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
    public ListNode detectCycle2(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode f = head;
        ListNode s = head;
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
}
