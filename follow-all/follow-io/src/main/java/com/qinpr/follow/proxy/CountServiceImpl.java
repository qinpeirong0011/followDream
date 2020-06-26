package com.qinpr.follow.proxy;

/**
 * Created by qinpr on 2020/4/27.
 */
public class CountServiceImpl implements CountService {
    private int count = 0;

    @Override
    public int count() {
        return count ++;
    }
}
