package com.lzx.leetCode.chapter07_tree.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LZX
 * @code @create 2022-10-21 15:57:52
 */
public class Code_102_levelOrderTraversal {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 层次遍历 1
    // 使用队列
    public List<List<Integer>> levelOrder1(TreeNode node){

        if (node == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> levelTree = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        // flag 指向每一层的第一个元素
        TreeNode flag = node;

        while (!queue.isEmpty()){
            node = queue.poll();

            // 若当前元素为某一层的第一个元素, 则下一个加入队列的元素一定是下一层的第一个元素
            if (node == flag){
                flag = null;
                levelTree.add(new ArrayList<>());
            }
            levelTree.get(levelTree.size()-1).add(node.val);

            if (node.left != null){
                queue.add(node.left);
                if (flag == null){
                    flag = node.left;
                }
            }
            if (node.right != null){
                queue.add(node.right);
                if (flag == null){
                    flag = node.right;
                }
            }
        }

        return levelTree;

    }

    // 层次遍历 2
    // 先进行一次先序遍历, 将二叉树放入一个二维 List 中
    // 对二叉树的每一层的元素而言, 在前序遍历中是有序的
    public List<List<Integer>> levelOrder2(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> levelTree = new ArrayList<>();
        levelOrder2(node, levelTree, 1);
        return levelTree;
    }
    private void levelOrder2(TreeNode node, List<List<Integer>> levelTree, int level) {
        if (levelTree.size() < level){
            levelTree.add(new ArrayList<>());
        }
        levelTree.get(level-1).add(node.val);

        if (node.left != null) {
            levelOrder2(node.left, levelTree, level+1);
        }
        if (node.right != null) {
            levelOrder2(node.right, levelTree, level+1);
        }
    }
}
