package com.lzx.hsp.dataStructures.tree.tree1_binaryTree;


/**
 * @author LZX
 * @code @create 2022-08-11 8:01:25
 * 二叉树
 */
public class BinaryTree {
    private BinaryTreeNode root;

    // 删除节点
    public boolean delete(int deleteId){
        if (root != null){
            System.out.println("deleting root.id = " + root.id);
            if (root.id == deleteId){
                root = null;
                return true;
            }else {
                return root.delete(deleteId);
            }
        }
        return false;
    }

    // 前序查找
    public BinaryTreeNode preOrderSearch(int findId){
        if (root != null) {
            return root.preOrderSearch(findId);
        }else {
            return null;
        }
    }
    // 中序查找
    public BinaryTreeNode inorderSearch(int findId){
        if (root != null) {
            return root.inorderSearch(findId);
        }else {
            return null;
        }
    }
    // 后序查找
    public BinaryTreeNode postorderSearch(int findId){
        if (root != null) {
            return root.postorderSearch(findId);
        }else {
            return null;
        }
    }

    // 前序遍历
    public void preOrderTraversal(){
        if (root != null) {
            root.preOrderTraversal();
        }else {
            System.out.println("binary tree is empty");
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
    // 后序遍历
    public void postorderTraversal(){
        if (root != null) {
            root.postorderTraversal();
        }else {
            System.out.println("binary tree is empty");
        }
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }
}
