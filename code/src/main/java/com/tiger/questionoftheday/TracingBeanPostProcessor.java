package com.tiger.questionoftheday;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author shihujiang
 * @date 2022/1/19
 */
@Component
public class TracingBeanPostProcessor implements InstantiationAwareBeanPostProcessor, SmartInitializingSingleton, InitializingBean, Order, DisposableBean {

    private static int i = 1;
    private static final String BEAN_NAME = "tigerBean";


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.contains(beanName)) {
            System.out.println(i++ + "Initialization【初始化】" + beanName + " after。");
        }

        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.contains(beanName)) {
            System.out.println(i++ + "Initialization【初始化】" + beanName + "' before。");
        }

        return null;
    }

    @Override
    public int value() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("TracingBeanPostProcessor 单例实例化之后 ");
    }

    /**
     * postProcessBeforeInstantiation调用时机为bean实例化(Instantiation)之前.
     * 如果返回了bean实例,则会替代原来正常通过target bean生成的bean的流程.典型的例如aop返回proxy对象.
     * 此时bean的执行流程将会缩短, 只会执行 BeanPostProcessor.postProcessAfterInitialization接口完成初始化。
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (BEAN_NAME.contains(beanName)) {
            System.out.println("Instantiation【实例化】" + beanName + " before。");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.contains(beanName)) {
            System.out.println("Instantiation【实例化】" + beanName + " after。");
        }
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet --> TracingBeanPostProcessor");
    }

    /**
     * postProcessProperties调用时机为postProcessAfterInstantiation执行之后并返回true,
     * 返回的PropertyValues将作用于给定bean属性赋值
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (bean instanceof BasicDataType) {
            //System.out.println(beanName + " 实例化 --- 后处理属性.testBean01:"+ pvs.getPropertyValue("testBean01"));
            System.out.println("Instantiation【实例化】 " + beanName + " 实例化后处理.param1:" + pvs.getPropertyValue("param1") + ";param3:" + pvs.getPropertyValue("param3"));
        }
        return null;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(getClass().toString() + " bean was destroy");
    }
}
