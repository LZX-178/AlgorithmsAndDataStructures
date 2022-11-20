package com.lzx.hsp.dataStructures.linkedList.singleLinkedList;

/**
 * @author LZX
 * @code @create 2022-07-25 14:55
 */
public class SingleLinkedListNode {
    //数据域
    public int no;
    public String name;
    public String nickName;
    //next域
    public SingleLinkedListNode next;

    public SingleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SingleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
