package com.qinpr.follow.spring1;

import com.qinpr.follow.spring1.dao.TestDao1;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * Created by qinpr on 2020/5/24.
 */
@Component
public class QinFactory implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Class[] clazzs = {TestDao1.class};
        TestDao1 testDao1 = (TestDao1) Proxy.newProxyInstance(Test.class.getClassLoader(), clazzs, new QinProxyHandler());
        return testDao1;
    }

    @Override
    public Class<?> getObjectType() {
        return TestDao1.class;
    }
}
