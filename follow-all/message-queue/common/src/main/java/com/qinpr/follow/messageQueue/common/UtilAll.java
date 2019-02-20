package com.qinpr.follow.messageQueue.common;

/**
 * Created by qinpr on 2019/2/19.
 */
public class UtilAll {

    public static long computeEclipseTimeMilliseconds(final long beginTime) {
        return System.currentTimeMillis() - beginTime;
    }
}
