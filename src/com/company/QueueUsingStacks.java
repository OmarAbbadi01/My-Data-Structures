package com.company;

import java.util.Stack;

public class QueueUsingStacks<E> {
    private Stack<E> stk1, stk2;

    public QueueUsingStacks() {
        stk1 = new Stack<>();
        stk2 = new Stack<>();
    }

    public void enqueue(E value) {
        stk1.add(value);
    }

    public E dequeue() {
        if (stk1.isEmpty()) return null;
        while (!stk1.isEmpty()) {
            stk2.add(stk1.pop());
        }
        E temp = stk2.pop();
        while (!stk2.isEmpty()) {
            stk1.add(stk2.pop());
        }
        return temp;
    }

    public int size() {
        return stk1.size();
    }

    @Override
    public String toString() {
        return stk1.toString();
    }
}
