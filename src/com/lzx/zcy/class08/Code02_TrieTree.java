package com.lzx.zcy.class08;

import com.lzx.utils.*;

import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-09-12 9:37:27
 *
 * 字典树, 也叫前缀树
 *
 */
public class Code02_TrieTree {

    private static class Node{
        // 记录有多少个字符串经过了该结点
        public int pass;
        // 记录有多少个字符串以该结点结尾
        public int end;
        // 记录该节点的 26 条路是否存在
        public Node[] next = new Node[26];

        public Node() {
        }
    }


    public static class TrieTree{
        private Node root = new Node();

        // 将一个字符串插入字典树
        public void insert(String word){
            if (StringUtils.isEmptyString(word)){
                return;
            }

            char[] chars = word.toCharArray();

            Node temp = root;
            temp.pass++;

            // 遍历所有字符
            for (char aChar : chars) {
                int index = aChar - 'a';
                // 若当前字符对应的路不存在, 则创建之
                if (temp.next[index] == null) {
                    temp.next[index] = new Node();
                }
                temp = temp.next[index];
                temp.pass++;
            }

            temp.end++;
        }

        // 查找一个单词加入过几次
        public int search(String word){
            if (StringUtils.isEmptyString(word)){
                return 0;
            }

            char[] chars = word.toCharArray();

            Node temp = root;

            for (char aChar : chars) {
                int index = aChar - 'a';
                if (temp.next[index] == null){
                    return 0;
                }
                temp = temp.next[index];
            }
            return temp.end;
        }

        // 删除一个单词,
        // 注意, 当一个节点的 pass 为 0 时, 将指向该节点的指针置空
        public void delete(String word){
            if (search(word) == 0){
                return;
            }
            char[] chars = word.toCharArray();

            Node temp = root;

            temp.pass--;

            for (char aChar : chars) {
                int index = aChar - 'a';
                if (--temp.next[index].pass == 0){
                    temp.next[index] = null;
                    return;
                }
                temp = temp.next[index];
            }
            temp.end--;
        }

        // 查询 有多少个字符串是以 pre 作为前缀的
        public int prefixNumber(String pre){
            if (StringUtils.isEmptyString(pre)){
                return 0;
            }

            char[] chars = pre.toCharArray();

            Node temp = root;

            for (char aChar : chars) {
                int index = aChar - 'a';
                if (temp.next[index] == null){
                    return 0;
                }
                temp = temp.next[index];
            }
            return temp.pass;
        }

    }

    // 对数器
    public static class RightTrieTree {

        private HashMap<String, Integer> box;

        public RightTrieTree() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }
}
