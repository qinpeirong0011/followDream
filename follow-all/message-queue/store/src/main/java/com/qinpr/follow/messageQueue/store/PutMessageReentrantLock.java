package com.qinpr.follow.messageQueue.store;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qinpr on 2019/2/20.
 */
public class PutMessageReentrantLock implements PutMessageLock {

    private ReentrantLock putMessageNormalLock = new ReentrantLock();

    @Override
    public void lock() {
        putMessageNormalLock.lock();
    }

    @Override
    public void unlock() {
        putMessageNormalLock.unlock();
    }
}
