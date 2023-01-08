package com.lzx.zcy.class26;

import com.lzx.utils.NumberUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2023-01-07 12:22:07
 *
 * ********************** 还需要仔细研究 !!! **********************
 *
 *
 */
public class Code01_IndexTree {

    // 对数器
    public static class IndexTree0 {
        private final int[] nums;

        public IndexTree0(int size) {
            int n = size + 1;
            nums = new int[n + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    // IndexTree1, 下标从1开始！
    public static class IndexTree1 {

        private int[] tree;
        private int N;

        // 下标从 1 开始
        // 虚构一个原数组 arr, 下标 [1, n];
        //      将 n 个位置看成 n 个叶子节点,
        //      在 [1, n] 位置上放置一颗满二叉树(保证覆盖所有位置, 叶子节点可超出n),
        //      父节点的值等于两个子节点的和
        //      但每个位置实际存储的值是 该节点所在的右边界的最高节点(最高右父亲)
        public IndexTree1(int size) {
            N = size;
            tree = new int[N+1];
        }

        // 查询 [1, index] 的累加和
        public int sum(int index) {
           return 0;
        }

        // arr[index] 加上 d
        // 在 tree 上, 该操作会影响以下位置的值
        //      index结点 以及 index所在的所有子树的右边界 ln
        //      首先 index 结点只会影响大于等于index 的节点
        //         1 找到 大于 index 的最小的 2 的幂(假设为 x = 01000..0)
        //              x, 2x, 4x, 8x... 均会影响
        //         2 对于 [index, x] 范围
        //              如果 index 为奇数
        //                  index, index+1, index+3, index+7 ... 均会影响
        //              如果 index 为偶数
        //                  index, index+1, index+3, index+7 ... 均会影响
        public void add(int index, int d) {

        }

    }


    // IndexTree2, 下标从0开始！
    public static class IndexTree2 {

        private final int[] tree;
        private final int N;
        private final int len;

        // 下标从 0 开始
        // 虚构一个原数组 arr, 下标 [0, n-1];
        //      将 n 个位置看成 n 个叶子节点,
        //      在 [0, n-1] 位置上放置一颗满二叉树(保证覆盖所有位置, 叶子节点可超出n-1),
        //      将这颗二叉树看成前缀树, 向左为 0, 向右为 1
        //      父节点的值等于两个子节点的和
        //      但每个位置实际存储的值是 该节点所在的右边界的最高节点(最高右父亲)
        public IndexTree2(int size) {
            N = size;
            tree = new int[N];

            // len 表示 N 写成二进制后的位数
            int temp = 1;
            int count = 0;
            while (N >= temp){
                count++;
                temp <<= 1;
            }
            len = count;
        }

        // 查询 [0, index] 的累加和
        // 相当于 在 [0, index] 范围上, 从左往右 尽可能大地挑二叉树
        // 最后一颗二叉树的右边界一定落在 index 位置
        public int sum(int index) {
            // 最后一颗二叉树的右边界一定落在 index 位置, 对应 index 末尾那一串连续的 1
            int sum = tree[index];

            // 找到 index 从右往左数的第一个 0 的位置;
            int offset = (index+1) & (-index-1);

            int limit = 1 << (len-1);
            while (offset <= limit){
                if ((index & offset) == 0){
                    // 如果 index 这一位为 0
                    // 将其改为 1
                    index |= offset;
                }else {
                    // 如果 index 这一位为 1
                    // 说明当初 在这个节点 向右走了, 需要加上左节点的值
                    // 访问这一节点的左节点值, (将这一位改为 0)
                    sum += tree[index & (~offset)];
                }
                offset <<= 1;
            }
            return sum;
        }

        // arr[index] 加上 d
        // 在 tree 上, 该操作会影响以下位置的值
        //      index结点 以及 index所在的所有子树的右边界 ln
        //      index 结点只会影响大于等于index 的节点 :
        //          从低位向高位遍历, 依次将 index 的 0 改成 1, 即可找到这些点
        //          解释 :
        //              从低位向高位遍历的过程, 即不断寻找父节点的过程, 即寻找index所在的所有子树的过程
        //              如果当前位为 0, 说明生成index时,在这个节点向左走了,
        //              将 0 改为 1, 即可找到这个节点右边界对于的叶子节点
        public void add(int index, int d) {
            if (index < 0 || index >= N){
                return;
            }
            tree[index] += d;

            for (int i = 0, offset = 1; i < len; i++) {
                if ((index & offset) == 0){
                    index |= offset;
                    if (index < N){
                        tree[index] += d;
                    }else {
                        break;
                    }
                }
                offset <<= 1;
            }
        }

    }

    @Test
    public void test_IndexTree2() {
        IndexTree2 indexTree2 = new IndexTree2(3);

        // 0 1 2
        // 0 0 0
        int ans1 = indexTree2.sum(2);
        System.out.println("ans1 = " + ans1); // 0

        // 0 1 2
        // 0 0 3
        indexTree2.add(2, 3);
        ans1 = indexTree2.sum(2);
        System.out.println("ans1 = " + ans1); // 3
        ans1 = indexTree2.sum(1);
        System.out.println("ans1 = " + ans1); // 0

        // 0 1 2
        // 0 2 3
        indexTree2.add(1, 2);
        ans1 = indexTree2.sum(2);
        System.out.println("ans1 = " + ans1); // 5
        ans1 = indexTree2.sum(1);
        System.out.println("ans1 = " + ans1); // 2
        ans1 = indexTree2.sum(0);
        System.out.println("ans1 = " + ans1); // 0
    }

    @Test
    public void test_IndexTree1() {
        for (int j = 0; j < 100; j++) {
            int len = NumberUtils.getRandomInt(1, 100);
            IndexTree0 indexTree0 = new IndexTree0(len);
            IndexTree2 indexTree2 = new IndexTree2(len);

            for (int i = 0; i < 50000; i++) {
                int index = NumberUtils.getRandomInt(1, len);
                int num = NumberUtils.getRandomInt(-100, 100);
                indexTree0.add(index, num);
                indexTree2.add(index-1, num);

                index = NumberUtils.getRandomInt(1, len);
                int ans0 = indexTree0.sum(index);
                int ans2 = indexTree2.sum(index-1);

                if (ans0 != ans2){
                    throw new RuntimeException("error");
                }
            }
        }

        System.out.println("OK");
    }

}
