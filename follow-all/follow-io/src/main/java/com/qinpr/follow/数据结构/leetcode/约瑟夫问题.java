package com.qinpr.follow.数据结构.leetcode;

/**
 * 问题:1,2,3,4,5,6,7,8围成一个圆,从1开始数,数到到3就杀掉,
 *     求退出顺序,并最后一个保留的数字是多少
 *
 * 解题思路:借助循环链表
 *  1:current 用于指向某个节点,
 *  2:void reset() 让current指针指向头节点
 *  3:E next(): 让current往下走一步,也就是current = current.next;
 *  4:E remove(): 删除current指针指向的节点,删除成功current指向下一个节点
 *
 * Created by qinpr on 2020/5/6.
 */
public class 约瑟夫问题 {

}
