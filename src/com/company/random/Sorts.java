package com.company.random;

import com.company.heap.MinHeap;

import java.util.ArrayList;
import java.util.Arrays;

public class Sorts<E extends Comparable<E>> {


    public static int[] MergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int[] firstHalf = new int[array.length / 2];
        System.arraycopy(array, 0, firstHalf, 0, array.length / 2);
        MergeSort(firstHalf);

        int[] secondHalf = new int[array.length - array.length / 2];
        System.arraycopy(array, array.length / 2, secondHalf, 0, array.length - array.length / 2);
        MergeSort(secondHalf);

        array = merge(array, firstHalf, secondHalf);
        return array;
    }

    private static int[] merge(int[] list, int[] list1, int[] list2) {
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < list1.length && index2 < list2.length) {
            if (list1[index1] < list2[index2]) {
                list[index++] = list1[index1++];
            } else {
                list[index++] = list2[index2++];
            }
        }
        while (index1 < list1.length) {
            list[index++] = list1[index1++];
        }
        while (index2 < list2.length) {
            list[index++] = list2[index2++];
        }
        return list;
    }

    public static <E extends Comparable<E>> E[] HeapSort(E[] array) {
        ArrayList<E> list = new ArrayList<>(Arrays.asList(array));
        MinHeap<E> heap = new MinHeap<>(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.remove();
        }
        return array;
    }

    public static int[] BubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static int[] SelectionSort(int[] array) {
        int index;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;

        }
        return array;
    }

    public static int[] InsertionSort(int[] array) {
        int insert, j;
        for (int i = 1; i < array.length; i++) {
            insert = array[i];
            for (j = 1; j <= i; j++) {
                if (insert < array[i - j]) {
                    array[i - j + 1] = array[i - j];
                } else {
                    break;
                }
            }
            array[i - j + 1] = insert;
        }
        return array;
    }

}
