package com.lzx.hsp.dataStructures.hashTable;

/**
 * @author LZX
 * @code @create 2022-08-03 11:11
 */
public class HashTableNode {
    public int id;
    public String name;
    public HashTableNode next;

    public HashTableNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + name + "}";
    }
}
