package com.lzx.hsp.dataStructures.stack;

import org.junit.Test;

/**
 * @author LZX
 * @code @create 2022-07-26 16:01
 */
public class CalculatorTest {
    @Test
    public void test_run() {
        Calculator calculator = new Calculator();
        String[] expressions ={
                "1 + 1",
                "2 - 3",
                " 4 *5",
                " 88/  4",
                "(5-6 )*( 9+ 6)",
                "   8/4 +5*9/3*6- 7",
                "7+2*6-4",
                "(7+8)*8- 6",
                "(1+(4+5+2)-3)+(6+8)",
                "-1 + 2",
                "-(1+2)",
                "-1",
                "(-1)",
                " -   8/4 +5*9/3*6- 7",
                "(5-6 )*(-( 9+ 6))",
                "(5-6 )*( - 9+ 6)",
        };
        int[] anss = {
           2,
           -1,
           20,
           22,
           -15,
           85,
           15,
           114,
           23,
           1,
           -3,
           -1,
           -1,
            81,
            15,
            3,
        };
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            int ans = anss[i];
            int result = calculator.calculate(expression);
            System.out.println("expression : " + expression + " = "  + result);
            System.out.println("ans = " + ans);
            if (ans != result){
                throw new RuntimeException("error");
            }
        }
    }
}
