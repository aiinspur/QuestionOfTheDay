package com.tiger.questionoftheday.springevent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/2/15
 */
@Component
@Slf4j
public class UserRegisterListener {


    @Async
    @EventListener(classes = UserRegisterEvent.class)
    @Order(9)
    public void sendSmsListener(UserRegisterEvent userRegisterEvent) {
        User user = userRegisterEvent.getUser();
        log.info("{},register监听-->send sms,{}", Thread.currentThread(), user);
    }


    @Async
    @EventListener(classes = UserRegisterEvent.class)
    @Order(12)
    public void registerListener(UserRegisterEvent userRegisterEvent) {
        User user = userRegisterEvent.getUser();
        log.info("{},register监听-->reg,{}", Thread.currentThread(), user);
    }


    //@Async
    @EventListener(UserRegisterEvent.class)
    @Order(6)
    public void recommendListener(UserRegisterEvent userRegisterEvent) {
        User user = userRegisterEvent.getUser();
        log.info("{},register监听-->recommend,{}", Thread.currentThread(), user);
    }

    @Order(4)
    //@Async
    @EventListener(UserEmailEvent.class)
    public void sendEmail(UserEmailEvent userEmailEvent) {
        User user = userEmailEvent.getUser();
        log.info("{},sendEmail监听,{}", Thread.currentThread().getName(), user);
    }

}
