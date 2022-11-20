package com.lzx.hsp.dataStructures.tree.tree5_binarySortTree;

import com.lzx.hsp.dataStructures.Utils.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-13 15:05:48
 */
public class BinarySortTreeTest {
    private BinarySortTree binarySortTree;

    @Before
    public void init(){
        binarySortTree = new BinarySortTree();
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
        int[] array = {7, 10, 12, 9};
        //                  7
        //                    \
        //                     10
        //                     / \
        //                    9   12
        for (int i : array) {
            binarySortTree.add(new BinarySortTreeNode(i));
        }
    }

    @Test
    public void test_levelOrderPrint() {
        int nodeNum = 10;
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < nodeNum; i++) {
            tree.add(new BinarySortTreeNode(i+1));
        }
        TreeUtils.levelOrderPrint(tree.root);
    }

    @Test
    public void test_add() {
        TreeUtils.levelOrderPrint(binarySortTree.root);
        binarySortTree.inorderTraversal();
    }

    @Test
    public void test_search() {
        binarySortTree.inorderTraversal();
        int[] find = {7, 3, 10, 12, 5, 1, 9, 10, 11, 0, 5};

        for (int i : find) {
            BinarySortTreeNode[] searchResult = binarySortTree.search(i);
            System.out.println("*********search*********");
            System.out.println("findValue = " + i);
            if (searchResult == null) {
                System.out.println("no such node");
            }else {
                BinarySortTreeNode parent = searchResult[0];
                BinarySortTreeNode node = searchResult[1];
                System.out.println("parent = " + parent);
                System.out.println("node = " + node);
            }
        }
    }

    @Test
    public void test_searchMax_searchMin() {
        binarySortTree.inorderTraversal();

        BinarySortTreeNode[] searchResult1 = binarySortTree.searchMax();
        System.out.println("*********search max*********");
        BinarySortTreeNode parent = searchResult1[0];
        BinarySortTreeNode node = searchResult1[1];
        System.out.println("parent = " + parent);
        System.out.println("node = " + node);

        BinarySortTreeNode[] searchResult2 = binarySortTree.searchMin();
        System.out.println("*********search min*********");
        parent = searchResult2[0];
        node = searchResult2[1];
        System.out.println("parent = " + parent);
        System.out.println("node = " + node);
    }

    @Test
    public void test_searchMax_searchMin_forEach() {
        binarySortTree.inorderTraversal();

        binarySortTree.inorderTraversal( binarySortTreeNode -> {
            System.out.println("****************** node " + binarySortTreeNode.value + " ******************");
            BinarySortTreeNode[] searchResult1 = binarySortTreeNode.searchMax();
            System.out.println("*********search max*********");
            BinarySortTreeNode parent = searchResult1[0];
            BinarySortTreeNode node = searchResult1[1];
            System.out.println("parent = " + parent);
            System.out.println("node = " + node);

            BinarySortTreeNode[] searchResult2 = binarySortTreeNode.searchMin();
            System.out.println("*********search min*********");
            parent = searchResult2[0];
            node = searchResult2[1];
            System.out.println("parent = " + parent);
            System.out.println("node = " + node);
        });

    }

    @Test
    public void test_delete() {
        binarySortTree.inorderTraversal();
        int[] delNodes = {7, 3, 10, 12, 5, 1, 9, 10, 11, 0, 5};

        for (int i : delNodes) {
            boolean isDelete = binarySortTree.delete(i);
            System.out.println("***************************delete***************************");
            System.out.println("findValue = " + i);
            System.out.println("isDelete = " + isDelete);
            System.out.println("*********inorderTraversal*********");
            binarySortTree.inorderTraversal();
            init();
        }
    }
}
