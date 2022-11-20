package com.lzx.zcy.class10;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-20 9:19:22
 * 递归遍历二叉树
 */
public class Code02_RecursiveTraversalBT {
    private static class Node{
        public int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + value + '}';
        }
    }

    public Node head;

    @Before
    public void init(){
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        //       1
        //      /  \
        //    2      3
        //   / \    / \
        //  4   5  6   7
    }

    @Test
    public void test_Traversal() {
        System.out.println("***** " + "recursiveTraversal" + " *****");
        recursiveTraversal(head);
        System.out.println("***** " + "preorderTraversal" + " *****");
        preorderTraversal(head);
        System.out.println("***** " + "inorderTraversal" + " *****");
        inorderTraversal(head);
        System.out.println("***** " + "postorderTraversal" + " *****");
        postorderTraversal(head);
    }

    //递归序遍历二叉树
    public void recursiveTraversal(Node node){
        if (node == null) {
            return;
        }
        System.out.println("node = " + node + ", count = 1");
        recursiveTraversal(node.left);
        System.out.println("node = " + node + ", count = 2");
        recursiveTraversal(node.right);
        System.out.println("node = " + node + ", count = 3");
    }
    //前序遍历二叉树
    public void preorderTraversal(Node node){
        if (node == null) {
            return;
        }
        System.out.println("node = " + node + ", count = 1");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    //中序遍历二叉树
    public void inorderTraversal(Node node){
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println("node = " + node + ", count = 2");
        inorderTraversal(node.right);
    }
    //后序遍历二叉树
    public void postorderTraversal(Node node){
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println("node = " + node + ", count = 3");
    }
}
