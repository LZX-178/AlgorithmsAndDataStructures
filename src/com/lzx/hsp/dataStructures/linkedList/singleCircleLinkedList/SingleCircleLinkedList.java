package com.lzx.hsp.dataStructures.linkedList.singleCircleLinkedList;


/**
 * @author LZX
 * @code @create 2022-07-26 9:53
 * 单向环行链表 不带头节点 懒汉式
 */
public class SingleCircleLinkedList {
    private SingleCircleLinkedListNode first;

    public boolean isEmpty(){
        return first == null;
    }

    public void add(SingleCircleLinkedListNode node){
        if (isEmpty()){
            first = node;
            node.next = node;
        }else {
            SingleCircleLinkedListNode temp = first;
            while (temp.next != first){
                temp = temp.next;
            }
            temp.next = node;
            node.next = first;
        }
    }

    public void list(){
        System.out.println("*****************************************");
        if (isEmpty()){
            System.out.println("list is empty");
            return;
        }
        SingleCircleLinkedListNode temp = first;
        while (true){
            System.out.println(temp);
            temp = temp.next;
            if (temp == first){
                return;
            }
        }
    }

    //Josephu 问题为： 设编号为 1， 2， … n 的 n 个人围坐一圈， 约定编号为 k（1<=k<=n） 的人从 1 开始报数， 数
    //到 m 的那个人出列， 它的下一位又从 1 开始报数， 数到 m 的那个人又出列， 依次类推， 直到所有人出列为止， 由
    //此产生一个出队编号的序列。
    public void josepfu(int k, int m){
        System.out.println("****************josepfu*****************");
        if (isEmpty()){
            System.out.println("list is empty");
            return;
        }
        //用 temp.next 找到编号为 k 的节点
        SingleCircleLinkedListNode temp = first;
        while (true){
            if (temp.next.no == k){
                break;
            }
            temp = temp.next;
            if (temp == first){
                System.out.println("k is not exist");
                break;
            }
        }

        int i = 1;
        first = temp;
        temp = null;
        //有一个以上节点时
        if (first.next != first){
            while (true){
                //数到 m 时
                if (i == m){
                    System.out.println(first.next);
                    //删除m节点
                    first.next = first.next.next;
                    //更新或退出
                    if (first.next == first){
                        break;
                    }else {
                        i = 1;
                        continue;
                    }
                }
                i++;
                first = first.next;
            }
        }
        System.out.println(first);
        first = null;
    }
}
