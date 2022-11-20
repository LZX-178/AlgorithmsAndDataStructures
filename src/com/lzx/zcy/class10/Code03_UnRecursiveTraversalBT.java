package com.lzx.zcy.class10;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-20 9:44:05
 * 非递归遍历二叉树
 *
 * 非递归 前中后序遍历二叉树 3 的代码还有改进的空间!!!!! (想办法抽取成递归序遍历!!!)
 */
public class Code03_UnRecursiveTraversalBT {
    private static class Node{
        public int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + value + '}';
        }
    }

    public Node head;

    @Before
    public void init(){
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        //       1
        //      /  \
        //    2      3
        //   / \    / \
        //  4   5  6   7
    }

    @Test
    public void test_Traversal() {
        // 递归序
        System.out.println("***** " + "recursiveTraversal1" + " *****");
        recursiveTraversal1(head);
        System.out.println("***** " + "recursiveTraversal2" + " *****");
        recursiveTraversal2(head);

        // 前序
        System.out.println("***** " + "preorderTraversal1" + " *****");
        preorderTraversal1(head);
        System.out.println("***** " + "preorderTraversal2" + " *****");
        preorderTraversal2(head);
        System.out.println("***** " + "preorderTraversal3" + " *****");
        preorderTraversal3(head);
        System.out.println("***** " + "preorderTraversal4" + " *****");
        preorderTraversal4(head);

        // 中序
        System.out.println("***** " + "inorderTraversal1" + " *****");
        inorderTraversal1(head);
        System.out.println("***** " + "inorderTraversal2" + " *****");
        inorderTraversal2(head);
        System.out.println("***** " + "inorderTraversal3" + " *****");
        inorderTraversal3(head);

        // 后续
        System.out.println("***** " + "postorderTraversal1" + " *****");
        postorderTraversal1(head);
        System.out.println("***** " + "postorderTraversal2" + " *****");
        postorderTraversal2(head);
        System.out.println("***** " + "postorderTraversal3" + " *****");
        postorderTraversal3(head);
        System.out.println("***** " + "postorderTraversal4" + " *****");
        postorderTraversal4(head);
    }

    // 非递归 递归序遍历二叉树 1
    // 前中后序遍历的通解， 由此方法可得 前中后序的非递归遍历
    // 使用一个栈来模拟方法栈
    // 维护一个 map 来确定是第几次访问该节点
    public void recursiveTraversal1(Node node){
        if (node == null) {
            return;
        }
        // 模拟方法栈
        Stack<Node> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<Node, Integer> accessCount = new HashMap<>();

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
                System.out.println("node = " + node + ", count = " + count);
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.left);
            }else if (count == 1){
                count = 2;
                System.out.println("node = " + node + ", count = " + count);
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.right);
            }else {
                count = 3;
                System.out.println("node = " + node + ", count = " + count);
//                accessCount.put(node, count);
            }
        }
    }

    // 非递归 递归序遍历二叉树 2
    // 前中后序遍历的通解， 由此方法可得 前中后序的非递归遍历
    // 使用一个栈来模拟方法栈
    // 使用 last 指针来指向 上一个第三次访问的结点 以确定当前结点是第几次访问
    public void recursiveTraversal2(Node node){
        if (node == null) {
            return;
        }
        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        System.out.println("node = " + node + ", count = 1");
        // 永远指向上一个第三次访问的结点
        Node last = null;

        while (!stack.isEmpty()){
            node = stack.peek();

            if (node.left != null && last != node.left && last != node.right){
                // 如果左节点非空
                // 且 上一次第三次访问节点 既不是 左节点 也不是 右节点
                // 说明 左右节点都没有访问过
                stack.push(node.left);
                System.out.println("node = " + node.left + ", count = 1");
            }else if (node.right != null && last != node.right){
                // 如果 左节点已经访问过了 或 左节点为空
                // 如果 右节点非空 且 上一次第三次访问节点 不是 右节点
                // 说明 应该访问右节点了
                System.out.println("node = " + node + ", count = 2");
                stack.push(node.right);
                System.out.println("node = " + node.right + ", count = 1");
            }else {
                // 左右节点访问完成
                // 第三次访问当前结点
                last = stack.pop();
                if (node.right == null){
                    // 如果右节点为空, 不会进入第二个循环分支
                    // 补上第二次访问
                    System.out.println("node = " + last + ", count = 2");
                }
                System.out.println("node = " + last + ", count = 3");
            }
        }
    }



    // 非递归 前序遍历二叉树 1
    // 参考 非递归 递归序遍历二叉树 1
    public void preorderTraversal1(Node node){

        if (node == null) {
            return;
        }
        // 模拟方法栈
        Stack<Node> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<Node, Integer> accessCount = new HashMap<>();

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
                System.out.println("node = " + node + ", count = " + count);
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
//                accessCount.put(node, count);
            }
        }

    }
    // 非递归 中序遍历二叉树 1
    // 参考 非递归 递归序遍历二叉树 1
    public void inorderTraversal1(Node node){

        if (node == null) {
            return;
        }
        // 模拟方法栈
        Stack<Node> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<Node, Integer> accessCount = new HashMap<>();

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
                System.out.println("node = " + node + ", count = " + count);
                accessCount.put(node, count);

                stack.push(node);
                stack.push(node.right);
            }else {
                count = 3;
//                System.out.println("node = " + node + ", count = " + count);
//                accessCount.put(node, count);
            }
        }

    }
    // 非递归 后序遍历二叉树 1
    // 参考 非递归 递归序遍历二叉树 1
    public void postorderTraversal1(Node node){

        if (node == null) {
            return;
        }
        // 模拟方法栈
        Stack<Node> stack = new Stack<>();
        // 记录是第几次访问某个节点
        HashMap<Node, Integer> accessCount = new HashMap<>();

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
                System.out.println("node = " + node + ", count = " + count);
//                accessCount.put(node, count);
            }
        }

    }




    // 非递归 前序遍历二叉树 2
    // 参考 非递归 递归序遍历二叉树 2
    public void preorderTraversal2(Node node){

        if (node == null) {
            return;
        }
        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        System.out.println("node = " + node + ", count = 1");
        // 永远指向上一个第三次访问的结点,
        // 但初始化不能为 null,
        // 得是一个 保证 last != node.left 和 last != node.right 为真的值
        Node last = node;

        while (!stack.isEmpty()){
            node = stack.peek();

            if (node.left != null && last != node.left && last != node.right){
                // 如果左节点非空
                // 且 上一次第三次访问节点 既不是 左节点 也不是 右节点
                // 说明 左右节点都没有访问过
                stack.push(node.left);
                System.out.println("node = " + node.left + ", count = 1");
            }else if (node.right != null && last != node.right){
                // 如果 左节点已经访问过了 或 左节点为空
                // 如果 右节点非空 且 上一次第三次访问节点 不是 右节点
                // 说明 应该访问右节点了
//                System.out.println("node = " + node + ", count = 2");
                stack.push(node.right);
                System.out.println("node = " + node.right + ", count = 1");
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

    }
    // 非递归 中序遍历二叉树 2
    // 参考 非递归 递归序遍历二叉树 2
    public void inorderTraversal2(Node node){

        if (node == null) {
            return;
        }
        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<Node> stack = new Stack<>();
        stack.push(node);
//        System.out.println("node = " + node + ", count = 1");
        // 永远指向上一个第三次访问的结点,
        // 但初始化不能为 null,
        // 得是一个 保证 last != node.left 和 last != node.right 为真的值
        Node last = node;

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
                System.out.println("node = " + node + ", count = 2");
                stack.push(node.right);
//                System.out.println("node = " + node.right + ", count = 1");
            }else {
                // 左右节点访问完成
                // 第三次访问当前结点
                last = stack.pop();
                if (node.right == null){
                    // 如果右节点为空, 不会进入第二个循环分支
                    // 补上第二次访问
                    System.out.println("node = " + last + ", count = 2");
                }
//                System.out.println("node = " + last + ", count = 3");
            }
        }


    }
    // 非递归 后序遍历二叉树 2
    // 参考 非递归 递归序遍历二叉树 2
    public void postorderTraversal2(Node node){

        if (node == null) {
            return;
        }
        // 栈里存访问过一次和两次的结点
        // 约定压栈时第一次访问
        Stack<Node> stack = new Stack<>();
        stack.push(node);
//        System.out.println("node = " + node + ", count = 1");
        // 永远指向上一个第三次访问的结点,
        // 但初始化不能为 null,
        // 得是一个 保证 last != node.left 和 last != node.right 为真的值
        Node last = node;

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
                System.out.println("node = " + last + ", count = 3");
            }
        }

    }




    // 非递归 前序遍历二叉树 3
    // 参考 非递归 递归序遍历二叉树
    // *****但是是面向过程的, 特别麻烦*****
    // 同样使用 "方法栈的思想", 栈里存的是 已经访问过一次的节点(第一次访问时打印)
    public void preorderTraversal3(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        // 约定 ：每次压栈时访问节点
        while (true) {
            // 每次循环将 以 node 为根的子树的 左边界压入栈
            do {
                stack.push(node);
                System.out.println("node = " + node + ", count = 1");
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
            while (node.right == null){
                if (stack.isEmpty()){
                    // 如果 栈里没有 待检查是否存在右子树的 节点
                    // 且当 当前结点已访问, 当前结点的左子树已访问, 当前结点的右子树为空
                    // 则遍历完成
                    return;
                }
                node = stack.pop();
            }
            // 将其更新为 node
            node = node.right;
        }
    }
    // 非递归 中序遍历二叉树 3
    // 参考 非递归 递归序遍历二叉树
    // *****但是是面向过程的, 特别麻烦*****
    // 使用 "方法栈的思想", 栈里存的是 已经访问过一次的节点
    // 在访问第二次时(第二次访问时打印), 获取其右子树的信息后, 将其弹栈
    public void inorderTraversal3(Node node){

        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        // 约定 ：每次压栈时访问节点
        while (true) {
            // 每次循环将 以 node 为根的子树的 左边界压入栈
            do {
                stack.push(node);
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
            System.out.println("node = " + node + ", count = 2");
            while (node.right == null){
                if (stack.isEmpty()){
                    // 如果 栈里没有 待检查是否存在右子树的 节点
                    // 且当 当前结点的左子树已访问, 当前结点已访问, 当前结点的右子树为空
                    // 则遍历完成
                    return;
                }
                node = stack.pop();
                System.out.println("node = " + node + ", count = 2");
            }
            // 将其更新为 node
            node = node.right;
        }
    }
    // 非递归 后序遍历二叉树 3
    // 参考 非递归 递归序遍历二叉树
    // *****但是是面向过程的, 特别麻烦*****
    // 使用 "方法栈的思想", 栈里存的是 已经访问过一次的节点
    // 第二次访问时将其从栈里拿出来, 放入一个新的栈
    // 如何确定何时第三次访问新栈的元素是难点
    public void postorderTraversal3(Node node){
        if (node == null) {
            return;
        }

        // 存储左边界
        Stack<Node> stack = new Stack<>();
        // 存储右边界
        Stack<Node> stack2 = new Stack<>();

        while (true) {
            // 每次循环将 以 node 为根的子树的 左边界压入栈
            do {
                stack.push(node);
                node = node.left;
            }while (node != null);

            // 然后回溯左边界， 找到边界上第一个非空的 "右节点"
            node = stack.pop();
            while (node.right == null){
                System.out.println("node = " + node + ", count = 3");

                // 若当前访问结点为 右边界栈顶元素 的 右结点
                // 则说明 当前层左边界 已经访问完成了
                // 不可能有 某个节点的左节点 为 当前结点
                while (!stack2.isEmpty() && stack2.peek().right == node){
                    // root 回到上一层左边界
                    // 继续寻找左边界上第一个非空的 "右节点"
                    node = stack2.pop();
                    System.out.println("node = " + node + ", count = 3");
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
    }




    // 非递归 前序遍历二叉树 4
    // 不是模拟方法栈
    // 栈里存的是待访问的树的根节点
    // 每次循环弹出一个元素并访问
    // 再将该元素的 右节点 和 左节点 压栈 (如果非空的话)
    public void preorderTraversal4(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        // 约定 ：每次弹栈时访问节点
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println("node = " + node + ", count = 1");

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    // 非递归 后序遍历二叉树 4
    // 不是模拟方法栈
    // 栈里存的是 待访问的树的根节点
    // 每次循环弹出一个元素并压入一个新的栈
    // 再将该元素的 左节点 和 右节点 压栈 (如果非空的话)
    // 最后将新栈依次弹出并访问
    public void postorderTraversal4(Node node){

        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
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
            System.out.println("node = " + stack2.pop() + ", count = 3");
        }

    }

}
