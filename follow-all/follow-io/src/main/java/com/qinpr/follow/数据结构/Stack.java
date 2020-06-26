package com.qinpr.follow.数据结构;

/**
 * 栈:只能在一端操作元素 后进先出 LIFO
 * 线性表,动态数组和链表都可以实现. 栈最频繁的动作是尾部元素操作
 * Created by qinpr on 2020/5/6.
 */
public class Stack<E> {
    private List<E> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.add(e);
    }

    public E pop() {
        return list.remove(size() -1);
    }

    public E top() {
        return list.get(size()-1);
    }
}
