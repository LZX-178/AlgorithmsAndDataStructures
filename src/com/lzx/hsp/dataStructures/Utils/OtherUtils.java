package com.lzx.hsp.dataStructures.Utils;

/**
 * @author LZX
 * @code @create 2022-08-15 14:14:57
 */
public class OtherUtils {
    //生成指定长度的空格
    public static String getBlank(int numberOfSpaces){
        if (numberOfSpaces < 1){
            return "";
        }
        return String.format("%" + numberOfSpaces + "s", "");
    }
}
