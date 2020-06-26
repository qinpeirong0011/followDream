package com.qinpr.follow.数据结构.sort;

import java.util.Arrays;

/**
 * Created by qinpr on 2020/5/9.
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {-2,20,3,9,-1,10};
//        selectSort(arr);

        int[] arr = new int[80000];
        for (int i=0; i< arr.length ; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        selectSort(arr);
        System.out.println("选择排序耗时: " + (System.currentTimeMillis() - start));
    }

    public static void selectSort(int[] arr) {
        int count = 0;
        for (int i=0 ; i < arr.length -1; i++) {
            int mixIndex = i;
            int min = arr[i];

            for (int j=i+1; j< arr.length ; j++) {
                if (min > arr[j]) {
                    count++;
                    min = arr[j];
                    mixIndex = j;
                }
            }
            //交换
            if (mixIndex != i) {
                arr[mixIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+ i + "轮排序后");
//            System.out.println(Arrays.toString(arr));
        }
        System.out.println("循环总次数" + count);
    }
}
