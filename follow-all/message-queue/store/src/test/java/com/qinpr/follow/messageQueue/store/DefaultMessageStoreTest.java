package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.BrokerConfig;
import com.qinpr.follow.messageQueue.store.config.FlushDiskType;
import com.qinpr.follow.messageQueue.store.config.MessageStoreConfig;
import com.qinpr.follow.messageQueue.store.stats.BrokerStatsManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

/**
 * Created by qinpr on 2019/2/19.
 */
public class DefaultMessageStoreTest {
    private final String StoreMessage = "Once, there was a chance for me!";
    private SocketAddress StoreHost;
    private SocketAddress BornHost;
    private MessageStore messageStore;

    @Before
    public void init() throws Exception {
        StoreHost = new InetSocketAddress(InetAddress.getLocalHost(), 8123);
        BornHost = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0);
        messageStore = buildMessageStore();
    }

    @Test
    public void testWriteAndRead() {
        System.out.println("Test-----");
    }

    @After
    public void destory() {
        System.out.println("After-----");
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

    private class MyMessageArrivingListener implements MessageArrivingListener {

        @Override
        public void arriving(final String topic, final int queueId, final long logicOffset, final long tagsCode, final long msgStoreTime,
                             final byte[] filterBitMap, final Map<String, String> properties) {

        }
    }

}
