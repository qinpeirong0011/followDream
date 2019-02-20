package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.BrokerConfig;
import com.qinpr.follow.messageQueue.store.config.MessageStoreConfig;
import com.qinpr.follow.messageQueue.store.config.StorePathConfigHelper;
import com.qinpr.follow.messageQueue.store.stats.BrokerStatsManager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by qinpr on 2019/2/19.
 */
public class DefaultMessageStore implements MessageStore {

    // 存储相关的配置
    private final MessageStoreConfig messageStoreConfig;
    // CommitLog 核心处理类，消息存储在commitlog文件中
    private final CommitLog commitLog;
    //topic的消费队列
    private final ConcurrentMap<String/* topic */, ConcurrentMap<Integer/* queueId */, ConsumeQueue>> consumeQueueTable;
    //消息达到监听器
    private final MessageArrivingListener messageArrivingListener;
    private final BrokerConfig brokerConfig;
    //Broker统计服务
    private final BrokerStatsManager brokerStatsManager;
    //MappedFile分配线程，RocketMQ使用内存映射处理commitlog,consumeQueue文件
    private final AllocateMappedFileService allocateMappedFileService;
    //对外内存池化
    private final TransientStorePool transientStorePool;

    private RandomAccessFile lockFile;

    private FileLock lock;

    private volatile boolean shutdown = true;

    boolean shutDownNormal = false;

    public DefaultMessageStore(final MessageStoreConfig messageStoreConfig, final BrokerStatsManager brokerStatsManager,
                               final MessageArrivingListener messageArrivingListener, final BrokerConfig brokerConfig) throws IOException {
        this.messageArrivingListener = messageArrivingListener;
        this.brokerConfig = brokerConfig;
        this.messageStoreConfig = messageStoreConfig;
        this.brokerStatsManager = brokerStatsManager;
        this.allocateMappedFileService = new AllocateMappedFileService(this);
        this.commitLog = new CommitLog(this);
        this.consumeQueueTable = new ConcurrentHashMap<>(32);

        this.transientStorePool = new TransientStorePool(messageStoreConfig);

        if (messageStoreConfig.isTransientStorePoolEnable()) {
            transientStorePool.init();
        }

        this.allocateMappedFileService.start();

        File file = new File(StorePathConfigHelper.getLockFile(messageStoreConfig.getStorePathRootDir()));
        MappedFile.ensureDirOK(file.getParent());
        lockFile = new RandomAccessFile(file, "rw");
    }

    public MessageStoreConfig getMessageStoreConfig() {
        return messageStoreConfig;
    }

    public TransientStorePool getTransientStorePool() {
        return transientStorePool;
    }

    public AllocateMappedFileService getAllocateMappedFileService() {
        return allocateMappedFileService;
    }

    @Override
    public void start() throws Exception {
        lock = lockFile.getChannel().tryLock(0, 1, false);
        if (lock == null || lock.isShared() || lock.isValid()) {
            throw new RuntimeException("Lock failed, MQ already started");
        }

        lockFile.getChannel().write(ByteBuffer.wrap("lock".getBytes()));
        lockFile.getChannel().force(true);

        this.commitLog.start();
        this.shutdown = false;
    }
}
