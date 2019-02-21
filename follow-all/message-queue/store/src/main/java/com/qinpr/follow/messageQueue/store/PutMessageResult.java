package com.qinpr.follow.messageQueue.store;

/**
 * Created by qinpr on 2019/2/20.
 */
public class PutMessageResult {
    private PutMessageStatus putMessageStatus;
    private AppendMessageResult appendMessageResult;

    public PutMessageResult(final PutMessageStatus putMessageStatus, final AppendMessageResult appendMessageResult) {
        this.putMessageStatus = putMessageStatus;
        this.appendMessageResult = appendMessageResult;
    }
}
