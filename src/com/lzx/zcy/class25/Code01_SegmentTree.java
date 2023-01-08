package com.lzx.zcy.class25;

import com.lzx.utils.ArrayUtils;
import com.lzx.utils.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2023-01-06 10:58:34
 *
 *
 * ********************** 还需要仔细研究 !!! **********************
 *
 *
 * 待改进 : 将 线段树2 的 update任务 和 add任务 分开下派
 */
public class Code01_SegmentTree {

    // 对数器
    public static class SegmentTree0 {
        public int[] arr;

        public SegmentTree0(int[] origin) {
            arr = new int[origin.length + 1];
            System.arraycopy(origin, 0, arr, 1, origin.length);
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

        public void print(){
            System.out.println("arr = " + Arrays.toString(arr));
        }
    }

    // 线段树1
    // add(int start, int end, int C) : 对 arr 数组 [start, end] 范围上的数都加 C
    // query(int start, int end) : 查询 arr 数组 [start, end] 范围上的数的和
    public static class SegmentTree1 {

        private final int n;
        private final int[] arr;
        private final int[] sum;
        private final int[] lazy;

        public SegmentTree1(int[] origin) {
            // 1 记元素个数为 n
            n = origin.length;
            int len = origin.length + 1;

            // 2 将数组的下标改为从 1 开始
            arr = new int[len];
            System.arraycopy(origin, 0, arr, 1, n);

            // 3 记录某一个范围的累加和信息
            // 实际上是一颗顺序存储的二叉树,
            // sum[1] 对应 [1, n] 的和, 记 mid = (1+n) >> 1
            //      左孩子为 sum[2], 对应 [1, mid] 的和
            //      右孩子为 sum[3], 对应 [mid, n] 的和
            sum = new int[len << 2];

            // 4 记录某一个范围懒更新的任务
            lazy = new int[len << 2];

            // sum 数组的初始化
            build(1, n, 1);
        }

        // 将 arr 数组上 [L, R] 范围的累加和记录在 sum[root] 位置
        public void build(int L, int R, int root){
            if (L == R){
                sum[root] = arr[L];
                return;
            }

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;

            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            build(L, mid, leftNode);
            build(mid+1, R, rightNode);

            sum[root] = sum[leftNode] + sum[rightNode];
        }

        // 将 arr 数组上 [start, end] 范围的数加上 C
        public void add(int start, int end, int C){
            add(start, end, C, 1, n, 1);
        }
        // 当前来到的节点为 root, 对应的累加和范围为 [L, R]
        // 约定 [start, end] 一定是 [L, R] 的子数组
        private void add(int start, int end, int C, int L, int R, int root) {
            if (start == L && end == R){
                lazy[root] += C;
                return;
            }

            // 没有 update 操作时, 这里可以不用 pushDown (加法交换律)
            // pushDown(L, R, root);

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;
            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            sum[root] += C * (end-start+1);

            // 依据 [start, end] 是否跨越 mid 分成三种情况
            if (end <= mid){
                add(start, end, C, L, mid, leftNode);
            }else if (start > mid){
                add(start, end, C, mid+1, R, rightNode);
            }else {
                add(start, mid, C, L, mid, leftNode);
                add(mid+1, end, C, mid+1, R, rightNode);
            }
        }

        // 完成 root 节点的任务, 并下发到其子节点
        private void pushDown(int L, int R, int root) {
            if (lazy[root] == 0){
                return;
            }
            sum[root] += lazy[root] * (R-L+1);
            if (L != R){
                int leftNode = root << 1;
                int rightNode = leftNode | 1;
                lazy[leftNode] += lazy[root];
                lazy[rightNode] += lazy[root];
            }
            lazy[root] = 0;
        }

        // 查询 arr 数组上 [start, end] 范围的累加和
        public long query(int start, int end){
            return query(start, end, 1, n, 1);
        }
        private long query(int start, int end, int L, int R, int root){
            // 先 pushDown, 保证当前结点的 sum[root] 的正确性
            pushDown(L, R, root);
            if (start == L && end == R){
                return sum[root];
            }

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;
            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            // 依据 [start, end] 是否跨越 mid 分成三种情况
            if (end <= mid){
                return query(start, end, L, mid, leftNode);
            }else if (start > mid){
                return query(start, end, mid+1, R, rightNode);
            }else {
                return
                    query(start, mid, L, mid, leftNode) +
                    query(mid+1, end, mid+1, R, rightNode);
            }

        }
    }

    // 线段树2
    // add(int start, int end, int C) : 对 arr 数组 [start, end] 范围上的数都加 C
    // update(int start, int end, int C) : 对 arr 数组 [start, end] 范围上的数都改成 C
    // query(int start, int end) : 查询 arr 数组 [start, end] 范围上的数的和
    public static class SegmentTree2 {

        private final int n;
        private final int[] arr;
        private final int[] sum;
        private final int[] lazy;
        private final int[] update;
        private final boolean[] flag;

        public SegmentTree2(int[] origin) {
            // 1 记元素个数为 n
            n = origin.length;
            int len = origin.length + 1;

            // 2 将数组的下标改为从 1 开始
            arr = new int[len];
            System.arraycopy(origin, 0, arr, 1, n);

            // 3 记录某一个范围的累加和信息
            // 实际上是一颗顺序存储的二叉树,
            // sum[1] 对应 [1, n] 的和, 记 mid = (1+n) >> 1
            //      左孩子为 sum[2], 对应 [1, mid] 的和
            //      右孩子为 sum[3], 对应 [mid, n] 的和
            sum = new int[len << 2];

            // 4 记录某一个范围懒更新的任务
            lazy = new int[len << 2];

            // 5 记录某一个范围的更新任务, 以及某一个范围更新任务是否存在
            update = new int[len << 2];
            flag = new boolean[len << 2];

            // sum 数组的初始化
            build(1, n, 1);
        }

        // 将 arr 数组上 [L, R] 范围的累加和记录在 sum[root] 位置
        public void build(int L, int R, int root){
            if (L == R){
                sum[root] = arr[L];
                return;
            }

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;

            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            build(L, mid, leftNode);
            build(mid+1, R, rightNode);

            sum[root] = sum[leftNode] + sum[rightNode];
        }

        // 将 arr 数组上 [start, end] 范围的数加上 C
        public void add(int start, int end, int C){
            add(start, end, C, 1, n, 1);
        }
        // 当前来到的节点为 root, 对应的累加和范围为 [L, R]
        // 约定 [start, end] 一定是 [L, R] 的子数组
        private void add(int start, int end, int C, int L, int R, int root) {
            if (start == L && end == R){
                lazy[root] += C;
                return;
            }

            // 有 update 操作时, 必须 pushDown
            pushDown(L, R, root);

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;
            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            sum[root] += C * (end-start+1);

            // 依据 [start, end] 是否跨越 mid 分成三种情况
            if (end <= mid){
                add(start, end, C, L, mid, leftNode);
            }else if (start > mid){
                add(start, end, C, mid+1, R, rightNode);
            }else {
                add(start, mid, C, L, mid, leftNode);
                add(mid+1, end, C, mid+1, R, rightNode);
            }
        }

        // 完成 root 节点的任务, 并下发到其子节点
        private void pushDown(int L, int R, int root) {
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            if (flag[root]){
                sum[root] = update[root] * (R-L+1);
                flag[root] = false;

                if (L != R){
                    update[leftNode] = update[rightNode] = update[root];
                    flag[leftNode] = flag[rightNode] = true;
                    lazy[leftNode] = lazy[rightNode] = 0;
                }
            }

            if (lazy[root] == 0){
                return;
            }
            sum[root] += lazy[root] * (R-L+1);
            if (L != R){
                lazy[leftNode] += lazy[root];
                lazy[rightNode] += lazy[root];
            }
            lazy[root] = 0;
        }

        // 查询 arr 数组上 [start, end] 范围的累加和
        public long query(int start, int end){
            return query(start, end, 1, n, 1);
        }
        private long query(int start, int end, int L, int R, int root){
            // 先 pushDown, 保证当前结点的 sum[root] 的正确性
            pushDown(L, R, root);
            if (start == L && end == R){
                return sum[root];
            }

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;
            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            // 依据 [start, end] 是否跨越 mid 分成三种情况
            if (end <= mid){
                return query(start, end, L, mid, leftNode);
            }else if (start > mid){
                return query(start, end, mid+1, R, rightNode);
            }else {
                return
                        query(start, mid, L, mid, leftNode) +
                                query(mid+1, end, mid+1, R, rightNode);
            }

        }

        // 将 arr 数组上 [start, end] 范围的数都改成 C
        public void update(int start, int end, int C){
            update(start, end, C, 1, n, 1);
        }
        // 当前来到的节点为 root, 对应的累加和范围为 [L, R]
        // 约定 [start, end] 一定是 [L, R] 的子数组
        private void update(int start, int end, int C, int L, int R, int root){
            if (start == L && end == R){
                lazy[root] = 0;
                update[root] = C;
                flag[root] = true;
                return;
            }

            // 有 update 操作时, 必须 pushDown
            pushDown(L, R, root);

            // 取 L 和 R 的上中点
            int mid = (L+R) >> 1;
            // root 的左右孩子
            int leftNode = root << 1;
            int rightNode = leftNode | 1;

            // 依据 [start, end] 是否跨越 mid 分成三种情况
            if (end <= mid){
                update(start, end, C, L, mid, leftNode);
            }else if (start > mid){
                update(start, end, C, mid+1, R, rightNode);
            }else {
                update(start, mid, C, L, mid, leftNode);
                update(mid+1, end, C, mid+1, R, rightNode);
            }

            pushDown(L, mid, leftNode);
            pushDown(mid+1, R, rightNode);
            sum[root] = sum[leftNode] + sum[rightNode];
        }
    }

    @Test
    public void test_SegmentTree1() {
        int[] arr0 = {1, 1, 1, 1};
        int[] arr1 = Arrays.copyOf(arr0, arr0.length);

        SegmentTree0 segmentTree0 = new SegmentTree0(arr0);
        SegmentTree1 segmentTree1 = new SegmentTree1(arr1);

        System.out.println("***** " + "null" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        long ans0 = segmentTree0.query(1, 4);
        long ans1 = segmentTree1.query(1, 4);
        System.out.println("ans10 = " + ans0);
        System.out.println("ans11 = " + ans1);
        segmentTree0.print();

        System.out.println("***** " + "add(2, 3, 3)" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        segmentTree0.add(2, 3, 3);
        segmentTree1.add(2, 3, 3);
        ans0 = segmentTree0.query(1, 4);
        ans1 = segmentTree1.query(1, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
        segmentTree0.print();

        System.out.println("***** " + "add(2, 3, 3)" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        segmentTree0.add(2, 3, 3);
        segmentTree1.add(2, 3, 3);
        ans0 = segmentTree0.query(1, 4);
        ans1 = segmentTree1.query(1, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
        segmentTree0.print();

        System.out.println("***** " + "add(1, 3, 1)" + " *****");
        System.out.println("***** " + "query(2, 4)" + " *****");
        segmentTree0.add(1, 3, 1);
        segmentTree1.add(1, 3, 1);
        ans0 = segmentTree0.query(2, 4);
        ans1 = segmentTree1.query(2, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans1 = " + ans1);
        segmentTree0.print();
    }

    @Test
    public void test_SegmentTree2() {
        int[] arr0 = ArrayUtils.generateRandomArray(1, 50, -10, 10);
        int[] arr1 = Arrays.copyOf(arr0, arr0.length);

        SegmentTree0 segmentTree0 = new SegmentTree0(arr0);
        SegmentTree1 segmentTree1 = new SegmentTree1(arr1);

        int testTimes = 1000000;
        int n = arr0.length;
        int start, end, C;

        for (int i = 0; i < testTimes; i++) {
            start = NumberUtils.getRandomInt(1, n);
            end = NumberUtils.getRandomInt(start, n);
            C = NumberUtils.getRandomInt(-50, 50);

            segmentTree0.add(start, end, C);
            segmentTree1.add(start, end, C);

            start = NumberUtils.getRandomInt(1, n);
            end = NumberUtils.getRandomInt(start, n);

            long ans0 = segmentTree0.query(start, end);
            long ans1 = segmentTree1.query(start, end);

            if (ans0 != ans1){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans1 = " + ans1);
                segmentTree0.print();
                throw new RuntimeException("error");
            }
        }

        System.out.println("OK");
    }

    @Test
    public void test_SegmentTree3() {
        int[] arr0 = ArrayUtils.generateRandomArray(1, 50, -50, -50);
        int[] arr2 = Arrays.copyOf(arr0, arr0.length);

        SegmentTree0 segmentTree0 = new SegmentTree0(arr0);
        SegmentTree2 segmentTree2 = new SegmentTree2(arr2);

        int testTimes = 1000000;
        int n = arr0.length;
        int start, end, C;

        for (int i = 0; i < testTimes; i++) {
            start = NumberUtils.getRandomInt(1, n);
            end = NumberUtils.getRandomInt(start, n);
            C = NumberUtils.getRandomInt(-50, 50);

            if (Math.random() < 0.5){
                segmentTree0.update(start, end, C);
                segmentTree2.update(start, end, C);
            }else {
                segmentTree0.add(start, end, C);
                segmentTree2.add(start, end, C);
            }

            start = NumberUtils.getRandomInt(1, n);
            end = NumberUtils.getRandomInt(start, n);

            long ans0 = segmentTree0.query(start, end);
            long ans2 = segmentTree2.query(start, end);

            if (ans0 != ans2){
                System.out.println("ans0 = " + ans0);
                System.out.println("ans2 = " + ans2);
                System.out.println("start = " + start);
                System.out.println("end = " + end);
                segmentTree0.print();
                throw new RuntimeException("error");
            }
        }

        System.out.println("OK");
    }

    @Test
    public void test_SegmentTree4() {
        int[] arr0 = {1, 2, 1, 1};
        int[] arr2 = Arrays.copyOf(arr0, arr0.length);

        SegmentTree0 segmentTree0 = new SegmentTree0(arr0);
        SegmentTree2 segmentTree2 = new SegmentTree2(arr2);

        System.out.println("***** " + "null" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        long ans0 = segmentTree0.query(1, 4);
        long ans2 = segmentTree2.query(1, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans2 = " + ans2);
        segmentTree0.print();

        System.out.println("***** " + "update(2, 3, 3)" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        segmentTree0.update(1, 3, 3);
        segmentTree2.update(1, 3, 3);
        ans0 = segmentTree0.query(1, 4);
        ans2 = segmentTree2.query(1, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans2 = " + ans2);
        segmentTree0.print();

        System.out.println("***** " + "update(2, 3, 3)" + " *****");
        System.out.println("***** " + "query(1, 4)" + " *****");
        segmentTree0.update(2, 4, 2);
        segmentTree2.update(2, 4, 2);
        ans0 = segmentTree0.query(1, 4);
        ans2 = segmentTree2.query(1, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans2 = " + ans2);
        segmentTree0.print();

        System.out.println("***** " + "update(1, 3, 1)" + " *****");
        System.out.println("***** " + "query(2, 4)" + " *****");
        segmentTree0.update(1, 3, 1);
        segmentTree2.update(1, 3, 1);
        ans0 = segmentTree0.query(2, 4);
        ans2 = segmentTree2.query(2, 4);
        System.out.println("ans0 = " + ans0);
        System.out.println("ans2 = " + ans2);
        segmentTree0.print();
    }
}
