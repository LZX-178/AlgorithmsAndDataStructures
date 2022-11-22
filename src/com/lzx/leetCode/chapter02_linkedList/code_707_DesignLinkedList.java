package com.lzx.leetCode.chapter02_linkedList;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-22 17:06:46
 * 707. 设计链表
 *   设计链表的实现。
 *   您可以选择使用单链表或双链表。
 *      单链表中的节点应该具有两个属性：
 *          val和 next。
 *          val是当前节点的值，
 *          next是指向下一个节点的指针/引用。
 *      双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
 *   假设链表中的所有节点都是 0-index 的。
 * 
 * 在链表类中实现这些功能：
 *      get(index)：
 *          获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 *      addAtHead(val)：
 *          在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 *      addAtTail(val)：
 *          将值为 val 的节点追加到链表的最后一个元素。
 *      addAtIndex(index,val)：
 *          在链表中的第index个节点之前添加值为val 的节点。
 *          如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 *          如果 index 大于链表长度，则不会插入节点。
 *          如果 index 小于 0，则在头部插入节点。
 *      deleteAtIndex(index)：
 *          如果索引index 有效，则删除链表中的第 index 个节点。
 *
 */
public class code_707_DesignLinkedList {
    @Test
    public void test_MyLinkedList() {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        int i = list.get(1);
        list.deleteAtIndex(0);
        int i1 = list.get(0);
    }
    @Test
    public void test_MyLinkedList2() {
        MyLinkedList list = new MyLinkedList();
        list.addAtTail(1);
        list.addAtTail(2);
        int i1 = list.get(0);
        int i2 = list.get(1);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        list.deleteAtIndex(4);
        System.out.println("***** " + "delete" + " *****");
        i1 = list.get(0);
        i2 = list.get(1);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
    }

    private static class MyLinkedList {

        private static class Node{
            public int val;
            public Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        private Node head;
        private Node tail;
        private int length;

        public MyLinkedList() {

        }

        public int get(int index) {
            if (length == 0 || index < 0 || index > length-1){
                return -1;
            }
            return getNode(index).val;
        }

        // 确保 index 合法, head 非空
        private Node getNode(int index){
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public void addAtHead(int val) {
            Node temp = new Node(val);
            if (head == null){
                head = tail = temp;
            }else {
                temp.next = head;
                head = temp;
            }
            length++;
        }

        public void addAtTail(int val) {
            if (head == null){
                head = tail = new Node(val);
            }else {
                tail.next = new Node(val);
                tail = tail.next;
            }
            length++;
        }

        public void addAtIndex(int index, int val) {
            if (index <= 0){
                addAtHead(val);
            }else if (index < length){
                // 能进入这个分支, 说明
                //      length >= 2,
                //      1 <= index < length
                Node node = getNode(index - 1);
                Node temp = new Node(val);
                temp.next = node.next;
                node.next = temp;
                length++;
            }else if (index == length){
                addAtTail(val);
            }
        }

        public void deleteAtIndex(int index) {
            if (length == 0 || index < 0 || index > length-1){
                return;
            }
            if (index == 0){
                head = head.next;
            }else {
                Node temp = getNode(index - 1);
                temp.next = temp.next.next;
                if (index == length-1){
                    tail = temp;
                }
            }
            length--;

        }
    }
}
