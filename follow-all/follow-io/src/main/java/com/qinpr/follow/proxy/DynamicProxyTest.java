package com.qinpr.follow.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;

/**
 * Created by qinpr on 2020/4/27.
 */
public class DynamicProxyTest {
    public static void main(String[] args) throws Exception {
        CountServiceImpl delegate = new CountServiceImpl();
        long start = System.currentTimeMillis();
        CountService jdkProxy = createJDKProxy(delegate);
        System.out.println("create jdk Proxy cost : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        CountService cglibProxy = createCglibProxy(delegate);
        System.out.println("create cglib Proxy cost : " + (System.currentTimeMillis() - start));


        start = System.currentTimeMillis();
        CountService javassistProxy = createJavassistProxy(delegate);
        System.out.println("create javassist Proxy cost : " + (System.currentTimeMillis() - start));

        System.out.println("====================================");
        for (int i = 0; i < 3; i++) {
            test(jdkProxy, "Run JDK Proxy");
            test(cglibProxy, "Run Cglig Proxy");
            test(javassistProxy, "Run Javassist Proxy");
            System.out.println("----------------------------------");
        }
    }

    private static void test(CountService service, String label) throws Exception {
        service.count(); //warm up
        int count = 100000000;
        long time = System.currentTimeMillis();
        for (int i=0; i<count; i++) {
            service.count();
        }
        time = System.currentTimeMillis() - time;
        System.out.println(label + " " + time + "ms, " + new DecimalFormat().format(count * 1000/ time) + " t/s");
    }

    private static CountService createJDKProxy(final CountService delegate) {
        CountService jdkProxy = (CountService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{CountService.class},
                new JDKHandler(delegate));
        return jdkProxy;
    }

    private static class JDKHandler implements InvocationHandler {

        final Object delegate;

        public JDKHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(delegate, args);
        }
    }

    public static CountService createCglibProxy(final CountService delegate) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibInterceptor(delegate));
        enhancer.setInterfaces(new Class[]{CountService.class});
        CountService cglibProxy = (CountService) enhancer.create();
        return cglibProxy;
    }

    private static class CglibInterceptor implements MethodInterceptor {

        final Object delegate;

        public CglibInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return method.invoke(delegate, objects);
        }
    }

    public static CountService createJavassistProxy(final CountService delegate) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{CountService.class});
        Class<?> proxyClass = proxyFactory.createClass();
        CountService javassistProxy = (CountService) proxyClass.newInstance();
        ((ProxyObject) javassistProxy).setHandler(new JavaAssitInterceptor(delegate));
        return javassistProxy;
    }

    public static class JavaAssitInterceptor implements MethodHandler {

        final Object delegate;

        public JavaAssitInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
            return method.invoke(delegate, objects);
        }
    }
}
