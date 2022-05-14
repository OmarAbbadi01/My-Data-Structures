package com.company;

import java.util.HashSet;
import java.util.LinkedList;

public class MyLinkedList<E> implements MyList<E> {

    static class Node<E> {
        E value;
        Node<E> next;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head, tail;
    private int size = 0;

    public MyLinkedList() {

    }

    public MyLinkedList(E[] data) {
        for (E element : data)
            addLast(element);
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(E element) {
        if (tail == null) {
            addFirst(element);
        } else {
            Node<E> newNode = new Node<>(element);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if (head != null) {
            E old = head.value;
            size--;
            if (head.next == null) {
                head = tail = null;
                return old;
            }
            head = head.next;
            return old;
        }
        return null;
    }

    public E removeLast() {
        if (tail == null) {
            return null;
        } else if (head == tail) {
            return removeFirst();
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            E temp = tail.value;
            current.next = null;
            tail = current;
            size--;
            return temp;
        }
    }

    public E getFirst() {
        if (head != null)
            return head.value;
        return null;
    }

    public E getLast() {
        if (tail != null)
            return tail.value;
        return null;
    }

    @Override
    public void add(E element) {
        addFirst(element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0) {
            return;
        } else if (index >= size) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            Node<E> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            Node<E> newNode = new Node(element);
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int indexOf(E element) {
        if (head == null) {
            return -1;
        }
        if (head == tail) {
            if (head.value.equals(element)) {
                return 0;
            } else {
                return -1;
            }
        }
        Node<E> temp = head;
        int index = 0;
        while (temp != null) {
            if (element.equals(temp.value))
                return index;
            index++;
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int lastIndexOf(E element) {
        if (head == null) {
            return -1;
        }
        if (head == tail) {
            if (head.value.equals(element)) {
                return 0;
            }
        }
        int index = -1;
        Node<E> temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.value.equals(element))
                index = i;
            i++;
            temp = temp.next;
        }
        return index;
    }

    @Override
    public E remove(E element) {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            if (head.value.equals(element)) {
                return removeFirst();
            } else {
                return null;
            }
        }
        if (head.value.equals(element)) {
            return removeFirst();
        }
        if (tail.value.equals(element)) {
            return removeLast();
        }
        Node<E> prev = head, current;
        while (prev.next != null) {
            current = prev.next;
            if (current.value.equals(element)) {
                E old = current.value;
                prev.next = current.next;
                size--;
                return old;
            }
            prev = prev.next;
        }
        return null;
    }

    @Override
    public E removeByIndex(int index) {
        checkIndex(index);
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> prev = head, current;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        current = prev.next;
        E old = current.value;
        prev.next = current.next;
        size--;
        return old;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        if (head == null) {
            return null;
        }
        if (index == 0) {
            E old = head.value;
            head.value = element;
            return old;
        }
        if (index == size - 1) {
            E old = tail.value;
            tail.value = element;
            return old;
        }
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        E old = temp.value;
        temp.value = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String s = "[";
        Node<E> temp = head;
        while (temp != null) {
            s += temp.value;
            if (temp.next != null) {
                s += ", ";
            }
            temp = temp.next;
        }
        return s + "]";
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    // Exercises

    public void swap(int index1, int index2) {
        // check if index1 and index2 are valid
        checkIndex(index1);
        checkIndex(index2);

        // can't perform swaps in those cases:
        if (head == null || head == tail || index1 == index2)
            return;

        // to make sure index1 is on the left and index2 on the right (easier to solve)
        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }

        // declare 6 references of type Node<E>
        Node<E> first = head, firstPrev = null, firstAfter,
                second = head, secondPrev = null, secondAfter;
        // make first point to the first node to swap
        // and firstPrev points to the previous of first
        for (int i = 0; i < index1; i++) {
            firstPrev = first;
            first = first.next;
        }
        // make firstAfter point to the next node of first
        firstAfter = first.next;

        // same for second
        for (int i = 0; i < index2; i++) {
            secondPrev = second;
            second = second.next;
        }
        secondAfter = second.next;

        // Start swapping
        first.next = secondAfter;

        // this if statement is true when we swap two adjacent nodes
        // without the if statement we may end up in an infinite loop.
        if (secondPrev != first)
            secondPrev.next = first;

        // first prev will be null only if the first node is the head (index1 == 0)
        // without this if statement we may cause a null pointer exception.
        if (firstPrev != null)
            firstPrev.next = second;

        // firstAfter will be equal to second when we are swapping two adjacent nodes
        // without this if statement we may end up in an infinite loop.
        if (firstAfter != second)
            second.next = firstAfter;
        else
            second.next = first;


        // After swapping we should reset the head and tail if needed

        // Case1: swapping head with tail
        // we should swap head with tail to keep the list normal
        if (index1 == 0 && index2 == size - 1) {
            Node<E> temp = head;
            head = tail;
            tail = temp;
        }

        // Case2: swapping head with regular node
        // we should reset the head
        else if (index1 == 0) {
            head = second;
        }

        // Case3: swapping tail with regular node
        // we should reset the tail
        else if (index2 == size - 1) {
            tail = first;
        }
    }

    // Exam question
    public E valueAtNNodeFromLast(int n) {
        Node<E> temp = head;
        int move = size - n;
        for (int i = 0; i < move; i++) {
            temp = temp.next;
        }
        return temp.value;
    }

    //Q206 Leetcode
    public void reverse2() {
        if (head == null)
            return;
        if (head == tail)
            return;
        Node<E> left = head, mid = head.next, right = mid.next;
        while (mid != null) {
            mid.next = left;
            left = mid;
            mid = right;
            if (right != null)
                right = right.next;
        }
        head.next = null;
        Node<E> temp = head;
        head = tail;
        tail = temp;
    }

    //Q234 Leetcode
    public boolean isPalindrome() {
        LinkedList<E> list = new LinkedList<>();
        Node<E> temp = head;
        for (int i = 0; i < size / 2; i++) {
            list.addLast(temp.value);
            temp = temp.next;
        }
        if (size % 2 == 1)
            temp = temp.next;
        while (temp != null) {
            if (list.isEmpty() || !temp.value.equals(list.removeLast()))
                return false;
            temp = temp.next;
        }
        return true;
    }

    //Q83 Leetcode:
    // Q1:
    public void removeDuplicates() {
        if (head == null || head.next == null)
            return;
        Node<E> current = head;
        while (current.next != null) {
            if (current.value == current.next.value) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
        if (size == 1)
            tail = head;
    }

    // Q2:
    public int sum(Node<Integer> head1, Node<Integer> head2) {
        int sum = 0;
        while (head1 != null) {
            sum *= 10;
            sum += head1.value + head2.value;
            head1 = head1.next;
            head2 = head2.next;
        }
        return sum;
    }

    // Recursion
    public void print(Node<E> c) {
        if (c == null)
            return;
        print(c.next);
        System.out.print(c.value + "  ");
    }

    // reverse a linked list using recursion
    public void reverse() {
        if (head == null || head.next == null)
            return;
        Node<E> mid = head;
        for (int i = 0; i < size / 2; i++)
            mid = mid.next;
        R = mid;
        reverse(head);
    }

    private Node<E> R;

    private void reverse(Node<E> L) {
        if (L == R) {
            if (size % 2 == 1)
                R = R.next;
            return;
        }
        reverse(L.next);
        // swap
        E swap = L.value;
        L.value = R.value;
        R.value = swap;
        R = R.next;
    }

    // similar method to reverse a linked list using recursion
    public void reverse3() {
        if (head == null || head.next == null)
            return;
        Node<E> mid = head;
        for (int i = 0; i < size / 2; i++)
            mid = mid.next;
        reverse3(head, mid);
    }

    private Node<E> reverse3(Node<E> left, Node<E> right) {
        if (left == right) {
            if (size % 2 == 1)
                right = right.next;
            return right;
        }
        right = reverse3(left.next, right);
        // swap
        E temp = right.value;
        right.value = left.value;
        left.value = temp;
        return right.next;
    }

    // check if a linked list is palindrome or not recursively
    public boolean isPalindrome2() {
        if (head == null || head.next == null)
            return true;
        Node<E> temp = head;
        for (int i = 0; i < size / 2; i++)
            temp = temp.next;
        right = temp;
        boolean isOdd = false;
        if (size % 2 == 1)
            isOdd = true;
        return isPalindrome2(head, isOdd);
    }

    private Node<E> right;

    private boolean isPalindrome2(Node<E> left, boolean isOdd) {
        if (left == right) {
            if (isOdd)
                right = right.next;
            return true;
        }
        boolean t = isPalindrome2(left.next, isOdd);
        if (!t || !left.value.equals(right.value))
            return false;
        right = right.next;
        return true;
    }

    public boolean hasCycel(Node<E> c) {
        HashSet<Node<E>> set = new HashSet<>();
        while (c != null) {
            if (set.contains(c.next))
                return true;
            set.add(c);
            c = c.next;
        }
        return false;
    }


}
