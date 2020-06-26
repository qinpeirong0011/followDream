package com.qinpr.follow.spring;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by qinpr on 2020/4/27.
 */
public class DynamicProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("query db : " + method.getAnnotation(Select.class).value()[0]);
        return null;
    }
}
