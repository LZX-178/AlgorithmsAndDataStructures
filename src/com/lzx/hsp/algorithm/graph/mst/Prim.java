package com.lzx.hsp.algorithm.graph.mst;

/**
 * @author LZX
 * @code @create 2022-08-20 13:13:44
 * 从某个顶点开始, 每次都遍历与当前子图连通的所有边, ( 这样的边的特点是 : 一个顶点被访问过了, 另一个未被访问 )
 * 选择其中权值最小的边和边中为访问的顶点加入当前子图
 */
public class Prim {
    public Graph creatMinTree(Graph graph, int vertex){
        // 创建最小生成树并初始化
        int vertexSize = graph.vertexes.size();
        Graph minTree = new Graph(vertexSize);
        for (int i = 0; i < vertexSize; i++) {
            String v = graph.vertexes.get(i);
            minTree.insertVertex(v);
        }
        // 标记节点是否被访问过
        boolean[] isVisited = new boolean[vertexSize];
        isVisited[vertex] = true;
        int v1;
        int v2;
        int minWeight;
        for (int i = 0; i < vertexSize - 1; i++) {// 一共需要生成 vertexSize - 1 条边
            v1 = -1;
            v2 = -1;
            minWeight = 100;
            // 遍历所有的边
            // 寻找一条新的边, 一个节点已访问, 另一个未被访问, 且权值最小
            for (int j = 0; j < vertexSize; j++) {
                if (!isVisited[j]){
                    continue;
                }
                for (int k = 0; k < vertexSize; k++) {
                    if (!isVisited[k] && graph.edges[j][k] < minWeight){
                        minWeight = graph.edges[j][k];
                        v1 = j;
                        v2 = k;
                    }
                }
            }
            isVisited[v2] = true;
            minTree.insertEdge(v1, v2, minWeight);
            System.out.println("**************** minWeight ****************");
            System.out.println("v1 = " + graph.vertexes.get(v1));
            System.out.println("v2 = " + graph.vertexes.get(v2));
            System.out.println("minWeight = " + minWeight);
        }

        return minTree;
    }
}
