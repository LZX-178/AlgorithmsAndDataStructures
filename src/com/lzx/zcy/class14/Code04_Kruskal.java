package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Edge;
import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import org.junit.Test;

import java.util.*;

/**
 * @author LZX
 * @code @create 2022-10-27 10:49:31
 * 克鲁斯卡尔最小生成树算法
 * 依次将权重最小的边加入到生成树中(如果边两边的节点不在一个连通子图的话),
 * 直到只有一个连通子图
 */
public class Code04_Kruskal {

    private static class UnionFind {
        // 记录每个节点的父亲
        public HashMap<Node, Node> parents;
        // 记录每个代表节点所在集合的节点个数
        public HashMap<Node, Integer> sizeMap;

        public UnionFind(HashMap<Integer, Node> nodes) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (Node node : nodes.values()) {
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 找到一个节点的最高父亲
        public Node findParent(Node node) {
            Stack<Node> path = new Stack<>();

            Node parent = parents.get(node);
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
        public boolean isSameSet(Node a, Node b) {
            return findParent(a) == findParent(b);
        }

        // 合并两个集合
        public void union(Node a, Node b) {

            Node head1 = findParent(a);
            Node head2 = findParent(b);

            if (head1 == null || head2 == null){
                return;
            }

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

    // 此处 kruskalMST 函数为无向图版本
    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind = new UnionFind(graph.nodes);
        // 小根堆, 将从小的边到大的边，依次弹出
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        queue.addAll(graph.edges);

        HashSet<Edge> res = new HashSet<>();
        while (unionFind.sets() > 1 && !queue.isEmpty()){
            Edge edge = queue.poll();

            if (!unionFind.isSameSet(edge.from,edge.to)){
                unionFind.union(edge.from,edge.to);
                res.add(edge);
            }
        }

        return res;
    }

    @Test
    public void test_sortedTopology() {
        int[][] matrix = {
                {1, 1, 2},
                {3, 1, 3},
                {100, 3, 2},
                {2, 4, 2},
                {4, 4, 5},
                {50, 5, 3},
        };
        //                    1
        //           [1]/          \[3]
        //             /            \
        //            2   --[100]--  3
        //            \             /
        //          [2]\           /[50]
        //              4 --[4]--  5
        Graph graph = new Graph(matrix);
        Set<Edge> set = kruskalMST(graph);
        set.forEach(System.out::println);
    }
}
