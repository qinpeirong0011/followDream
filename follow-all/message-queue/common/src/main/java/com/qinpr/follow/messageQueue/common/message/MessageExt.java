package com.qinpr.follow.messageQueue.common.message;

import java.net.SocketAddress;

/**
 * Created by qinpr on 2019/2/19.
 */
public class MessageExt extends Message {
    private static final long serialVersionUID = 1965080233653796580L;

    private int queueId;

    private int storeSize;

    private long queueOffset;

    private int sysFlag;

    private long bornTimestamp;
    private SocketAddress bornHost;

    private long storeTimestamp;
    private SocketAddress storeHost;
    private String msgId;
    private long commitLogOffset;
    private int bodyCRC;
    private int reconsumeTimes;

    public MessageExt() {
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(final int queueId) {
        this.queueId = queueId;
    }

    public int getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(final int storeSize) {
        this.storeSize = storeSize;
    }

    public long getQueueOffset() {
        return queueOffset;
    }

    public void setQueueOffset(final long queueOffset) {
        this.queueOffset = queueOffset;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(final String msgId) {
        this.msgId = msgId;
    }

    public long getCommitLogOffset() {
        return commitLogOffset;
    }

    public void setCommitLogOffset(final long commitLogOffset) {
        this.commitLogOffset = commitLogOffset;
    }

    public int getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(final int sysFlag) {
        this.sysFlag = sysFlag;
    }

    public long getBornTimestamp() {
        return bornTimestamp;
    }

    public void setBornTimestamp(final long bornTimestamp) {
        this.bornTimestamp = bornTimestamp;
    }

    public SocketAddress getBornHost() {
        return bornHost;
    }

    public void setBornHost(final SocketAddress bornHost) {
        this.bornHost = bornHost;
    }

    public long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(final long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public SocketAddress getStoreHost() {
        return storeHost;
    }

    public void setStoreHost(final SocketAddress storeHost) {
        this.storeHost = storeHost;
    }

    public int getBodyCRC() {
        return bodyCRC;
    }

    public void setBodyCRC(final int bodyCRC) {
        this.bodyCRC = bodyCRC;
    }

    public int getReconsumeTimes() {
        return reconsumeTimes;
    }

    public void setReconsumeTimes(final int reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }
}
