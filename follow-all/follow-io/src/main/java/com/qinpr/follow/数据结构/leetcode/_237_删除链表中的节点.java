package com.qinpr.follow.数据结构.leetcode;

/**
 * Created by qinpr on 2020/5/5.
 */
public class _237_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
