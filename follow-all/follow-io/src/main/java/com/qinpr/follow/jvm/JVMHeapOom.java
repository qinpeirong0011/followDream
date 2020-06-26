package com.qinpr.follow.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinpr on 2020/4/15.
 */
public class JVMHeapOom {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        boolean flag = true;
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]); //每次增加一个1M大小的数据对象
            } catch (Throwable t) {
                t.printStackTrace();
                flag = false;
                System.out.println("总执行次数:" + i);
            }
        }
    }
}
