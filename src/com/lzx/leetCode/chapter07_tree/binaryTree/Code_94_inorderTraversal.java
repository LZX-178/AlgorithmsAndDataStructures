package com.lzx.leetCode.chapter07_tree.binaryTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-20 15:35:47
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */
public class Code_94_inorderTraversal {
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

    public TreeNode head;

    @Before
    public void init(){
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
    }

    @Test
    public void test_Traversal() {

        System.out.println("***** " + "inorderTraversal1" + " *****");
        List<Integer> list = inorderTraversal0(head);
        System.out.println("list = " + list);
        list = inorderTraversal1(head);
        System.out.println("list = " + list);
        list = inorderTraversal2(head);
        System.out.println("list = " + list);
        list = inorderTraversal3(head);
        System.out.println("list = " + list);
    }

    // 0 递归遍历
    public List<Integer> inorderTraversal0(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        inorderTraversal(root, list);

        return list;
    }
    private void inorderTraversal(TreeNode root, List<Integer> list){
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    // 1 非递归遍历
    public List<Integer> inorderTraversal1(TreeNode node) {
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }


        // 模拟方法栈
        Stack<TreeNode> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<TreeNode, Integer> accessCount = new HashMap<>();

        stack.push(node);

        // 约定 ：每次弹栈时访问该节点， 压栈不算访问
        while (!stack.isEmpty()){
            node = stack.pop();
            if (node == null){
                continue;
            }

            Integer count = accessCount.get(node);
            if (count == null) {
                count = 1;
//                System.out.println("node = " + node + ", count = " + count);
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.left);
            }else if (count == 1){
                count = 2;
//                System.out.println("node = " + node + ", count = " + count);
                list.add(node.val);
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.right);
            }else {
                count = 3;
//                System.out.println("node = " + node + ", count = " + count);
//                accessCount.put(node, count);
            }
        }


        return list;
    }

    // 2 非递归遍历
    public List<Integer> inorderTraversal2(TreeNode node) {
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }


        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
//        System.out.println("node = " + node + ", count = 1");
        // 永远指向上一个第三次访问的结点,
        // 但初始化不能为 null,
        // 得是一个 保证 last != node.left 和 last != node.right 为真的值
        TreeNode last = node;

        while (!stack.isEmpty()){
            node = stack.peek();

            if (node.left != null && last != node.left && last != node.right){
                // 如果左节点非空
                // 且 上一次第三次访问节点 既不是 左节点 也不是 右节点
                // 说明 左右节点都没有访问过
                stack.push(node.left);
//                System.out.println("node = " + node.left + ", count = 1");
            }else if (node.right != null && last != node.right){
                // 如果 左节点已经访问过了 或 左节点为空
                // 如果 右节点非空 且 上一次第三次访问节点 不是 右节点
                // 说明 应该访问右节点了
//                System.out.println("node = " + node + ", count = 2");
                list.add(node.val);
                stack.push(node.right);
//                System.out.println("node = " + node.right + ", count = 1");
            }else {
                // 左右节点访问完成
                // 第三次访问当前结点
                last = stack.pop();
                if (node.right == null){
                    // 如果右节点为空, 不会进入第二个循环分支
                    // 补上第二次访问
//                    System.out.println("node = " + last + ", count = 2");
                    list.add(last.val);
                }
//                System.out.println("node = " + last + ", count = 3");
            }
        }
        return list;
    }

    // 3 非递归遍历
    public List<Integer> inorderTraversal3(TreeNode node) {
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }


        Stack<TreeNode> stack = new Stack<>();

        // 约定 ：每次压栈时访问节点
        while (true) {
            // 每次循环将 以 node 为根的子树的 左边界压入栈
            do {
                stack.push(node);
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
//            System.out.println("node = " + node + ", count = 2");
            list.add(node.val);
            while (node.right == null){
                if (stack.isEmpty()){
                    // 如果 栈里没有 待检查是否存在右子树的 节点
                    // 且当 当前结点的左子树已访问, 当前结点已访问, 当前结点的右子树为空
                    // 则遍历完成
                    return list;
                }
                node = stack.pop();
//                System.out.println("node = " + node + ", count = 2");
                list.add(node.val);
            }
            // 将其更新为 node
            node = node.right;
        }

    }

}
