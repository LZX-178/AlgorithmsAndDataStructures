package com.lzx.hsp.dataStructures.Utils;


import com.lzx.hsp.dataStructures.tree.tree5_binarySortTree.BinarySortTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-08-15 14:13:48
 * 改进的代码 : DataStructuresZCY\algorithmbasic2020-master\src\com\algorithmzuo\\utils\tree
 */
public class TreeUtils {
    // 层次遍历打印整颗数, 打印 非完全二叉树 会存在问题
    public static void levelOrderPrint(BinarySortTreeNode root){
        if (root == null){
            System.out.println("tree is empty");
            return;
        }
        List<List<BinarySortTreeNode>> levelOrderList = levelOrderTraversal(root);
        System.out.println("**********************levelOrderPrint**********************");
        int level = levelOrderList.size();

        // 打印树型结构
        // 计算每一层开始元素的位置 和 元素之间的间隔
        // 约定元素的最大长度
        int maxLength = 2;
        // 每一层开始元素的位置
        String[] startBlank = new String[level];
        // 元素之间的间隔
        String[] separatorBlank = new String[level];
        // 最后一层元素的 开始位置 为顶格
        int startBlankNum = 0;
        // 最后一层元素的 间隔 为 元素的最大长度+1
        int separatorBlankNum = maxLength + 1;
        // 逐层计算
        for (int i = level; i >= 1; i--) {
            startBlank[i-1] = OtherUtils.getBlank(startBlankNum);
            separatorBlank[i-1] = OtherUtils.getBlank(separatorBlankNum);
            // 上一层元素的开始位置 = 当前元素的开始位置 + 元素的最大长度 + 当前元素间隔/2
            startBlankNum = startBlankNum + maxLength + separatorBlankNum / 2;
            // 上一层元素的间隔 = 当前间隔*2 + 元素最大长度
            separatorBlankNum = separatorBlankNum * 2 + maxLength;
        }

        // 打印
        for (int i = 0; i < level; i++) {
            List<BinarySortTreeNode> nodes = levelOrderList.get(i);
            System.out.print(startBlank[i]);
            for (int j = 0; j < nodes.size(); j++) {
                BinarySortTreeNode node = nodes.get(j);
                // value 占2个输出位置，数字靠右对齐，位数不足2个则在右边补空格
                if (node != null) {
                    System.out.printf("%2d", node.value);
                }else {
                    System.out.print("  ");
                }
                System.out.print(separatorBlank[i]);
            }
            System.out.println();
        }

        // 打印结点详细信息
        for (int i = 0; i < level; i++) {
            List<BinarySortTreeNode> nodes = levelOrderList.get(i);
            System.out.println("*********** level " + (i+1) + " ***********");
            for (BinarySortTreeNode node : nodes) {
                if (node != null) {
                    System.out.println(node);
                }
            }
        }
    }

    // 层次遍历将整颗树添加进一个二维 list 中
    private static List<List<BinarySortTreeNode>> levelOrderTraversal(BinarySortTreeNode root){
        List<List<BinarySortTreeNode>> list = new ArrayList<>();
        levelOrderTraversal(root, list, 1);
        list.remove(list.size()-1);
        return list;
    }
    // 前序遍历二叉排序树
    // 对二叉树的每一层的元素而言, 在前序遍历中是有序的
    // node 为当前遍历到的元素, level 为 node 所处的层
    private static void levelOrderTraversal(BinarySortTreeNode node, List<List<BinarySortTreeNode>> list, int level){
        if (list.size() < level){
            list.add(new ArrayList<>());
        }
        // 将当前结点添加到 list 的 level 层中, null 值也当成一个节点进行添加
        list.get(level-1).add(node);
        if (node != null){
            // 向左递归
            levelOrderTraversal(node.left, list, level+1);
            // 向右递归
            levelOrderTraversal(node.right, list, level+1);
        }
    }
}
