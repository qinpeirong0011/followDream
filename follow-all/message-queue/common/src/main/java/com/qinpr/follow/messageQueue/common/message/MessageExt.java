package com.qinpr.follow.messageQueue.common.message;

/**
 * Created by qinpr on 2019/2/19.
 */
public class MessageExt extends Message {
    private static final long serialVersionUID = 1965080233653796580L;

    private int queueId;

    private int storeSize;

    private long queueOffset;

    private String msgId;
    private long commitLogOffset;
    private int bodyCRC;
    private int reconsumeTimes;

    public MessageExt() {
    }
}
