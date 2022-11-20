package com.lzx.hsp.algorithm.greedy;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author LZX
 * @code @create 2022-08-19 14:18:50
 */
public class SetCoverTest {
    //创建广播电台,放入到Map
    HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
    //allAreas 存放所有的地区
    HashSet<String> allAreas = new HashSet<>();
    
    @Test
    public void test_solution() {
        test_init();
        SetCover setCover = new SetCover();
        HashSet<String> result = setCover.solution(broadcasts, allAreas);
        System.out.println("******** result ***********");
        for (String next : result) {
            System.out.println(next);
        }
    }

    @Test
    public void test_init() {
        for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
            String broadcast = entry.getKey();
            HashSet<String> areas = entry.getValue();
            System.out.print(broadcast + " : ");
            areas.forEach(area -> System.out.print(area + " "));
            System.out.println();
        }
        System.out.println("******** allAreas **********");
        allAreas.forEach(area -> System.out.print(area + " "));
        System.out.println();
    }

    @Before
    public void init(){
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
    }
}
