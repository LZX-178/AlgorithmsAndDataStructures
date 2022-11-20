package com.lzx.zcy.class08;


/**
 * @author LZX
 * @code @create 2022-09-12 10:46:59
 *
 * 基数排序
 */
public class Code04_RadixSort {
    // 要求数据必须是非负整数
    public void radixSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int[] temp = new int[arr.length];
        int digit;

        // 获得当前数组中最大数的位数
        int maxBit = getMaxBit(arr);

        // 遍历每一位, 在稳定的情况下, 按该位进行排序
        for (int i = 1; i <= maxBit; i++) {

            // 1 获取 count 数组, count数组记录的含义 :
            // 按该位进行排序后, temp 数组会按该位分成 10 段,
            // count数组 则记录这 10 段末尾的下标 (实际是 下标+1, 即前n段一共有多少个数)
            int[] count = new int[10];
            for (int k : arr) {
                digit = getDigit(k, i);
                count[digit]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j-1];
            }

            // 2 逆序遍历原数组
            // 按照 count数组 的指引, 将元素放入 temp 中对应的段
            // 注意 : 放入一个元素后, 段的末尾要递减
            for (int j = arr.length - 1; j >= 0; j--) {
                digit = getDigit(arr[j], i);
                temp[count[digit]-1] = arr[j];
                count[digit]--;
            }

//            // 3 拷贝 temp 至 原数组
//            System.arraycopy(temp, 0, arr, 0, arr.length);
            // 3 交换两个数组
            int[] t = temp;
            temp = arr;
            arr = t;
        }
        // 注意, 最后的 arr 必须指向原来的内存!!!,
        // 如果经过了偶数次交换
        //       arr(存储当前正确的解) 还是指向 原来的arr, 不需要额外操作
        // 如果经过了奇数次交换, arr 和 temp 的内存地址颠倒了,
        //      需要将 arr(存储当前正确的解) 拷贝 至 temp(原来的 arr)
        if ((maxBit % 2) == 1){
            System.arraycopy(arr, 0, temp, 0, arr.length);
        }
    }

    // 获得一个数组中最大的数的位数
    private int getMaxBit(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int maxBit = 0;
        while (max > 0){
            max /= 10;
            maxBit++;
        }

        return maxBit;
    }

    // 获得一个数的第几位是几, (个位为第一位)
    public int getDigit(int num, int d){
        int pow = (int) Math.pow(10, d - 1);
        return  (num / pow) % 10;
    }

}
