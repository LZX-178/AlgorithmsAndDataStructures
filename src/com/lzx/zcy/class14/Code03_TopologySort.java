package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import org.junit.Test;

import java.util.*;

/**
 * @author LZX
 * @code @create 2022-10-27 10:21:25
 * 拓扑排序
 * 对于有向无环图, 依次输出入度为 0 的点, 输出后删除该点和以其开始的边
 */
public class Code03_TopologySort {
    public static List<Node> sortedTopology(Graph graph) {
        // 记录节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 存储入度为 0 的待打印结点
        Queue<Node> queue = new LinkedList<>();

        ArrayList<Node> list = new ArrayList<>();

        graph.nodes.forEach((i,node) -> {
            inMap.put(node, node.in);
            if (node.in == 0){
                queue.add(node);
            }
        });

        while (!queue.isEmpty()){
            Node node = queue.poll();
            list.add(node);

            for (Node next : node.nexts) {
                int newIn  = inMap.get(next)-1;
                if (newIn  == 0){
                    queue.add(next);
                }
                inMap.put(next, newIn );
            }
        }

        return list;
    }

    @Test
    public void test_sortedTopology() {
        int[][] matrix = {
                {1, 1, 2},
                {1, 2, 3},
                {1, 3, 4},
                {1, 4, 5},
                {1, 1, 3},
                {1, 3, 6},
                {1, 5, 6},
        };
        //   1  -> 2  ->  3   -> 4   ->   5
        //    \_________/>  \            /
        //                   \>        </
        //                         6
        Graph graph = new Graph(matrix);
        List<Node> list = sortedTopology(graph);
        list.forEach(System.out::println);
    }
}
