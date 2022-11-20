package com.lzx.hsp.dataStructures.linkedList.singleCircleLinkedList;

/**
 * @author LZX
 * @code @create 2022-07-26 9:54
 */
public class SingleCircleLinkedListNode {
    //数据域
    public int no;
    public String name;
    public String nickName;
    //next域
    public SingleCircleLinkedListNode next;

    public SingleCircleLinkedListNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SingleCircleLinkedListNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
