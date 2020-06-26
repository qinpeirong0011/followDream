package com.qinpr.follow.数据结构.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by qinpr on 2020/5/7.
 */
public class _226_翻转二叉树 {

    /**
     * 通过前序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTreeNode(TreeNode root) {
        if (root == null) {
            return root;
        }

        //交换左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTreeNode(root.left);
        invertTreeNode(root.right);
        return root;
    }

    /**
     * 通过后序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTreeNode2(TreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeNode2(root.left);
        invertTreeNode2(root.right);

        //交换左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    /**
     * 通过中序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTreeNode3(TreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeNode3(root.left);

        //交换左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTreeNode3(root.left);
        return root;
    }

    /**
     * 通过层序遍历实现
     * @param root
     * @return
     */
    public TreeNode invertTreeNode4(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);//根节点入队

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            //交换左右子树
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            if (node.left != null) {
                queue.offer(node);
            }

            if (node.right != null) {
                queue.offer(node);
            }
        }

        return root;
    }
}
