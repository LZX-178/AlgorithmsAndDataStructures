package com.lzx.hsp.algorithm.search;

/**
 * @author LZX
 * @code @create 2022-08-03 8:50
 * 斐波那契查找, 利用斐波那契数列来确定 mid 值
 * 斐波那契数列 {1, 1, 2, 3, 5, 8, 13, 21, 34, 55 } 发现斐波那契数列的两个相邻数 的比例， 无限接近 黄金分割值 0.618
 * 斐波那契查找原理与前两种相似， 仅仅改变了中间结点（mid） 的位置， mid 不再是中间或插值得到， 而是位于黄金分割点附近，
 * 即 mid=low+F(k-1)-1（F 代表斐波那契数列）
 * 由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质， 可以得到 （F[k]-1） =（F[k-1]-1） +（F[k-2]-1） +1 。 该式说明：
 * 只要顺序表的长度为 F[k]-1， 则可以将该表分成长度为 F[k-1]-1 和 F[k-2]-1 的两段 从而中间位置为 mid=low+F(k-1)-1
 * 但顺序表长度 n 不一定刚好等于 F[k]-1， 所以需要将原来的顺序表长度 n 增加至 F[k]-1
 */
public class FibonacciSearch {
    public int i;
    public int[] fibonacciArray = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987,
                                    1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418,
                                    317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352,
                                    24157817, 39088169, 63245986, 102334155};
    public int search(int[] array, int findValue){
        if (findValue > array[array.length-1] || findValue < array[0]){
            return -1;
        }
        i = 0;
        return search(array, findValue, 0, array.length-1);
    }
    public int search(int[] array, int findValue, int start, int end){
        if (start > end){
            return -1;
        }
        // 确定 k 的值
        int k = 1;
        while (fibonacciArray[k]-1 < (end - start +1)){
            k++;
        }
        int mid = start + fibonacciArray[k-1]-1;
        System.out.println( " search " + ++i +
                " findValue = " + findValue +
                " array[start] = " + array[start] +
                " array[end] = " + array[end] +
                " start = " + start +
                " end = " + end +
                " mid = " + mid);
        int midValue = array[mid];
        if (midValue == findValue){
            return mid;
        }else if (midValue > findValue){
            return search(array, findValue, start, mid-1);
        }else {
            return search(array, findValue, mid+1, end);
        }
    }
}
