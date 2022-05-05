package com.tiger.questionoftheday.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
@Slf4j
public class AccountService2 {

    //static ThreadLocal<Integer> ctx = new ThreadLocal();

    private int balance = 0;

    public synchronized void add(int a) {
        balance = (balance + a);
        log.info(Thread.currentThread().getName() + " " + balance);
    }

    public synchronized int getBalance() {
        return balance;
    }


}
