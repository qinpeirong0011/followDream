package com.qinpr.follow.messageQueue.store.config;

import java.io.File;

/**
 * Created by qinpr on 2019/2/19.
 */
public class StorePathConfigHelper {

    public static String getLockFile(final String rootDir) {
        return rootDir + File.separator + "lock";
    }
}
