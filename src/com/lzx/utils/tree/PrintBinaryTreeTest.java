package com.lzx.utils.tree;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-23 10:57:29
 */
public class PrintBinaryTreeTest {
    public static class Node implements BTNode {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int v) {
            value = v;
        }

        @Override
        public Node getLeft() {
            return left;
        }

        @Override
        public void setLeft(BTNode left) {
            this.left = (Node) left;
        }

        @Override
        public Node getRight() {
            return right;
        }

        @Override
        public void setRight(BTNode right) {
            this.right = (Node) right;
        }

        @Override
        public String getValue() {
            return value + "";
        }

        @Override
        public void setValue(Integer value) {
            this.value = value;
        }

    }

    @Test
    public void test_printTree1() {
        Node head = new Node(1);
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
        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
        printBinaryTree.print(true);
    }
    @Test
    public void test_printTree2() {
        Node head = new Node(10);
        head.left = new Node(20);
        head.right = new Node(30);
        head.left.left = new Node(40);
        head.left.right = new Node(50);
        head.right.left = new Node(60);
        head.right.right = new Node(70);

        //         10
        //       /    \
        //     20      30
        //    /  \    /   \
        //  40   50  60   70
        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
        printBinaryTree.print(true);
    }
    @Test
    public void test_printTree3() {
        Node head = new Node(10);
        head.right = new Node(30);
        head.right.left = new Node(60);
        head.right.right = new Node(70);

        //         10
        //            \
        //             30
        //            /   \
        //           60   70
        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
        printBinaryTree.print(true);
    }
    @Test
    public void test_printTree4() {
        Node root = new Node(1);

        Node headLeft = new Node(1);
        headLeft.left = new Node(2);
        headLeft.right = new Node(3);
        headLeft.left.left = new Node(4);
        headLeft.left.right = new Node(5);
        headLeft.right.left = new Node(6);
        headLeft.right.right = new Node(7);
        root.left = headLeft;

        Node headRight = new Node(1);
        headRight.left = new Node(2);
        headRight.right = new Node(3);
        headRight.left.left = new Node(4);
        headRight.left.right = new Node(5);
        headRight.right.left = new Node(6);
        headRight.right.right = new Node(7);
        root.right = headRight;

        //              1
        //        /            \
        //       1              1
        //      /  \           /  \
        //    2      3       2      3
        //   / \    / \     / \    / \
        //  4   5  6   7   4   5  6   7
        PrintBinaryTree printBinaryTree = new PrintBinaryTree(root);
        printBinaryTree.print(true);
    }
    @Test
    public void test_printTree5() {
        Node root = new Node(1);

        Node headLeft = new Node(110);
        headLeft.left = new Node(2222);
        headLeft.right = new Node(3);
        headLeft.left.left = new Node(4);
        headLeft.left.right = new Node(512);
        headLeft.right.left = new Node(6);
        headLeft.right.right = new Node(7);
        root.left = headLeft;

        Node headRight = new Node(15);
        headRight.left = new Node(2);
        headRight.right = new Node(345);
        headRight.left.left = new Node(445);
        headRight.left.right = new Node(5);
        headRight.right.left = new Node(65);
        headRight.right.right = new Node(788);
        root.right = headRight;

        //              1
        //        /            \
        //       1              1
        //      /  \           /  \
        //    2      3       2      3
        //   / \    / \     / \    / \
        //  4   5  6   7   4   5  6   7
        PrintBinaryTree printBinaryTree = new PrintBinaryTree(root);
        printBinaryTree.print(true);
    }
    @Test
    public void test_printTree6() {
        Node head = new Node(1);

        RandomBTUtils treeUtils = new RandomBTUtils(head, 5, 63, 1, 99, 6);
        treeUtils.generateRandomBT();

        PrintBinaryTree printBinaryTree = new PrintBinaryTree(head);
        printBinaryTree.print(true);
    }
}
