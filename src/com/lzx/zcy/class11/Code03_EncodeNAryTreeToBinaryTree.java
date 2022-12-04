package com.lzx.zcy.class11;

import com.lzx.utils.tree.BTNode;
import com.lzx.utils.tree.PrintBinaryTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-12-04 20:51
 * 将一棵 n叉树编码为一棵二叉树，并对二叉树进行解码，得到原始的 n 叉树。
 * n 叉树是一棵有根树，其中每个节点的子树不超过 n 个。
 * 类似地，二叉树是一棵有根树，其中每个节点的子树不超过 2 个。
 * 编码/解码算法的工作方式不受限制。
 * 您只需要确保一个n叉树可以被编码为一个二叉树，并且这个二叉树可以被解码为原始的n叉树结构。
 */
public class Code03_EncodeNAryTreeToBinaryTree {

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
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
    }


    public UndirectedGraphNode decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        inOrderDecode(root, list);
        list.forEach(System.out::println);
        UndirectedGraphNode head = new UndirectedGraphNode(list.poll().val);
        inOrderGenerate2(head, list);
        return head;
    }

    private void inOrderDecode(TreeNode root, LinkedList<TreeNode> list) {
        list.add(root);
        if (root.left != null) {
            inOrderDecode(root.left, list);
        }else {
            list.add(null);
        }
        if (root.right != null) {
            inOrderDecode(root.right, list);
        }else {
            list.add(null);
        }
    }

    private void inOrderGenerate2(UndirectedGraphNode head, LinkedList<TreeNode> list) {
        TreeNode node = list.poll();
        while (node != null){
            UndirectedGraphNode newNode = new UndirectedGraphNode(node.val);
            head.neighbors.add(newNode);
            inOrderGenerate2(newNode, list);
            node = list.poll();
        }
    }


    // 编码
    public TreeNode encode(UndirectedGraphNode root) {
        if (root == null){
            return null;
        }
        LinkedList<UndirectedGraphNode> list = new LinkedList<>();
        inOrderEncode(root, list);
        TreeNode head = new TreeNode(list.poll().label);
        inOrderGenerate1(head, list);
        return head;
    }

    private void inOrderEncode(UndirectedGraphNode root, LinkedList<UndirectedGraphNode> list) {
        list.add(root);
        for (UndirectedGraphNode neighbor : root.neighbors) {
            inOrderEncode(neighbor, list);
        }
        list.add(null);
    }

    private void inOrderGenerate1(TreeNode head, LinkedList<UndirectedGraphNode> list) {
        UndirectedGraphNode node = list.poll();
        if (node != null) {
            head.left = new TreeNode(node.label);
            inOrderGenerate1(head.left, list);
        }
        node = list.poll();
        if (node != null) {
            head.right = new TreeNode(node.label);
            inOrderGenerate1(head.right, list);
        }
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


}
