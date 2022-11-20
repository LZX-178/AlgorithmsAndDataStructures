package com.lzx.zcy.class14.graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}

	// 从邻接表建立图
	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	public Graph(int[][] matrix) {
		nodes = new HashMap<>();
		edges = new HashSet<>();
		for (int[] edgeInfo : matrix) {
			int weight = edgeInfo[0];
			int from = edgeInfo[1];
			int to = edgeInfo[2];

			if (!nodes.containsKey(from)) {
				nodes.put(from, new Node(from));
			}
			if (!nodes.containsKey(to)) {
				nodes.put(to, new Node(to));
			}

			Node fromNode = nodes.get(from);
			Node toNode = nodes.get(to);
			Edge edge = new Edge(weight, fromNode, toNode);

			fromNode.nexts.add(toNode);
			fromNode.out++;
			fromNode.edges.add(edge);

			toNode.in++;

			edges.add(edge);
		}
	}
}
