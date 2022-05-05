package com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shihujiang
 * @date 2022/1/26
 */
@Configuration
public class BeanConfig {

    @Bean
    public InstantiationDemoBean instantiationDemoBean() {
        return new InstantiationDemoBean("0000");
    }
}
