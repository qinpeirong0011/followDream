package com.qinpr.follow.proxy.cglib;

import com.qinpr.follow.proxy.ProxyTestImpl;
import com.qinpr.follow.proxy.ProxyTestService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by qinpr on 2020/4/27.
 */
public class CgligDynamicProxy {

    public static void main(String[] args) {
        ProxyCglibTestServiceImpl delegate = new ProxyCglibTestServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibProxyInterceptor(delegate));
        enhancer.setInterfaces(new Class[]{ProxyTestService.class});
        ProxyCglibTestServiceImpl cglibProxy = (ProxyCglibTestServiceImpl) enhancer.create();
        cglibProxy.sayMorning();
    }

    private static class CglibProxyInterceptor implements MethodInterceptor {

        final Object delegate;

        public CglibProxyInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("执行前2");
            return method.invoke(delegate, objects);
        }
    }
}
