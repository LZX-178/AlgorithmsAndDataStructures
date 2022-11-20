package com.lzx.hsp.algorithm.graph.mst;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-20 13:13:53
 */
public class KruskalTest {
    Graph graph;

    @Test
    public void test_creatMinTree() {
        Kruskal kruskal = new Kruskal();
        Graph minTree = kruskal.creatMinTree(graph);
        System.out.println("********* graph ***********");
        graph.show();
        System.out.println("********* minTree ***********");
        minTree.show();
    }

    @Before
    public void init() {
        graph = new Graph(7);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        int INF = 99;

        //邻接矩阵的关系使用二维数组表示,99 这个大数， 表示两个点不联通
        int[][] weights = {
                            { INF,  12, INF, INF, INF,  16,  14},
                            {  12, INF,  10, INF, INF,   7, INF},
                            { INF,  10, INF,   3,   5,   6, INF},
                            { INF, INF,   3, INF,   4, INF, INF},
                            { INF, INF,   5,   4, INF,   2,   8},
                            {  16,   7,   6, INF,   2, INF,   9},
                            {  14, INF, INF, INF,   8,   9, INF}
        };

        graph.insertEdge(weights);
    }
}
