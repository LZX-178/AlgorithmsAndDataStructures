package com.lzx.hsp.dataStructures.linkedList.singleLinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-25 14:56
 */
public class SingleLinkedListTest {
    //创建链表
    private final SingleLinkedList singleLinkedList = new SingleLinkedList();

    @Before
    public void init(){
        //进行测试
        //先创建节点
        SingleLinkedListNode hero1 = new SingleLinkedListNode(1, "宋江", "及时雨");
        SingleLinkedListNode hero2 = new SingleLinkedListNode(2, "卢俊义", "玉麒麟");
        SingleLinkedListNode hero3 = new SingleLinkedListNode(3, "吴用", "智多星");
        SingleLinkedListNode hero4 = new SingleLinkedListNode(4, "林冲", "豹子头");

        //test_add
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        // test_addOrderByNo
//        singleLinkedList.addOrderByNo(hero4);
        singleLinkedList.addOrderByNo(hero4);
        singleLinkedList.addOrderByNo(hero1);
        singleLinkedList.addOrderByNo(hero2);
        singleLinkedList.addOrderByNo(hero3);
    }

    @Test
    public void test_add(){
        singleLinkedList.list();
    }

    @Test
    public void test_addOrderByNo() {
        singleLinkedList.list();
    }

    @Test
    public void test_update() {

        SingleLinkedListNode hero1 = new SingleLinkedListNode(1, "宋江1", "及时雨1");
        SingleLinkedListNode hero2 = new SingleLinkedListNode(2, "卢俊义1", "玉麒麟1");
        SingleLinkedListNode hero3 = new SingleLinkedListNode(3, "吴用1", "智多星1");
        SingleLinkedListNode hero4 = new SingleLinkedListNode(4, "林冲1", "豹子头1");


        singleLinkedList.list();
        singleLinkedList.update(hero1);
        singleLinkedList.list();
        singleLinkedList.update(hero2);
        singleLinkedList.update(hero3);
        singleLinkedList.update(hero4);
        singleLinkedList.list();
    }

    @Test
    public void test_delete() {
        singleLinkedList.list();

        singleLinkedList.delete(1);
        singleLinkedList.list();
        singleLinkedList.delete(4);
        singleLinkedList.list();

    }

    @Test
    public void test_getLength() {
        int length = singleLinkedList.getLength();
        System.out.println("length = " + length);
        singleLinkedList.delete(4);
        length = singleLinkedList.getLength();
        System.out.println("length = " + length);
    }

    @Test
    public void test_findIndexNode() {
        singleLinkedList.list();
        SingleLinkedListNode indexNode0 = singleLinkedList.findIndexNode(0);
        SingleLinkedListNode indexNode1 = singleLinkedList.findIndexNode(1);
        SingleLinkedListNode indexNode2 = singleLinkedList.findIndexNode(2);
        SingleLinkedListNode indexNode3 = singleLinkedList.findIndexNode(3);
        SingleLinkedListNode indexNode4 = singleLinkedList.findIndexNode(4);
        SingleLinkedListNode indexNode5 = singleLinkedList.findIndexNode(5);

        System.out.println("indexNode0 = " + indexNode0);
        System.out.println("indexNode1 = " + indexNode1);
        System.out.println("indexNode2 = " + indexNode2);
        System.out.println("indexNode3 = " + indexNode3);
        System.out.println("indexNode4 = " + indexNode4);
        System.out.println("indexNode5 = " + indexNode5);
    }

    @Test
    public void test_findLastIndexNode() {
        singleLinkedList.list();
        SingleLinkedListNode indexNode0 = singleLinkedList.findLastIndexNode(0);
        SingleLinkedListNode indexNode1 = singleLinkedList.findLastIndexNode(1);
        SingleLinkedListNode indexNode2 = singleLinkedList.findLastIndexNode(2);
        SingleLinkedListNode indexNode3 = singleLinkedList.findLastIndexNode(3);
        SingleLinkedListNode indexNode4 = singleLinkedList.findLastIndexNode(4);
        SingleLinkedListNode indexNode5 = singleLinkedList.findLastIndexNode(5);

        System.out.println("indexNode0 = " + indexNode0);
        System.out.println("indexNode1 = " + indexNode1);
        System.out.println("indexNode2 = " + indexNode2);
        System.out.println("indexNode3 = " + indexNode3);
        System.out.println("indexNode4 = " + indexNode4);
        System.out.println("indexNode5 = " + indexNode5);
    }

    @Test
    public void test_rollbackList() {
        singleLinkedList.list();
        singleLinkedList.rollbackList();
        singleLinkedList.list();
        singleLinkedList.rollbackList();
        singleLinkedList.list();

        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
        singleLinkedList.list();
        singleLinkedList.rollbackList();
        singleLinkedList.list();
    }
    @Test
    public void test_rollbackList2() {
        singleLinkedList.list();
        singleLinkedList.rollbackList2();
        singleLinkedList.list();
        singleLinkedList.rollbackList2();
        singleLinkedList.list();

        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
        singleLinkedList.list();
        singleLinkedList.rollbackList2();
        singleLinkedList.list();
    }
}
