package com.lzx.hsp.dataStructures.tree.tree5_binarySortTree;

import java.util.function.Consumer;

/**
 * @author LZX
 * @code @create 2022-08-13 15:04:57
 * 二叉排序树
 */
public class BinarySortTree {
    public BinarySortTreeNode root;

    // 删除节点
    public boolean delete(int findValue){
        BinarySortTreeNode[] searchResult = search(findValue);
        if (searchResult == null) {
            return false;
        }else {
            doDelete(searchResult[0], searchResult[1]);
            return true;
        }
    }
    public void doDelete(BinarySortTreeNode parent, BinarySortTreeNode node){
        // 删除根结点
        if (parent == null){
            if (node.left == null && node.right == null){ // 左右均为空
                root = null;
            }else if (node.left != null && node.right == null){ // 左非空, 右空
                root = node.left;
            }else if (node.left == null){ // 左空, 右非空
                root = node.right;
            }else { // 左右均非空
                BinarySortTreeNode[] searchMin = node.right.searchMin();
                node.value = searchMin[1].value;
                doDelete(searchMin[0] == null ? node : searchMin[0], searchMin[1]);
            }
            return;
        }
        // 删除非根节点
        if (node.left == null && node.right == null){ // 左右均为空
            if (parent.left == node){
                parent.left = null;
            }else {
                parent.right = null;
            }
        }else if (node.left != null && node.right == null){ // 左非空, 右空
            if (parent.left == node){
                parent.left = node.left;
            }else {
                parent.right = node.left;
            }
        }else if (node.left == null){ // 左空, 右非空
            if (parent.left == node){
                parent.left = node.right;
            }else {
                parent.right = node.right;
            }
        }else { // 左右均非空
            BinarySortTreeNode[] searchMin = node.right.searchMin();
            node.value = searchMin[1].value;
            doDelete(searchMin[0] == null ? node : searchMin[0], searchMin[1]);
        }
    }

    // 查找 二叉排序树 的最大节点
    // 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点, 第二个元素为该结点
    public BinarySortTreeNode[] searchMax() {
        if (root == null){
            return null;
        }
        return root.searchMax();
    }

    // 查找 二叉排序树 的最小节点
    // 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点, 第二个元素为该结点
    public BinarySortTreeNode[] searchMin() {
        if (root == null){
            return null;
        }
        return root.searchMin();
    }

    // 查找某个节点,
    // 如果找到了, 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点, 第二个元素为该结点
    // 如果没找到, 返回 null
    public BinarySortTreeNode[] search(int findValue){
        if (root == null){
            return null;
        }
        if (root.value == findValue){
            return new BinarySortTreeNode[]{null, root};
        }
        return root.search(findValue);
    }

    // 中序遍历
    public void inorderTraversal(){
        System.out.println("**************************inorderTraversal**************************");
        if (root == null) {
            System.out.println("tree is empty");
            return;
        }
        root.inorderTraversal();
    }
    // 中序遍历
    public void inorderTraversal(Consumer<BinarySortTreeNode> consumer){
        if (root == null) {
            System.out.println("tree is empty");
            return;
        }
        root.inorderTraversal(consumer);
    }


    // 添加节点
    public void add(BinarySortTreeNode node){
        if (node == null){
            System.out.println("node is empty");
            return;
        }
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
}
