package com.qinpr.follow.数据结构.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 重点: 1:二叉树添加
 *      2:前序遍历\中序遍历\后序遍历
 *      3:层序遍历,及其应用,高频面试题
 *        --计算二叉树的高度
 *        --判断一个二叉树是否为完全二叉树
 * 二叉搜索树
 * Created by qinpr on 2020/5/7.
 */
public class BinarySearchTree<E> {

    private int size;
    private Node<E> root;

    public int size() {
        return size();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {//添加第一个节点
            root = new Node<>(element, null);
            size++;
            return;
        }

        //添加的不是第一个节点
        //找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0; //方向
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {//相等
                return;
            }
        }

        //看看插入到父节点的那个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    public void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /**
     * 后续遍历
     */
    public void posorderTraversal() {
        posorderTraversal(root);
    }

    public void posorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        inorderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);//根节点入队

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node);
            }

            if (node.right != null) {
                queue.offer(node);
            }
        }
    }

    /**
     * 递归方案
     * 计算二叉树的高度
     * @return
     */
//    public int height() {
//        return height(root);
//    }
//
//    private int height(Node<E> node) {
//        return 1 + Math.max(height(node.left), height(node.right));
//    }

    /**
     * 非递归方法,层序遍历
     * 计算二叉树高度
     * @return
     */
    public int height() {
        if (root == null) {
            return 0;
        }

        int height = 0;    //树的高度
        int levelSize = 1; //存储每一层的元素数量
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);//根节点入队

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node);
            }

            if (node.right != null) {
                queue.offer(node);
            }

            if (node.right != null) {
                queue.offer(node);
            }

            if (levelSize == 0) {//意味即将访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    /**
     * 是否是完全二叉树
     * @return
     */
    public boolean isComplete() {
        if (root == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);//根节点入队

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                leaf = true;
            }
        }
       return true;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must be not null");
        }
    }

    /**
     * @param e1
     * @param e2
     * @return 返回值等于0, 代表e1=e2;
     *         返回值大于0, 代表e1>e2;
     *         返回值小于0, 代表e1< e2
     */
    private int compare(E e1, E e2) {
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println(bst.compare(10,1));
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.parent = parent;
            this.element = element;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
