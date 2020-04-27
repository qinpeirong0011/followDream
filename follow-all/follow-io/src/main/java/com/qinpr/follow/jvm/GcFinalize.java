package com.qinpr.follow.jvm;

import java.util.concurrent.TimeUnit;

/**
 * Created by qinpr on 2020/4/14.
 */
public class GcFinalize {
    String a  = "abc"; //在方法区的常量池中存储
    static GcFinalize gcFinalize = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize exec");
        gcFinalize = this;
    }


    public static void main(String[] args) throws InterruptedException {
        gcFinalize = new GcFinalize();
        gcFinalize = null;

        System.gc();
        TimeUnit.MILLISECONDS.sleep(1000);

        if (gcFinalize != null) {
            System.out.println("alive...");
        } else {
            System.out.println("dead...");
        }
    }
}
