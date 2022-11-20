package com.lzx.zcy.class11;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-21 18:21:17
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折 1 次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，
 * 此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕
 * 给定一个输入参数 N ，代表纸条 从下边向上方连续对折 N 次
 * 请从上到下打印所有折痕的方向。
 * 例如．N = 1 时，打印 ：down
 *      N = 2 时，打印：down down up
 */
public class Code07_PaperFolding {
    @Test
    public void test_printAllFolds() {
        printAllFolds(1);
        printAllFolds(2);
        printAllFolds(3);
        printAllFolds(4);
        printAllFolds(5);
    }

    // 编码 down - 0, up - 1
    // 分析 :
    // 逆向思维 : 每次折纸的过程可以想象成
    //           把当前的纸( i条折痕 )沿下沿翻一面
    //           那么折痕会新折痕会是这样组成的 :
    //                  原折痕( i条 ) + 一条下折痕 + 原折痕逆序后取反( i条 )
    // 例 :
    //      i       折痕
    //      1       0
    //      2       0 0 1
    //      3      (0 0 1) 0 (0 1 1)
    //
    // 数学建模 :
    //      可以将答案想象成这样一颗二叉树的中序遍历 :
    //                       0
    //                      /  \
    //                    0      1
    //                   / \    / \
    //                  0   1  0   1
    //          该二叉树为满二叉树, 共有 N 层
    //          头结点为 0
    //          且所有节点的 左节点为0 右节点为1
    //      证明 :
    //          数学归纳法 :
    //              假设 N = n 时成立, 证明 N = n + 1 时成立即可
    //                  将某个节点的 左节点和右节点 交换位置后再取反 会得到和原来一样的结构, 证明成立
    public void printAllFolds(int N) {
        if (N < 1){
            return;
        }
        printAllFolds(N, true);
        System.out.println();
    }

    // 当前层为 level (从上往下递减编码, 头结点为 N 层, 叶子结点为 1 层), isLeft 表示当前结点是否为父节点的 左节点
    public void printAllFolds(int level, boolean isLeft){
        if (level == 1){
            System.out.print(" " + (isLeft ? "0" : "1"));
            return;
        }

        printAllFolds(level-1, true);
        System.out.print(" " + (isLeft ? "0" : "1"));
        printAllFolds(level-1, false);
    }

}
