package com.qinpr.follow.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * 内存映射
 * Created by qinpr on 2019/2/12.
 */
public class MemoryDirectMap {
    public static void main(String[] args) {
        readFile("/Users/qinpr/Desktop/inventory");
    }

    public static void readFile(String path) {
        long start = System.currentTimeMillis();
        long fileLength = 0;
        //3M缓冲
        int bufferSize = 0x300000;
        File file = new File(path);
        fileLength = file.length();

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer inputBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);

            byte[] bytes = new byte[bufferSize];
            for (int offset = 0; offset < fileLength ; offset += bufferSize) {
                if (fileLength - offset >= bufferSize) {
                    for (int i = 0; i < bufferSize ; i++) {
                        bytes[i] = inputBuffer.get(offset + i);
                    }
                } else {
                    for (int i = 0; i < fileLength - offset ; i++) {
                        bytes[i] = inputBuffer.get(offset + i);
                    }
                }

                Scanner scanner = new Scanner(new ByteArrayInputStream(bytes)).useDelimiter(" ");
                while (scanner.hasNext()) {
                    System.out.println(scanner.next() + "");
                }
                scanner.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("内存映射cost:" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
