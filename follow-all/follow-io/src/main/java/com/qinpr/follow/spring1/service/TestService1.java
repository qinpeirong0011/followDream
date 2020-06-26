package com.qinpr.follow.spring1.service;

import com.qinpr.follow.spring1.dao.TestDao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by qinpr on 2020/4/27.
 */
@Component
public class TestService1 {
    public TestService1() {
        System.out.println("load TestService111...");
    }

        @Autowired
        TestDao1 testDao1;

    public void query() {
        System.out.println(testDao1.query());
    }
}
