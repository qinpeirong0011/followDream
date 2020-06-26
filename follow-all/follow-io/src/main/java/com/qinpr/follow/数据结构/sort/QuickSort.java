package com.qinpr.follow.数据结构.sort;

import java.util.Arrays;

/**
 * Created by qinpr on 2020/5/9.
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70};
//        quickSort(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i=0; i< arr.length ; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length -1);
        System.out.println("快速排序耗时: " + (System.currentTimeMillis() - start));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right)/2];
        int temp = 0;//临时变量,交换时使用
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }

            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
