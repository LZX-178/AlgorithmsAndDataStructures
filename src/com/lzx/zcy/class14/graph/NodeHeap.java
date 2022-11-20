package com.lzx.zcy.class14.graph;

import javafx.util.Pair;

import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-11-13 14:17:26
 */
public class NodeHeap {
    // 实际的堆结构
    private Node[] heap;

    // 记录节点在堆中的索引
    private HashMap<Node, Integer> heapIndexMap;

    // 记录节点从源节点出发到该节点的目前最小距离
    private HashMap<Node, Integer> distanceMap;

    // 堆上有多少个点
    private int size;

    public NodeHeap(int size) {
        heap = new Node[size];
        heapIndexMap = new HashMap<>();
        distanceMap = new HashMap<>();
        this.size = 0;
    }

    // a-y 的距离更新为 min( 原a-y ,newDistance)
    public void put(Node y, Integer newDistance) {
        if (heapIndexMap.get(y) == null){
            heap[size++] = y;
            heapIndexMap.put(y, size-1);
            distanceMap.put(y, newDistance);
            heapInsert(size -1);
        }else if (heapIndexMap.get(y) != -1){
            if (distanceMap.get(y) > newDistance){
                distanceMap.put(y, newDistance);
                heapify(heapIndexMap.get(y));
                heapInsert(heapIndexMap.get(y));
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Pair<Node, Integer> get() {
        Node node = heap[0];
        Integer distance = distanceMap.get(node);
        swap(0, --size);
        heap[size] = null;
        heapIndexMap.put(node, -1);
        distanceMap.remove(node);
        heapify(0);

        return new Pair<>(node, distance);
    }

    // 将index位置的数向上浮, 默认 index 已经合法
    // 只需要检验 index 和 其父节点 之间是否合法
    // 所以该结点移动到0位置，或者比父节点小, 停止移动
    private void heapInsert(int index){
        while (distanceMap.get(heap[index])< distanceMap.get(heap[(index-1)/2])){
            swap(index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    // 堆化 : 将 index 位置的数下沉, 默认 index 的子节点已经合法
    // 只需要检验 index 和 它的两个子节点 之间的合法性
    // 从 index 和 它的两个子节点 中选出最大的节点
    private void heapify(int index){
        int leftChild = (index << 1) | 1;
        int smallest;
        while (leftChild <= size - 1) {
            if (leftChild == size - 1) {
                smallest = leftChild;
            } else {
                smallest = distanceMap.get(heap[leftChild]) < distanceMap.get(heap[leftChild + 1]) ? leftChild : leftChild + 1;
            }

            if (distanceMap.get(heap[index]) <= distanceMap.get(heap[smallest])) {
                break;
            }
            swap(index, smallest);
            index = smallest;
            leftChild = (index << 1) + 1;
        }
    }

    private void swap(int i, int j) {
        heapIndexMap.put(heap[i], j);
        heapIndexMap.put(heap[j], i);

        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
