package com.lzx.hsp.dataStructures.tree.tree2_arrayBinaryTree;

/**
 * @author LZX
 * @code @create 2022-08-11 10:00:08
 * 顺序存储二叉树, 完全二叉树可以完美地存在一个数组中
 * 需求: 给你一个数组 {1,2,3,4,5,6,7}， 要求以二叉树前序遍历的方式进行遍历。 前序遍历的结果应当为 1,2,4,5,3,6,7
 */
public class ArrayBinaryTree {
    private final int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    // 前序遍历
    public void preOrderTraversal(){
        if (array == null || array.length == 0){
            System.out.println("ArrayBinaryTree is empty");
        }else {
            preOrderTraversal(0);
        }
    }
    private void preOrderTraversal(int index){
        System.out.print(array[index] + "  ");

        int left = index*2 + 1;

        if (left < array.length){
            preOrderTraversal(left);
            int right = index*2 + 2;
            if (right < array.length){
                preOrderTraversal(right);
            }
        }
    }
}
