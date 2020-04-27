package com.qinpr.follow.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by qinpr on 2020/4/13.
 */
public class JVMDemo {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        int index=0;
        while (true) {
            list.add(new User(index++, UUID.randomUUID().toString()));
        }
    }

    static class User {
        int i;

        String str;

        public User(int i, String str) {
            this.i = i;
            this.str = str;
        }
    }
}
