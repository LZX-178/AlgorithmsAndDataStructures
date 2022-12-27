package com.lzx.zcy.class19;

/*
 * 单调栈结构 :
 *      栈里的元素永远需要满足 栈底元素最小, 向上依次递增
 * 作用 :
 *      1 可以得到一个数组中任意一个元素 左右两边比他小的最近元素
 *      2 在一维数组中找第一个满足某种条件的数
 * 流程 :
 *      1 依次将数组元素压入单调栈,
 *          如果发现单调栈非法, 则弹出栈顶元素, 直至合法
 *          每次弹出元素 i 时, 记录 i 的左右两边比它小的最近元素,
 *              左边的结果为 i 的下一个元素,
 *              右边的结果为 要压入的元素
 *      2 单调栈依次弹栈, 并记录结果
 * 注意 :
 *      单调栈里元素i 的状态是, 左边比 i 小的元素已经找到, 等待一个右边比 i 小的元素来把它弹出去
 */


import com.lzx.utils.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LZX
 * @code @create 2022-12-27 11:35:08
 * 给定一个数组 arr, 对于数组中的每一个元素 i, 给出其 左右两边小于 i 的最近元素 的索引, 如果没有则返回 -1
 *
 * 例 :
 *             0  1  2  3  4  5  6  7
 *      arr = [3, 4, 2, 6, 1, 7, 0, 5]
 *
 *      答案 :
 *      0 -1  2
 *      1  0  2
 *      2 -1  4
 *      3  2  4
 *      4 -1  6
 *      5  4  6
 *      6 -1 -1
 *      7  6 -1
 */
public class Code01_MonotonousStack {

    // 对数器
    public int[][] getNearLessNoRepeat0(int[] arr) {
        int[][] res = new int[arr.length][2];
        // 只存位置！
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) { // 当遍历到i位置的数，arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int j = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = leftLessIndex;
                res[j][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[j][0] = leftLessIndex;
            res[j][1] = -1;
        }
        return res;
    }

    // 如果元素没有重复值, 使用单调栈即可
    public int[][] getNearLessNoRepeat1(int[] arr) {
        if (arr == null || arr.length == 0){
            return null;
        }

        int[][] result = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();

        // 将元素压栈
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                Integer t = stack.pop();
                result[t][0] = stack.isEmpty() ? -1 : stack.peek();
                result[t][1] = i;
            }
            stack.push(i);
        }

        // 剩余元素弹栈
        while (stack.size() > 1){
            Integer t = stack.pop();
            result[t][0] = stack.peek();
            result[t][1] = -1;
        }
        Integer t = stack.pop();
        result[t][0] = -1;
        result[t][1] = -1;

        return result;
    }


    // 对数器
    public int[][] getNearLess0(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    // 如果有重复值, 重复元素需要用数组存储
    public int[][] getNearLess1(int[] arr){

        if (arr == null || arr.length == 0){
            return null;
        }

        int[][] result = new int[arr.length][2];
        Stack<ArrayList<Integer>> stack = new Stack<>();

        // 将元素压栈
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                ArrayList<Integer> t = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer tt : t) {
                    result[tt][0] = l;
                    result[tt][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        // 剩余元素弹栈
        while (stack.size() > 1){
            ArrayList<Integer> t = stack.pop();
            int l = stack.peek().get(stack.peek().size()-1);
            for (Integer tt : t) {
                result[tt][0] = l;
                result[tt][1] = -1;
            }
        }
        ArrayList<Integer> t = stack.pop();
        for (Integer tt : t) {
            result[tt][0] = -1;
            result[tt][1] = -1;
        }

        return result;

    }

    // 改进方法1, 不使用数组
    public int[][] getNearLess2(int[] arr){

        if (arr == null || arr.length == 0){
            return null;
        }

        int[][] result = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> temp = new Stack<>();

        // 将元素压栈
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                // 获取弹出的元素 t
                Integer t = stack.pop();

                // 将与 t 相等的所有元素压入 temp
                temp.push(t);
                while (!stack.isEmpty() && arr[stack.peek()] == arr[t]){
                    temp.push(stack.pop());
                }

                // 弹出 temp 中所有元素并赋值
                int l = stack.isEmpty() ? -1 : stack.peek();
                while (!temp.isEmpty()){
                    t = temp.pop();
                    result[t][0] = l;
                    result[t][1] = i;
                }
            }
            stack.push(i);
        }

        // 剩余元素弹栈
        while (!stack.isEmpty()){
            // 获取弹出的元素 t
            Integer t = stack.pop();

            // 将与 t 相等的所有元素压入 temp
            temp.push(t);
            while (!stack.isEmpty() && arr[stack.peek()] == arr[t]){
                temp.push(stack.pop());
            }

            // 弹出 temp 中所有元素并赋值
            int l = stack.isEmpty() ? -1 : stack.peek();
            while (!temp.isEmpty()){
                t = temp.pop();
                result[t][0] = l;
                result[t][1] = -1;
            }
        }

        return result;
    }


    @Test
    public void test_getNearLessNoRepeat1() {
        int[] arr = {3, 4, 2, 6, 1, 7, 0, 5};

        int[][] ans0 = getNearLessNoRepeat0(arr);
        int[][] ans1 = getNearLessNoRepeat1(arr);

        System.out.println("***** " + "ans0" + " *****");
        for (int i = 0; i < ans0.length; i++) {
            for (int j = 0; j < ans0[i].length; j++) {
                System.out.print(ans0[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("***** " + "ans1" + " *****");
        for (int i = 0; i < ans1.length; i++) {
            for (int j = 0; j < ans1[i].length; j++) {
                System.out.print(ans1[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void test_getNearLessNoRepeat2() {
        for (int u = 0; u < 500000; u++) {
            int[] arr = ArrayUtils.generateRandomArray(10, 50, 1, 20);

            int[][] ans0 = getNearLessNoRepeat0(arr);
            int[][] ans1 = getNearLessNoRepeat1(arr);

            for (int i = 0; i < ans0.length; i++) {
                for (int j = 0; j < ans0[i].length; j++) {
                    if (ans0[i][j] != ans1[i][j]){
                        throw new RuntimeException("error");
                    }
                }

            }
        }
        System.out.println("OK");
    }

    @Test
    public void test_getNearLess1() {
        int[] arr = {3, 4, 1, 6, 1, 7, 0, 5};

        int[][] ans0 = getNearLess0(arr);
        int[][] ans1 = getNearLess1(arr);

        System.out.println("***** " + "ans0" + " *****");
        for (int i = 0; i < ans0.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < ans0[i].length; j++) {
                System.out.print(ans0[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("***** " + "ans1" + " *****");
        for (int i = 0; i < ans1.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < ans1[i].length; j++) {
                System.out.print(ans1[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void test_getNearLess2() {
        for (int u = 0; u < 500000; u++) {
            int[] arr = ArrayUtils.generateRandomArray(10, 50, 1, 20);

            int[][] ans0 = getNearLess0(arr);
            int[][] ans1 = getNearLess1(arr);
            int[][] ans2 = getNearLess2(arr);

            for (int i = 0; i < ans0.length; i++) {
                for (int j = 0; j < ans0[i].length; j++) {
                    if (ans0[i][j] != ans1[i][j] || ans0[i][j] != ans2[i][j] ){
                        throw new RuntimeException("error");
                    }
                }

            }
        }
        System.out.println("OK");
    }

}
