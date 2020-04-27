package com.qinpr.follow.concurrent;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qinpr on 2020/4/20.
 */
public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
//        lock.lock();
//        lock.unlock();
//
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("1", "2");
//        String value = hashMap.put("1", "3");
//
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        treeMap.put("1", "2");
//        treeMap.put("1", "3");
//        String val = treeMap.get("1");
//
//        AtomicInteger atomicInteger = new AtomicInteger();
//        int i = atomicInteger.incrementAndGet();
//
//
//        AtomicReference a;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }
}
