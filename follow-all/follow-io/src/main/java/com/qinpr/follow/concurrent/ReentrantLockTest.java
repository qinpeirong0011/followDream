package com.qinpr.follow.concurrent;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qinpr on 2020/4/20.
 */
public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock(true);


    private static Object obj = new Object();

    volatile int a = 0;

    public void say() {
        List<String> strings = Collections.synchronizedList(new ArrayList<String>());
        Map<Object, Object> maps = Collections.synchronizedMap(new HashMap<>());
        Set<Object> sets = Collections.synchronizedSet(new HashSet<>());


        lock.lock();
        lock.unlock();
        synchronized (obj) {
            System.out.println("--------");
        }
    }

    public synchronized void talk() {

    }

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
        new ReentrantLockTest().say();

    }
}
