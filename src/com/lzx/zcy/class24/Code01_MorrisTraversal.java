package com.lzx.zcy.class24;

import com.lzx.utils.tree.BTNode;
import com.lzx.utils.tree.GenerateRandomBT;
import com.lzx.utils.tree.PrintBinaryTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author LZX
 * @code @create 2023-01-03 17:15:58
 *
 * Morris 遍历
 *      时间复杂度 O(n), 空间复杂度 O(1)
 *          递归序遍历是    : 中 左 中 右 中 (每个节点访问 3 次)
 *          Morris序遍历是 : 中 左 中 右 (每个节点访问 2 次)
 */
public class Code01_MorrisTraversal {

    public static class Node implements BTNode {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
        public Node() {
        }

        @Override
        public BTNode getLeft() {
            return left;
        }

        @Override
        public BTNode getRight() {
            return right;
        }

        @Override
        public String getValue() {
            return value + "";
        }

        @Override
        public void setLeft(BTNode left) {
            this.left = (Node) left;
        }

        @Override
        public void setRight(BTNode right) {
            this.right = (Node) right;
        }

        @Override
        public void setValue(Integer value) {
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
    public void test_morrisRecursive() {
        System.out.println("***** " + "morrisRecursive" + " *****");
        ArrayList<String> morrisRecursive = new ArrayList<>();
        morrisRecursive(head, morrisRecursive);
        morrisRecursive.forEach(System.out::println);

        System.out.println("***** " + "morrisUnRecursive" + " *****");
        ArrayList<String> morrisUnRecursive = new ArrayList<>();
        morrisUnRecursive(head, morrisUnRecursive);
        morrisUnRecursive.forEach(System.out::println);

        System.out.println("***** " + "preOrderMorrisUnRecursive" + " *****");
        ArrayList<String> preOrderMorrisUnRecursive = new ArrayList<>();
        preOrderMorrisUnRecursive(head, preOrderMorrisUnRecursive);
        preOrderMorrisUnRecursive.forEach(System.out::println);

        System.out.println("***** " + "inOrderMorrisUnRecursive" + " *****");
        ArrayList<String> inOrderMorrisUnRecursive = new ArrayList<>();
        inOrderMorrisUnRecursive(head, inOrderMorrisUnRecursive);
        inOrderMorrisUnRecursive.forEach(System.out::println);

        System.out.println("***** " + "postOrderMorrisUnRecursive" + " *****");
        ArrayList<String> postOrderMorrisUnRecursive = new ArrayList<>();
        postOrderMorrisUnRecursive(head, postOrderMorrisUnRecursive);
        postOrderMorrisUnRecursive.forEach(System.out::println);
    }

    @Test
    public void test_morrisRecursive2() {
        for (int j = 0; j < 500000; j++) {
            // 生成随机二叉树
            Node head = new Node(0);
            GenerateRandomBT randomBT = new GenerateRandomBT(head, 2, 64, 1, 100, 6);
            randomBT.generateRandomBT();

            // 声明 5 种遍历的结果数组
            ArrayList<String> morrisRecursive0 = new ArrayList<>();
            ArrayList<String> morrisUnRecursive0 = new ArrayList<>();
            ArrayList<String> preOrderMorrisUnRecursive0 = new ArrayList<>();
            ArrayList<String> inOrderMorrisUnRecursive0 = new ArrayList<>();
            ArrayList<String> postOrderMorrisUnRecursive0 = new ArrayList<>();
            // 声明 5 种遍历的结果数组
            ArrayList<String> morrisRecursive1 = new ArrayList<>();
            ArrayList<String> morrisUnRecursive1 = new ArrayList<>();
            ArrayList<String> preOrderMorrisUnRecursive1 = new ArrayList<>();
            ArrayList<String> inOrderMorrisUnRecursive1 = new ArrayList<>();
            ArrayList<String> postOrderMorrisUnRecursive1 = new ArrayList<>();

            // 进行遍历
            morrisRecursive(head, morrisRecursive1);
            morrisUnRecursive(head, morrisUnRecursive1);
            preOrderMorrisUnRecursive(head, preOrderMorrisUnRecursive1);
            inOrderMorrisUnRecursive(head, inOrderMorrisUnRecursive1);
            postOrderMorrisUnRecursive(head, postOrderMorrisUnRecursive1);

            recursiveTraversal(head,
                    morrisRecursive0,
                    morrisUnRecursive0,
                    preOrderMorrisUnRecursive0,
                    inOrderMorrisUnRecursive0,
                    postOrderMorrisUnRecursive0);


            // 验证 morrisRecursive
            if (morrisRecursive0.size() != morrisRecursive1.size()){
                System.out.println("***** " + "morrisRecursive0" + " *****");
                morrisRecursive0.forEach(System.out::println);
                System.out.println("***** " + "morrisRecursive1" + " *****");
                morrisRecursive1.forEach(System.out::println);
                PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                printBinaryTree.print();
                throw new RuntimeException("error");
            }else {
                for (int i = 0; i < morrisRecursive0.size(); i++) {
                    if (!morrisRecursive0.get(i).equals(morrisRecursive1.get(i))){
                        System.out.println("***** " + "morrisRecursive0" + " *****");
                        morrisRecursive0.forEach(System.out::println);
                        System.out.println("***** " + "morrisRecursive1" + " *****");
                        morrisRecursive1.forEach(System.out::println);
                        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                        printBinaryTree.print();
                        throw new RuntimeException("error");
                    }
                }
            }

            // 验证 morrisUnRecursive
            if (morrisUnRecursive0.size() != morrisUnRecursive1.size()){
                System.out.println("***** " + "morrisUnRecursive0" + " *****");
                morrisUnRecursive0.forEach(System.out::println);
                System.out.println("***** " + "morrisUnRecursive1" + " *****");
                morrisUnRecursive1.forEach(System.out::println);
                PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                printBinaryTree.print();
                throw new RuntimeException("error");
            }else {
                for (int i = 0; i < morrisUnRecursive0.size(); i++) {
                    if (!morrisUnRecursive0.get(i).equals(morrisUnRecursive1.get(i))){
                        System.out.println("***** " + "morrisUnRecursive0" + " *****");
                        morrisUnRecursive0.forEach(System.out::println);
                        System.out.println("***** " + "morrisUnRecursive1" + " *****");
                        morrisUnRecursive1.forEach(System.out::println);
                        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                        printBinaryTree.print();
                        throw new RuntimeException("error");
                    }
                }
            }

            // 验证 preOrderMorrisUnRecursive
            if (preOrderMorrisUnRecursive0.size() != preOrderMorrisUnRecursive1.size()){
                System.out.println("***** " + "preOrderMorrisUnRecursive0" + " *****");
                preOrderMorrisUnRecursive0.forEach(System.out::println);
                System.out.println("***** " + "preOrderMorrisUnRecursive1" + " *****");
                preOrderMorrisUnRecursive1.forEach(System.out::println);
                PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                printBinaryTree.print();
                throw new RuntimeException("error");
            }else {
                for (int i = 0; i < preOrderMorrisUnRecursive0.size(); i++) {
                    if (!preOrderMorrisUnRecursive0.get(i).equals(preOrderMorrisUnRecursive1.get(i))){
                        System.out.println("***** " + "preOrderMorrisUnRecursive0" + " *****");
                        preOrderMorrisUnRecursive0.forEach(System.out::println);
                        System.out.println("***** " + "preOrderMorrisUnRecursive1" + " *****");
                        preOrderMorrisUnRecursive1.forEach(System.out::println);
                        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                        printBinaryTree.print();
                        throw new RuntimeException("error");
                    }
                }
            }

            // 验证 inOrderMorrisUnRecursive
            if (inOrderMorrisUnRecursive0.size() != inOrderMorrisUnRecursive1.size()){
                System.out.println("***** " + "inOrderMorrisUnRecursive0" + " *****");
                inOrderMorrisUnRecursive0.forEach(System.out::println);
                System.out.println("***** " + "inOrderMorrisUnRecursive1" + " *****");
                inOrderMorrisUnRecursive1.forEach(System.out::println);
                PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                printBinaryTree.print();
                throw new RuntimeException("error");
            }else {
                for (int i = 0; i < inOrderMorrisUnRecursive0.size(); i++) {
                    if (!inOrderMorrisUnRecursive0.get(i).equals(inOrderMorrisUnRecursive1.get(i))){
                        System.out.println("***** " + "inOrderMorrisUnRecursive0" + " *****");
                        inOrderMorrisUnRecursive0.forEach(System.out::println);
                        System.out.println("***** " + "inOrderMorrisUnRecursive1" + " *****");
                        inOrderMorrisUnRecursive1.forEach(System.out::println);
                        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                        printBinaryTree.print();
                        throw new RuntimeException("error");
                    }
                }
            }

            // 验证 postOrderMorrisUnRecursive
            if (postOrderMorrisUnRecursive0.size() != postOrderMorrisUnRecursive1.size()){
                System.out.println("***** " + "postOrderMorrisUnRecursive0" + " *****");
                postOrderMorrisUnRecursive0.forEach(System.out::println);
                System.out.println("***** " + "postOrderMorrisUnRecursive1" + " *****");
                postOrderMorrisUnRecursive1.forEach(System.out::println);
                PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                printBinaryTree.print();
                throw new RuntimeException("error");
            }else {
                for (int i = 0; i < postOrderMorrisUnRecursive0.size(); i++) {
                    if (!postOrderMorrisUnRecursive0.get(i).equals(postOrderMorrisUnRecursive1.get(i))){
                        System.out.println("***** " + "postOrderMorrisUnRecursive0" + " *****");
                        postOrderMorrisUnRecursive0.forEach(System.out::println);
                        System.out.println("***** " + "postOrderMorrisUnRecursive1" + " *****");
                        postOrderMorrisUnRecursive1.forEach(System.out::println);
                        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
                        printBinaryTree.print();
                        throw new RuntimeException("error");
                    }
                }
            }
        }
        System.out.println("OK");
    }

    // 对数器
    // 递归序遍历二叉树
    public void recursiveTraversal(Node node,
                                   ArrayList<String> morrisRecursive,
                                   ArrayList<String> morrisUnRecursive,
                                   ArrayList<String> preOrderMorrisUnRecursive,
                                   ArrayList<String> inOrderMorrisUnRecursive,
                                   ArrayList<String> postOrderMorrisUnRecursive){
        if (node == null) {
            return;
        }
        // 第一次访问
        morrisRecursive.add("node = " + node + ", count = 1");
        morrisUnRecursive.add("node = " + node + ", count = 1");
        preOrderMorrisUnRecursive.add("node = " + node + ", count = 1");

        recursiveTraversal(node.left,
                morrisRecursive,
                morrisUnRecursive,
                preOrderMorrisUnRecursive,
                inOrderMorrisUnRecursive,
                postOrderMorrisUnRecursive);

        // 第二次访问
        morrisRecursive.add("node = " + node + ", count = 2");
        morrisUnRecursive.add("node = " + node + ", count = 2");
        inOrderMorrisUnRecursive.add("node = " + node + ", count = 2");

        recursiveTraversal(node.right,
                morrisRecursive,
                morrisUnRecursive,
                preOrderMorrisUnRecursive,
                inOrderMorrisUnRecursive,
                postOrderMorrisUnRecursive);

        // 第三次访问
        postOrderMorrisUnRecursive.add("node = " + node + ", count = 3");
    }

    // 1 递归 morris序打印
    public void morrisRecursive(Node head, ArrayList<String> list) {
        if (head == null){
            return;
        }
        list.add("node = " + head + ", count = " + 1);
        morrisRecursive(head.left, list);
        list.add("node = " + head + ", count = " + 2);
        morrisRecursive(head.right, list);
    }

    // 2 非递归 morris序打印
    public void morrisUnRecursive(Node head, ArrayList<String> list) {
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight;

        // 首先 对于 一颗树来说, 第二次访问 头结点右边界的末尾(右孩子一定为 null) 时, 遍历结束
        // 如果是第一次访问 cur
        //      需要去访问 左子树
        //      找到 左子树右边界的末尾(记为 mostRight), 将其 right 置为 cur, 表示 cur 已经访问过一次
        // 如果是第二次访问 cur
        //      上一个访问的节点是 左子树的 mostRight, 通过 mostRight 的 right 访问cur
        //      此时应该访问 cur 的右子树
        while (cur != null){
            mostRight = cur.left;
            if (mostRight == null){
                // 如果左子树为空, 连续访问两次 cur
                list.add("node = " + cur + ", count = " + 1);
                list.add("node = " + cur + ", count = " + 2);
                // 接着访问 右子树
                cur = cur.right;
            }else {
                // 如果左子树非空
                // 找到 mostRight
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    //如果是第一次访问 cur
                    list.add("node = " + cur + ", count = " + 1);
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    //如果是第二次访问 cur
                    list.add("node = " + cur + ", count = " + 2);
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    // 3 非递归 Morris 遍历 改前序遍历
    public void preOrderMorrisUnRecursive(Node head, ArrayList<String> list) {
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight == null){
                // 如果左子树为空, 第一次访问 cur
                list.add("node = " + cur + ", count = " + 1);
                // 接着访问 右子树
                cur = cur.right;
            }else {
                // 如果左子树非空
                // 找到 mostRight
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    //如果是第一次访问 cur
                    list.add("node = " + cur + ", count = " + 1);
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    //如果是第二次访问 cur
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    // 4 非递归 Morris 遍历 改中序遍历
    public void inOrderMorrisUnRecursive(Node head, ArrayList<String> list) {
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight == null){
                // 如果左子树为空, 连续访问两次 cur
                list.add("node = " + cur + ", count = " + 2);
                // 接着访问 右子树
                cur = cur.right;
            }else {
                // 如果左子树非空
                // 找到 mostRight
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    //如果是第一次访问 cur
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    //如果是第二次访问 cur
                    list.add("node = " + cur + ", count = " + 2);
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    // 5 非递归 Morris 遍历 改后序遍历
    public void postOrderMorrisUnRecursive(Node head, ArrayList<String> list) {
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight == null){
                // 如果左子树为空

                // 接着访问 右子树
                cur = cur.right;
            }else {
                // 如果左子树非空

                // 找到 mostRight
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    // 如果是第一次访问 cur, 访问其左子树
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    // 如果是第二次访问 cur, 说明左子树访问完成, 逆序打印左子树的右边界
                    // 要先把 mostRight.right 置空, 否则不是正确的右边界
                    mostRight.right = null;
                    invertedOrder(cur.left, list);
                    cur = cur.right;
                }
            }
        }
        // 因为 head 没有左父亲,
        //      上述循环在访问完 head 右边界的 mostRight 就结束了
        //      而不会跳到 head 的左父亲
        // 需要额外将 head右边界逆序打印出来
        invertedOrder(head, list);
    }
    // 逆序 打印链表, (把 right 看成 next)
    private void invertedOrder(Node node, ArrayList<String> list) {
        if (node.right == null){
            list.add("node = " + node + ", count = " + 3);
            return;
        }

        Node m = node.right;
        Node r = m.right;
        node.right = null;

        while (r != null){
            m.right = node;
            node = m;
            m = r;
            r = r.right;
        }
        while (node != null){
            list.add("node = " + m + ", count = " + 3);
            m.right = r;
            r = m;
            m = node;
            node = node.right;
        }
        list.add("node = " + m + ", count = " + 3);
        m.right = r;
    }
}
