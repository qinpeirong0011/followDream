package com.qinpr.follow.messageQueue.common;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by qinpr on 2019/2/19.
 */
public abstract class ServiceThread implements Runnable {
    protected final Thread thread;

    protected volatile boolean stopped = false;

    public ServiceThread() {
        this.thread = new Thread(this, this.getServiceName());
    }

    public abstract String getServiceName();

    public void start() {
        this.thread.start();
    }

    public boolean isStopped() {
        return stopped;
    }

}
