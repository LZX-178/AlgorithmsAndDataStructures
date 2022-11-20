package com.lzx.leetCode.sort.mergeSort;

import java.util.LinkedList;

/**
 * @author LZX
 * @code @create 2022-09-08 18:15:25
 * 剑指 Offer II 077. 链表排序
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class Code_offer_077_sortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        LinkedList<ListNode> queue = new LinkedList<>();
        ListNode temp1;
        ListNode temp2;
        while (head != null){
            temp1 = head;
            if (temp1.next == null){
                queue.addLast(temp1);
                break;
            }
            temp2 = temp1.next;
            head = temp2.next;
            if (temp1.val <= temp2.val){
                temp2.next = null;
                queue.addLast(temp1);
            }else {
                temp2.next = temp1;
                temp1.next = null;
                queue.addLast(temp2);
            }
        }

        while (queue.size() > 1){
            temp1 = queue.removeFirst();
            temp2 = queue.removeFirst();
            queue.addLast(merge(temp1, temp2));
        }
        return queue.removeFirst();
    }

    // 合并两个有序链表
    // head 为新链表头, 初始值为 l1头 和 l2头 的较小值
    // tail 为新链表尾
    // temp 为另一段链表
    // 循环 : 比较 tail.next 和 temp 的大小
    //          tail.next 更小, 则迭代 tail
    //          temp 更小, 则 temp 成为新的 tail, temp 再指向另一段链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode head;
        ListNode tail;
        ListNode temp;
        if (l1.val <= l2.val){
            head = tail = l1;
            temp = l2;
        }else {
            head = tail = l2;
            temp = l1;
        }

        while (true){
            if (tail.next == null){
                tail.next = temp;
                break;
            }
            if (tail.next.val > temp.val) {
                l1 = temp;
                temp = tail.next;
                tail.next = l1;
            }
            tail = tail.next;
        }

        return head;
    }

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
}
