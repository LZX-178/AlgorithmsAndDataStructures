package com.lzx.hsp.algorithm.graph.mst;

/**
 * @author LZX
 * @code @create 2022-08-22 11:17:15
 */
public class Edge {
    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
