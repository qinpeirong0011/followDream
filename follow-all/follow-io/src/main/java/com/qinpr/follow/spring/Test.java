package com.qinpr.follow.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by qinpr on 2020/4/27.
 */
public class Test {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        ac.getBean(TestDao.class).query();

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
//        ac.getBean(TestDao.class).query();

//        Class[] clazzs = {TestDao.class};
//        TestDao testDao = (TestDao) Proxy.newProxyInstance(Test.class.getClassLoader(), clazzs, new DynamicProxyHandler());
//        testDao.query();

//        SqlSession session = null;
//        TestDao mapper = session.getMapper(TestDao.class);
//        ac.getBeanFactory().registerSingleton("xxx", testDao);
//        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
//        genericBeanDefinition.setAutowireMode();

    }
}
