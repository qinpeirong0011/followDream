package com.qinpr.follow.proxy.jdk;

import com.qinpr.follow.proxy.ProxyTestImpl;
import com.qinpr.follow.proxy.ProxyTestService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by qinpr on 2020/4/27.
 */
public class JDKDynamicProxy {

    public static void main(String[] args) {
        ProxyTestImpl delegate = new ProxyTestImpl();
        ProxyTestService jdkProxy = (ProxyTestService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{ProxyTestService.class},
                new JDKProxyHandler(delegate));
        jdkProxy.sayHello();
    }

    public static class JDKProxyHandler implements InvocationHandler {

        final Object delegate;

        public JDKProxyHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("执行前");
            return method.invoke(delegate, args);
        }
    }

}
