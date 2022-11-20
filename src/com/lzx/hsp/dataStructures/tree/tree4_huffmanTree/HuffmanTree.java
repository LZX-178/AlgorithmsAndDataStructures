package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-08-12 16:26:43
 * 给定 n 个权值作为 n 个叶子结点， 构造一棵二叉树， 若该树的带权路径长度(wpl)达到最小，
 * 称这样的二叉树为最优二叉树， 也称为哈夫曼树(Huffman Tree)
 * 树的带权路径长度：
 *      树的带权路径长度规定为所有叶子结点的带权路径长度之和，
 *      记为 WPL(weighted path length) ,
 *      权值越大的结点离根结点越近的二叉树才是最优二叉树。
 */
public class HuffmanTree {
    public HuffmanTreeNode root;

    // 生成霍夫曼树
    public static HuffmanTree createHuffmanTree(int[] array){
        if (array == null || array.length < 1){
            System.out.println("array error");
            return null;
        }

        // 创建霍夫曼树的链表
        HuffmanTreeNodeLinkedList linkedList = new HuffmanTreeNodeLinkedList();
        for (int j : array) {
            HuffmanTreeNode node = new HuffmanTreeNode(j, (char) j);
            linkedList.add(node);
        }
        // 从链表取出两个霍夫曼树, 组合成一个新的霍夫曼树, 并加入链表
        while (linkedList.head.next != null){
            HuffmanTreeNode node1 = linkedList.get();
            HuffmanTreeNode node2 = linkedList.get();

            HuffmanTreeNode newNode = new HuffmanTreeNode(node1.weight + node2.weight, null);
            if (node1.compareTo(node2) < 0){
                newNode.left = node1;
                newNode.right = node2;
            }else {
                newNode.left = node2;
                newNode.right = node1;
            }
            linkedList.add(newNode);
        }
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.root = linkedList.get();
        return huffmanTree;
    }

    // 生成霍夫曼树
    public static HuffmanTree createHuffmanTree(HashMap<Character, Integer> nodes){
        if (nodes == null || nodes.size() < 1){
            System.out.println("array error");
            return null;
        }

        // 创建霍夫曼树的链表
        HuffmanTreeNodeLinkedList linkedList = new HuffmanTreeNodeLinkedList();
        for (Map.Entry<Character, Integer> entry : nodes.entrySet()) {
            Character value = entry.getKey();
            Integer weight = entry.getValue();
            HuffmanTreeNode node = new HuffmanTreeNode(weight, value);
            linkedList.add(node);
        }

        // 从链表取出两个霍夫曼树, 组合成一个新的霍夫曼树, 并加入链表
        while (linkedList.head.next != null){
            HuffmanTreeNode node1 = linkedList.get();
            HuffmanTreeNode node2 = linkedList.get();

            HuffmanTreeNode newNode = new HuffmanTreeNode(node1.weight + node2.weight, null);
            if (node1.compareTo(node2) < 0){
                newNode.left = node1;
                newNode.right = node2;
            }else {
                newNode.left = node2;
                newNode.right = node1;
            }
            linkedList.add(newNode);
        }
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.root = linkedList.get();
        return huffmanTree;
    }

    // 前序遍历
    public void preOrderTraversal(){
        if (root != null) {
            root.preOrderTraversal();
        }else {
            System.out.println("binary tree is empty");
        }
    }
}
