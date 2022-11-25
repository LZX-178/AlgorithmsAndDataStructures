package com.lzx.leetCode.chapter07_tree.binaryTree;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-24 18:08:02
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Code_98_ValidateBinarySearchTree {
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

    @Test
    public void test_isValidBST() {
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);

        node2.left = node1;
        node2.right = node3;

        boolean validBST = isValidBST(node2);
        System.out.println("validBST = " + validBST);
    }

    public boolean isValidBST(TreeNode root) {
        int[] res = process(root);
        return res[0] == 1;
    }

    // 约定 数组的长度为 3
    //  0 : -1-空子树, 0-非平衡树, 1-平衡树
    //  1 : 该树的最小值
    //  2 : 该树的最大值
    private int[] process(TreeNode root) {
        if (root == null) {
            return new int[]{-1, 0, 0};
        }

        // 左右子树有一个为非平衡, 返回非平衡数
        int[] resLeft = process(root.left);
        if (resLeft[0] == 0){
            return new int[3];
        }
        int[] resRight = process(root.right);
        if (resRight[0] == 0){
            return new int[3];
        }

        if (resLeft[0] == -1 && resRight[0] == -1){
            // 如果左右子树均为空
            return new int[]{1, root.val, root.val};
        }else if (resLeft[0] == 1 && resRight[0] == 1){
            // 如果左右子树均非空
            if (resLeft[2] < root.val && resRight[1] > root.val){
                return new int[]{1, resLeft[1], resRight[2]};
            }else {
                return new int[3];
            }
        }else if (resLeft[0] == 1){
            // 如果左子树非空
            if (resLeft[2] < root.val){
                return new int[]{1, resLeft[1], root.val};
            }else {
                return new int[3];
            }
        }else {
            // 如果右子树非空
            if (resRight[1] > root.val){
                return new int[]{1, root.val, resRight[2]};
            }else {
                return new int[3];
            }
        }

    }
}
