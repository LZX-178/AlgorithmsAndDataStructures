package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-12 16:31:24
 */
public class HuffmanTreeTest {
    public final int[] array = {13, 7, 8, 3, 29, 6, 1};

    @Test
    public void test_HuffmanTree() {
        HuffmanTree huffmanTree = HuffmanTree.createHuffmanTree(array);
        assert huffmanTree != null;
        huffmanTree.preOrderTraversal();
    }
}
