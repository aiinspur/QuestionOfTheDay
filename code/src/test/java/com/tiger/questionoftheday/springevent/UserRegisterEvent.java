package com.tiger.questionoftheday.springevent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author shihujiang
 * @date 2022/2/15
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
