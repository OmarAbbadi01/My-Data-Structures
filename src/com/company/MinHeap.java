package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap<E extends Comparable<E>> {

    private List<E> list;

    public MinHeap() {
        list = new ArrayList<>();
    }

    public MinHeap(E[] array) {
        list = new ArrayList<>(Arrays.asList(array));
        buildHeap();
    }

    public MinHeap(List<E> list) {
        this.list = list;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = list.size() - 1; i >= 1; i--) {
            trickleUp(i);
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
            if (list.get(index).compareTo(list.get(parent)) < 0) {
                E temp = list.get(index);
                list.set(index, list.get(parent));
                list.set(parent, temp);
                trickleUp(parent);
            }
        }
    }

    private void trickleDown(int parent) {
        int leftIndex = 2 * parent + 1, rightIndex = 2 * parent + 2, minIndex = leftIndex;
        if (leftIndex >= list.size()) {
            return;
        } else if (rightIndex < list.size()) {
            if (list.get(leftIndex).compareTo(list.get(rightIndex)) > 0)
                minIndex = rightIndex;
        }
        if (list.get(parent).compareTo(list.get(minIndex)) > 0) {
            E temp = list.get(parent);
            list.set(parent, list.get(minIndex));
            list.set(minIndex, temp);
            trickleDown(minIndex);
        }
    }

    public List<E> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static <E extends Comparable<E>> List<E> buildHeap(List<E> list) {
        for (int i = list.size() - 1; i >= 1; i--) {
            trickleUp(list, i);
        }
        return list;
    }

    private static <E extends Comparable<E>> void trickleUp(List<E> list, int index) {
        if (index > 0 && index < list.size()) {
            int parent = (index - 1) / 2;
            if (list.get(index).compareTo(list.get(parent)) < 0) {
                E temp = list.get(index);
                list.set(index, list.get(parent));
                list.set(parent, temp);
                trickleUp(list, parent);
            }
        }
    }
}


