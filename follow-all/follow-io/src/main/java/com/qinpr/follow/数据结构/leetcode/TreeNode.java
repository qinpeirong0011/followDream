package com.qinpr.follow.数据结构.leetcode;

/**
 * Created by qinpr on 2020/5/7.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int val, TreeNode parent) {
        this.val = val;
        this.parent = parent;
    }
}
