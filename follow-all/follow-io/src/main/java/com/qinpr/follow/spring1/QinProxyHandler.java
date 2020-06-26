package com.qinpr.follow.spring1;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by qinpr on 2020/5/24.
 */
public class QinProxyHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("qinpr query db : " + method.getAnnotation(Select.class).value()[0]);
        return null;
    }
}
