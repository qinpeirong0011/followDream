package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.store.config.MessageStoreConfig;
import com.qinpr.follow.messageQueue.store.util.LibC;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by qinpr on 2019/2/19.
 */
public class TransientStorePool {

    private final int poolSize;
    private final int fileSize;
    private final Deque<ByteBuffer> availableBuffers;
    private final MessageStoreConfig storeConfig;

    public TransientStorePool(final MessageStoreConfig storeConfig) {
        this.storeConfig = storeConfig;
        this.poolSize = storeConfig.getTransientStorePoolSize();
        this.fileSize = storeConfig.getMapedFileSizeCommitLog();
        this.availableBuffers = new ConcurrentLinkedDeque<>();
    }

    /**
     * 这里使用了堆外内存池化的方式，来对生命周期较短，但涉及到I/O操作的对象进行对外内存的使用(netty就使用了该方式)
     */
    public void init() {
        for (int i = 0; i < poolSize; i++) {
            //直接分配系统内存 区别于ByteBuffer.allocate(fileSize): 在jvm中分配内存
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);
            long address = ((DirectBuffer) byteBuffer).address();
            Pointer pointer = new Pointer(address);
            //通过mlock可以将进程使用的部分或者全部的地址空间锁定在物理内存中，防止被交换到swap空间
            LibC.INSTANCE.mlock(pointer, new NativeLong(fileSize));
            availableBuffers.offer(byteBuffer);
        }
    }

    public void destory() {
        for (ByteBuffer byteBuffer : availableBuffers) {
            final long address = ((DirectBuffer) byteBuffer).address();
            Pointer pointer = new Pointer(address);
            LibC.INSTANCE.munlock(pointer, new NativeLong(fileSize));
        }
    }

    public ByteBuffer borrowBuffer() {
        ByteBuffer byteBuffer = availableBuffers.pollFirst();
        if (availableBuffers.size() < poolSize * 0.4) {

        }
        return byteBuffer;
    }
}
