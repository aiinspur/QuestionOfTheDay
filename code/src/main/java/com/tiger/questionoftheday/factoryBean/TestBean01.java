package com.tiger.questionoftheday.factoryBean;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * @author shihujiang
 * @date 2022/1/21
 */
public class TestBean01 implements SmartInitializingSingleton {

    public TestBean01() {
        System.out.println("TestBean01的空构造方法被调用");
    }

    @Override
    public String toString() {
        return "toString:testbean01";
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("TestBean01 单例实例化之后 ");
    }
}
