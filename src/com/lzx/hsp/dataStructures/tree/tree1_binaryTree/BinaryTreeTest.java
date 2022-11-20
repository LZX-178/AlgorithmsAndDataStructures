package com.lzx.hsp.dataStructures.tree.tree1_binaryTree;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-11 8:04:29
 */
public class BinaryTreeTest {

    private BinaryTree binaryTree;

    @Before
    public void init(){
        binaryTree = new BinaryTree();
        BinaryTreeNode node1 = new BinaryTreeNode(1, "宋江");
        BinaryTreeNode node2 = new BinaryTreeNode(2, "吴用");
        BinaryTreeNode node3 = new BinaryTreeNode(3, "卢俊义");
        BinaryTreeNode node4 = new BinaryTreeNode(4, "林冲");
        BinaryTreeNode node5 = new BinaryTreeNode(5, "关胜");

        //          1
        //         / \
        //        2   3
        //           / \
        //          5   4
        binaryTree.setRoot(node1);
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
    }

    @Test
    public void test_delete() {
        System.out.println("*************delete 1*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete1 = binaryTree.delete(1);
        System.out.println("isDelete1 = " + isDelete1);
        binaryTree.preOrderTraversal();

        init();
        System.out.println("*************delete 2*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete2 = binaryTree.delete(2);
        System.out.println("isDelete2 = " + isDelete2);
        binaryTree.preOrderTraversal();

        init();
        System.out.println("*************delete 3*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete3 = binaryTree.delete(3);
        System.out.println("isDelete3 = " + isDelete3);
        binaryTree.preOrderTraversal();

        init();
        System.out.println("*************delete 4*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete4 = binaryTree.delete(4);
        System.out.println("isDelete4 = " + isDelete4);
        binaryTree.preOrderTraversal();

        init();
        System.out.println("*************delete 5*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete5 = binaryTree.delete(5);
        System.out.println("isDelete5 = " + isDelete5);
        binaryTree.preOrderTraversal();

        init();
        System.out.println("*************delete 6*********************");
        System.out.println("--------------------before---------------------");
        binaryTree.preOrderTraversal();
        System.out.println("--------------------after----------------------");
        boolean isDelete6 = binaryTree.delete(6);
        System.out.println("isDelete6 = " + isDelete6);
        binaryTree.preOrderTraversal();
    }

    @Test
    public void test_traversal() {
        System.out.println("***************preOrderTraversal***************");
        binaryTree.preOrderTraversal(); // 1 2 3 5 4
        System.out.println("\n***************inorderTraversal***************");
        binaryTree.inorderTraversal(); // 2 1 5 3 4
        System.out.println("\n***************postorderTraversal***************");
        binaryTree.postorderTraversal(); // 2 5 4 3 1
    }

    @Test
    public void test_search() {
        BinaryTreeNode node1 = binaryTree.preOrderSearch(5);
        System.out.println("node1 = " + node1);
        BinaryTreeNode node2 = binaryTree.inorderSearch(5);
        System.out.println("node2 = " + node2);
        BinaryTreeNode node3 = binaryTree.postorderSearch(5);
        System.out.println("node3 = " + node3);
        BinaryTreeNode node4 = binaryTree.preOrderSearch(6);
        System.out.println("node4 = " + node4);
        BinaryTreeNode node5 = binaryTree.inorderSearch(6);
        System.out.println("node5 = " + node5);
        BinaryTreeNode node6 = binaryTree.postorderSearch(6);
        System.out.println("node6 = " + node6);
    }
}
