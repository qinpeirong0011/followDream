package com.qinpr.follow.messageQueue.store;

/**
 * Created by qinpr on 2019/2/19.
 */
public interface MessageStore {

    void start() throws Exception;

    void shutdown();

    void destroy();

    PutMessageResult putMessage(final MessageExtBrokerInner msg);

    long now();
}
