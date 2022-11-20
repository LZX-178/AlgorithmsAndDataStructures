package com.lzx.hsp.algorithm.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-08-19 14:16:56
 * 假设存在若干个需要付费的广播台， 以及广播台信号可以覆盖的地区。
 * 如何选择最少的广播台， 让所有的地区都可以接收到信号
 */
public class SetCover {
    public HashSet<String> solution(HashMap<String, HashSet<String>> broadcasts, HashSet<String> allAreas){
        HashSet<String> result = new HashSet<>();
        HashSet<String> temp = new HashSet<>();

        String maxKey;
        while (allAreas.size() != 0){
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
                temp.clear();
                String broadcast = entry.getKey();
                HashSet<String> areas = entry.getValue();

                temp.addAll(areas);
                temp.retainAll(allAreas);

                if (temp.size() > 0 && (maxKey == null || temp.size() > broadcasts.get(maxKey).size())){
                    maxKey = broadcast;
                }
            }
            if (maxKey != null){
                result.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        return result;
    }
}
