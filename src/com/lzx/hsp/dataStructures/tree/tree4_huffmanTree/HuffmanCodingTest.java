package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-08-12 18:18:14
 */
public class HuffmanCodingTest {
    @Test
    public void test_encode() {
        HuffmanCoding huffmanCoding = new HuffmanCoding();

        String[] strings = {
                            "i like like like java do you like a java",
                            "i like like like like like like java do you like a java xxxxxxxxxxxxxxxxxxxxxx"
                            };

        for (String str : strings) {
            System.out.println("str = " + str);
            System.out.println("str.length() = " + str.length()*8);

            HashMap<Character, String> codeTable = huffmanCoding.createCodeTable(str);
            System.out.println("codeTable = " + codeTable);

            String encodeStr = huffmanCoding.encode(str, codeTable);
            System.out.println("encodeStr.length() = " + encodeStr.length());

            String decodeStr = huffmanCoding.decode(encodeStr, codeTable);
            System.out.println("decodeStr = " + decodeStr);
            System.out.println("decodeStr.length() = " + decodeStr.length()*8);

            System.out.println("***************************");
        }
    }
}
