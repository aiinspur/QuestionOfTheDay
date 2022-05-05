package com.tiger.questionoftheday.springevent;

import com.tiger.questionoftheday.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shihujiang
 * @date 2022/2/15
 */
public class TestEntry extends BaseTest {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void publishEvent() {
        User user = User.builder().username("colorTiger").password("pwd!@#").build();
        //applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));
        //applicationContext.publishEvent(new UserEmailEvent(this, user));

    }
}
