package com.lzx.hsp.algorithm.graph.spa;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-22 14:00:23
 */
public class DijkstraTest {
    Graph graph;

    @Test
    public void test_creatMinTree() {
        graph.show();
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
        final int N = 99;// 表示不可以连接
        //邻接矩阵的关系使用二维数组表示, N 表示两个点不联通
        int[][] weights = new int[][]{
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N},
        };

        graph.insertEdge(weights);
    }
}
