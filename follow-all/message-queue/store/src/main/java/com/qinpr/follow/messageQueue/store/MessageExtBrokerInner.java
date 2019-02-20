package com.qinpr.follow.messageQueue.store;

import com.qinpr.follow.messageQueue.common.message.MessageExt;

/**
 * Created by qinpr on 2019/2/19.
 */
public class MessageExtBrokerInner extends MessageExt {
    private static final long serialVersionUID = 1988082466407850328L;

    private String propertiesString;
    private long tagsCode;
}
