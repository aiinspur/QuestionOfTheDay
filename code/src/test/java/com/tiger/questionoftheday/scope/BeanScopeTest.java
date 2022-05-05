package com.tiger.questionoftheday.scope;

import com.tiger.questionoftheday.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
public class BeanScopeTest extends BaseTest {

    @Autowired
    AccountService accountService;

    @Test
    public void sumTest() throws InterruptedException {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(25);
        executor.initialize();

        for (int i = 0; i < 30; i++) {
            executor.execute(() -> accountService.add(1));
        }

        //Thread.sleep(10000);

        Assertions.assertEquals(30, accountService.getBalance());


    }

}
