package com.qinpr.follow.spring;

import com.qinpr.follow.spring.dao.TestDao;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by qinpr on 2020/4/27.
 */
//@Component
public class SpringFactory implements FactoryBean {

    Class mapperInterface;

    /**
     * for xml
     */
    public SpringFactory() {
        System.out.println("00");
    }

    public SpringFactory(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
        System.out.println("11");
    }

    @Override
    public Object getObject() throws Exception {
        Class[] clazzs = new Class[] {mapperInterface};
        Object object = Proxy.newProxyInstance(SpringFactory.class.getClassLoader(), clazzs, new DynamicProxyHandler());
        return object;
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
