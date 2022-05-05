package com.tiger.questionoftheday.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shihujiang
 * @date 2022/2/16
 */
@Slf4j
@Service
public class AccountService {

    //static ThreadLocal<Integer> ctx = new ThreadLocal();

    private volatile int balance = 0;

    public synchronized void add(int a) {
        balance = (balance + a);
        log.info(Thread.currentThread().getName() + " " + balance);
    }

    public synchronized int getBalance() {
        return balance;
    }


}
