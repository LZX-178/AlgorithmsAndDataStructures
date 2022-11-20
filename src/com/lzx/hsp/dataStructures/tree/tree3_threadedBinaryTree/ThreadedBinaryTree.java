package com.lzx.hsp.dataStructures.tree.tree3_threadedBinaryTree;

/**
 * @author LZX
 * @code @create 2022-08-12 9:43:56
 * 线索二叉树
 */
public class ThreadedBinaryTree {

    public ThreadedBinaryTreeNode root;
    public ThreadedBinaryTreeNode pre;

    // 对二叉树进行中序线索化
    public void threadedNodes(){
        if (root == null){
            System.out.println("tree is empty");
        }else {
            threadedNodes(root);
        }
    }
    private void threadedNodes(ThreadedBinaryTreeNode node){
        // 线索化左子树
        if (node.left != null){
            threadedNodes(node.left);
        }

        // 线索化当前结点的左指针 和 pre 的右指针
        if (node.left == null){
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        // 更新 pre
        pre = node;

        // 线索化右子树
        if (node.right != null) {
            threadedNodes(node.right);
        }
    }

    // 线索化中序遍历
    public void threadedInorderTraversal(){
        if (root == null) {
            System.out.println("binary tree is empty");
            return;
        }

        ThreadedBinaryTreeNode node = root;
        while (node != null) {
            // 找到第一个左子树为空的结点
            while (node.leftType == 0) {
                node = node.left;
            }
            // 打印之
            System.out.println(node);

            // 如果该结点的 右指针 为 线索, 则一路打印
            // 直到 右指针 为 子树
            while (node.rightType == 1){
                node = node.right;
                System.out.println(node);
            }

            // 对该结点而言, 已经遍历的了左子树和当前结点
            // (中序遍历的性质 : 一个节点的中序序列中, 之前的节点为其左子树, 之后的为右子树)
            // 此时, 对 右子树 进行中序遍历
            node = node.right;
        }
    }

    // 中序遍历
    public void inorderTraversal(){
        if (root != null) {
            root.inorderTraversal();
        }else {
            System.out.println("binary tree is empty");
        }
    }
}
