package com.lzx.zcy.class13.UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-26 15:16:13
 * 并查集
 */
public class UnionFind<V> {
    // 记录值和节点的对应关系
    public HashMap<V, UnionFindNode<V>> nodes;
    // 记录每个节点的父亲
    public HashMap<UnionFindNode<V>, UnionFindNode<V>> parents;
    // 记录每个代表节点所在集合的节点个数
    public HashMap<UnionFindNode<V>, Integer> sizeMap;

    public UnionFind(List<V> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V value : values) {
            UnionFindNode<V> node = new UnionFindNode<>(value);
            nodes.put(value, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    // 找到一个节点的最高父亲
    public UnionFindNode<V> findParent(UnionFindNode<V> node) {
        Stack<UnionFindNode<V>> path = new Stack<>();

        UnionFindNode<V> parent = parents.get(node);
        while (parent != node){
            path.push(node);
            node = parent;
            parent = parents.get(node);
        }

        while (!path.isEmpty()){
            parents.put(path.pop(), node);
        }

        return node;
    }

    // 查询两个元素是否属于同一个集合
    public boolean isSameSet(V a, V b) {
        return findParent(nodes.get(a)) == findParent(nodes.get(b));
    }

    // 合并两个集合
    public void union(V a, V b) {
        if (nodes.get(a) == null || nodes.get(b) == null){
            return;
        }

        UnionFindNode<V> head1 = findParent(nodes.get(a));
        UnionFindNode<V> head2 = findParent(nodes.get(b));

        if (head1 != head2){
            Integer size1 = sizeMap.get(head1);
            Integer size2 = sizeMap.get(head2);
            if (size1 < size2){
                parents.put(head1, head2);
                sizeMap.put(head2, size1 + size2);
                sizeMap.remove(head1);
            }else {
                parents.put(head2, head1);
                sizeMap.put(head1, size1 + size2);
                sizeMap.remove(head2);
            }
        }
    }

    // 返回一共有多少个大类
    public int sets(){
        return sizeMap.size();
    }
}
