package com.lzx.leetCode.chapter07_tree.binaryTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-20 14:28:06
 * 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class Code_144_preorderTraversal {
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

        System.out.println("***** " + "preorderTraversal1" + " *****");
        List<Integer> list = preorderTraversal0(head);
        System.out.println("list = " + list);
        list = preorderTraversal1(head);
        System.out.println("list = " + list);
        list = preorderTraversal2(head);
        System.out.println("list = " + list);
        list = preorderTraversal3(head);
        System.out.println("list = " + list);
        list = preorderTraversal4(head);
        System.out.println("list = " + list);
    }

    // 0 递归遍历
    public List<Integer> preorderTraversal0(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        preorderTraversal(root, list);

        return list;
    }
    public void preorderTraversal(TreeNode root, List<Integer> list){
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    // 1 非递归遍历
    public List<Integer> preorderTraversal1(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }


        // 模拟方法栈
        Stack<TreeNode> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<TreeNode, Integer> accessCount = new HashMap<>();

        stack.push(root);

        // 约定 ：每次弹栈时访问该节点， 压栈不算访问
        while (!stack.isEmpty()){
            root = stack.pop();
            if (root == null){
                continue;
            }

            Integer count = accessCount.get(root);
            if (count == null) {
                count = 1;
//                System.out.println("root = " + root + ", count = " + count);
                list.add(root.val);
                accessCount.put(root, count);

                stack.push(root);
                stack.push(root.left);
            }else if (count == 1){
                count = 2;
//                System.out.println("root = " + root + ", count = " + count);
                accessCount.put(root, count);

                stack.push(root);
                stack.push(root.right);
            }else {
                count = 3;
//                System.out.println("root = " + root + ", count = " + count);
//                accessCount.put(node, count);
            }
        }

        return list;
    }

    // 2 非递归遍历
    public List<Integer> preorderTraversal2(TreeNode node){

        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
//        System.out.println("node = " + node + ", count = 1");
        list.add(node.val);
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
                list.add(node.left.val);
            }else if (node.right != null && last != node.right){
                // 如果 左节点已经访问过了 或 左节点为空
                // 如果 右节点非空 且 上一次第三次访问节点 不是 右节点
                // 说明 应该访问右节点了
//                System.out.println("node = " + node + ", count = 2");
                stack.push(node.right);
//                System.out.println("node = " + node.right + ", count = 1");
                list.add(node.right.val);
            }else {
                // 左右节点访问完成
                // 第三次访问当前结点
                last = stack.pop();
//                if (node.right == null){
//                    // 如果右节点为空, 不会进入第二个循环分支
//                    // 补上第二次访问
//                    System.out.println("node = " + last + ", count = 2");
//                }
//                System.out.println("node = " + last + ", count = 3");
            }
        }

        return list;

    }

    // 3 非递归遍历
    public List<Integer> preorderTraversal3(TreeNode node){
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
//                System.out.println("node = " + node + ", count = 1");
                list.add(node.val);
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
            while (node.right == null){
                if (stack.isEmpty()){
                    // 如果 栈里没有 待检查是否存在右子树的 节点
                    // 且当 当前结点已访问, 当前结点的左子树已访问, 当前结点的右子树为空
                    // 则遍历完成
                    return list;
                }
                node = stack.pop();
            }
            // 将其更新为 node
            node = node.right;
        }
    }

    // 4 非递归遍历
    public List<Integer> preorderTraversal4(TreeNode node){
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        // 约定 ：每次弹栈时访问节点
        while (!stack.isEmpty()) {
            node = stack.pop();
//            System.out.println("node = " + node + ", count = 1");
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }
}
