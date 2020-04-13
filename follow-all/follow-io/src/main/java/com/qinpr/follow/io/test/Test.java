package com.qinpr.follow.io.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by qinpr on 2019/5/13.
 */
public class Test {
    public static void main(String[] args) {
        Map<String, Integer> test = new ConcurrentHashMap<>();
        test.put("a", 1);


        Map<String, Integer> t1 = new ConcurrentHashMap<>();
        t1.put("x", 10);
        t1.put("y", 10);
        t1.put("z   ", 10);
    }
}
