package com.tiger.questionoftheday.springevent;

import lombok.Builder;
import lombok.Data;

/**
 * @author shihujiang
 * @date 2022/2/15
 */
@Data
@Builder
public class User {

    private String username;

    private String password;
}
