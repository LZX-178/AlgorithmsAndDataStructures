package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;

/**
 * @author LZX
 * @code @create 2022-08-12 16:46:58
 */
public class HuffmanTreeNodeLinkedList {
    public HuffmanTreeNode head;
    // 遍历
    public void list(){
        if (head == null) {
            System.out.println("list is empty");
            return;
        }
        HuffmanTreeNode temp = head;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
    // 按顺序插入
    public void add(HuffmanTreeNode node){
        // 链表为空时
        if (head == null){
            head = node;
            return;
        }
        // 插在头结点之前时
        if (node.weight <= head.weight){
            node.next = head;
            head = node;
            return;
        }

        HuffmanTreeNode temp = head;
        while (temp.next != null && temp.next.weight < node.weight){
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }
    // 取出链表头的元素
    public HuffmanTreeNode get(){
        if (head == null){
            System.out.println(" HuffmanTreeNodeLinkedList is empty");
            return null;
        }
        HuffmanTreeNode temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }
}
