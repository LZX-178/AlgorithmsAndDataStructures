package com.lzx.hsp.dataStructures.hashTable;

/**
 * @author LZX
 * @code @create 2022-08-03 11:10
 */
public class HashTable {
    private final HashTableLinkedList[] linkedListArray;
    private final int size;

    public HashTable(int size) {
        this.size = size;
        linkedListArray = new HashTableLinkedList[size];
        for (int i = 0; i < linkedListArray.length; i++) {
            linkedListArray[i] = new HashTableLinkedList();
        }
    }
    public void add(HashTableNode node){
        int hashValue = hashFunction(node.id);
        linkedListArray[hashValue].add(node);
    }
    public void delete(int findId){
        int hashValue = hashFunction(findId);
        linkedListArray[hashValue].delete(findId);
    }
    public void update(HashTableNode node){
        int hashValue = hashFunction(node.id);
        linkedListArray[hashValue].update(node);
    }
    public HashTableNode search(int findId){
        int hashValue = hashFunction(findId);
        return linkedListArray[hashValue].search(findId);
    }
    public void list(){
        for (int i = 0; i < linkedListArray.length; i++) {
            System.out.print("linkedList[" + i + "] = ");
            linkedListArray[i].list();
        }
    }
    public int hashFunction(int id){
        return id % size;
    }
}
