package com.lzx.hsp.dataStructures.linkedList.singleCircleLinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-26 9:54
 */
public class SingleCircleLinkedListTest {
    //创建链表
    private final SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList();

    @Before
    public void init(){
        //进行测试
        //先创建节点
        SingleCircleLinkedListNode hero1 = new SingleCircleLinkedListNode(1, "宋江", "及时雨");
        SingleCircleLinkedListNode hero2 = new SingleCircleLinkedListNode(2, "卢俊义", "玉麒麟");
        SingleCircleLinkedListNode hero3 = new SingleCircleLinkedListNode(3, "吴用", "智多星");
        SingleCircleLinkedListNode hero4 = new SingleCircleLinkedListNode(4, "林冲", "豹子头");
        SingleCircleLinkedListNode hero5 = new SingleCircleLinkedListNode(5, "公孙胜", "入云龙");


        //test_add
        singleCircleLinkedList.add(hero1);
        singleCircleLinkedList.add(hero2);
        singleCircleLinkedList.add(hero3);
        singleCircleLinkedList.add(hero4);
        singleCircleLinkedList.add(hero5);
    }

    @Test
    public void test_add() {
        singleCircleLinkedList.list();
    }

    @Test
    public void test_josepfu() {
        singleCircleLinkedList.list();
        singleCircleLinkedList.josepfu(1, 2);
    }
}
