package com.qinpr.follow.io.test;

/**
 * Created by qinpr on 2019/5/9.
 */
public class Tsub extends Tsuper {
    @Override
    public void t1() {
        System.out.println("tsub t1");
    }

    @Override
    public void t2() {
        System.out.println("tsub");
        super.t2();
    }

    public static void main(String[] args) {
        Tsub tsub = new Tsub();
        tsub.t2();
    }
}
