package com.lzx.hsp.dataStructures.tree.tree6_AVLTree;

import com.lzx.hsp.dataStructures.Utils.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-14 12:14:55
 */
public class AVLTreeTest {
    private AVLTree avlTree;

    @Before
    public void init(){
        avlTree = new AVLTree();
//        int[] array = {7, 3, 10, 12, 5, 1, 9};
        //                  7
        //                /   \
        //              3      10
        //             / \     / \
        //            1   5   9   12
//        int[] array = {7, 3, 5, 1};
        //                  7
        //                /
        //              3
        //             / \
        //            1   5
//        int[] array = {7, 10, 12, 9};
        //                  7
        //                    \
        //                     10
        //                     / \
        //                    9   12
        int size = 32;
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        for (int i : array) {
            avlTree.root = AVLTree.add(avlTree.root, i);
        }
    }


    @Test
    public void test_add() {
        TreeUtils.levelOrderPrint(avlTree.root);
        avlTree.inorderTraversal();
    }
}
