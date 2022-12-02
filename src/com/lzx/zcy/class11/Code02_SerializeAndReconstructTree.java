package com.lzx.zcy.class11;

import com.lzx.utils.tree.BTNode;
import com.lzx.utils.tree.GenerateRandomBT;
import com.lzx.utils.tree.PrintBinaryTree;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LZX
 * @code @create 2022-12-02 14:47:19
 * 二叉树的序列化和反序列化
 *
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 *      因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 *
 */
public class Code02_SerializeAndReconstructTree {
    public static class Node implements BTNode {
        public int value;
        public Node left;
        public Node right;

        public Node() {
            this.value = 0;
        }

        public Node(int data) {
            this.value = data;
        }

        @Override
        public BTNode getLeft() {
            return left;
        }

        @Override
        public void setLeft(BTNode left) {
            this.left = (Node) left;
        }

        @Override
        public BTNode getRight() {
            return right;
        }

        @Override
        public void setRight(BTNode right) {
            this.right = (Node) right;
        }

        @Override
        public String getValue() {
            return value+"";
        }

        @Override
        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        preSerial(head, ans);
        return ans;
    }

    private void preSerial(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
            return;
        }
        ans.add(String.valueOf(head.value));
        preSerial(head.left, ans);
        preSerial(head.right, ans);
    }

    public Node buildByPreQueue(Queue<String> preList){
        Node head;
        String value = preList.poll();
        if (value == null) {
            return null;
        }else {
            head = new Node(Integer.parseInt(value));
            buildByPreQueue(preList, head);
        }
        return head;
    }

    private void buildByPreQueue(Queue<String> preList, Node node) {
        String value = preList.poll();
        if (value != null) {
            node.left = new Node(Integer.parseInt(value));
            buildByPreQueue(preList, node.left);
        }

        value = preList.poll();
        if (value != null) {
            node.right = new Node(Integer.parseInt(value));
            buildByPreQueue(preList, node.right);
        }
    }

    @Test
    public void test_Traversal1(){
        Node head;
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

        PrintBinaryTree printer1 = new PrintBinaryTree(head);
        printer1.print(false);

        Queue<String> serial = preSerial(head);

        System.out.println("***** " + "serial" + " *****");
        System.out.println("serial = " + serial);

        System.out.println("***** " + "buildByPreQueue" + " *****");
        Node newHead = buildByPreQueue(serial);
        PrintBinaryTree printer2 = new PrintBinaryTree(newHead);
        printer2.print(false);
    }

    @Test
    public void test_Traversal2() {
        Node head = new Node(1);
        GenerateRandomBT randomBT = new GenerateRandomBT(head, 5, 20, 1, 100, 4);
        randomBT.generateRandomBT();

        PrintBinaryTree printer1 = new PrintBinaryTree(head);
        printer1.print(false);

        Queue<String> serial = preSerial(head);

        System.out.println("***** " + "serial" + " *****");
        System.out.println("serial = " + serial);

        System.out.println("***** " + "buildByPreQueue" + " *****");
        Node newHead = buildByPreQueue(serial);
        PrintBinaryTree printer2 = new PrintBinaryTree(newHead);
        printer2.print(false);
    }
}
