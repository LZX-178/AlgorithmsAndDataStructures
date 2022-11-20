package com.lzx.zcy.class12;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-10-25 19:55:41
 *
 * 输入矩阵的宽, 生成环行矩阵
 *         1   2   3   4
 *         12  13  14  5
 *         11  16  15  6
 *         10   9  8   7
 */
public class Code06_spiralOrderGenerate {

    public void generate1(int n){
        int[][] arr = new int[n][n];

        int count = 0; // 要显示的数据
        int maxX = n - 1; // x轴的最大下标
        int maxY = n - 1; // Y轴的最大下标
        int minX = 0; // x轴的最小下标
        int minY = 0; // Y轴的最小下标
        while (minX <= maxX) {
            for (int x = minX; x <= maxX; x++) {
                arr[minY][x] = ++count;
            }
            minY++;
            for (int y = minY; y <= maxY; y++) {
                arr[y][maxX] = ++count;
            }
            maxX--;
            for (int x = maxX; x >= minX; x--) {
                arr[maxY][x] = ++count;
            }
            maxY--;
            for (int y = maxY; y >= minY; y--) {
                arr[y][minX] = ++count;
            }
            minX++;
        }

        for (int[] ints : arr) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    // 只需要正常遍历二维数组一次
    public void generate2(int n){

        int up = 0, down = 0;
        int[][] nums = new int[n][n];
        int[] upPoints = new int[n/2+1];
        int[] downPoints = new int[n/2+1];
        int i, j;

        //计算第一行和最后一行的值，并记录下两个拐点
        for(j = 0; j < nums[0].length; j++) {
            nums[0][j] = j+1;
        }
        upPoints[up++] = nums[0][j-1];
        for(j = 0; j < nums[n-1].length; j++) {
            nums[n-1][j] = 3*n-2 - j;
        }
        downPoints[down++] = nums[n-1][0];

        //计算其余行的值
        //1   2   3   4
        //12  13  14  5
        //11  16  15  6
        //10   9  8    7
        for(i = 2; i < nums.length; i++ ) {
            if(i%2 == 0) {
                j = 0;
                for(int ptDown = 0; ptDown < down; ptDown++) {
                    nums[i/2][j++] =  downPoints[ptDown] + n-1-i/2-ptDown;
                }
                for(; j < n - up; j++) {
                    nums[i/2][j] = nums[i/2][j-1] + 1;
                }
                upPoints[up++] = nums[i/2][j-1];

                for(int ptUp = 0; ptUp < up-1; ptUp++) {
                    nums[i/2][j++] =  upPoints[up-ptUp-2] + ptUp + 1;
                }
            }else {
                j = nums[n-i/2-1].length-1;
                for(int ptUp = 0; ptUp < up; ptUp++) {
                    nums[n-i/2-1][j--] =  upPoints[ptUp] + n-i/2-1 - ptUp;
                }

                for(; j > down-1; j--) {
                    nums[n-i/2-1][j] = nums[n-i/2-1][j+1] + 1;
                }
                downPoints[down++] = nums[n-i/2-1][j+1];

                for(int ptDown = 0; ptDown < down-1; ptDown++) {
                    nums[n-i/2-1][j--] =  downPoints[down-ptDown-2] + 1 + ptDown;
                }
            }
        }

        int  sum = 0;
        for(i = 0; i < nums.length; i++) {
            for(j = 0; j < nums[i].length; j++ ) {
                sum += nums[i][j];
                System.out.print(nums[i][j] + "\t");
            }
            System.out.println();

        }
        System.out.println(sum);
        System.out.println((1 + n*n)*n*n/2);


    }

    @Test
    public void test_1() {
        System.out.println("***** " + "generate1" + " *****");
        generate1(6);
        System.out.println("***** " + "generate2" + " *****");
        generate2(6);
    }

}
