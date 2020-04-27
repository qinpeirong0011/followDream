package com.qinpr.follow.io;

/**
 * Created by qinpr on 2020/4/14.
 */
public class CountGC {
    Object instance = null;

    public static void main(String[] args) {
        CountGC a = new CountGC();
        CountGC b = new CountGC();

        a.instance = b;
        b.instance = a;

        a = null;
        b = null;

        System.gc();
    }
}
