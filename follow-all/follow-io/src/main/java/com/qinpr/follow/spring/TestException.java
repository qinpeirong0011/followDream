package com.qinpr.follow.spring;

/**
 * Created by qinpr on 2020/4/29.
 */
public class TestException {

    public void t1(int a) throws Exception {
        System.out.println("test-------------");
        if (a > 0) {
            throw new Exception("123");
        }
    }

    public static void main(String[] args) {
        TestException t = new TestException();
        try {
            t.t1(10);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
