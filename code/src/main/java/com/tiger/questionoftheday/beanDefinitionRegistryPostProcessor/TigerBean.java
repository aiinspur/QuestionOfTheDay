package com.tiger.questionoftheday.beanDefinitionRegistryPostProcessor;

/**
 * @author shihujiang
 * @date 2022/1/25
 */
public class TigerBean {

    public TigerBean() {

    }

    public TigerBean(String name) {
        this.name = name;

    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RegistryBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
