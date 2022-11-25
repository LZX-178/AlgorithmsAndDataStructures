package com.lzx.leetCode.chapter07_tree.binaryTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-20 18:15:23
 * 145. 二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 */
public class Code_145_postorderTraversal {
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

        System.out.println("***** " + "postorderTraversal1" + " *****");
        List<Integer> list = postorderTraversal0(head);
        System.out.println("list = " + list);
        list = postorderTraversal1(head);
        System.out.println("list = " + list);
        list = postorderTraversal2(head);
        System.out.println("list = " + list);
        list = postorderTraversal3(head);
        System.out.println("list = " + list);
        list = postorderTraversal4(head);
        System.out.println("list = " + list);
    }

    // 0 递归遍历
    public List<Integer> postorderTraversal0(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        postorderTraversal(root, list);

        return list;
    }
    public void postorderTraversal(TreeNode root, List<Integer> list){
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

    // 1 非递归遍历
    public List<Integer> postorderTraversal1(TreeNode node){
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
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.right);
            }else {
                count = 3;
//                System.out.println("node = " + node + ", count = " + count);
                list.add(node.val);
//                accessCount.put(node, count);
            }
        }


        return list;
    }
    // 2 非递归遍历
    public List<Integer> postorderTraversal2(TreeNode node){
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
                stack.push(node.right);
//                System.out.println("node = " + node.right + ", count = 1");
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
                list.add(last.val);
            }
        }


        return list;
    }
    // 3 非递归遍历
    public List<Integer> postorderTraversal3(TreeNode node){
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }

        // 存储左边界
        Stack<TreeNode> stack = new Stack<>();
        // 存储右边界
        Stack<TreeNode> stack2 = new Stack<>();

        while (true) {
            // 每次循环将 以 node 为根的子树的 左边界压入栈
            do {
                stack.push(node);
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
            while (node.right == null){
//                System.out.println("node = " + node + ", count = 3");
                list.add(node.val);

                // 若当前访问结点为 右边界栈顶元素 的 右结点
                // 则说明 当前层左边界 已经访问完成了
                // 不可能有 某个节点的左节点 为 当前结点
                while (!stack2.isEmpty() && stack2.peek().right == node){
                    // root 回到上一层左边界
                    // 继续寻找左边界上第一个非空的 "右节点"
                    node = stack2.pop();
//                    System.out.println("node = " + node + ", count = 3");
                    list.add(node.val);
                }

                if (stack.isEmpty()){
                    // 若左边界栈为空, 说明所有左边界访问完成
                    // 程序结束
                    node = null;
                    break;
                }else {
                    // 回溯左边界的上一个元素
                    node = stack.pop();
                }
            }
            if (node == null){
                break;
            }
            // 进入下一层左边界
            stack2.push(node);
            node = node.right;
        }

        return list;
    }
    // 4 非递归遍历
    public List<Integer> postorderTraversal4(TreeNode node){
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }


        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            stack2.push(node);

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        while (!stack2.isEmpty()){
//            System.out.println("node = " + stack2.pop() + ", count = 3");
            list.add(stack2.pop().val);
        }


        return list;
    }

}
