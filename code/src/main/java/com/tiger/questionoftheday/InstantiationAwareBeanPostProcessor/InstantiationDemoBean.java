package com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor;

/**
 * @author shihujiang
 * @date 2022/1/26
 */
public class InstantiationDemoBean {

    private String name;

    public InstantiationDemoBean(){

    }

    public InstantiationDemoBean(String name){
        System.out.println("构造函数被调用.");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InstantiationDemoBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
