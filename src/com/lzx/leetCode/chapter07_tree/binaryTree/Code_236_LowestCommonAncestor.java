package com.lzx.leetCode.chapter07_tree.binaryTree;

import org.junit.Test;
import sun.awt.datatransfer.DataTransferer;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-06 17:31
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 最近公共祖先的定义为：
 * “对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * 所有 Node.val 互不相同 。
 */
public class Code_236_LowestCommonAncestor {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法1 先找到其中一个节点 p， 再从该节点的路径出发， 寻找另一个节点，
    // 如果没找到, 说明另一个节点在 p 的子树上
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root){
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = root;
        TreeNode node, cur = null;

        // 先找到其中一个节点
        stack.push(root);
        while (!stack.isEmpty()){
            node = stack.peek();

            if (node.left != null && last != node.left && last != node.right){
                if (node.left == p || node.left == q){
                    cur = node.left;
                    break;
                }
                stack.push(node.left);
            }else if (node.right != null && last != node.right){
                if (node.right == p || node.right == q){
                    cur = node.right;
                    break;
                }
                stack.push(node.right);
            }else {
                last = stack.pop();
            }
        }

        if (cur == null) {
            return null;
        }
        // target 为另一个节点
        TreeNode target = cur == p ? q : p;
        while (!stack.isEmpty()){
            node = stack.pop();
            // 此处只需要找路径的 右节点, 因为左节点已经找过了
            if (node.right != null && node.right != cur){
                if(search(node.right, target)){
                    return node;
                }
            }
            cur = node;
        }

        // 没找到 target , 则说明 target 在原节点的子树上
        return target == p ? q : p;
    }
    // 寻找 root 树上是否包含 target
    private boolean search(TreeNode root, TreeNode target) {
        if (root == target){
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = root;

        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if (node.left != null && last != node.left && last != node.right){
                if (node.left == target){
                    return true;
                }
                stack.push(node.left);
            }else if (node.right != null && last != node.right){
                if (node.right == target){
                    return true;
                }
                stack.push(node.right);
            }else {
                last = stack.pop();
            }
        }

        return false;
    }


    // 方法2 递归版本
    // 对于 最近公共祖先 节点, 只有以下两种情况
    //     1 是 p 或 q 其中一个
    //     2 左子树包含 q, 右子树包含 p, 或反之
    // 对于其他的祖先节点, 只有一个子树是包含 p 或者 q 的
    private TreeNode ans;
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
        dfs(root, p, q);
        return ans;
    }
    // 返回该子树是否包含 p 或 q
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return false;
        }
        boolean flagL = dfs(root.left, p, q);
        if (ans != null){
            return true;
        }

        boolean flagR = dfs(root.right, p, q);
        if (ans != null){
            return true;
        }

        if ((flagL && flagR) || ((flagR || flagL) && (root == p || root == q))){
            ans = root;
            return true;
        }
        return flagL || flagR || root == p || root == q;
    }

    @Test
    public void test_1() {
        TreeNode head;
        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        //       1
        //      /  \
        //    2      3
        //   / \    / \
        //  4   5  6   7

        TreeNode treeNode = lowestCommonAncestor(head, head.left.left , head.right.left);
        System.out.println("treeNode.val = " + treeNode.val);
    }
}
