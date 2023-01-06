package com.lzx.zcy.class24;


import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-04 19:37:05
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 树中节点数的范围在 [0, 105] 内
 */
public class Code02_MinDepth {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 方法1 : 递归
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null){
            minDepth = Math.min(minDepth, minDepth1(root.left));
        }
        if (root.right != null){
            minDepth = Math.min(minDepth, minDepth1(root.right));
        }
        return minDepth+1;
    }

    // 方法2 : Morris遍历
    // 引理 :
    //      所有叶子节点一定是某颗子树( 记为t )的最右边界(mostRight)
    // 又因为所有 mostRight 访问完成后会去访问 t 的左父亲 (除了t为根节点的情况),
    // 所以只需要 在访问某个节点第二次时, 去检验其左子树的 mostRight,
    // 如果该 mostRight 为叶子节点, 记录其高度即可
    //      注意 : 最后还需要去检验 根节点的最右边界(mostRight)
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int curDepth = 1, minDepth = Integer.MAX_VALUE;
        TreeNode cur = root, mostRight;
        int temp;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight == null){
                cur = cur.right;
                // 向下移动, 高度递增
                curDepth++;
            }else {
                temp = 0;
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                    temp++;
                }
                if (mostRight.right == null){
                    // 第一次访问 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    // 向下移动, 高度递增
                    curDepth++;
                }else {
                    // 第二次访问 cur,
                    // 说明上一个访问的节点是 cur 左子树的 mostRight,
                    // curDepth-- 后得到 mostRight 的高度
                    curDepth--;
                    if (mostRight.left == null){
                        minDepth = Math.min(minDepth, curDepth);
                    }
                    mostRight.right = null;
                    cur = cur.right;
                    // 计算 cur 右节点的高度
                    curDepth -= temp;
                }
            }
        }

        // 检验 根节点的最右边界(mostRight)
        temp = 1;
        while (root.right != null){
            root = root.right;
            temp++;
        }
        if (root.left == null){
            minDepth = Math.min(minDepth, temp);
        }

        return minDepth;
    }

    // 方法3 : 深度优先搜索, 发现当前深度大于已知的最小深度时, 停止搜索
    public int depth;
    public int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        minDepth3(root, 1);
        return depth;
    }

    private void minDepth3(TreeNode root, int d) {
        if (d == depth){
            return;
        }
        if (root.left == null && root.right == null){
            depth = d;
        }else {
            if (root.left != null) {
                minDepth3(root.left, d + 1);
            }
            if (root.right != null) {
                minDepth3(root.right, d + 1);
            }
        }
    }

    // 方法4 : 宽度优先搜索, 最快

    @Test
    public void test_minDepth2() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;

        int ans1 = minDepth1(node1);
        int ans2 = minDepth2(node1);

        System.out.println("ans1 = " + ans1);
        System.out.println("ans2 = " + ans2);
    }
}
