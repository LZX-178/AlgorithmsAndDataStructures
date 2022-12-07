package com.lzx.zcy.class12;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LZX
 * @code @create 2022-12-07 13:36:33
 * 给定一个由字符串组成的数组 strs
 * 必须把所有的字符串拼接起来
 * 返回所有可能的拼接结果中，字典序最小的结果
 *
 * 例 [b, ba], 返回结果为 bab
 */
public class Code01_LowestLexicography {
    // 贪心算法, 贪心策略为 每次选择 "最小" 的字符串, 如果 a + b <= b + a, 则称 a <= b
    // 证明 :
    //      符号介绍 :
    //          假设原始集合为 S = {a, x1, x2, ..., xn}, 假设 a 为 s 中 "最小的"
    //          用 + 表示拼接
    //          用 min(S) 表示 S 所有可能的拼接结果中，字典序最小的结果
    //          用 s-a 表示集合 {x1, x2, ..., xn}
    //          则贪心策略的证明等价于以下命题的证明
    //                      min(S) =  a + min(s-a)
    //          即 S 所有可能的拼接结果中，字典序最小的结果 = 以 a 为开头, 其余字符串所有可能的拼接结果中，字典序最小的结果
    //      证明过程 :
    // 24行     原命题          min(S) = a + min(S-a)
    //          等价于   a + min(S-a) <= xi + min(S-xi) (以 a 开头优于以 其他 x 开头, 这里的 <= 指的是字典序)
    //          由于     a + min(S-a) <= a + xi + min(S-a-xi) <= xi + a + min(S-a-xi)  (因为 a 是 "最小的")
    //          要证明原命题只需证明 :
    //                        xi + a + min(S-a-xi) <=  xi + min(S-xi)
    //                  即         a + min(S-a-xi) <= min(S-xi)
    //                  即       在集合 S-xi 中, 以 a 开头的是最优的
    //  31行            即         min(S-xi) = a + min(S-a-xi)
    //          结合 24 行 和 31 行 : 可以不断从 S 中取出 x,
    //          直至集合中只剩余两个元素
    //                             min({a, xj}) = a + min({xj}) = a + xj
    //          则易证
    public String lowestString1(String[] strs) {
        Arrays.sort(strs, (s1, s2) -> (s1+s2).compareTo(s2+s1));
        StringBuilder ans = new StringBuilder();
        for (String str : strs) {
            ans.append(str);
        }
        return ans.toString();
    }


    @Test
    public void test_1() {
        String[] strs = {"b", "ab", "ba"};
//        String[] strs = {"bct", "abc", "cks"};

        String ans = lowestString1(strs);

        System.out.println("ans = " + ans);
    }
}
