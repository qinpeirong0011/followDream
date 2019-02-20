package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.ServiceThread;
import com.qinpr.follow.messageQueue.common.UtilAll;
import com.qinpr.follow.messageQueue.store.config.FlushDiskType;

/**
 * Created by qinpr on 2019/2/19.
 */
public class CommitLog {

    private final MappedFileQueue mappedFileQueue;
    private final DefaultMessageStore defaultMessageStore;
    private final FlushCommitLogService flushCommitLogService;

    private final FlushCommitLogService commitLogService;

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
        this.putMessageLock = defaultMessageStore.getMessageStoreConfig().isUseReentrantLockWhenPutMessage() ?
                new PutMessageReentrantLock() : new PutMessageSpinLock();
    }

    public void start() {
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
}
