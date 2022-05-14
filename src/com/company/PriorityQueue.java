package com.company;

public class PriorityQueue<E extends Comparable<E>> {

    private MaxHeap<E> heap;

    public PriorityQueue() {
       heap = new MaxHeap<>();
    }
    
    public void enqueue(E value) {
        heap.add(value);
    }
    
    public E dequeue() {
        return heap.remove();
    }
    
    public E peekFirst(){
        return heap.getFirst();
    }
    
    public E peekLast() {
        return heap.getLast();
    }
    
    @Override
    public String toString() {
        return heap.toString();
    }
}
