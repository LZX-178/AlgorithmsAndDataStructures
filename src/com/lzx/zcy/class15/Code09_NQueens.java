package com.lzx.zcy.class15;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-11-15 15:19:58
 * N 皇后问题, 参考 zcy 的代码, 带改进, 将判断当前局面是否合法, 改进成哪些位置是合法的
 */
public class Code09_NQueens {
    // 返回 n 皇后问题的解法数量, n < 32, 非递归解法
    public int nQueens(int n){

        int[] arr = new int[n];
        int limited = 1 << (n-1);
        int result = 0;
        boolean flag = false;

        for (int j = 0; j < n; j++) {
            arr[j] = 1;
        }


        int i = 0;
        while (true){
            while (arr[i] <= limited){
                flag = isLegal(arr, i);
                if (flag){
                    break;
                }
                arr[i] <<= 1;
            }
            if (flag){
                flag = false;
                if (i < n-1){
                    i++;
                }else {
                    result++;
                    arr[i] <<= 1;
                }
            }else {
                if (i == 0){
                    break;
                }
                arr[i] = 1;
                i--;
                arr[i] <<= 1;
            }
        }

        return result;
    }

    // 判断 [0, i] 行是否合法
    private boolean isLegal(int[] arr, int i) {
        for (int j = 0; j < i; j++) {
            if (arr[j] == arr[i]){
                return false;
            }
            if (arr[j] > arr[i]){
                if (arr[j] == (arr[i] << (i - j))){
                    return false;
                }
            }else {
                if (arr[i] == (arr[j] << (i - j))){
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test_nQueens() {
        for (int i = 1; i < 10; i++) {
            int result = nQueens(i);
            System.out.println("i = " + i + ", result = " + result);
        }
    }
}
