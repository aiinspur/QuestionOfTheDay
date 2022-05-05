package com.tiger.questionoftheday.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author shihujiang
 * @date 2022/1/21
 */
public class CustomFactoryBean implements FactoryBean<TestBean01> {


    private TestBean01 testBean01;


    @Override
    public TestBean01 getObject() {
        System.out.println("getObject 返回 testBean01");
        testBean01 = new TestBean01();
        return testBean01;
    }

    @Override
    public Class<?> getObjectType() {
        return testBean01 == null ? TestBean01.class : testBean01.getClass();
    }
}
