package com.company.queue;

import java.util.LinkedList;

public class MyQueue<E> {

    private LinkedList<E> list;

    public MyQueue() {
        list = new LinkedList<>();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E peekFirst() {
        return list.getFirst();
    }

    public E peekLast() {
        return list.getLast();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list = new LinkedList<>();
    }

    public boolean contains(E value) {
        return list.contains(value);
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
