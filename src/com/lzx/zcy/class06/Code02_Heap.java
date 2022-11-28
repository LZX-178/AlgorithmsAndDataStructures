package com.lzx.zcy.class06;

/**
 * @author LZX
 * @code @create 2022-09-11 13:08:36
 * 堆结构
 *      当前结点为 i
 *      左节点 2*i + 1
 *      右节点 2*i + 2
 *      父节点 (i-1)/2 ---- 这里 i 可以为 0, 因为 -1/2 = 0
 */
public class Code02_Heap {
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit){
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        // 向堆中添加一个数
        public void push(int value){
            if (isFull()){
                return;
            }
            heap[heapSize] = value;
            heapInsert(heapSize++);
        }

        // 取出堆顶的元素
        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("heap is empty");
            }
            int res = heap[0];
            swap(0, --heapSize);
            heapify(0);
            return res;
        }

        // 将index位置的数向上浮, 默认 index 的子节点已经合法
        // 只需要检验 index 和 其父节点 之间是否合法
        // 所以当该结点移动到0位置，或者比父节点小, 停止移动
        private void heapInsert(int index){
            while (heap[index] > heap[(index-1)/2]){
                swap(index, (index-1)/2);
                index = (index-1)/2;
            }
        }

        // 堆化 : 将 index 位置的数下沉, 默认 index 的子节点已经合法
        // 只需要检验 index 和 它的两个子节点 之间的合法性
        // 从 index 和 它的两个子节点 中选出最大的节点
        private void heapify(int index){
            int leftChild = (index << 1) | 1;
            int biggest;
            while (leftChild <= heapSize - 1) {
                if (leftChild == heapSize - 1) {
                    biggest = leftChild;
                } else {
                    biggest = heap[leftChild] > heap[leftChild + 1] ? leftChild : leftChild + 1;
                }

                if (heap[index] >= heap[biggest]) {
                    break;
                }
                swap(index, biggest);
                index = biggest;
                leftChild = (index << 1) + 1;
            }
        }

        public void swap(int i, int j){
            heap[i] = heap[i] ^ heap[j];
            heap[j] = heap[i] ^ heap[j];
            heap[i] = heap[i] ^ heap[j];
        }
    }

    // 对数器
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }
}
