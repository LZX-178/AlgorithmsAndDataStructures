package com.lzx.hsp.dataStructures.tree.tree3_threadedBinaryTree;

/**
 * @author LZX
 * @code @create 2022-08-12 9:44:30
 * 线索二叉树
 */
public class ThreadedBinaryTreeNode {
    public final int id;
    public String name;

    public ThreadedBinaryTreeNode left;
    public ThreadedBinaryTreeNode right;
    // 约定 : type 为 0 表示孩子, 为 1 表示前驱或后继
    public int leftType;
    public int rightType;


    // 中序遍历
    public void inorderTraversal(){
        if (left != null && leftType == 0) {
            left.inorderTraversal();
        }

        System.out.println(this);

        if (right != null && rightType == 0) {
            right.inorderTraversal();
        }
    }

    @Override
    public String toString() {
        return "ThreadedBinaryTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", left=" + (left == null ? null : left.id) +
                ", right=" + (right == null ? null : right.id) +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }

    public ThreadedBinaryTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
