package com.tiger.questionoftheday;

import com.tiger.questionoftheday.InstantiationAwareBeanPostProcessor.InstantiationDemoBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

/**
 * 实例化感知后处理器测试
 *
 * @author shihujiang
 * @date 2022/1/25
 */
@SpringBootTest
public class InstantiationAwareBeanPostProcessorTest extends BaseTest {

    @Test
    public void test() {
        InstantiationDemoBean demoBean = (InstantiationDemoBean) applicationContext.getBean("instantiationDemoBean");
        Assertions.assertEquals("0000", demoBean.getName());
    }




}
