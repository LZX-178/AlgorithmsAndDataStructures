package com.lzx.hsp.dataStructures.tree.tree2_arrayBinaryTree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-11 10:00:25
 */
public class ArrayBinaryTreeTest {
    @Test
    public void test_preOrderTraversal() {
        int length = 7;
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrderTraversal();
    }
}
