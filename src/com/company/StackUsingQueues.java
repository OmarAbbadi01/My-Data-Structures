package com.company;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues<E> {

    private Queue<E> q2;
    private Queue<E> q1;

    public StackUsingQueues() {
        q2 = new LinkedList<>();
        q1 = new LinkedList<>();
    }

    public void push(E e) {
        q2.add(e);
        while (!q1.isEmpty())
            q2.add(q1.poll());
        while (!q2.isEmpty())
            q1.add(q2.poll());
    }

    public E pop() {
        if (q1.isEmpty())
            throw new EmptyStackException();
        return q1.poll();
    }

    public E peek() {
        if (q1.isEmpty())
            throw new EmptyStackException();
        return q1.peek();
    }

    @Override
    public String toString() {
        return q1.toString();
    }

}