package com.lzx.leetCode.tree.binaryTree;


import javafx.util.Pair;

/**
 * @author LZX
 * @code @create 2022-10-24 16:14:35
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class Code_110_BalancedBinaryTree {
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

    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        Pair<Boolean, Integer> pair = isBalancedAndHeight(root);
        return pair.getKey();
    }

    private Pair<Boolean, Integer> isBalancedAndHeight(TreeNode root) {
        if (root == null){
            return new Pair<>(true, 0);
        }
        Pair<Boolean, Integer> leftPair = isBalancedAndHeight(root.left);
        Pair<Boolean, Integer> rightPair = isBalancedAndHeight(root.right);

        Integer leftHeight = leftPair.getValue();
        Integer rightHeight = rightPair.getValue();
        Integer height = Math.max(leftHeight, rightHeight) + 1;
        Boolean isBalanced = leftPair.getKey() && rightPair.getKey() && (Math.abs(leftHeight-rightHeight) < 2);

        return new Pair<>(isBalanced, height);
    }
}
