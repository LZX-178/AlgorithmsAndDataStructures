package com.lzx.hsp.dataStructures.tree.tree6_AVLTree;

/**
 * @author LZX
 * @code @create 2022-08-14 12:14:35
 * Adelson-Velskii and Landis 数是带有平衡条件的二叉搜索树 : 左右子树高度相差最多为 1
 */
public class AVLTree {
    public AVLTreeNode root;

    // 添加节点
    public static AVLTreeNode add(AVLTreeNode root, int value){
        if (root == null){
            root = new AVLTreeNode(value);
            root.height = 0;
            return root;
        }else if (value < root.value){
            root.left = add((AVLTreeNode) root.left, value);

            if (getTreeHeight((AVLTreeNode)root.left) - getTreeHeight((AVLTreeNode)root.right) == 2){
                if (value < root.left.value){
                    root = SingleRotateLeftLeft(root);
                }else {
                    root = DoubleRotateLeftRight(root);
                }
            }
        }else if (value > root.value){
            root.right = add((AVLTreeNode) root.right, value);

            if (getTreeHeight((AVLTreeNode)root.right) - getTreeHeight((AVLTreeNode)root.left) == 2){
                if (value > root.right.value){
                    root = SingleRotateRightRight(root);
                }else {
                    root = DoubleRotateRightLeft(root);
                }
            }
        }
        root.height = Math.max(getTreeHeight((AVLTreeNode) root.left), getTreeHeight((AVLTreeNode) root.right)) + 1;
        return root;
    }

    // 返回一棵树的高度 约定 : 左右子树为空的结点高度为 0
    public static int getTreeHeight(AVLTreeNode root){
        if (root == null){
            return -1;
        }
        return root.height;
    }

    // 左右旋转 : 对 node 的 左子树 进行右右旋转, 再对 node 进行左左旋转
    public static AVLTreeNode DoubleRotateLeftRight(AVLTreeNode node){
        node.left = SingleRotateRightRight((AVLTreeNode) node.left);
        return SingleRotateLeftLeft(node);
    }
    // 右左旋转 : 对 node 的 右子树 进行左左旋转, 再对 node 进行右右旋转
    public static AVLTreeNode DoubleRotateRightLeft(AVLTreeNode node){
        node.right = SingleRotateLeftLeft((AVLTreeNode)node.right);
        return SingleRotateRightRight(node);
    }

    // 左左旋转 :
    //      当 node结点左子树 的高度 减 右子树 高度大于 1 时,
    //      将 node 的左子节点旋转为新的根节点并返回, node 作为新的根节点右子树
    // 约定 : node非空, node.left 非空
    public static  AVLTreeNode SingleRotateLeftLeft(AVLTreeNode node){
        AVLTreeNode newRoot = (AVLTreeNode)node.left;
        // 旋转操作
        node.left = newRoot.right;
        newRoot.right = node;

        // 维护高度, node 和 node.left 下的三颗子树的高度都不需要维护
        node.height = Math.max(getTreeHeight((AVLTreeNode) node.left), getTreeHeight((AVLTreeNode) node.right)) + 1;
        newRoot.height = Math.max(node.height, getTreeHeight((AVLTreeNode) newRoot.left)) + 1;

        return newRoot;
    }
    // 右右旋转 :
    //      当 node结点右子树 的高度 减 左子树 高度大于 1 时,
    //      将 node 的右子节点旋转为新的根节点并返回, node 作为新的根节点左子树
    // 约定 : node非空, node.right 非空
    public static  AVLTreeNode SingleRotateRightRight(AVLTreeNode node){
        AVLTreeNode newRoot = (AVLTreeNode)node.right;
        // 旋转操作
        node.right = newRoot.left;
        newRoot.left = node;

        // 维护高度, node 和 node.right 下的三颗子树的高度都不需要维护
        node.height = Math.max(getTreeHeight((AVLTreeNode) node.left), getTreeHeight((AVLTreeNode) node.right)) + 1;
        newRoot.height = Math.max(node.height, getTreeHeight((AVLTreeNode) newRoot.right)) + 1;

        return newRoot;
    }

    // 中序遍历
    public void inorderTraversal(){
        System.out.println("**********************inorderTraversal**********************");
        if (root == null) {
            System.out.println("tree is empty");
            return;
        }
        root.inorderTraversal();
    }

}
