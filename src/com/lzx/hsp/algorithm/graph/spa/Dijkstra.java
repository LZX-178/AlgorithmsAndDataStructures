package com.lzx.hsp.algorithm.graph.spa;

import com.lzx.hsp.algorithm.graph.mst.Graph;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-08-22 13:54:26
 * Dijkstra算法的主要步骤为：
 *      将节点分为两部分，一部分是已经确定从起点开始的最短路径的节点集合 V ，
 *      另一部分是还未确定最短路径的节点集合 U
 *      用 V 中节点作为中转节点，
 *      来更新起点到 U 中节点的最短路径（称为松弛操作），
 *      然后将 U 中最短路径最小的节点作为新的已确认最短路径的节点加入 V 中；
 *      重复操作，直到将终点加入V中。
 */
public class Dijkstra {
    public boolean[] isVisited;
    public int[] pre;
    public int[] distance;

    public int[] getShortestPath(Graph graph, int start){
        int size = graph.vertexes.size();
        // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
        isVisited = new boolean[size];
        isVisited[start] = true; //设置出发顶点被访问过
        // 每个下标对应的值为前一个顶点下标, 会动态更新
        pre = new int[size];
        // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
        distance = new int[size];
        Arrays.fill(distance, 99);
        //设置出发顶点的访问距离为0
        distance[start] = 0;
        update(graph, start);


        
        return distance;
    }
    
    // 松弛操作
    private void update(Graph graph, int node){
        int dis;
        // 遍历 node 的所有连接的边
        for (int i = 0; i < graph.edges[node].length; i++) {
            if (graph.edges[node][i] < 99 && !isVisited[i]){
                dis = graph.edges[node][i] + distance[node];
                distance[i] = dis;
                isVisited[i] = true;
            }

        }
    }
}
