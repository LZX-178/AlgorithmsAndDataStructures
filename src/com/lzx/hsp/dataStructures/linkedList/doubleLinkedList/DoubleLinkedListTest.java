package com.lzx.hsp.dataStructures.linkedList.doubleLinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-26 9:05
 */
public class DoubleLinkedListTest {
    //创建链表
    private final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

    @Before
    public void init(){
        //进行测试
        //先创建节点
        DoubleLinkedListNode hero1 = new DoubleLinkedListNode(1, "宋江", "及时雨");
        DoubleLinkedListNode hero2 = new DoubleLinkedListNode(2, "卢俊义", "玉麒麟");
        DoubleLinkedListNode hero3 = new DoubleLinkedListNode(3, "吴用", "智多星");
        DoubleLinkedListNode hero4 = new DoubleLinkedListNode(4, "林冲", "豹子头");


        // test_addOrderByNo
//        doubleLinkedList.addOrderByNo(hero4);
        doubleLinkedList.addOrderByNo(hero4);
        doubleLinkedList.addOrderByNo(hero1);
        doubleLinkedList.addOrderByNo(hero2);
        doubleLinkedList.addOrderByNo(hero3);
    }

    @Test
    public void test_addOrderByNo() {
        doubleLinkedList.list();
    }

    @Test
    public void test_update() {

        DoubleLinkedListNode hero1 = new DoubleLinkedListNode(1, "宋江1", "及时雨1");
        DoubleLinkedListNode hero2 = new DoubleLinkedListNode(2, "卢俊义1", "玉麒麟1");
        DoubleLinkedListNode hero3 = new DoubleLinkedListNode(3, "吴用1", "智多星1");
        DoubleLinkedListNode hero4 = new DoubleLinkedListNode(4, "林冲1", "豹子头1");


        doubleLinkedList.list();
        doubleLinkedList.update(hero1);
        doubleLinkedList.list();
        doubleLinkedList.update(hero2);
        doubleLinkedList.update(hero3);
        doubleLinkedList.update(hero4);
        doubleLinkedList.list();
    }

    @Test
    public void test_delete() {
        doubleLinkedList.list();

        doubleLinkedList.delete(1);
        doubleLinkedList.list();
        doubleLinkedList.delete(4);
        doubleLinkedList.list();

    }
}
