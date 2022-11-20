package com.lzx.hsp.dataStructures.hashTable;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-03 11:11
 */
public class HashTableTest {
    @Test
    public void test_HashTable() {
        HashTable hashTable = new HashTable(5);
        // 测试 添加 和 遍历
        for (int i = 0; i < 20; i++) {
            HashTableNode node = new HashTableNode(i, "emp" + i);
            hashTable.add(node);
        }
        hashTable.list();
        // 测试 删除
//        System.out.println("delete 2");
//        hashTable.delete(2);
//        hashTable.list();
//        System.out.println("delete 2");
//        hashTable.delete(2);
//        hashTable.list();
//        System.out.println("delete 1");
//        hashTable.delete(1);
//        hashTable.list();
//        System.out.println("delete 10");
//        hashTable.delete(10);
//        hashTable.list();
//        System.out.println("delete 22");
//        hashTable.delete(22);
//        hashTable.list();
        // 测试 修改
        HashTableNode emp1 = new HashTableNode(1, "emp-1");
        HashTableNode emp2 = new HashTableNode(2, "emp-2");
        HashTableNode emp3 = new HashTableNode(3, "emp-3");
        HashTableNode emp10 = new HashTableNode(10, "emp-10");
        HashTableNode emp11 = new HashTableNode(11, "emp-11");
        HashTableNode emp22 = new HashTableNode(22, "emp-22");
        System.out.println("update 1 2 3 10 11 22");
        hashTable.update(emp1);
        hashTable.update(emp2);
        hashTable.update(emp3);
        hashTable.update(emp10);
        hashTable.update(emp11);
        hashTable.update(emp22);
        hashTable.list();
        // 测试 查找
        HashTableNode node1 = hashTable.search(1);
        System.out.println("node1 = " + node1);
        HashTableNode node2 = hashTable.search(2);
        System.out.println("node2 = " + node2);
        HashTableNode node3 = hashTable.search(3);
        System.out.println("node3 = " + node3);
        HashTableNode node22 = hashTable.search(22);
        System.out.println("node22 = " + node22);
    }

    @Test
    public void test_HashTableLinkedList() {
        HashTableLinkedList linkedList = new HashTableLinkedList();

        // 测试 添加 和 遍历
        for (int i = 1; i < 11; i++) {
            HashTableNode node = new HashTableNode(i, "emp" + i);
            linkedList.add(node);
        }
        linkedList.list();

        // 测试 删除
//        linkedList.delete(2);
//        linkedList.list();
//        linkedList.delete(2);
//        linkedList.list();
//        linkedList.delete(1);
//        linkedList.list();
//        linkedList.delete(10);
//        linkedList.list();

        // 测试 修改
//        HashTableNode emp1 = new HashTableNode(1, "emp-1");
//        HashTableNode emp2 = new HashTableNode(2, "emp-2");
//        HashTableNode emp3 = new HashTableNode(3, "emp-3");
//        HashTableNode emp10 = new HashTableNode(10, "emp-10");
//        HashTableNode emp11 = new HashTableNode(11, "emp-11");
//        linkedList.update(emp1);
//        linkedList.update(emp2);
//        linkedList.update(emp3);
//        linkedList.update(emp10);
//        linkedList.update(emp11);
//        linkedList.list();

        // 测试 查找
        HashTableNode node1 = linkedList.search(1);
        System.out.println("node1 = " + node1);
        HashTableNode node2 = linkedList.search(2);
        System.out.println("node2 = " + node2);
        HashTableNode node3 = linkedList.search(3);
        System.out.println("node3 = " + node3);
        HashTableNode node22 = linkedList.search(22);
        System.out.println("node22 = " + node22);
    }
}
