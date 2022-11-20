package com.lzx.zcy.class11;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-24 15:45:52
 * 给定二叉树的某个节点, 返回该节点在中序遍历中的后继节点
 * 每个节点有一个指向父亲的指针
 * 头结点的父亲指针为空
 */
public class Code06_SucceedNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    @Test
    public void test_getSucceedNode() {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSucceedNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSucceedNode(test));
    }

    // 递归序在第二次访问一个节点后, 打印该节点, 是为中序遍历
    // 若有右节点, 程序向右节点进行中序遍历,
    // 若无右节点, 向上回溯, 第一个输出的节点是访问第二次的节点
    public Node getSucceedNode(Node node) {
        if (node == null){
            return null;
        }
        if (node.right != null){
            node = node.right;
            while (node.left != null){
                node = node.left;
            }
            return node;
        }else {
            while (node.parent != null){
                if (node.parent.left == node){
                    return node.parent;
                }
                node = node.parent;
            }
            return null;
        }
    }

}
