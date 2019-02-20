package com.qinpr.follow.messageQueue.store;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by qinpr on 2019/2/20.
 */
public class PutMessageSpinLock implements PutMessageLock {

    private AtomicBoolean putMessageSpinLock = new AtomicBoolean(true);

    @Override
    public void lock() {
        boolean flag;
        do {
            flag = this.putMessageSpinLock.compareAndSet(true, false);
        } while (!flag);
    }

    @Override
    public void unlock() {
        this.putMessageSpinLock.compareAndSet(false, true);
    }
}
