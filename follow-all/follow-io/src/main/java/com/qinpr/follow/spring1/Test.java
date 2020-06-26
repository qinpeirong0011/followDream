package com.qinpr.follow.spring1;

import com.qinpr.follow.spring1.app.AppConfig;
import com.qinpr.follow.spring1.dao.TestDao1;
import com.qinpr.follow.spring1.service.TestService1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qinpr on 2020/5/24.
 */
public class Test {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        ac.getBean(TestDao1.class).query();

        HashMap<String, String> map = new HashMap<>(17);
        map.put("1","a");
        map.put("1","b");
        map.put("16","a");
        System.out.println("0000000000");

//        ArrayList<String> list = new ArrayList<>();
//        list.add("1");

        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), queue);



    }
}
