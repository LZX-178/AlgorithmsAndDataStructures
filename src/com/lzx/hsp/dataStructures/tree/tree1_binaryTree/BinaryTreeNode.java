package com.lzx.hsp.dataStructures.tree.tree1_binaryTree;

/**
 * @author LZX
 * @code @create 2022-08-11 8:01:44
 */
public class BinaryTreeNode {
    public final int id;
    public String name;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public boolean delete(int deleteId){
        boolean isDelete = false;
        if (left != null){
            System.out.println("deleting left.id = " + left.id);
            if (left.id == deleteId){
                left = null;
                return true;
            }else {
                isDelete = left.delete(deleteId);
                if (isDelete){
                    return true;
                }
            }
        }
        if (right != null) {
            System.out.println("deleting right.id = " + right.id);
            if (right.id == deleteId){
                right = null;
                return true;
            }else {
                isDelete = right.delete(deleteId);
            }
        }
        return isDelete;
    }

    // 前序查找
    public BinaryTreeNode preOrderSearch(int findId){
        System.out.println("preOrderSearch, this.id = " + id);
        if (id == findId){
            return this;
        }

        BinaryTreeNode findNode = null;
        if (left != null) {
            if ((findNode = left.preOrderSearch(findId)) != null){
                return findNode;
            }
        }
        if (right != null){
            findNode = right.preOrderSearch(findId);
        }
        return findNode;
    }
    // 中序查找
    public BinaryTreeNode inorderSearch(int findId){

        BinaryTreeNode findNode = null;
        if (left != null) {
            if ((findNode = left.inorderSearch(findId)) != null){
                return findNode;
            }
        }

        System.out.println("inorderSearch, this.id = " + id);
        if (id == findId){
            return this;
        }

        if (right != null) {
            findNode = right.inorderSearch(findId);
        }
        return findNode;
    }
    // 后序查找
    public BinaryTreeNode postorderSearch(int findId){

        BinaryTreeNode findNode;
        if (left != null) {
            if ((findNode = left.postorderSearch(findId)) != null){
                return findNode;
            }
        }

        if (right != null) {
            if ((findNode = right.postorderSearch(findId)) != null){
                return findNode;
            }
        }

        System.out.println("postorderSearch, this.id = " + id);
        if (id == findId){
            return this;
        }

        return null;
    }

    // 前序遍历
    public void preOrderTraversal(){
        System.out.println(this);

        if (left != null) {
            left.preOrderTraversal();
        }

        if (right != null) {
            right.preOrderTraversal();
        }
    }
    // 中序遍历
    public void inorderTraversal(){
        if (left != null) {
            left.inorderTraversal();
        }

        System.out.println(this);

        if (right != null) {
            right.inorderTraversal();
        }
    }
    // 后序遍历
    public void postorderTraversal(){
        if (left != null) {
            left.postorderTraversal();
        }

        if (right != null) {
            right.postorderTraversal();
        }

        System.out.println(this);
    }


    public BinaryTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
