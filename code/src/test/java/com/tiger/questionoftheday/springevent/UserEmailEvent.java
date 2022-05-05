package com.tiger.questionoftheday.springevent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
@Getter
public class UserEmailEvent extends ApplicationEvent {

    private User user;

    public UserEmailEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
