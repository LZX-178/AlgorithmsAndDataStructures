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

    // 按先序序列化
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

    // 按先序反序列化, 为便于测试, 每个元素的值要诚实
    public Node buildByPreQueue(Queue<String> preList){
        Node head;
        String value = preList.poll();
        if (value == null) {
            return null;
        }else {
            head = new Node(Integer.parseInt(value)*10);
            buildByPreQueue(preList, head);
        }
        return head;
    }
    private void buildByPreQueue(Queue<String> preList, Node node) {
        String value = preList.poll();
        if (value != null) {
            node.left = new Node(Integer.parseInt(value)*10);
            buildByPreQueue(preList, node.left);
        }

        value = preList.poll();
        if (value != null) {
            node.right = new Node(Integer.parseInt(value)*10);
            buildByPreQueue(preList, node.right);
        }
    }

    // 按层序序列化
    public Queue<String> levelSerial(Node head){
        Queue<Node> queue = new LinkedList<>();
        Queue<String> ans = new LinkedList<>();

        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();

            if (node == null) {
                ans.add(null);
            }else {
                ans.add(String.valueOf(node.value));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return ans;
    }
    // 按层序反序列化, 为便于测试, 每个元素的值要诚实
    public Node buildByLevelQueue(Queue<String> preList){

        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.parseInt(value) * 10);

        // queue 记录当前层的元素
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);

        // 每次都遍历一层的元素
        while (!preList.isEmpty()) {
            // 遍历当前层的每一个元素, for循环其实可以省略
//            for (int i = 0, width = queue.size(); i < width; i++) {
                Node node = queue.poll();

                // 如果左节点非空, 进行赋值,
                // 并可能需要为 左节点 的左右节点赋值, 将其加入 queue,
                value = preList.poll();
                if (value != null) {
                    node.left = new Node(Integer.parseInt(value) * 10);
                    queue.add(node.left);
                }

                // 同上, 处理右节点
                value = preList.poll();
                if (value != null) {
                    node.right = new Node(Integer.parseInt(value) * 10);
                    queue.add(node.right);
                }
//            }
        }
        return head;
    }


    @Test
    public void test_preSerial1(){
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

        PrintBinaryTree printer = new PrintBinaryTree(head);
        printer.print(false);

        Queue<String> serial = preSerial(head);

        System.out.println("***** " + "serial" + " *****");
        System.out.println("serial = " + serial);

        System.out.println("***** " + "buildByPreQueue" + " *****");
        printer.root = buildByPreQueue(serial);
        printer.print(false);
    }

    @Test
    public void test_preSerial2() {
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

    @Test
    public void test_leverSerial1() {
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

        PrintBinaryTree printer = new PrintBinaryTree(head);
        printer.print(false);

        Queue<String> serial = levelSerial(head);
        System.out.println("serial = " + serial);

        Node newHead = buildByLevelQueue(serial);
        printer.root = newHead;
        printer.print(false);
    }

    @Test
    public void test_leverSerial2() {

        Node head = new Node(1);
        GenerateRandomBT randomBT = new GenerateRandomBT(head, 5, 20, 1, 100, 4);
        randomBT.generateRandomBT();

        PrintBinaryTree printer = new PrintBinaryTree(head);
        printer.print(false);

        Queue<String> serial = levelSerial(head);

        System.out.println("***** " + "serial" + " *****");
        System.out.println("serial = " + serial);

        System.out.println("***** " + "buildByLevelQueue" + " *****");
        Node newHead = buildByLevelQueue(serial);
        PrintBinaryTree printer2 = new PrintBinaryTree(newHead);
        printer2.print(false);

    }
}
