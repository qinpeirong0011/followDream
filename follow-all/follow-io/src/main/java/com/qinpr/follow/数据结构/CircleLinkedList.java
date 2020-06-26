package com.qinpr.follow.数据结构;

/**
 * 双向循环链表  jdk官方LinkedList是非循环的
 * Created by qinpr on 2020/5/5.
 */
public class CircleLinkedList<E> extends AbstractList<E> implements List<E> {

    private static final int ELEMENT_NOT_FOUND = -1;

    private Node<E> first;

    private Node<E> last;

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (size == index) {// 尾部添加
            Node<E> oldLast = last;
            last = new Node<E>(last, element, first);
            if (oldLast == null) { //链表添加的第一个元素
                first = last;
                first.prev = first;
                first.next = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;

            if (index == 0) {//index=0的位置插入节点
                first = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (size == 1) {//删除只有一个节点
            first = null;
            last = null;
        } else {
            prev.next = next;
            next.prev = prev;

            if (node == first) { //index==0
                first = next;
            }

            if (node == last) {
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.element))
                    return index;
                index++;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i=0; i< size; i++) {
            if (i != 0) {
                string.append(",");
            }

            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i=0; i< index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i=size -1; i> index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }
}
