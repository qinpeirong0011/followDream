package com.qinpr.follow.messageQueue.store;

import java.util.Map;

/**
 * Created by qinpr on 2019/2/19.
 */
public interface MessageArrivingListener {
    void arriving(String topic, int queueId, long logicOffset, long tagsCode,
                  long msgStoreTime, byte[] filterBitMap, Map<String, String> properties);
}
