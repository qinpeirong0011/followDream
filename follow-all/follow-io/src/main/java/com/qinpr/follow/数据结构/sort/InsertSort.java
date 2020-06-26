package com.qinpr.follow.数据结构.sort;

import java.util.Arrays;

/**
 * Created by qinpr on 2020/5/9.
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        insertSort(arr);
        int[] arr = new int[80000];
        for (int i=0; i< arr.length ; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        insertSort(arr);
        System.out.println("插入排序耗时: " + (System.currentTimeMillis() - start));
    }

    public static void insertSort(int[] arr) {
        int count = 0;
        for (int i=1; i<arr.length ; i++) {
            int insertVal = arr[i];
            int insertIndex = i -1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                count++;
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if ((insertIndex +1) == i) {
                arr[insertIndex +1] = insertVal;
            }
//            System.out.println("第" + i + "轮循环后:" + Arrays.toString(arr));
        }
        System.out.println("交换总次数" + count);
    }
}
