package com.tiger.questionoftheday.scope;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.devtools.remote.server.HttpStatusHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Future;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
@Slf4j
public class BeanScopeTest2 {


    @Test
    public void sumTest() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(25);
        executor.initialize();

        AccountService2 accountService2 = new AccountService2();
        for (int i = 0; i < 300; i++) {
            executor.submit(() -> {
                accountService2.add(1);
            });
        }

        Assertions.assertEquals(300, accountService2.getBalance());

    }

}
