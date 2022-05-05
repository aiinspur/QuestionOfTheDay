package com.tiger.questionoftheday;

import com.tiger.questionoftheday.beanDefinitionRegistryPostProcessor.TigerBean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author shihujiang
 * @date 2022/1/25
 */
@SpringBootTest
public class BeanDefinitionRegistryPostProcessorTest extends BaseTest {

    @Test
    public void test() {
        TigerBean tigerBean = (TigerBean) applicationContext.getBean("tigerBean");
        assertNotNull(tigerBean);
        assertEquals("tiger-name-updated", tigerBean.getName());
    }
}
