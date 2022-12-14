package com.lzx.zcy.class11;

import com.lzx.utils.tree.BTNode;
import com.lzx.utils.tree.PrintBinaryTree;
import org.junit.Test;

import java.util.*;

/**
 * @author LZX
 * @code @create 2022-12-04 20:51
 * 将一棵 n叉树编码为一棵二叉树，并对二叉树进行解码，得到原始的 n 叉树。
 * n 叉树是一棵有根树，其中每个节点的子树不超过 n 个。
 * 类似地，二叉树是一棵有根树，其中每个节点的子树不超过 2 个。
 * 编码/解码算法的工作方式不受限制。
 * 您只需要确保一个n叉树可以被编码为一个二叉树，并且这个二叉树可以被解码为原始的n叉树结构。
 *
 *                                           1
 *                                       /      \
 *                                     2         3
 *                                  4 5 6     7 8 9 10
 *
 *
 *                                           1
 *                                       /
 *                                   2
 *                               /       \
 *                             4           3
 *                               \         /
 *                                5      7
 *                                 \       \
 *                                  6        8
 *                                            \
 *                                             9
 *                                              10
 */
public class Code03_EncodeNAryTreeToBinaryTree {

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

        @Override
        public String toString() {
            return "UndirectedGraphNode{" +
                    "label=" + label +
                    '}';
        }
    }

    private static class TreeNode implements BTNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        @Override
        public BTNode getLeft() {
            return left;
        }

        @Override
        public BTNode getRight() {
            return right;
        }

        @Override
        public String getValue() {
            return val+"";
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    /******************** 方法1 **********************/
    // N 叉数的子节点个数是无限的, 可以对应二叉树的高度是无限的, 对应策略如下 :
    //      一个二叉树节点对应一个多叉树节点
    //      二叉树节点的左节点 对应 多叉树节点的第一个子节点(无子节点则为空)
    //      二叉树节点的右节点 对应 多叉树节点的兄弟节点(无兄弟节点则为空)
    // 可以看出, 两颗树的先序遍历是一致的

    // 先序遍历编码
    public TreeNode encode(UndirectedGraphNode root) {
        if (root == null){
            return null;
        }

        // 先序序列化 多叉树
        LinkedList<UndirectedGraphNode> list = new LinkedList<>();
        encodeInorderSerialize(root, list);

        // 先序反序列化 二叉树
        TreeNode head = new TreeNode(list.poll().label);
        encodeInOrderDeserialize(head, list);

        return head;
    }
    // 先序序列化 多叉树
    private void encodeInorderSerialize(UndirectedGraphNode root, LinkedList<UndirectedGraphNode> list) {
        list.add(root);
        for (UndirectedGraphNode neighbor : root.neighbors) {
            encodeInorderSerialize(neighbor, list);
        }
        list.add(null);
    }
    // 先序反序列化 二叉树
    private void encodeInOrderDeserialize(TreeNode head, LinkedList<UndirectedGraphNode> list) {
        UndirectedGraphNode node = list.poll();
        if (node != null) {
            head.left = new TreeNode(node.label);
            encodeInOrderDeserialize(head.left, list);
        }
        node = list.poll();
        if (node != null) {
            head.right = new TreeNode(node.label);
            encodeInOrderDeserialize(head.right, list);
        }
    }

    // 先序遍历解码
    public UndirectedGraphNode decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 先序序列化 二叉树
        LinkedList<TreeNode> list = new LinkedList<>();
        decodeInorderSerialize(root, list);

        // 先序反序列化 多叉树
        UndirectedGraphNode head = new UndirectedGraphNode(list.poll().val);
        decodeInOrderDeserialize(head, list);

        return head;
    }
    // 先序序列化 二叉树
    private void decodeInorderSerialize(TreeNode root, LinkedList<TreeNode> list) {
        list.add(root);

        if (root.left != null) {
            decodeInorderSerialize(root.left, list);
        }else {
            list.add(null);
        }

        if (root.right != null) {
            decodeInorderSerialize(root.right, list);
        }else {
            list.add(null);
        }
    }
    // 先序反序列化 多叉树
    private void decodeInOrderDeserialize(UndirectedGraphNode head, LinkedList<TreeNode> list) {
        TreeNode node = list.poll();
        while (node != null){
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.val);
            head.neighbors.add(newNode);
            decodeInOrderDeserialize(newNode, list);
            node = list.poll();
        }
    }


    /******************** 方法2 **********************/
    // 将 方法1 改为非递归的
    // 非递归 先序遍历编码
    public TreeNode encode2(UndirectedGraphNode root) {
        if (root == null){
            return null;
        }
        Stack<UndirectedGraphNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        Stack<Integer> stack1Count = new Stack<>();

        TreeNode head = new TreeNode(root.label);
        stack1.push(root);
        stack1Count.push(0);
        stack2.push(head);

        // 非递归 递归序遍历多叉树 + 非递归 先序遍历二叉树 (这里的先序指的是生成节点的时机, 当然得是第一次访问)
        while (!stack1.isEmpty()){
            UndirectedGraphNode node = stack1.peek();
            Integer count = stack1Count.pop();
            TreeNode treeNode = stack2.peek();

            if (count < node.neighbors.size()) {
                UndirectedGraphNode next = node.neighbors.get(count);
                stack1.push(next);
                stack1Count.push(count+1);
                stack1Count.push(0);

                // 如果多叉树节点向下走, 则分成两种情况
                if (count == 0){
                    // 向第一个子节点走
                    // 此时二叉树节点向左节点走
                    treeNode.left = new TreeNode(next.label);
                    stack2.push(treeNode.left);
                }else {
                    // 向非第一个子节点走
                    // 此时二叉树节点向右节点走
                    treeNode.right = new TreeNode(next.label);
                    stack2.push(treeNode.right);
                }
            }else {
                stack1.pop();

                // 如果多叉树节点向上走, 则分成两种情况
                // 如果多叉树节点为叶子节点
                //      因为该多叉树节点可能有兄弟, 二叉树节点需要向右节点走,
                //      所以二叉树节点不动
                // 如果多叉树节点为非叶子节点
                //      因为该多叉树节点可能有兄弟, 二叉树节点需要向右节点走
                //      但此时二叉树节点并不是指向 该多叉树节点(对应的), 而是其最后一个子节点
                //      所以需要一直向上回溯, 直到二叉树节点指向 该多叉树节点(对应的)
                if (node.neighbors.size() != 0) {
                    for (int i = 0; i < node.neighbors.size(); i++) {
                        stack2.pop();
                    }
                }
            }
        }

        return head;
    }

    // 非递归 先序遍历解码
    public UndirectedGraphNode decode2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<UndirectedGraphNode> stack2 = new Stack<>();
        TreeNode last = root;

        UndirectedGraphNode head = new UndirectedGraphNode(root.val);
        stack1.push(root);
        stack2.push(head);

        while (!stack1.isEmpty()){
            TreeNode node = stack1.peek();
            if (node.left != null && last != node.left && last != node.right){
                // 二叉树向左走
                stack1.push(node.left);
                UndirectedGraphNode temp = new UndirectedGraphNode(node.left.val);
                stack2.peek().neighbors.add(temp);
                stack2.push(temp);
            }else if (node.right != null && last != node.right){
                // 二叉树向右走
                // 多叉树节点应该退回父节点, 去遍历当前结点的兄弟
                stack2.pop();
                // 当前结点添加新的子节点
                stack1.push(node.right);
                UndirectedGraphNode temp = new UndirectedGraphNode(node.right.val);
                stack2.peek().neighbors.add(temp);
                stack2.push(temp);
            }else {
                // 二叉树向上走
                if (node.right == null){
                    // 如果右节点为空, 说明当前多叉树节点没有兄弟了
                    // 需要去遍历其父节点的兄弟
                    stack2.pop();
                }
                last = stack1.pop();
            }
        }
        return head;
    }

    /******************** 方法3 **********************/
    // 左神解法
    public TreeNode encode3(UndirectedGraphNode root){
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.label);
        head.left = en(root.neighbors);
        return head;
    }
    private TreeNode en(List<UndirectedGraphNode> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (UndirectedGraphNode child : children) {
            TreeNode tNode = new TreeNode(child.label);
            if (head == null) {
                head = tNode;
            } else {
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = en(child.neighbors);
        }
        return head;
    }


    public UndirectedGraphNode decode3(TreeNode root){
        if (root == null) {
            return null;
        }
        UndirectedGraphNode head = new UndirectedGraphNode(root.val);
        head.neighbors = de(root.left);
        return head;
    }
    public ArrayList<UndirectedGraphNode> de(TreeNode root) {
        ArrayList<UndirectedGraphNode> children = new ArrayList<>();
        while (root != null) {
            UndirectedGraphNode cur = new UndirectedGraphNode(root.val);
            cur.neighbors = de(root.left);
            children.add(cur);
            root = root.right;
        }
        return children;
    }


    @Test
    public void test_encode() {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);
        UndirectedGraphNode node6 = new UndirectedGraphNode(6);
        UndirectedGraphNode node7 = new UndirectedGraphNode(7);
        UndirectedGraphNode node8 = new UndirectedGraphNode(8);
        UndirectedGraphNode node9 = new UndirectedGraphNode(9);
        UndirectedGraphNode node10 = new UndirectedGraphNode(10);

        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node4);
        node2.neighbors.add(node5);
        node2.neighbors.add(node6);
        node3.neighbors.add(node7);
        node3.neighbors.add(node8);
        node3.neighbors.add(node9);
        node3.neighbors.add(node10);

        TreeNode treeNode = encode(node1);
        PrintBinaryTree printer = new PrintBinaryTree(treeNode);
        printer.print();

        UndirectedGraphNode decode = decode(treeNode);
    }

    @Test
    public void test_encode2() {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        UndirectedGraphNode node5 = new UndirectedGraphNode(5);
        UndirectedGraphNode node6 = new UndirectedGraphNode(6);
        UndirectedGraphNode node7 = new UndirectedGraphNode(7);
        UndirectedGraphNode node8 = new UndirectedGraphNode(8);
        UndirectedGraphNode node9 = new UndirectedGraphNode(9);
        UndirectedGraphNode node10 = new UndirectedGraphNode(10);

        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node4);
        node2.neighbors.add(node5);
        node2.neighbors.add(node6);
        node3.neighbors.add(node7);
        node3.neighbors.add(node8);
        node3.neighbors.add(node9);
        node3.neighbors.add(node10);

        TreeNode treeNode = encode3(node1);
        PrintBinaryTree printer = new PrintBinaryTree(treeNode);
        printer.print(false);

        UndirectedGraphNode node = decode3(treeNode);

        printer.root = encode(node);
        printer.print(false);

    }
}
