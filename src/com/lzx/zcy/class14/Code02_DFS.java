package com.lzx.zcy.class14;

import com.lzx.zcy.class14.graph.Graph;
import com.lzx.zcy.class14.graph.Node;
import org.junit.Test;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-10-27 10:02:44
 * 深度优先遍历
 */
public class Code02_DFS {

    public static void depthFirstSearch(Node start) {
        if (start == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        System.out.println(start);
        set.add(start);
        stack.push(start);

        while (!stack.isEmpty()){
            start = stack.pop();
            for (Node next : start.nexts) {
                if (!set.contains(next)){
                    System.out.println(next);
                    set.add(next);
                    stack.push(start);
                    stack.push(next);
                    break;
                }
            }
        }
    }

    @Test
    public void test_dfs() {
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
        depthFirstSearch(graph.nodes.get(1));
    }

}
