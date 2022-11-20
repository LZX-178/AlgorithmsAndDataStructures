package com.lzx.hsp.dataStructures.tree.tree5_binarySortTree;

import java.util.function.Consumer;

/**
 * @author LZX
 * @code @create 2022-08-13 15:05:42
 */
public class BinarySortTreeNode {
    public int value;
    public BinarySortTreeNode left;
    public BinarySortTreeNode right;

    // 查找某个节点, 已经确定当前结点不是要找的结点
    // 如果找到了, 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点, 第二个元素为该结点
    // 如果没找到, 返回 null
    public BinarySortTreeNode[] search(int findValue) {
        if (findValue < value){
            if (left == null) {
                return null;
            }
            if (left.value == findValue){
                return new BinarySortTreeNode[]{this, left};
            }else {
                return left.search(findValue);
            }
        }else {
            if (right == null) {
                return null;
            }
            if (right.value == findValue){
                return new BinarySortTreeNode[]{this, right};
            }else {
                return right.search(findValue);
            }
        }
    }

    // 查找以 当前结点为根 的二叉排序树 的最小节点
    // 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点(有可能为 null), 第二个元素为该结点
    public BinarySortTreeNode[] searchMin() {
        BinarySortTreeNode parent = null;
        BinarySortTreeNode node = this;

        while (node.left != null){
            parent = node;
            node = node.left;
        }

        return new  BinarySortTreeNode[]{parent, node};
    }
    // 查找以 当前结点为根 的二叉排序树 的最大节点
    // 返回的 BinarySortTreeNode[]数组 第一个元素为其父节点(有可能为 null), 第二个元素为该结点
    public BinarySortTreeNode[] searchMax() {
        BinarySortTreeNode parent = null;
        BinarySortTreeNode node = this;

        while (node.right != null){
            parent = node;
            node = node.right;
        }

        return new BinarySortTreeNode[]{parent, node};
    }
    // 添加节点
    public void add(BinarySortTreeNode node){
        if (node.value < value){
            if (left == null){
                left = node;
            }else {
                left.add(node);
            }
        }else {
            if (right == null){
                right = node;
            }else {
                right.add(node);
            }
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
    // 中序遍历
    public void inorderTraversal(Consumer<BinarySortTreeNode> consumer) {
        if (left != null) {
            left.inorderTraversal(consumer);
        }

        consumer.accept(this);

        if (right != null) {
            right.inorderTraversal(consumer);
        }
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                ", left=" + (left == null ? null : left.value) +
                ", right=" + (right == null ? null : right.value) +
                '}';
    }

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

}
