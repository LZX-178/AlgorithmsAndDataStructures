package com.lzx.hsp.dataStructures.linkedList.doubleLinkedList;

/**
 * @author LZX
 * @code @create 2022-07-26 9:04
 * 双向链表 带头节点
 */
public class DoubleLinkedList {

    private final DoubleLinkedListNode head = new DoubleLinkedListNode(0 , "", "");

    //打印链表
    public void list(){
        System.out.println("*************************************");
        if (head.next == null){
            System.out.println("linkedList is empty");
        }else {
            //确定一定会打印一次, 使用do while
            DoubleLinkedListNode temp = head.next;
            while (true){
                System.out.println(temp);
                if (temp.next == null){
                    break;
                }
                temp = temp.next;
            }
            while (true){
                System.out.println(temp);
                if (temp.pre == head){
                    break;
                }
                temp = temp.pre;
            }
        }
    }

    //按顺序添加节点
    public void addOrderByNo(DoubleLinkedListNode node){
        // 把node插入 temp 和 temp.next 之间
        DoubleLinkedListNode temp = head;
        // 找到正确的 next 位置
        while (true){
            // temp.next 的四种状况决定操作
            if (temp.next == null){
                break;
            }
            if (temp.next.no == node.no){
                System.out.println("节点已经存在");
                return;
            }
            if (node.no < temp.next.no){
                break;
            }
            temp = temp.next;
        }

        if (temp.next != null) {
            node.next = temp.next;
            node.pre = temp;
            temp.next = node;
            node.next.pre = node;
        }else {
            temp.next = node;
            node.pre = temp;
        }
    }
    //根据新节点的编号进行修改
    public void update(DoubleLinkedListNode newNode){
        if (head.next == null){
            System.out.println("linkedList is empty");
            return;
        }
        //temp 为要找的节点, 若没找到则为空
        DoubleLinkedListNode temp = head.next;
        while (temp != null && temp.no != newNode.no){
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("no such node");
            return;
        }
        temp.name = newNode.name;
        temp.nickName = newNode.nickName;
    }
    //根据 no 删除节点
    public void delete(int nodeNo){
        if (head.next == null){
            System.out.println("linkedList is empty");
            return;
        }
        //temp 为要找的节点, 若没找到则为空
        DoubleLinkedListNode temp = head.next;
        while (temp != null && temp.no != nodeNo){
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("no such node");
            return;
        }
        // 找到了要删除的节点, 分是否为最后一个节点两种情况
        if (temp.next == null) {
            temp.pre.next = null;
        }else {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        }
    }


}
