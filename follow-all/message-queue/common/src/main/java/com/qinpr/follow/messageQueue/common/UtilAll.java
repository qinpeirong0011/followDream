package com.qinpr.follow.messageQueue.common;

import java.io.File;
import java.util.zip.CRC32;

/**
 * Created by qinpr on 2019/2/19.
 */
public class UtilAll {

    public static long computeEclipseTimeMilliseconds(final long beginTime) {
        return System.currentTimeMillis() - beginTime;
    }

    public static int crc32(byte[] array) {
        if (array != null) {
            return crc32(array, 0, array.length);
        }
        return 0;
    }

    public static int crc32(byte[] array, int offset, int length) {
        CRC32 crc32 = new CRC32();
        crc32.update(array, offset, length);
        return (int) (crc32.getValue() & 0x7FFFFFFF);
    }

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }

        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                deleteFile(file);
            }
            file.delete();
        }
    }
}
