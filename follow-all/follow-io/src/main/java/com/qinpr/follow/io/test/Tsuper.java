package com.qinpr.follow.io.test;

/**
 * Created by qinpr on 2019/5/9.
 */
public class Tsuper implements TInterface {
    @Override
    public void t1() {
        System.out.println("1");
    }

    @Override
    public void t2() {
        t1();
        System.out.println("2");
    }
}
