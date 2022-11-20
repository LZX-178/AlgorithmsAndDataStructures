package com.lzx.hsp.dataStructures.tree.tree4_huffmanTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-08-12 18:18:00
 */
public class HuffmanCoding {

    // 根据码表对字符串进行编码
    public String encode(String str, HashMap<Character, String> codeTable){
        StringBuilder newStr = new StringBuilder();
        for (char c : str.toCharArray()) {
            newStr.append(codeTable.get(c));
        }
        return newStr.toString();
    }
    // 根据码表对字符串进行解码
    public String decode(String str, HashMap<Character, String> codeTable){
        HashMap<String, Character> newCodeTable = new HashMap<>();
        for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
            newCodeTable.put(entry.getValue(), entry.getKey());
        }

        StringBuilder newStr = new StringBuilder();
        StringBuilder tempKey = new StringBuilder();
        Character tempValue = null;
        for (char c : str.toCharArray()) {
            tempKey.append(c);
            if ((tempValue = newCodeTable.get(tempKey.toString())) != null){
                newStr.append(tempValue);
                tempKey.delete(0, tempKey.length());
            }
        }
        if (tempKey.length() > 0){
            throw new RuntimeException("decode error");
        }
        return newStr.toString();
    }
    // 生成码表
    public HashMap<Character, String> createCodeTable(String str){
        // 对字符串统计词频
        HashMap<Character, Integer> nodes = getNodesMap(str);
        // 再生成霍夫曼树
        HuffmanTree huffmanTree = HuffmanTree.createHuffmanTree(nodes);
        // 对霍夫曼树进行前序遍历编码
        HashMap<Character, String> codeTable = new HashMap<>();
        StringBuilder huffmanCode = new StringBuilder();
        assert huffmanTree != null;
        preOrderCreateCodeTable(huffmanTree.root, codeTable, huffmanCode);

        return codeTable;
    }
    // 对霍夫曼树进行前序遍历编码
    private void preOrderCreateCodeTable(HuffmanTreeNode node, HashMap<Character, String> codeTable, StringBuilder huffmanCode) {
        if (node.value != null){
            codeTable.put(node.value, huffmanCode.toString());
        }

        if (node.left != null){
            huffmanCode.append('0');
            preOrderCreateCodeTable(node.left, codeTable, huffmanCode);
        }
        if (node.right != null){
            huffmanCode.append('1');
            preOrderCreateCodeTable(node.right, codeTable, huffmanCode);
        }

        if (huffmanCode.length() > 0){
            huffmanCode.deleteCharAt(huffmanCode.length()-1);
        }
    }
    // 统计字符串的字符频次
    private HashMap<Character, Integer> getNodesMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        return map;
    }
}
