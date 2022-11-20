package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;


/**
 * @author LZX
 * @code @create 2022-08-12 16:31:18
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
    public int weight;
    public Character value;
    public HuffmanTreeNode left;
    public HuffmanTreeNode right;

    public HuffmanTreeNode next;

    // 前序遍历
    public void preOrderTraversal(){
        System.out.println(this);

        if (left != null) {
            left.preOrderTraversal();
        }

        if (right != null) {
            right.preOrderTraversal();
        }
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "weight=" + weight +
                ", value='" + value + '\'' +
                '}';
    }

    public HuffmanTreeNode(int weight, Character value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return weight - o.weight;
    }
}
