package com.qinpr.follow.messageQueue.common.message;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by qinpr on 2019/2/19.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 2960077968062405481L;

    private String topic;
    private int flag;
    private Map<String, String> properties;
    private byte[] body;
    private String transactionId;

    public Message() {
    }
}
