package com.lzx.hsp.dataStructures.tree.tree6_AVLTree;

import com.lzx.hsp.dataStructures.tree.tree5_binarySortTree.BinarySortTreeNode;

/**
 * @author LZX
 * @code @create 2022-08-14 12:14:50
 */
public class AVLTreeNode extends BinarySortTreeNode {
    public int height;

    public AVLTreeNode(int value) {
        super(value);
    }

    // 计算当前结点的高度
    public int getHeight(){
        return Math.max(left == null ? 0 : ((AVLTreeNode)left).getHeight(),
                        right == null ? 0 : ((AVLTreeNode)right).getHeight()) + 1;
    }
    // 计算左子树的高度
    public int getLeftHeight(){
        if (left == null){
            return 0;
        }
        return ((AVLTreeNode)left).getHeight();
    }
    // 计算右子树的高度
    public int getRightHeight(){
        if (right == null){
            return 0;
        }
        return ((AVLTreeNode)right).getHeight();
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                ", left=" + (left == null ? null : left.value) +
                ", right=" + (right == null ? null : right.value) +
                '}';
    }
}
