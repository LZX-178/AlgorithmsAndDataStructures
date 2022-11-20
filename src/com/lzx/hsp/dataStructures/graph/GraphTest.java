package com.lzx.hsp.dataStructures.graph;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-08-15 15:19:25
 */
public class GraphTest {
    public Graph graph;

    public void init1(){
        graph = new Graph(5);
        String[] Vertexes = {"A", "B", "C", "D", "E"};
        for (String vertex : Vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 1);
        graph.insertEdge("C", "B", 1);
        graph.insertEdge("E", "B", 1);
        graph.insertEdge("D", "B", 1);

    }
    public void init2(){
        graph = new Graph(8);
        String[] Vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertex : Vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge("1", "2", 1);
        graph.insertEdge("1", "3", 1);
        graph.insertEdge("2", "4", 1);
        graph.insertEdge("2", "5", 1);
        graph.insertEdge("4", "8", 1);
        graph.insertEdge("5", "8", 1);
        graph.insertEdge("3", "6", 1);
        graph.insertEdge("3", "7", 1);
    }

    @Test
    public void test_show() {
//        init1();
        init2();
        graph.show();
    }

    @Test
    public void test_DepthFirstSearch() {
//        init1();
        init2();
        graph.DepthFirstSearch();
    }

    @Test
    public void test_BroadFirstSearch() {
//        init1();
        init2();
        graph.BroadFirstSearch();
    }
}
