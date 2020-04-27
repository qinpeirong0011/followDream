package com.qinpr.follow.jvm;

/**
 * Created by qinpr on 2020/4/15.
 */
public class JVMStackDeep {
    private static int index = 1;

    public void call() {
        index ++;
        call();
    }

    public static void main(String[] args) {
        JVMStackDeep instance = new JVMStackDeep();
        try {
            instance.call();
        } catch (Throwable t) {
            System.out.println("JVM Stack Deep : " + index);
            t.printStackTrace();
        }
    }
}
