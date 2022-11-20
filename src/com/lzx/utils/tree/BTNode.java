package com.lzx.utils.tree;

public interface BTNode {
        BTNode getLeft();
        void setLeft(BTNode left);
        BTNode getRight();
        void setRight(BTNode right);
        String getValue();
        void setValue(Integer value);
}