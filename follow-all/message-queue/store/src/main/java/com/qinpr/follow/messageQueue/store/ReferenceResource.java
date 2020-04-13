package com.qinpr.follow.messageQueue.store;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qinpr on 2019/2/19.
 */
public abstract class ReferenceResource {

    protected final AtomicLong refCount = new AtomicLong(1);
    protected volatile boolean available = true;
    protected volatile boolean cleanupOver = false;

    public synchronized boolean hold() {
        if (isAvailable()) {
            if (this.refCount.getAndIncrement() > 0 ) {
                return true;
            } else {
                this.refCount.getAndDecrement();
            }
        }
        return false;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void release() {
        long value = this.refCount.decrementAndGet();
        if (value > 0 ) {
            return;
        }

        synchronized (this) {
            this.cleanupOver = this.cleanup(value);
        }
    }

    public abstract boolean cleanup(final long currentRef);

    public boolean isCleanupOver() {
        return this.refCount.get() <= 0 && this.cleanupOver;
    }
}
