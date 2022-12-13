package com.lzx.zcy.class16;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author LZX
 * @code @create 2022-11-18 9:57:39
 *
 * **************************** 效率待改进 ****************************
 *
 * 给定一个字符串 str ，给定一个字符串类型的数组 arr 。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，
 * 目的是拼出str来, 可以重复使用贴纸, 字符的范围是 a-z
 * 返回需要至少多少张贴纸可以完成这个任务, 如果不能完成, 则返回 -1。
 * 例：str = 'babac" ,arr= {"ba","c","abcd'}
 * 至少需要两张贴纸 "ba" 和 "abcd"，因为使用这两张贴纸，把每一个字符单独剪开，
 * 含有 2 个 a、2 个 b、1 个 c。是可以拼出 str 的。所以返回 2。
 * 测试
 * https://leetcode.cn/problems/stickers-to-spell-word/
 */
public class Code03_StickersToSpellWord {
    // 方法一, 暴力递归
    public int minStickers1(String[] stickers, String target) {
        int res = minStickers1(stickers, toCharArray(target), target.length());
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public int[] toCharArray(String string) {
        int[] result = new int[26];
        for (char c : string.toCharArray()) {
            result[c - 'a']++;
        }
        return result;
    }
    // rest 为 target 中 所有元素之和
    public int minStickers1(String[] stickers, int[] target, int rest) {
        if (rest == 0) {
            return 0;
        }
        int minResult = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            int[] charCount = toCharArray(sticker);
            int[] newTarget = Arrays.copyOf(target, target.length);
            int newRest = rest;
            // 根据下一张贴纸的选择进行分类
            for (int i = 0; i < charCount.length; i++) {
                int min = Math.min(target[i], charCount[i]);
                if (min > 0) {
                    newTarget[i] -= min;
                    newRest -= min;
                }
            }
            if (newRest != rest){
                minResult = Math.min(minResult, minStickers1(stickers, newTarget, newRest));
            }
        }
        return minResult == Integer.MAX_VALUE ? Integer.MAX_VALUE : minResult+1;
    }

    // 方法二, 记忆化搜索
    public int[][] stickersCount;
    public HashMap<String, Integer> result;

    public int minStickers2(String[] stickers, String target) {
        stickersCount = new int[stickers.length][];
        for (int i = 0; i < stickersCount.length; i++) {
            stickersCount[i] = toCharArray(stickers[i]);
        }
        result = new HashMap<>();

        int res = minStickers2(target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public int minStickers2(String target) {
        if (target.length() == 0) {
            return 0;
        }
        if (result.get(target) != null){
            return result.get(target);
        }

        int minResult = Integer.MAX_VALUE;
        for (int[] charCount : stickersCount) {
            int[] targetCount = toCharArray(target);
            StringBuilder newTarget = new StringBuilder();
            // 根据下一张贴纸的选择进行分类
            for (int i = 0; i < charCount.length; i++) {
                int min = Math.min(targetCount[i], charCount[i]);
                if (min > 0) {
                    targetCount[i] -= min;
                }
                for (int j = 0; j < targetCount[i]; j++) {
                    newTarget.append((char) ('a'+i));
                }
            }
            if (newTarget.length() != target.length()){
                minResult = Math.min(minResult, minStickers2(newTarget.toString()));
            }
        }
        int ans = minResult == Integer.MAX_VALUE ? Integer.MAX_VALUE : minResult+1;
        result.put(target, ans);
        return ans;
    }

    @Test
    public void test_minStickers() {
//        String[] stickers = {"ba", "c", "abcd" };
//        String target = "babac";

        String[] stickers = {
                "control","heart","interest","stream","sentence","soil","wonder","them",
//                "month","slip","table","miss","boat","speak","figure","no","perhaps","twenty",
//                "throw","rich","capital","save","method","store","meant","life","oil","string",
//                "song","food","am","who","fat","if","put","path","come","grow","box","great","word",
                "object","stead","common","fresh","the","operate","where","road","mean"};
        String target = "stoodcrease";

        int result = minStickers2(stickers, target);

        System.out.println("result = " + result);
    }
}
