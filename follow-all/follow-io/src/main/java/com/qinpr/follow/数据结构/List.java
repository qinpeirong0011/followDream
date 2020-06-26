package com.qinpr.follow.数据结构;

/**
 * Created by qinpr on 2020/5/5.
 */
public interface List<E> {

    static final int ELEMENT_NOT_FOUND = -1;

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(E element);

    E get(int index);

    E set(int index, E element);

    void add(E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E element);
}
