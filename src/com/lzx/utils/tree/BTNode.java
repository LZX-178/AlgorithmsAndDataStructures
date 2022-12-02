package com.lzx.utils.tree;


public interface BTNode {
        BTNode getLeft();
        BTNode getRight();
        String getValue();

        // 如果需要随机生成二叉树的话
        //      需要重写 set 方法
        //      需要有空参构造器
        //      权限修饰符足够大
        default void setLeft(BTNode left){

        }
        default void setRight(BTNode right){

        }
        default void setValue(Integer value){

        }
}