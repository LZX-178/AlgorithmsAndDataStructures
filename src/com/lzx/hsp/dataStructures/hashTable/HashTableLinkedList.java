package com.lzx.hsp.dataStructures.hashTable;

/**
 * @author LZX
 * @code @create 2022-08-03 11:11
 */
public class HashTableLinkedList {
    // 无头结点
    public HashTableNode head;

    public void list(){
        if (head == null){
            System.out.println("empty list");
            return;
        }
        HashTableNode temp = head;
        System.out.print(temp);
        temp = temp.next;
        while (temp != null){
            System.out.print("-->" + temp );
            temp = temp.next;
        }
        System.out.println();
    }
    public void add(HashTableNode node){
        if (head == null){
            head = node;
            return;
        }
        HashTableNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }
    public void delete(int findId){
        if (head == null){
            System.out.println("empty list");
            return;
        }
        if (head.id == findId){
            head = head.next;
            return;
        }
        HashTableNode temp = head;
        while (temp.next != null && temp.next.id != findId){
            temp = temp.next;
        }
        if (temp.next == null){
            System.out.println("no such node");
        }else {
            temp.next = temp.next.next;
        }
    }
    public void update(HashTableNode node){
        if (head == null){
            System.out.println("empty list");
            return;
        }
        HashTableNode temp = head;
        while (temp != null && temp.id != node.id){
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("no such node");
        }else {
            temp.name = node.name;
        }
    }
    public HashTableNode search(int id){
        if (head == null){
            System.out.println("empty list");
            return null;
        }
        HashTableNode temp = head;
        while (temp != null && temp.id != id){
            temp = temp.next;
        }
        return temp;
    }
}
