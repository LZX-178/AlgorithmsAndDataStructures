package com.lzx.hsp.dataStructures.tree.tree3_threadedBinaryTree;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-12 9:44:35
 */
public class ThreadedBinaryTreeTest {
    private ThreadedBinaryTree threadedBinaryTree;

    @Before
    public void init(){
        threadedBinaryTree = new ThreadedBinaryTree();

//        ThreadedBinaryTreeNode node1 = new ThreadedBinaryTreeNode(1, "宋江");
//        ThreadedBinaryTreeNode node2 = new ThreadedBinaryTreeNode(2, "吴用");
//        ThreadedBinaryTreeNode node3 = new ThreadedBinaryTreeNode(3, "卢俊义");
//        ThreadedBinaryTreeNode node4 = new ThreadedBinaryTreeNode(4, "林冲");
//        ThreadedBinaryTreeNode node5 = new ThreadedBinaryTreeNode(5, "关胜");
//
//        //          1
//        //         / \
//        //        2   3
//        //           / \
//        //          5   4
//        threadedBinaryTree.root = node1;
//        node1.left = node2;
//        node1.right = node3;
//        node3.right = node4;
//        node3.left = node5;

        ThreadedBinaryTreeNode node1 = new ThreadedBinaryTreeNode(1, "tom");
        ThreadedBinaryTreeNode node3 = new ThreadedBinaryTreeNode(3, "jack");
        ThreadedBinaryTreeNode node6 = new ThreadedBinaryTreeNode(6, "smith");
        ThreadedBinaryTreeNode node8 = new ThreadedBinaryTreeNode(8, "mary");
        ThreadedBinaryTreeNode node10 = new ThreadedBinaryTreeNode(10, "king");
        ThreadedBinaryTreeNode node14 = new ThreadedBinaryTreeNode(14, "dim");

        //            1
        //         /     \
        //        3       6
        //       / \     /
        //      8   10  14
        threadedBinaryTree.root = node1;
        node1.left = node3;
        node1.right = node6;
        node3.left = node8;
        node3.right = node10;
        node6.left = node14;
    }

    @Test
    public void test_threadedNodes() {
        threadedBinaryTree.inorderTraversal();
        System.out.println("***********threadedNodes***********");
        threadedBinaryTree.threadedNodes();
        threadedBinaryTree.inorderTraversal();
        System.out.println("***********threadedInorderTraversal***********");
        threadedBinaryTree.threadedInorderTraversal();
    }
}
