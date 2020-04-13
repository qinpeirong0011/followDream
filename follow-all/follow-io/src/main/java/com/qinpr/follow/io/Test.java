package com.qinpr.follow.io;

import java.math.BigDecimal;

/**
 * Created by qinpr on 2019/5/6.
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(0);
        if (b1.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("小于等于0");
        } else {
            System.out.println("大于0");
        }
    }
}
