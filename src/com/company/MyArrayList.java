package com.company;

public class MyArrayList<E> implements MyList<E> {

    private int size = 0;
    private final int INITIAL_CAPACITY = 16;
    private E[] data;

    public MyArrayList() {
        data = (E[]) new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(E[] objects) {
        data = (E[]) new Object[INITIAL_CAPACITY];
        for (E object : objects) {
            add(object);
        }
    }

    public void trimToSize() {
        if (size != data.length) {
            E[] data2 = (E[]) new Object[size];
            System.arraycopy(data, 0, data2, 0, size);
            data = data2;
        }
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] data2 = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, data2, 0, size);
            data = data2;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(E element) {
        int index = indexOf(element);
        if (index == -1) {
            return null;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public E removeByIndex(int index) {
        checkIndex(index);
        E temp = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E newElement) {
        checkIndex(index);
        E temp = data[index];
        data[index] = newElement;
        return temp;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s += get(i);
            if (i != size - 1)
                s += ", ";
        }
        s += "]";
        return s;
    }

}

