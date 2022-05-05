package com.tiger.questionoftheday;

import com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor.InstantiationDemoBean;
import com.tiger.questionoftheday.beanDefinitionRegistryPostProcessor.TigerBean;
import com.tiger.questionoftheday.factoryBean.CustomFactoryBean;
import com.tiger.questionoftheday.factoryBean.TestBean01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 */
@SpringBootApplication
public class QuestionofthedayApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuestionofthedayApplication.class, args);
    }


    @Bean
    public TestBean01 testBean01() {
        CustomFactoryBean factoryBean = new CustomFactoryBean();
        return factoryBean.getObject();
    }

}
