package com.lzx.hsp.algorithm.graph.mst;

import org.junit.Before;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-20 13:13:53
 */
public class PrimTest {
    Graph graph;

    @Test
    public void test_creatMinTree() {
        Prim prim = new Prim();
        Graph minTree = prim.creatMinTree(graph, 1);
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

        //邻接矩阵的关系使用二维数组表示,99 这个大数， 表示两个点不联通
        int[][] weights = new int[][]{
                                    {99,5,7,99,99,99,2},
                                    {5,99,99,9,99,99,3},
                                    {7,99,99,99,8,99,99},
                                    {99,9,99,99,99,4,99},
                                    {99,99,8,99,99,5,4},
                                    {99,99,99,4,5,99,6},
                                    {2,3,99,99,4,6,99},
                                    };

        graph.insertEdge(weights);
    }
}
