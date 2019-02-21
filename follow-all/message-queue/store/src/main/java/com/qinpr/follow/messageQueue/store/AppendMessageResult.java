package com.qinpr.follow.messageQueue.store;

/**
 * Created by qinpr on 2019/2/20.
 */
public class AppendMessageResult {

    private AppendMessageStatus status;
    private long wroteOffset;
    private int wroteBytes;
    private String msgId;
    private long storeTimestamp;
    private long logicsOffset;
    private long pagecacheRT = 0;

    public AppendMessageResult(AppendMessageStatus status) {
        this(status, 0, 0, "", 0, 0, 0);
    }

    public AppendMessageResult(final AppendMessageStatus status, final long wroteOffset, final int wroteBytes, final String msgId, final long storeTimestamp, final long logicsOffset, final long pagecacheRT) {
        this.status = status;
        this.wroteOffset = wroteOffset;
        this.wroteBytes = wroteBytes;
        this.msgId = msgId;
        this.storeTimestamp = storeTimestamp;
        this.logicsOffset = logicsOffset;
        this.pagecacheRT = pagecacheRT;
    }

    public AppendMessageStatus getStatus() {
        return status;
    }

    public void setStatus(final AppendMessageStatus status) {
        this.status = status;
    }

    public long getWroteOffset() {
        return wroteOffset;
    }

    public void setWroteOffset(final long wroteOffset) {
        this.wroteOffset = wroteOffset;
    }

    public int getWroteBytes() {
        return wroteBytes;
    }

    public void setWroteBytes(final int wroteBytes) {
        this.wroteBytes = wroteBytes;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(final String msgId) {
        this.msgId = msgId;
    }

    public long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(final long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public long getLogicsOffset() {
        return logicsOffset;
    }

    public void setLogicsOffset(final long logicsOffset) {
        this.logicsOffset = logicsOffset;
    }

    public long getPagecacheRT() {
        return pagecacheRT;
    }

    public void setPagecacheRT(final long pagecacheRT) {
        this.pagecacheRT = pagecacheRT;
    }
}
