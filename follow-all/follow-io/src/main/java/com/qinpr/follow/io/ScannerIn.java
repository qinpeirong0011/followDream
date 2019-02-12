package com.qinpr.follow.io;

/**
 * Created by qinpr on 2019/2/12.
 */
public class ScannerIn {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("请输入字符:");
        while(true) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            System.out.println(">>>" + line);
        }
    }
}
