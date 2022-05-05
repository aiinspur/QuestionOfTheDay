package com.tiger.questionoftheday.springevent.ordered;

import com.tiger.questionoftheday.springevent.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
@Slf4j
@Component
public class SendSmsListener implements SmartApplicationListener  {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserRegisterEvent.class;
    }

    @Override
    public int getOrder() {
        return 9;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        log.info("receive user register event 。send sms ");
    }
}
