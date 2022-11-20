package com.lzx.zcy.class14.graph;

public class Edge {
	public int weight;
	public Node from;
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "Edge{" +
				"weight=" + weight +
				", from=" + from +
				", to=" + to +
				'}';
	}
}
