package com.lzx.zcy.class06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-11-29 10:05:27
 *
 * 加强堆
 *      系统提供的堆 (PriorityQueue) 无法做到的事情：
 *          1）已经入堆的元素，如果参与排序的指标方法变化，
 *              系统提供的堆无法做到时间复杂度O(logN)调整！都是O(N)的调整！
 *          2）系统提供的堆只能弹出堆顶，做不到自由删除任何一个堆中的元素，
 *              或者说，无法在时间复杂度O(logN)内完成！一定会高于O(logN)
 *      根本原因：无反向索引表
 */
public class Code05_HeapGreater {
    private static class HeapGreater<T>{
        private final ArrayList<T> heap;
        private int heapSize;
        private final HashMap<T, Integer> indexMap;
        private final Comparator<? super T> comp;

        public HeapGreater(Comparator<? super T> comparator) {
            this.comp = comparator;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
        }

        public boolean isEmpty(){
            return heapSize == 0 ;
        }

        public int size(){
            return heapSize;
        }

        public boolean contains(T t){
            return indexMap.containsKey(t);
        }

        public T peek(){
            return heap.get(0);
        }

        public void push(T t){
            heap.add(t);
            indexMap.put(t, heapSize);
            heapInsert(heapSize++);
        }

        public T pop(){
            T ans = heap.get(0);
            swap(0, heapSize-1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        public void remove(T t){
            Integer index = indexMap.get(t);
            heapSize--;
            if (index != heapSize) {
                swap(index, heapSize);
                resign(heap.get(index));
            }
            heap.remove(heapSize);
            indexMap.remove(t);
        }

        public void resign(T t){
            heapify(indexMap.get(t));
            heapInsert(indexMap.get(t));
        }


        private void heapInsert(int index){
            while (index != 0 && comp.compare(heap.get(index), heap.get((index-1)/2)) < 0){
                swap(index, (index-1)/2);
                index = (index-1)/2;
            }
        }

        private void heapify(int index){
            int less;
            int leftChild = 2*index + 1;
            while (leftChild < heapSize){
                less = leftChild;
                if (leftChild != heapSize-1 && comp.compare(heap.get(leftChild), heap.get(leftChild+1)) > 0){
                    less++;
                }
                if (comp.compare(heap.get(index), heap.get(less)) <= 0){
                    break;
                }
                swap(less, index);
                index = less;
                leftChild = 2*index+1;
            }
        }

        private void swap(int i, int j) {
            if (i == j){
                return;
            }

            T t1 = heap.get(i);
            T t2 = heap.get(j);

            heap.set(i, t2);
            heap.set(j, t1);

            indexMap.put(t2, i);
            indexMap.put(t1, j);
        }

        public void printHeap(){
            System.out.println("heap = " + heap);
        }


    }
}
