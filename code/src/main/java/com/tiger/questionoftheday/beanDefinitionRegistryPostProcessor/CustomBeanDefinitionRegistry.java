package com.tiger.questionoftheday.beanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/1/25
 */
@Component
public class CustomBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("【BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry】【Bean定义注册】自定义bean注册-tigerBean");
        GenericBeanDefinition customBeanDefinition = new GenericBeanDefinition();
        customBeanDefinition.setBeanClass(TigerBean.class);
        customBeanDefinition.setScope("singleton");
        customBeanDefinition.setLazyInit(false);
        customBeanDefinition.setAutowireCandidate(true);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name","setting name by custom bean definition registry.");
        customBeanDefinition.setPropertyValues(propertyValues);

        registry.registerBeanDefinition("tigerBean",customBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
