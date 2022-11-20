package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Edge;
import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author LZX
 * @code @create 2022-10-27 14:22:26
 * 普瑞姆最小生成树算法
 * 从某个连通子图开始, 将与该连通子图相连的边中 权值最小的边(如果边两边的节点不在一个连通子图的话) 以及 该边相连的点
 * 加入到连通子图中
 * 直到只有一个连通子图
 */
public class Code05_Prim {

    // 此处 primMST 函数为有向图版本
    public static Set<Edge> primMST(Graph graph) {
        HashSet<Node> nodeSet = new HashSet<>();
        HashSet<Edge> edgeSet = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));


        // 此处的 for循环 可处理多个连通子图
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                queue.addAll(node.edges);
                while (nodeSet.size() < graph.nodes.size() && !queue.isEmpty()){
                    Edge edge = queue.poll();
                    if (!nodeSet.contains(edge.to)){
                        nodeSet.add(edge.to);
                        edgeSet.add(edge);
                        queue.addAll(edge.to.edges);
                    }
                }
            }
        }
        return edgeSet;
    }

    @Test
    public void test_prim() {

        int[][] matrix = {
                {1, 1, 2},
                {3, 1, 3},
                {100, 2, 3},
                {2, 2, 4},
                {4, 4, 5},
                {50, 3, 5},
        };
        //                    1
        //           [1]/          \[3]
        //            </            \>
        //            2   --[100]--> 3
        //            \             /
        //          [2]\>         </[50]
        //              4 --[4]--> 5
        Graph graph = new Graph(matrix);
        Set<Edge> set = primMST(graph);
        set.forEach(System.out::println);

    }
}
