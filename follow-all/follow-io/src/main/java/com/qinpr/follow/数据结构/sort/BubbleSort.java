package com.qinpr.follow.数据结构.sort;

import java.util.Arrays;

/**
 * Created by qinpr on 2020/5/9.
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {-2,20,3,9,-1,10};
//        asc(arr);
//        desc(arr);
        int[] arr = new int[80000];
        for (int i=0; i< arr.length ; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        asc(arr);
        System.out.println("冒泡排序耗时: " + (System.currentTimeMillis() - start));
    }

    public static void asc(int[] arr) {
        int count = 0;
        int temp = 0;
        for (int i=0 ; i<arr.length -1 ; i++) {
            for (int j=0 ; j<arr.length -1 -i; j++) {
                if (arr[j] > arr[j+1]) {
                    count++;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("交换总次数"+ count);
    }

    public static void desc(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i=0 ; i<arr.length -1 ; i++) {
            for (int j=0 ; j<arr.length -1 -i; j++) {
                if (arr[j] < arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
            System.out.println("第" + (i+1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));
        }
    }
}

//        int temp = 0;
//        for (int i=1 ; i<arr.length ; i++) {
//            for (int j=arr.length -i ; j > 0; j--) {
//                if (arr[j] > arr[j-1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j-1];
//                    arr[j-1] = temp;
//                }
//            }
//            System.out.println("第" + (i+1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
//        }
