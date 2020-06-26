package com.qinpr.follow.数据结构;

/**
 * 双向链表 VS 单向链表 : 查询减少一半
 * 双向链表 VS 动态数组 :
 *    动态数组: 开辟和销毁内存空间的次数相对较少,但可能造成内存空间浪费(可以通过缩容解决)
 *    双向链表: 开辟和销毁内存空间的次数相对较多,但不会造成内存空间浪费
 *
 * 使用场景:
 *    1: 如果频繁在尾部进行添加\删除操作,动态数组和双向链表均可(时间复杂度O(1))
 *    2: 如果频繁在头部进行添加\删除操作,建议选择使用双向链表(动态数组是O(n)的时间复杂度)
 *    3: 如果频繁在任意位置进行添加\删除操作,建议选择使用双向链表(动态数组最坏时间复杂度是O(n),指头部位置,双向链表最坏是O(n)/2)
 *    4: 如果有频繁的查询操作(随机访问操作),建议选择动态数组
 *
 * 疑问:
 *     有了双向链表,单向链表还有何用处(hash表用到)
 *

 * 双向链表
 * Created by qinpr on 2020/5/5.
 */
public class LinkedList<E> extends AbstractList<E> implements List<E> {

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
            last = new Node<E>(last, element, null);
            if (oldLast == null) { //链表添加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);

            if (prev == null) {
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
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
