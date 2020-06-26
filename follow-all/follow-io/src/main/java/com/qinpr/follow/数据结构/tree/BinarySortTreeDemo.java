package com.qinpr.follow.数据结构.tree;

/**
 * Created by qinpr on 2020/5/8.
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7,3,10,12,5,1,9};
//        BinarySortTree binarySortTree = new BinarySortTree();
//        for (int i=0; i<arr.length ; i++) {
//            binarySortTree.add(new Node(arr[i]));
//        }
//
//        binarySortTree.infixOrder();

        int[] arr = {4,3,6,5,7,8};
        AVLTree avlTree = new AVLTree();
        for (int i=0; i<arr.length ; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("树的高度:" + avlTree.getRoot().height());
        System.out.println("左子树的高度:" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度:" + avlTree.getRoot().rightHeight());


    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空,不能遍历");
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void delete(int value) {
//        二叉树的删除节点，有三种情况
//        1、删除叶子节点
//        2、删除只有一颗子树的节点
//        3、删除有两棵子树的节点
//
//        思路：
//        情况3：
//        1、需要先找到要删除的目标节点targetNode
//        2、找到targetNode的父节点
//        3、从targetNode的右子树中找到最小的节点
//        4、临时变量保存改最小节点tempNode
//        5、删除掉最小节点
//        6、targetNode和tempNode交换
    }
}

class AVLTree extends BinarySortTree {

}

//创建node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    //递归的形式添加形式
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入的节点的值,和当前子树节点的值关系
        if (node.value < this.value) {
            //
            if (this.left == null) {
               this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else { //添加节点的值大与当前节点
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }

        //当添加完节点后,如果(右子树高度-左子树高度)>1, 进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            leftRotate();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {//向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && value < this.left.value) {
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.right.value) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }
    }

    //左子树高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //右子树高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        System.out.println("node.value=" + value + ",发生左旋转");
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }
}
