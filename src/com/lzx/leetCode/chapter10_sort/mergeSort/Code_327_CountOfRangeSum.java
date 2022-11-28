package com.lzx.leetCode.chapter10_sort.mergeSort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-11-25 15:36:05
 *
 * 327. 区间和的个数
 *
 *      给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 *      求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 *      区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 *      题目数据保证
 *          答案是一个 32 位 的整数
 *          lower <= upper
 *
 *  方法1 : 暴力遍历
 *  方法2 : 引入排序,
 *              原数组的序是不能动的
 *              子数组的和 是可以排序的, 而题目正是考察 子数组的和 的大小
 *                  对一段数组 [start, end]来说, 如果解决了这段数组的 子数组的和 对结果的影响
 *                  那么对其两端的数组来说 就只关心 以 start 和 end 为端点的子数组, 且这些子数组的序可以是任意的
 *  方法3 : 方法2的改进, 将二分查找改为滑动指针
 *  方法4 : 方法3 的优雅版本, 换了一种建模方式
 */
public class Code_327_CountOfRangeSum {
    /*************************************** 方法1 超出时间限制 ***************************************/
    // 遍历所有子数组, 时间复杂度 O(n2)
    public int countRangeSum1(int[] nums, int lower, int upper) {
        int counts = 0;
        long[] arr = new long[nums.length];


        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j < arr.length; j++) {
                arr[j] += nums[i];
                if (arr[j] <= upper && arr[j] >= lower){
                    counts++;
                }
            }
        }

        return counts;
    }

    /*************************************** 方法2 执行时间 113ms ***************************************/
    // 引入排序, 如果子数组的和是有序的话, 就不需要去遍历所有子数组了,
    // sumsLeft 为前缀和数组
    //      是自底向上逐步生成的
    //      在区间 [start, end] 上计算所有 以 start 开头的子数组的和
    //      并 从小到大 排序
    // sumsRight 为后缀和数组
    //      是自底向上逐步生成的
    //      在区间 [start, end] 上计算所有 以 end 结尾的子数组的和
    //      并 从大到小 排序
    // 在对 sumsLeft 和 sumsRight 归并排序的过程中
    //      默认 左数组 和 右数组 的 子数组 已经全部检查过了, 需要检查 跨越左右 的子数组, 即 :
    //      统计 左数组 的 sumsRight 和 右数组的 sumsLeft 之和 落在 [lower, upper] 范围的个数, 即为答案
    //      相当于遍历(从右往左) 左数组 sumsRight[i]
    //          对每一个 sumsRight[i] 去右数组上 二分搜索 [upper - sumsRight[i], lower - sumsRight[i]]
    //          得到答案 indexR 和 indexL
    //              这个 merge 过程是 O( n * log n ) 的, 算上 merge 对数组的二分, 总复杂度是 O( log n * n * log n )

    //              由于 sumsRight[i] 也是递增的, 搜索到的 indexR 和 indexL 一定是逐步向左移动的
    //              所以每次二分搜索可以从上一次的结果开始, 即 indexR 和 indexL 不回退

    //              似乎 merge 过程复杂度可以达到 O( n ) ?
    //              注意, indexR 和 indexL 虽然不回退, 但 每次需要去 二分搜索
    //              merge 过程复杂度 应该为以下 n 项相加 :
    //                  log n + loglog n + ... + log...log n
    //              merge 过程复杂度 应该介于 O( n ) (至少应该遍历左数组一次)和 O( n * log n ) (以上n项会小于 n项log n 相加)之间
    //              总复杂度会 介于 O( log n * n ) 和 O( log n * n * log n ) 之间 ?
    public int[] nums;
    public long[] sumsLeft;
    public long[] sumsRight;

    public int count;
    public long lower;
    public long upper;

    public int countRangeSum2(int[] nums, int lower, int upper) {
        this.nums = nums;
        this.sumsLeft = new long[nums.length];

        this.count = 0;
        this.lower = lower;
        this.upper = upper;

        // sumsLeft 和 sumsRight 初始状态均为 原数组的拷贝
        for (int i = 0; i < sumsLeft.length; i++) {
            sumsLeft[i] = nums[i];
        }
        this.sumsRight = Arrays.copyOf(sumsLeft, sumsLeft.length);

        // mergeAndCount 的返回值为 原数组上的 [start, end] 区间元素之和
        // 即此处的返回值为 对原数组求总和
        mergeAndCount2(0, nums.length-1);

        return this.count;
    }
    // 返回值为 原数组上的 [start, end] 区间元素之和
    private long mergeAndCount2(int start, int end) {
        if (start == end){
            if (nums[start] <= upper && nums[start] >= lower){
                count++;
            }
            return nums[start];
        }

        // 对左数组 和 右数组 做处理
        int mid = start + ((end - start)>>1);
        long sumLeft = mergeAndCount2(start, mid);
        long sumRight = mergeAndCount2(mid+1, end);

        // 从右往左遍历 左数组的 sumsRight(后缀和), 在右数组的 sumsLeft(前缀和) 上进行二分查找
        // 初始的查找范围为 [mid+1, end]
        int indexL = end, indexR = end;
        for (int i = mid; i >= start; i--) {
            indexR = binarySearchRight(mid + 1, indexR, upper - sumsRight[i]);
            if (indexR == -1){
                // 右边界非法, 说明当前 左数组的元素 太大了, 再往后只会更大, 退出循环
                break;
            }
            indexL = binarySearchLeft(mid + 1, indexL, lower - sumsRight[i]);
            if (indexL == -1){
                // 左边界非法, 说明当前 左数组的元素 不够大了, 进行下一次循环
                indexL = end;
                continue;
            }
            count += indexR - indexL + 1;
        }


        // 维护 sumsLeft(前缀和) 数组
        // 归并规则为
        //      左数组的 sumsLeft 不变
        //      右数组的 sumsLeft 全部元素加上 左数组的 元素总和(sumLeft)
        int i = start, j = mid+1, index = 0;
        long[] tempL = new long[end-start+1];
        while (i <= mid && j <= end){
            tempL[index++] = sumsLeft[i] < sumsLeft[j] + sumLeft ? sumsLeft[i++] : sumsLeft[j++] + sumLeft;
        }
        while (i <= mid){
            tempL[index++] = sumsLeft[i++];
        }
        while (j <= end){
            tempL[index++] = sumsLeft[j++] + sumLeft;
        }
        System.arraycopy(tempL, 0, sumsLeft, start, end-start+1);


        // 维护 sumsRight(后缀和) 数组
        // 归并规则为
        //      左数组的 sumsRight 全部元素加上 右数组的 元素总和(sumRight)
        //      右数组的 sumsRight 不变
        long[] tempR = new long[end-start+1];
        i = start;
        j = mid+1;
        index = 0;
        while (i <= mid && j <= end){
            tempR[index++] = sumsRight[i] + sumRight >= sumsRight[j] ? sumsRight[i++] + sumRight : sumsRight[j++];
        }
        while (i <= mid){
            tempR[index++] = sumsRight[i++] + sumRight;
        }
        while (j <= end){
            tempR[index++] = sumsRight[j++];
        }
        System.arraycopy(tempR, 0, sumsRight, start, end-start+1);

        // 返回 左数组 和 右数组 的元素之和
        return sumLeft + sumRight;
    }
    // 计算 sumsLeft[start, end] 范围上  大于等于 lower 的 index,
    // start < end
    // 如果不存在则返回 -1
    private int binarySearchLeft(int start, int end, long lower) {
        if (sumsLeft[start] >= lower){
            return start;
        }
        if (sumsLeft[end] < lower){
            return -1;
        }
        int index = end;
        int mid;
        while (start <= end){
            mid = start + ((end-start)>>1);
            if (sumsLeft[mid] >= lower){
                index = mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return index;
    }
    // 计算 sums[start, end] 范围上  小于等于 upper 的 index,
    // start < end
    // 如果不存在则返回 -1
    private int binarySearchRight(int start, int end, long upper) {
        if (sumsLeft[end] <= upper){
            return end;
        }
        if (sumsLeft[start] > upper){
            return -1;
        }
        int index = start;
        int mid;
        while (start <= end){
            mid = start + ((end-start)>>1);
            if (sumsLeft[mid] <= upper){
                index = mid;
                start = mid+1;
            }else {
                end = mid-1;
            }
        }
        return index;
    }

    /*************************************** 方法3 执行时间 93ms ***************************************/
    // 对方法2 做出改进
    // 参考左神的解, 将 二分搜索 改为 滑动指针
    // merge 过程的复杂度来到 O( n ), 总复杂度来到 O( log n * n )
    public int countRangeSum3(int[] nums, int lower, int upper) {
        this.nums = nums;
        this.sumsLeft = new long[nums.length];

        this.count = 0;
        this.lower = lower;
        this.upper = upper;

        // sumsLeft 和 sumsRight 初始状态均为 原数组的拷贝
        for (int i = 0; i < sumsLeft.length; i++) {
            sumsLeft[i] = nums[i];
        }
        this.sumsRight = Arrays.copyOf(sumsLeft, sumsLeft.length);

        // mergeAndCount 的返回值为 原数组上的 [start, end] 区间元素之和
        // 即此处的返回值为 对原数组求总和
        mergeAndCount3(0, nums.length-1);

        return this.count;
    }
    // 返回值为 原数组上的 [start, end] 区间元素之和
    private long mergeAndCount3(int start, int end) {
        if (start == end){
            if (nums[start] <= upper && nums[start] >= lower){
                count++;
            }
            return nums[start];
        }

        // 对左数组 和 右数组 做处理
        int mid = start + ((end - start)>>1);
        long sumLeft = mergeAndCount3(start, mid);
        long sumRight = mergeAndCount3(mid+1, end);

        // 将 二分搜索 改为 滑动指针
        // 从右往左遍历 左数组的 sumsRight(后缀和), 在右数组的 sumsLeft(前缀和) 上进行查找
        // 初始的查找范围为 [mid+1, end]
        int indexL = -1, indexR = end;
        for (int i = mid; i >= start; i--) {

            while (indexR >= mid+1){
                if (sumsRight[i] + sumsLeft[indexR] <= upper){
                    break;
                }else {
                    indexR--;
                }
            }
            if (indexR == mid){
                break;
            }

            // 如果是第一次查找,  indexL 从 indexR+1 开始,
            // 否则沿用上一次的 indexL
            indexL = indexL == -1 ? indexR+1 : indexL;

            while (indexL > mid+1){
                if (sumsRight[i] + sumsLeft[indexL-1] >= lower){
                    indexL--;
                }else {
                    break;
                }
            }
            count += indexR - indexL + 1;
        }


        // 维护 sumsLeft(前缀和) 数组
        // 归并规则为
        //      左数组的 sumsLeft 不变
        //      右数组的 sumsLeft 全部元素加上 左数组的 元素总和(sumLeft)
        int i = start, j = mid+1, index = 0;
        long[] tempL = new long[end-start+1];
        while (i <= mid && j <= end){
            tempL[index++] = sumsLeft[i] < sumsLeft[j] + sumLeft ? sumsLeft[i++] : sumsLeft[j++] + sumLeft;
        }
        while (i <= mid){
            tempL[index++] = sumsLeft[i++];
        }
        while (j <= end){
            tempL[index++] = sumsLeft[j++] + sumLeft;
        }
        System.arraycopy(tempL, 0, sumsLeft, start, end-start+1);


        // 维护 sumsRight(后缀和) 数组
        // 归并规则为
        //      左数组的 sumsRight 全部元素加上 右数组的 元素总和(sumRight)
        //      右数组的 sumsRight 不变
        long[] tempR = new long[end-start+1];
        i = start;
        j = mid+1;
        index = 0;
        while (i <= mid && j <= end){
            tempR[index++] = sumsRight[i] + sumRight >= sumsRight[j] ? sumsRight[i++] + sumRight : sumsRight[j++];
        }
        while (i <= mid){
            tempR[index++] = sumsRight[i++] + sumRight;
        }
        while (j <= end){
            tempR[index++] = sumsRight[j++];
        }
        System.arraycopy(tempR, 0, sumsRight, start, end-start+1);

        // 返回 左数组 和 右数组 的元素之和
        return sumLeft + sumRight;
    }

    /*************************************** 方法4 执行时间 98ms ***************************************/
    // 左神的解
    // 先计算出 前缀和数组 preSum (第一位为 0), 子数组[start, end]的和 = preSum[end+1] - preSum[start]
    // 也就是说, preSum 中 任意 i < j (必须保证), preSum[j] - preSum[i] 代表一个原数组中子数组的和
    // 再对前缀和数组进行归并排序
    public int countRangeSum4(int[] nums, int lower, int upper) {
        long[] preSum = new long[nums.length+1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        return mergeAndCount4(preSum, 0, preSum.length - 1, lower, upper);

    }

    private int mergeAndCount4(long[] preSum, int start, int end, int lower, int upper) {
        if (start == end){
            // 这种情况是非法的
            return 0;
        }

        int mid = start + ((end-start)>>1);

        int countLeft = mergeAndCount4(preSum, start, mid, lower, upper);
        int countRight = mergeAndCount4(preSum, mid+1, end, lower, upper);

        int ans = 0;
        int indexLeft = mid+1, indexRight = -1;
        for (int i = start; i <= mid; i++) {
            while (indexLeft <= end){
                if (preSum[indexLeft] >= lower+preSum[i]){
                    break;
                }else {
                    indexLeft++;
                }
            }
            if (indexLeft > end){
                break;
            }
            indexRight = Math.max(indexRight, indexLeft);

            while (indexRight <= end){
                if (preSum[indexRight] <= upper+preSum[i]){
                    indexRight++;
                }else {
                    break;
                }
            }
            ans += indexRight - indexLeft;
        }

        long[] temp = new long[end - start + 1];
        int i = start, j = mid+1, index=0;
        while (i <= mid && j <= end){
            temp[index++] = preSum[i] <= preSum[j] ? preSum[i++] : preSum[j++];
        }
        while (i <= mid){
            temp[index++] = preSum[i++];
        }
        while (j <= end){
            temp[index++] = preSum[j++];
        }
        System.arraycopy(temp, 0, preSum, start, temp.length);

        return ans + countLeft + countRight;
    }

    @Test
    public void test_method2() {
        int[] arr = {-2, 5, -1, 5, -6};
//        int[] arr = {-2, 5, -1};
        int lower = -2;
        int upper = 2;

        int sum1 = countRangeSum1(arr, lower, upper);
        int sum2 = countRangeSum2(arr, lower, upper);
        int sum3 = countRangeSum3(arr, lower, upper);
        int sum4 = countRangeSum4(arr, lower, upper);
        System.out.println("sum1 = " + sum1);
        System.out.println("sum2 = " + sum2);
        System.out.println("sum3 = " + sum3);
        System.out.println("sum4 = " + sum4);
        System.out.println("Arrays.toString(sumsLeft) = " + Arrays.toString(sumsLeft));
        System.out.println("Arrays.toString(sumsRight) = " + Arrays.toString(sumsRight));
    }
}
