package com.lzx.leetCode.chapter02_linkedList;

import com.lzx.zcy.class14.graph.Graph;

import java.util.HashSet;

/**
 * @author LZX
 * @code @create 2022-11-23 13:54
 * 142. 环形链表 II
 *
 *      给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回 null。
 *
 *      如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 *      为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 *      如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 *      不允许修改 链表。
 */
public class Code_142_LinkedListCycle2 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

    }

    // 双指针, 弗洛伊德判环
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode fastP = head, slowP = head;

        do {
            if (fastP.next == null || fastP.next.next == null){
                return null;
            }
            fastP = fastP.next.next;
            slowP = slowP.next;
        }while (fastP != slowP);

        fastP = head;
        while (fastP != slowP){
            fastP = fastP.next;
            slowP = slowP.next;
        }
        return slowP;
    }

    // 集合
    public ListNode detectCycle2(ListNode head){
        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.contains(head)){
                set.add(head);
                head = head.next;
            }else {
                break;
            }
        }
        return head;
    }
}
