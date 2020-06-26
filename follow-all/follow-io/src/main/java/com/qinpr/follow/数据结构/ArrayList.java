package com.qinpr.follow.数据结构;

/**
 * 动态数组
 * Created by qinpr on 2020/5/5.
 */
public class ArrayList<E> extends AbstractList<E> implements List<E> {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 2;

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[])new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public void clear() {
        size = 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", size:" + size);
        }
        return elements[index];
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", size:" + size);
        }

        E old = elements[index];
        elements[index] = element;
        return old;
    }

//    public void add(E element) {
//        ensureCapacity(size + 1);
//        elements[size++] = element;
//
//    }

    public void add(int index, E element) {
        ensureCapacity(size+1);
        for (int i= size - 1; i> index ; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = element;
        size++;
        System.out.println("size=" + size);
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", size:" + size);
        }

        E old = elements[index];
        for (int i =index + 1; i<size - 1 ; i++) {
            elements[i-1] = elements[i];
        }
        size--;
        return old;
    }

    public int indexOf(E element) {
        for (int i=0; i< size; i++) {
            if (elements[i] == element) return i;
        }
        return ELEMENT_NOT_FOUND;
    }

   private void ensureCapacity(int capacity) {
       int oldCapacity = elements.length;
       if (oldCapacity >= capacity) {
           return;
       }
       //新容量为旧容量的1.5倍
       int newCapacity = oldCapacity + (oldCapacity >> 1);
       E[] newElements = (E[])new Object[newCapacity];
       for (int i=0; i< size; i++) {
           newElements[i] = elements[i];
       }
       elements = newElements;
       System.out.println(oldCapacity + "扩容为" + newCapacity);
   }

    /**
     * 数组缩容
     * 如果扩容的倍数和缩容的时机设计的不得当,可能会导致复杂度震荡
     * 复杂度O(1)->O(n)
     */
    private void trim() {
        int capacity = elements.length;
        if (size >= capacity >> 1 && size <= DEFAULT_CAPACITY) {
            return;
        }

        int newCapacity = capacity >> 1;
        E[] newElements = (E[])new Object[newCapacity];
        for (int i=0; i< size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        for (int i=0; i< size; i++) {
            if (i != 0) {
                string.append(",");
            }

            string.append(elements[i]);
//            if (i != size -1) {
//                string.append(",");
//            }
        }
        string.append("]");
        return string.toString();
    }
}
