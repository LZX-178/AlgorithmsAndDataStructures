package com.lzx.hsp.dataStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author LZX
 * @code @create 2022-08-15 14:37:57
 */
public class Graph {
    // 点集
    public ArrayList<String> vertexes;
    // 边集
    public int[][] edges;
    // 边的数目
    private int numOfEdges;

    // 广度优先遍历
    public void BroadFirstSearch(){
        if (vertexes == null || vertexes.size() == 0){
            System.out.println("graph is empty");
            return;
        }
        boolean[] isVisited = new boolean[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]){
                LinkedList<Integer> queue = new LinkedList<>();

                System.out.println(vertexes.get(i));
                isVisited[i] = true;
                queue.addLast(i);
                System.out.println("queue = " + queue);
                BroadFirstSearch(queue, isVisited);
            }
        }
    }
    // 约定 : queue 中的节点已经访问过了
    private void BroadFirstSearch(LinkedList<Integer> queue, boolean[] isVisited) {
        while (!queue.isEmpty()){
            Integer index = queue.removeFirst();
            System.out.println("*********** index = " + vertexes.get(index) + " ****************");
            System.out.println("*********** queue = " + queue + " ****************");

            for (int i = 0; i < vertexes.size(); i++) {
                if (edges[index][i] != 0){
                    System.out.println("test " + vertexes.get(index) + " -> " + vertexes.get(i));
                    System.out.println(vertexes);
                    System.out.println(Arrays.toString(isVisited));
                    if (!isVisited[i]){
                        System.out.println(vertexes.get(i));
                        isVisited[i] = true;
                        queue.addLast(i);
                        System.out.println("queue = " + queue);
                    }
                }
            }
        }
    }

    // 深度优先遍历
    public void DepthFirstSearch(){
        if (vertexes == null || vertexes.size() == 0){
            System.out.println("graph is empty");
            return;
        }
        boolean[] isVisited = new boolean[vertexes.size()];
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]){
                DepthFirstSearch(isVisited, i);
            }
        }
    }
    private void DepthFirstSearch(boolean[] isVisited, int index) {
        System.out.println(vertexes.get(index));
        isVisited[index] = true;

        for (int i = 0; i < vertexes.size(); i++) {
            if (edges[index][i] != 0){
                System.out.println("test " + vertexes.get(index) + " -> " + vertexes.get(i));
                System.out.println(vertexes);
                System.out.println(Arrays.toString(isVisited));
                if (!isVisited[i]){
                    DepthFirstSearch(isVisited, i);
                }
            }
        }
    }

    // 打印 变量的字符最大长度默认为2
    public void show(){
        System.out.print("  ");
        for (String vertex : vertexes) {
            System.out.printf(" %2s", vertex);
        }

        for (int i = 0; i < vertexes.size(); i++) {
            String vertex = vertexes.get(i);
            System.out.printf("\n%2s", vertex);

            for (int j = 0; j < vertexes.size(); j++) {
                int weight = edges[i][j];
                System.out.printf(" %2d", weight);
            }
        }
    }
    // 返回 边 的权值
    public int getWeight(String vertex1, String vertex2){
        int index1 = getIndexOfVertex(vertex1);
        int index2 = getIndexOfVertex(vertex2);
        if (index1 == -1 || index2 == -1){
            return -1;
        }
        return edges[index1][index2];
    }
    // 返回节点索引
    public int getIndexOfVertex(String vertex){
        for (int i = 0; i < vertexes.size(); i++) {
            if (Objects.equals(vertexes.get(i), vertex)){
                return i;
            }
        }
        return -1;
    }
    // 返回节点个数
    public int gerNumOfVertex(){
        return vertexes.size();
    }
    // 返回边个数
    public int getNumOfEdges() {
        return numOfEdges;
    }
    // 插入节点
    public void insertVertex(String vertex){
        vertexes.add(vertex);
    }
    // 添加边
    public void insertEdge(String vertex1, String vertex2, int weight){
        int index1 = getIndexOfVertex(vertex1);
        int index2 = getIndexOfVertex(vertex2);
        if (index1 == -1 || index2 == -1){
            System.out.println("vertex error !!!");
            return;
        }
        insertEdge(index1, index2, weight);
    }
    private void insertEdge(int index1, int index2, int weight){
        edges[index1][index2] = weight;
        edges[index2][index1] = weight;
        numOfEdges++;
    }
    // 构造器, 输入节点的最大个数
    public Graph(int numOfVertexes){
        vertexes = new ArrayList<>(numOfVertexes);
        edges = new int[numOfVertexes][numOfVertexes];
        numOfEdges = 0;
    }
}
