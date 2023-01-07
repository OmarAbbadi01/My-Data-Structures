package com.company.heap;

import java.util.ArrayList;

public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> list = new ArrayList<>();

    public MaxHeap() {
    }

    public MaxHeap(E[] array) {
        for (E value : array) {
            add(value);
        }
    }

    public void add(E value) {
        list.add(value);
        trickleUp(list.size() - 1);
    }

    public E remove() {
        if (list.size() == 1) {
            return list.remove(0);
        }
        E temp = list.get(0);
        list.set(0, list.remove(list.size() - 1));
        trickleDown(0);
        return temp;
    }

    public int size() {
        return list.size();
    }

    private void trickleUp(int index) {
        if (index > 0 && index < list.size()) {
            int parent = (index - 1) / 2;
            if (list.get(index).compareTo(list.get(parent)) > 0) {
                E temp = list.get(index);
                list.set(index, list.get(parent));
                list.set(parent, temp);
                trickleUp(parent);
            }
        }
    }

    private void trickleDown(int parent) {
        if (parent < list.size()) {
            int leftIndex = 2 * parent + 1, rightIndex = 2 * parent + 2;
            // CASE 1: NO LEFT && NO RIGHT
            if (leftIndex >= list.size()) {
                return;
            }
            // CASE 2: THERE EXISTS LEFT BUT NO RIGHT
            if (leftIndex == list.size() - 1) {
                if (list.get(parent).compareTo(list.get(leftIndex)) < 0) {
                    E temp = list.get(parent);
                    list.set(parent, list.get(leftIndex));
                    list.set(leftIndex, temp);
                    trickleDown(leftIndex);
                }
            } else if (list.get(leftIndex).compareTo(list.get(rightIndex)) > 0) {
                if (list.get(parent).compareTo(list.get(leftIndex)) < 0) {
                    E temp = list.get(parent);
                    list.set(parent, list.get(leftIndex));
                    list.set(leftIndex, temp);
                    trickleDown(leftIndex);
                }
            } else {
                if (list.get(parent).compareTo(list.get(rightIndex)) < 0) {
                    E temp = list.get(parent);
                    list.set(parent, list.get(rightIndex));
                    list.set(rightIndex, temp);
                    trickleDown(rightIndex);
                }
            }
        }
    }

    public ArrayList<E> getList() {
        return list;
    }

    public E getFirst() {
        return (!list.isEmpty()) ? list.get(0) : null;
    }

    public E getLast() {
        return (!list.isEmpty()) ? list.get(list.size() - 1) : null;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
}
