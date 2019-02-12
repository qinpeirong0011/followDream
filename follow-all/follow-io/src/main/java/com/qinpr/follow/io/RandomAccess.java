package com.qinpr.follow.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by qinpr on 2019/2/11.
 */
public class RandomAccess {
    private static Logger logger = LoggerFactory.getLogger(RandomAccess.class);

    public static void main(String[] args) throws FileNotFoundException {
        readFile1("/Users/qinpr/Desktop/inventory");
        readFile2("/Users/qinpr/Desktop/inventory");
    }

    /**
     * 传统io不指定缓冲区大小
     * @param path
     */
    public static void readFile1(String path) {
        long start = System.currentTimeMillis();
        File file = new File(path);
        if (file.isFile()) {
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileReader.close();
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();
                System.out.println("无指定缓冲区cost:" + (end - start));
            }
        }
    }

    public static void readFile2(String path) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        //5M缓冲区
        int bufSize = 1024 * 1024* 5;
        File file = new File(path);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);
        String enter = "\n";

        try {
            byte[] bs = new byte[bufSize];
            String temp = null;
            //每次读5M到缓冲区
            while (channel.read(byteBuffer) != -1) {
                int position = byteBuffer.position();
                byteBuffer.rewind();
                //将缓冲区数据读到数组中
                byteBuffer.get(bs);
                //清除缓冲
                byteBuffer.clear();
                temp = new String(bs, 0, position);
                int fromIndex = 0;
                int endIndex = 0;
                //按行读缓冲区数据
                while ((endIndex = temp.indexOf(enter, fromIndex)) != -1) {
                    String line = temp.substring(fromIndex, endIndex);
                    System.out.println(line);
                    fromIndex = endIndex + 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("指定缓冲区cost:" + (end - start));
    }
}
