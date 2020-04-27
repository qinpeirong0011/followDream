package com.qinpr.follow.jvm;

/**
 * Created by qinpr on 2020/4/17.
 */
public class OverwriteTest {
    public static class FakeFather {
        public Object test() {
            System.out.println("fakeFather test");
            return null;
        }
    }

    public static class FakeSon extends FakeFather {
        public String test() {
            System.out.println("fakeSon test");
            return null;
        }
    }

    public static void main(String[] args) {
        new FakeSon().test();
    }
}
