package com.lzx.zcy.class11;

import com.lzx.utils.tree.BTNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LZX
 * @code @create 2022-10-21 15:27:15
 * 层次遍历二叉树
 */
public class Code01_LevelTraversalBT {
    private static class Node implements BTNode {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }

        @Override
        public Node getLeft() {
            return left;
        }

        @Override
        public void setLeft(BTNode left) {
            this.left = (Node) left;
        }

        @Override
        public Node getRight() {
            return right;
        }

        @Override
        public void setRight(BTNode right) {
            this.right = (Node) right;
        }

        @Override
        public String getValue() {
            return value + "";
        }

        @Override
        public void setValue(Integer value) {
            this.value = value;
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
        System.out.println("***** " + "levelTraversal1" + " *****");
        levelTraversal1(head);
        System.out.println("\n***** " + "levelTraversal2" + " *****");
        levelTraversal2(head);
    }

    // 层次遍历 1
    // 使用队列
    public void levelTraversal1(Node node){
        if (node == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // flag 指向每一层的第一个元素
        Node flag = node;

        while (!queue.isEmpty()){
            node = queue.poll();

            // 若当前元素为某一层的第一个元素, 则下一个加入队列的元素一定是下一层的第一个元素
            if (node == flag){
                flag = null;
                System.out.println();
            }
            System.out.print(" " + node.value);

            if (node.left != null){
                queue.add(node.left);
                if (flag == null){
                    flag = node.left;
                }
            }
            if (node.right != null){
                queue.add(node.right);
                if (flag == null){
                    flag = node.right;
                }
            }
        }
    }

    // 层次遍历 2
    // 先进行一次先序遍历, 将二叉树放入一个二维 List 中
    // 对二叉树的每一层的元素而言, 在前序遍历中是有序的
    public void levelTraversal2(Node node) {
        if (node == null) {
            return;
        }
        List<List<Node>> levelTree = new ArrayList<>();
        levelTraversal2(node, levelTree, 1);


        for (List<Node> nodes : levelTree) {
            for (Node val : nodes) {
                System.out.print(" " + val.value);
            }
            System.out.println();
        }
    }
    private void levelTraversal2(Node node, List<List<Node>> levelTree, int level) {
        if (levelTree.size() < level){
            levelTree.add(new ArrayList<>());
        }
        levelTree.get(level-1).add(node);

        if (node.left != null) {
            levelTraversal2(node.left, levelTree, level+1);
        }
        if (node.right != null) {
            levelTraversal2(node.right, levelTree, level+1);
        }
    }

}
