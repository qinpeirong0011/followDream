package com.qinpr.follow.messageQueue.store;

import java.nio.ByteBuffer;

/**
 * Created by qinpr on 2019/2/20.
 */
public interface AppendMessageCallback {
    AppendMessageResult doAppend(final long fileFromOffset, final ByteBuffer byteBuffer,
                                 final int maxBlank, final MessageExtBrokerInner msg);
}
