package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import com.lzx.zcy.class14.graph.NodeHeap;
import javafx.util.Pair;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-11-13 14:06:42
 * 迪杰特斯拉单源最短路径算法
 * 对于没有负边的图, 从某个节点出发, 寻找该节点到其余节点的最短路径
 *      用一张表记录 当前 节点a 到其余节点的最短路径, 初始时 a-a为零, 其余距离为无穷大
 *      每次取出这张表中最小的距离 a-x, 并对 x节点 的所有 相邻节点y 做一次松弛操作 :
 *          a-y 的距离更新为 min( 原a-y , a-x  + x-y )
 */
public class Code06_Dijkstra {
    // 考虑使用其他结构的图, 代码会更简洁
    // 输入 出发节点 和 总节点个数, 输出该节点到其余节点的最短路径
    public HashMap<Node, Integer> dijkstra(Node head, int size) {
        // 用一张表记录 当前 节点a 到其余节点的最短路径
        NodeHeap heap = new NodeHeap(size);

        HashMap<Node, Integer> result = new HashMap<>();
        Pair<Node, Integer> pair;
        Node node;
        Integer distance;

        // 初始时 a-a为零, 其余距离为无穷大
        heap.put(head, 0);

        while (!heap.isEmpty()){
            pair = heap.get();
            // 每次取出这张表中最小的距离 a-x
            node = pair.getKey();
            distance = pair.getValue();

            result.put(node, distance);

            for (Node next : node.nexts) {
                // 并对 x节点 的所有 相邻节点y 做一次松弛操作
                heap.put(next, distance + node.getDistance(next));
            }
        }

        return result;
    }

    @Test
    public void test_dijkstra() {
        int[][] matrix = {
                {1, 1, 2},
                {3, 1, 3},
                {100, 2, 3},
                {2, 2, 4},
                {4, 4, 5},
                {50, 3, 5},
        };
        //                    1
        //           [1]/          \[3]
        //            </            \>
        //            2   --[100]--> 3
        //            \             /
        //          [2]\>         </[50]
        //              4 --[4]--> 5
        Graph graph = new Graph(matrix);
        HashMap<Node, Integer> res = dijkstra(graph.nodes.get(1), 5);
        for (Map.Entry<Node, Integer> entry : res.entrySet()) {
            System.out.println("node = " + entry.getKey().value + ", distance = " + entry.getValue());
        }
    }
}
