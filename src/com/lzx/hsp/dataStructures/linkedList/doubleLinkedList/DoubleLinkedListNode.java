package com.lzx.hsp.dataStructures.linkedList.doubleLinkedList;

/**
 * @author LZX
 * @code @create 2022-07-26 9:05
 */
public class DoubleLinkedListNode {
    //数据域
    public int no;
    public String name;
    public String nickName;
    //next域
    public DoubleLinkedListNode next;
    //pre域
    public DoubleLinkedListNode pre;

    public DoubleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
