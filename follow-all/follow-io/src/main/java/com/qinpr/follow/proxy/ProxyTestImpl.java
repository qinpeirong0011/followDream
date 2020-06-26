package com.qinpr.follow.proxy;

/**
 * Created by qinpr on 2020/4/27.
 */
public class ProxyTestImpl implements ProxyTestService {
    @Override
    public void sayHello() {
        System.out.println("say chinese hello1");
    }

    public void sayEnglish() {
        System.out.println("say english");
    }
}
