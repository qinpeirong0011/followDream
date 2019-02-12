package com.qinpr.follow.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 共享内存--输出
 * Created by qinpr on 2019/2/12.
 */
public class MemoryShareRead {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/qinpr/Desktop/temp", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        int lastIndex = 0;

        for (int i=1 ; i<27 ; i++) {
            byte flag = mappedByteBuffer.get(0);
            byte index = mappedByteBuffer.get(1);

            if (flag != 2 || index == lastIndex) {
                i--;
                continue;
            }

            lastIndex = index;
            System.out.println("Read Share Memory :" + System.currentTimeMillis() + ": position " + index + " 读出数据:" + (char)mappedByteBuffer.get(index));
            mappedByteBuffer.put(0, (byte)0);
            if (index == 27) {
                break;
            }
        }
    }
}
