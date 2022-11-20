package com.lzx.zcy.class04;

import com.lzx.utils.ArrayUtils;
import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-09-08 16:34:35
 */
public class Code02_SmallSum_test {
    @Test
    public void test_smallSum() {
        int[] array = {1,3,4,2,5};
        Code02_SmallSum smallSum = new Code02_SmallSum();
        int sum = smallSum.smallSum(array);
        System.out.println("sum = " + sum);

        for (int i = 0; i < 50000; i++) {
            int[] array1 = ArrayUtils.generateRandomArray(100, -100, 100);
            int[] array2 = new int[array1.length];
            System.arraycopy(array1, 0, array2, 0, array1.length);

            int sum1 = smallSum.smallSum(array1);
            int sum2 = smallSum.comparator(array2);

            System.out.println(i + "---" + (sum1 == sum2));

            if (sum1 != sum2){
                throw new RuntimeException("error");
            }
        }
    }
}
