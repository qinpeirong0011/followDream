package com.qinpr.follow.jvm;

/**
 * Created by qinpr on 2020/4/16.
 */
public class JVMCommandTest {
    private static int index = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println(index++);
        }
    }
}
