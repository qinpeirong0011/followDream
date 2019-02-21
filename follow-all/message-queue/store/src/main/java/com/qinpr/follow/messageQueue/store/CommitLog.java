package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.ServiceThread;
import com.qinpr.follow.messageQueue.common.UtilAll;
import com.qinpr.follow.messageQueue.store.config.FlushDiskType;

import java.nio.ByteBuffer;

/**
 * Created by qinpr on 2019/2/19.
 */
public class CommitLog {

    private final MappedFileQueue mappedFileQueue;
    private final DefaultMessageStore defaultMessageStore;
    private final FlushCommitLogService flushCommitLogService;

    private final FlushCommitLogService commitLogService;

    private final AppendMessageCallback appendMessageCallback;
    private volatile long beginTimeInLock = 0;
    private final PutMessageLock putMessageLock;

    public CommitLog(final DefaultMessageStore defaultMessageStore) {
        this.mappedFileQueue = new MappedFileQueue(defaultMessageStore.getMessageStoreConfig().getStorePathCommitLog(),
                defaultMessageStore.getMessageStoreConfig().getMapedFileSizeCommitLog(), defaultMessageStore.getAllocateMappedFileService());
        this.defaultMessageStore = defaultMessageStore;

        if (FlushDiskType.SYNC_FLUSH == defaultMessageStore.getMessageStoreConfig().getFlushDiskType()) {
            flushCommitLogService = new GroupCommitService();
        } else {
            flushCommitLogService = new FlushRealTimeService();
        }

        this.commitLogService = new CommitRealTimeService();
        this.appendMessageCallback = new DefaultAppendMessageCallback(defaultMessageStore.getMessageStoreConfig().getMaxMessageSize());
        this.putMessageLock = defaultMessageStore.getMessageStoreConfig().isUseReentrantLockWhenPutMessage() ?
                new PutMessageReentrantLock() : new PutMessageSpinLock();
    }

    public void start() {
        //数据刷盘服务
        this.flushCommitLogService.start();
        if (defaultMessageStore.getMessageStoreConfig().isTransientStorePoolEnable()) {
            this.commitLogService.start();
        }
    }

    public PutMessageResult putMessage(final MessageExtBrokerInner msg) {
        msg.setStoreTimestamp(System.currentTimeMillis());
        msg.setBodyCRC(UtilAll.crc32(msg.getBody()));

        AppendMessageResult result = null;
        String topic = msg.getTopic();
        int queueId = msg.getQueueId();

        MappedFile mappedFile = this.mappedFileQueue.getLastMappedFile();
        putMessageLock.lock();

        try {
            long beginLockTimestamp = this.defaultMessageStore.getSystemClock().now();
            this.beginTimeInLock = beginLockTimestamp;
            msg.setStoreTimestamp(beginLockTimestamp);

            if (null == mappedFile || mappedFile.isFull()) {
                mappedFile = this.mappedFileQueue.getLastMappedFile(0);
            }
            if (null == mappedFile) {
                beginTimeInLock = 0;
                return new PutMessageResult(PutMessageStatus.CREATE_MAPEDFILE_FAILED, null);
            }

            result = mappedFile.appendMessage(msg, this.appendMessageCallback);

        } finally {
            putMessageLock.unlock();
        }

        return null;
    }

    abstract class FlushCommitLogService extends ServiceThread {
        protected static final int RETRY_TIMES_OVER = 10;
    }

    class GroupCommitService extends FlushCommitLogService {

        @Override
        public String getServiceName() {
            return GroupCommitService.class.getSimpleName();
        }

        @Override
        public void run() {

        }
    }

    class FlushRealTimeService extends FlushCommitLogService {

        @Override
        public String getServiceName() {
            return FlushRealTimeService.class.getSimpleName();
        }

        @Override
        public void run() {

        }
    }

    class CommitRealTimeService extends FlushCommitLogService {

        @Override
        public String getServiceName() {
            return CommitRealTimeService.class.getSimpleName();
        }

        @Override
        public void run() {

        }
    }

    class DefaultAppendMessageCallback implements AppendMessageCallback {

        public DefaultAppendMessageCallback(final int size) {
        }

        @Override
        public AppendMessageResult doAppend(final long fileFromOffset, final ByteBuffer byteBuffer, final int maxBlank, final MessageExtBrokerInner msg) {
            return null;
        }
    }
}
