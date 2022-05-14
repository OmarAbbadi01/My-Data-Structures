package com.company;

import java.util.Stack;

public class QueueUsingStack<E> {

    Stack<E> stk;

    public QueueUsingStack() {
        stk = new Stack<>();
    }

    public void enqueue(E element) {
        stk.push(element);
    }

    public int size() {
        return stk.size();
    }

    public boolean isEmpty() {
        return stk.isEmpty();
    }

    public E dequeue() {
        if (stk.size() == 1) {
            return stk.pop();
        }
        E temp = stk.pop();
        E toReturn = dequeue();
        stk.push(temp);
        return toReturn;
    }

    public E peekFirst() {
        if (stk.size() == 1) {
            return stk.peek();
        }
        E temp = stk.pop();
        E other = peekFirst();
        stk.push(temp);
        return other;
    }

    public E peekLast() {
        return stk.peek();
    }

    @Override
    public String toString() {
        return stk.toString();
    }
}
