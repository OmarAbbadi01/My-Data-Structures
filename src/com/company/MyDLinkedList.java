package com.company;

public class MyDLinkedList<E> implements MyList<E> {

    class Node<E> {

        E value;
        Node<E> next, previous;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head, tail;
    private int size = 0;

    public MyDLinkedList() {

    }

    public MyDLinkedList(E[] data) {
        for (E element : data)
            addLast(element);
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        if (tail == null) {
            addFirst(e);
        } else {
            Node<E> newNode = new Node<>(e);
            tail.next = newNode;
            newNode.previous = tail;
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
            head.previous = null;
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
            Node<E> current = tail.previous;
            E temp = tail.value;
            tail.previous = null;
            tail = current;
            tail.next = null;
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
            newNode.previous = temp;
            temp.next = newNode;
            newNode.next.previous = newNode;
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
            if (element.equals(temp.value)) {
                return index;
            }
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
        int index = size - 1;
        Node<E> temp = tail;
        while (temp != null) {
            if (temp.value.equals(element))
                return index;
            index--;
            temp = temp.previous;
        }
        return index;
    }

    @Override
    public E remove(E element) {
        if (head == null) {
            return null;
        } else if (head.value.equals(element)) {
            return removeFirst();
        } else if (tail.value.equals(element)) {
            return removeLast();
        }
        Node<E> prev = head, current;
        while (prev.next != null) {
            current = prev.next;
            if (current.value.equals((element))) {
                prev.next = current.next;
                current.next.previous = prev;
                current.next = current.previous = null;
                size--;
                return element;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
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
        current.next.previous = prev;
        current.next = current.previous = null;
        return old;
    }

    @Override
    public E set(int index, E value) {
        checkIndex(index);
        if (head == null) {
            return null;
        }
        if (index == size - 1) {
            E old = tail.value;
            tail.value = value;
            return old;
        }
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        E old = temp.value;
        temp.value = value;
        return old;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }

    @Override
    public String toString() {
        String s = "[";
        Node<E> temp = head;
        while (temp != null) {
            s += temp.value;
            if (temp.next != null)
                s += ", ";
            temp = temp.next;
        }
        return s + "]";
    }

    // To make sure that the pointers are well managed
    public String toStringBackward() {
        String s = "[";
        Node<E> temp = tail;
        while (temp != null) {
            s += temp.value;
            if (temp.previous != null)
                s += ", ";
            temp = temp.previous;
        }
        return s + "]";
    }

    public void reverse() {
        Node<E> left = head, right = tail;
        reverse(left, right);
    }

    private void reverse(Node<E> left, Node<E> right) {
        // case1 is for odd size of the list
        // and case2 is for even size of the list.
        if (left == right || left.previous == right)
            return;
        E temp = left.value;
        left.value = right.value;
        right.value = temp;
        reverse(left.next, right.previous);
    }

}


