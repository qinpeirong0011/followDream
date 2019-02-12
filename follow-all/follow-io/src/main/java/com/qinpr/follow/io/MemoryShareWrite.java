package com.qinpr.follow.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * 共享内存--输入
 * Created by qinpr on 2019/2/12.
 */
public class MemoryShareWrite {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/qinpr/Desktop/temp", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        //清除文件内容
        for (int i=0 ; i < 1024 ; i++) {
            mappedByteBuffer.put(i, (byte)0);
        }

        Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("请输入字符:");
        int index = 0;
        while (index < 1024) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

//            int flag = mappedByteBuffer.get(0);
//            if (flag != 0) {
//                index ++;
//                continue;
//            }

            //正在写数据，标志第一个字节为 1
            mappedByteBuffer.put(0, (byte)1);
            //写数据的位置
            mappedByteBuffer.put(1, (byte)index);

            System.out.println("Write Share Memory : " + System.currentTimeMillis() + ":position:" + index + " 写入数据:" + input);
            //index 位置写入数据
            mappedByteBuffer.put(index ,(byte)index);
            //置可读数据标志第一个字节为 2
            mappedByteBuffer.put(0 ,(byte)2);
            index ++;

        }

    }

}
