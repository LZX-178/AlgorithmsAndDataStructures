package com.lzx.hsp.algorithm.graph.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author LZX
 * @code @create 2022-08-20 13:13:44
 * 按照权值从小到大的顺序选择 n-1 条边， 并保证这 n-1 条边不构成回路
 * 如何保证添加边时不构成回路 :
 *      定义一个连通子图的终点 : 每个连通子图有且只有一个终点
 *      用一个 ends 数组 存放某个顶点的下一个顶点, -1 表示没有下一个顶点, 即为该连通子图的终点
 *      但一条边的两个顶点的 连通子图终点 不同时, 才可以添加该条边, 并减少一个 连通子图终点
 *  连通子图终点的维护 :
 *      添加边时, 将 一个连通子图终点 的 下一个顶点 赋值为 另一个 连通子图终点
 *      这样就可以保证 新的连通子图的所有顶点 的 终点 为同一个 ( 数学归纳法可以证明 )
 */
public class Kruskal {
    public Graph creatMinTree(Graph graph){
        // 创建最小生成树并初始化
        int vertexSize = graph.vertexes.size();
        Graph minTree = new Graph(vertexSize);
        for (int i = 0; i < vertexSize; i++) {
            String v = graph.vertexes.get(i);
            minTree.insertVertex(v);
        }

        int[] ends = new int[vertexSize]; // 记录 各个顶点的 终点
        Arrays.fill(ends, -1);
        Edge[] edges = getEdges(graph);
        for (int i = 0, n = 0; i < edges.length && n < vertexSize - 1; i++) {
            Edge edge = edges[i];
            int start = edge.start;
            int end = edge.end;
            int end1 = getEnd(ends, start);
            int end2 = getEnd(ends, end);
            if(end1 != end2){
                minTree.insertEdge(start, end, edge.weight);
                n++;
                // 连通子图终点的维护
                ends[end1] = end2;
                System.out.println("**************** edge ****************");
                System.out.println("v1 = " + graph.vertexes.get(start));
                System.out.println("v2 = " + graph.vertexes.get(end));
                System.out.println("minWeight = " + edge.weight);
                System.out.println("Arrays.toString(ends) = " + Arrays.toString(ends));
            }
        }

        return minTree;
    }
    // 对边进行排序
    public Edge[] getEdges(Graph graph) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.edges.length; i++) {
            for (int j = 0; j < i; j++) {
                if (graph.edges[i][j] > 0 && graph.edges[i][j] < 99){
                    edges.add(new Edge(j, i, graph.edges[i][j]));
                }
            }
        }
        edges.sort(Comparator.comparingInt(e -> e.weight));
        return edges.toArray(new Edge[0]);
    }
    // 获取一个顶点所在的连通子图的终点
    private int getEnd(int[] ends, int vertex){
        while (ends[vertex] != -1){
            vertex = ends[vertex];
        }
        return vertex;
    }
}
