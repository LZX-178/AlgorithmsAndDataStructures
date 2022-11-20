package com.lzx.leetCode.sort.mergeSort;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-09 15:41:08
 * 剑指 Offer II 078. 合并排序链表
 * 给定一个链表数组，每个链表都已经按升序排列。
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 目前采用的是 自低向上, 消耗空间较多, 采用自顶向下可以减少空间消耗 ( 使用方法的返回值来代替声明一个变量 )
 */
public class Code_offer_078_mergeKLists {

    @Test
    public void test_mergeKLists() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode l7 = new ListNode(2);
        ListNode l8 = new ListNode(6);
        l7.next = l8;

        ListNode[] lists = {l1, l4, l7};
        ListNode temp = mergeKLists(lists);
        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }

        int count = lists.length;

        while (count > 1){
            int i, j;
            if (count % 2 == 0){
                for (i = 0, j = 0; i < count;) {
                    lists[j++] = merge(lists[i++], lists[i++]);
                }
                count >>= 1;
            }else {
                for (i = 0, j = 0; i < count-1;) {
                    lists[j++] = merge(lists[i++], lists[i++]);
                }
                lists[j] = lists[i];
                count >>= 1;
                count++;
            }
        }
        return lists[0];
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        ListNode tail;
        ListNode temp;
        if (list1.val > list2.val){
            temp = list1;
            list1 = list2;
            list2 = temp;
        }
        tail = list1;

        while (true){
            if (tail.next == null){
                tail.next = list2;
                break;
            }

            if (tail.next.val > list2.val) {
                temp = tail.next;
                tail.next = list2;
                list2 = temp;
            }
            tail = tail.next;
        }

        return list1;
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
