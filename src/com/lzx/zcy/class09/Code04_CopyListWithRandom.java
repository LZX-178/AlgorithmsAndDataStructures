package com.lzx.zcy.class09;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-09-13 15:45:56
 *
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝
 *
 * 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class Code04_CopyListWithRandom {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 方法一 : 使用 map
    public static Node copyRandomList1(Node head){
        if (head == null){
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Node temp = head;

        while (temp != null){
            Node node = new Node(temp.val);
            map.put(temp, node);
            temp = temp.next;
        }

        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            Node key = entry.getKey();
            Node value = entry.getValue();

            value.next = map.get(key.next);
            value.random = map.get(key.random);
        }
        return map.get(head);
    }

    // 方法二 : 把克隆的节点插入到原节点的后面, 克隆 random 后再分离两条链表
    public static Node copyRandomList2(Node head){
        if (head == null){
            return null;
        }

        Node temp = head;
        while (temp != null){
            Node node = new Node(temp.val);
            node.next = temp.next;
            temp.next = node;
            temp = node.next;
        }

        temp = head;
        while (temp != null){
            temp.next.random = temp.random == null ? null : temp.random.next;
            temp = temp.next.next;
        }

        temp = head;
        Node result = head.next;
        Node resultTemp = result;

        while (resultTemp.next != null){
            temp.next = resultTemp.next;
            temp = temp.next;
            resultTemp.next = temp.next;
            resultTemp = resultTemp.next;
        }
        temp.next = null;

        return result;
    }


}
