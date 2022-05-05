package com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shihujiang
 * @date 2022/1/26
 */
public class BeanMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("执行目标方法前。"+method);
        System.out.println("目标方法:"+method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("执行目标方法后。");

        return object;
    }
}
