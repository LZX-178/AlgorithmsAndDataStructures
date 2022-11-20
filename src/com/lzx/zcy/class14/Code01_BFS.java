package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LZX
 * @code @create 2022-10-27 9:41:33
 * 宽度优先遍历
 */
public class Code01_BFS {
    public static void breadthFirstSearch(Node start) {
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);

        while (!queue.isEmpty()){
            start = queue.poll();

            System.out.println(start);

            for (Node next : start.nexts) {
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    @Test
    public void test_bfs() {
        int[][] matrix = {
                {1, 1, 2},
                {1, 2, 1},
                {1, 1, 3},
                {1, 1, 4},
                {1, 2, 3},
                {1, 2, 5},
                {1, 3, 4},
                {1, 3, 4},
                {1, 4, 3},
                {1, 4, 6},
                {1, 5, 6},
                {1, 6, 4},
        };
        //           1
        //    <//>    \>     \>
        //  2     ->   3  <-->  4
        //    \>            <//>
        //      5    ->   6
        Graph graph = new Graph(matrix);
        breadthFirstSearch(graph.nodes.get(1));
    }
}
