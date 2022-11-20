package com.lzx.leetCode.tree.trieTree;

/**
 * @author LZX
 * @code @create 2022-09-12 14:17:59
 * 剑指 Offer II 062. 实现前缀树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 */
public class Code_offer_62_Trie {
    private static class Trie {

        private static class TrieNode{
            private boolean end;
            private final TrieNode[] next = new TrieNode[26];
        }

        private final TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode temp = root;
            for (char aChar : word.toCharArray()) {
                int index = aChar - 'a';
                if (temp.next[index] == null){
                    temp.next[index] = new TrieNode();
                }
                temp = temp.next[index];
            }
            temp.end = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode temp = root;
            for (char aChar : word.toCharArray()) {
                int index = aChar - 'a';
                if (temp.next[index] == null){
                    return false;
                }
                temp = temp.next[index];
            }
            return temp.end;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode temp = root;
            for (char aChar : prefix.toCharArray()) {
                int index = aChar - 'a';
                if (temp.next[index] == null){
                    return false;
                }
                temp = temp.next[index];
            }
            return true;
        }
    }
}
