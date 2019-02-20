package com.qinpr.follow.messageQueue.store;

/**
 * Created by qinpr on 2019/2/20.
 */
public interface PutMessageLock {
    void lock();

    void unlock();
}
