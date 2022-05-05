package com.tiger.questionoftheday.extensionPoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/1/21
 */
@Component
public class BeanFactoryPostProcessorExtension implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        for (int i = 1; i <= beanDefinitionNames.length; i++) {
//            System.out.println(i + " bean definition name: 【" + beanDefinitionNames[i-1]+"】");
//        }

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("tigerBean");
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        if (beanDefinition != null && mpv != null){
            PropertyValue value = mpv.getPropertyValue("name");
            System.out.print("【BeanFactoryPostProcessor.postProcessBeanFactory】修改之前 name 的value是："+value.getValue()+"\n");
            value.setConvertedValue("tiger-name-updated");
        }


//        String beanName = "basicDataType";
//        System.out.println(beanFactory.getBeanDefinition(beanName).getBeanClassName());


    }
}
