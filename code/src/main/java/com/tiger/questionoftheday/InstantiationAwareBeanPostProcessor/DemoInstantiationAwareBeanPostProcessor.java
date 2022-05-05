package com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/1/26
 */
@Component
public class DemoInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass == InstantiationDemoBean.class) {
            System.out.println("InstantiationDemoBean 实例化 After");
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new BeanMethodInterceptor());
            InstantiationDemoBean demoBean = (InstantiationDemoBean) enhancer.create();
            System.out.println("返回动态代理对象。");
            return demoBean;
        }


        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {

        if (bean instanceof InstantiationDemoBean) {
            System.out.println("InstantiationDemoBean 实例化 After");
        }

        // 返回值会影响postProcessProperties是否执行，返回false不执行
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("beanName:" + beanName + "执行..postProcessProperties");

        return pvs;
    }
}
