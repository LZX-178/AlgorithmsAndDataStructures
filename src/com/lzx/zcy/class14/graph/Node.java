package com.lzx.zcy.class14.graph;

import java.util.ArrayList;
import java.util.HashMap;

// 点结构的描述(有向图)
public class Node {
	public int value;
	// 节点的入度
	public int in;
	// 节点的出度
	public int out;
	// 节点的下一个节点
	public ArrayList<Node> nexts;
	// 节点的边
	public ArrayList<Edge> edges;

	public HashMap<Node, Integer> distance;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}

	// 获取当前结点到 to 的距离
	public int getDistance(Node to){
		if (distance == null){
			distance = new HashMap<>();
			for (Edge edge : edges) {
				distance.put(edge.to, edge.weight);
			}
		}
		return distance.get(to);
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}
}
