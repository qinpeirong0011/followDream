package com.qinpr.follow.spring;

import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by qinpr on 2020/4/27.
 */
public class ImportBeanDefintionRegistart implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        //给一个包名
        //遍历实现
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SpringFactory.class);
        GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
        genericBeanDefinition.setAutowireMode(3);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.qinpr.follow.spring.dao.XXXDao");

        registry.registerBeanDefinition("XXXDao", genericBeanDefinition);

    }
}
