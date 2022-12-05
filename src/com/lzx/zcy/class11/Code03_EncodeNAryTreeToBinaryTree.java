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


    // 非递归 先序遍历编码
    public TreeNode encode2(UndirectedGraphNode root) {
        if (root == null){
            return null;
        }
        Stack<UndirectedGraphNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        HashMap<UndirectedGraphNode, Integer> map = new HashMap<>();

        TreeNode head = new TreeNode(root.label);
        stack1.push(root);
        stack2.push(head);

        // 非递归 递归序遍历多叉树 + 非递归 先序遍历二叉树 (这里的先序指的是生成节点的时机, 当然得是第一次访问)
        while (!stack1.isEmpty()){
            UndirectedGraphNode node = stack1.peek();
            TreeNode treeNode = stack2.peek();

            Integer count = map.get(node);
            count = count == null ? 0 : count;
            if (count < node.neighbors.size()) {
                UndirectedGraphNode next = node.neighbors.get(count);
                stack1.push(next);
                map.put(node, count+1);

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
        HashMap<TreeNode, Integer> map = new HashMap<>();

        UndirectedGraphNode head = new UndirectedGraphNode(root.val);
        stack1.push(root);
        stack2.push(head);

        int num = 0;

        while (!stack1.isEmpty() && !stack2.isEmpty()){
            TreeNode node = stack1.peek();
            UndirectedGraphNode graphNode = stack2.peek();


            Integer count = map.get(node);
            if (count == null){
                map.put(node, 1);
                if (node.left == null){
                    // 二叉树左节点为空, 需要向右走
                    // 说明多叉树节点应该退回父节点
                    stack2.pop();
                }else {
                    stack1.push(node.left);
                    UndirectedGraphNode temp = new UndirectedGraphNode(node.left.val);
                    graphNode.neighbors.add(temp);
                    stack2.push(temp);
                }
            }else if (count == 1){
                map.put(node, 2);
                if (node.right != null){
                    // 二叉树向右走
                    // 当前结点添加新的子节点
                    stack1.push(node.right);
                    UndirectedGraphNode temp = new UndirectedGraphNode(node.right.val);
                    graphNode.neighbors.add(temp);
                    stack2.push(temp);
                }
            }else {
                stack1.pop();
                num++;
                if (num == graphNode.neighbors.size()){
                    num = 0;
                    stack2.pop();
                }
            }
        }

        return head;
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

        TreeNode treeNode = encode2(node1);
        PrintBinaryTree printer = new PrintBinaryTree(treeNode);
        printer.print();

        UndirectedGraphNode node = decode2(treeNode);

        TreeNode encode = encode(node);
        printer.root = encode;
        printer.print();

    }
}
