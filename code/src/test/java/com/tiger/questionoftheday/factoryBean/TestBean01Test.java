package com.tiger.questionoftheday.factoryBean;

import com.tiger.questionoftheday.BasicDataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBean01Test {

    @Autowired
    TestBean01 testBean01;

    @Autowired
    BasicDataType basicDataType;

    @Test
    public void test001() {

        Assertions.assertEquals("welcome", basicDataType.getParam1());
        Assertions.assertEquals("toString:testbean01", testBean01.toString());


    }


}