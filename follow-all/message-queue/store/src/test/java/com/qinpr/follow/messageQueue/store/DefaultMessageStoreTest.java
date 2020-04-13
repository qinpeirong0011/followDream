package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.BrokerConfig;
import com.qinpr.follow.messageQueue.common.UtilAll;
import com.qinpr.follow.messageQueue.store.config.FlushDiskType;
import com.qinpr.follow.messageQueue.store.config.MessageStoreConfig;
import com.qinpr.follow.messageQueue.store.stats.BrokerStatsManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qinpr on 2019/2/19.
 */
public class DefaultMessageStoreTest {
    private final String StoreMessage = "Once, there was a chance for me!";
    private int QUEUE_TOTAL = 100;
    private AtomicInteger QueueId = new AtomicInteger(0);
    private SocketAddress StoreHost;
    private SocketAddress BornHost;
    private byte[] MessageBody;
    private MessageStore messageStore;

    @Before
    public void init() throws Exception {
        StoreHost = new InetSocketAddress(InetAddress.getLocalHost(), 8123);
        BornHost = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0);
        messageStore = buildMessageStore();
        messageStore.start();
    }

    @Test
    public void testWriteAndRead() {
        long totalMsgs = 1;
        QUEUE_TOTAL = 1;
        MessageBody = StoreMessage.getBytes();

        for (long i=0; i < totalMsgs; i++) {
            messageStore.putMessage(buildMessage());
        }
    }

    @After
    public void destory() {
        messageStore.shutdown();
        messageStore.destroy();

        MessageStoreConfig messageStoreConfig = new MessageStoreConfig();
        File file = new File(messageStoreConfig.getStorePathRootDir());
        UtilAll.deleteFile(file);
    }

    private MessageStore buildMessageStore() throws Exception {
        MessageStoreConfig messageStoreConfig = new MessageStoreConfig();
        messageStoreConfig.setMapedFileSizeCommitLog(1024 * 1024 * 10);
        messageStoreConfig.setMapedFileSizeConsumeQueue(1024 * 1024 * 10);
        messageStoreConfig.setMaxHashSlotNum(10000);
        messageStoreConfig.setMaxIndexNum(100 * 100);
        messageStoreConfig.setFlushDiskType(FlushDiskType.SYNC_FLUSH);
        messageStoreConfig.setFlushIntervalConsumeQueue(1);
        return new DefaultMessageStore(messageStoreConfig, new BrokerStatsManager("simpleTest"), new MyMessageArrivingListener(), new BrokerConfig());
    }

    private MessageExtBrokerInner buildMessage() {
        MessageExtBrokerInner msg = new MessageExtBrokerInner();
        msg.setTopic("FooBar");
        msg.setTags("TAG1");
        msg.setKeys("Hello");
        msg.setBody(MessageBody);
        msg.setKeys(String.valueOf(System.currentTimeMillis()));
        msg.setQueueId(Math.abs(QueueId.getAndIncrement()) % QUEUE_TOTAL);
        msg.setSysFlag(0);
        msg.setBornTimestamp(System.currentTimeMillis());
        msg.setStoreHost(StoreHost);
        msg.setBornHost(BornHost);
        return msg;
    }

    private class MyMessageArrivingListener implements MessageArrivingListener {

        @Override
        public void arriving(final String topic, final int queueId, final long logicOffset, final long tagsCode, final long msgStoreTime,
                             final byte[] filterBitMap, final Map<String, String> properties) {

        }
    }

}
