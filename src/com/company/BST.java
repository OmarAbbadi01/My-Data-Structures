package com.company;

import java.util.*;

public class BST<E extends Comparable<E>> implements Tree<E> {

    class Node<E> {

        E value;
        Node<E> left, right;

        public Node(E value) {
            this.value = value;
        }
    }

    private int size;
    private Node<E> root;

    public BST() {

    }

    public BST(E[] array) {
        insertArray(array);
    }

    private void insertArray(E[] array) {
        for (E e : array) {
            insert(e);
        }
    }

    @Override
    public boolean insert(E value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return true;
        }
        Node<E> parent = root, current = root;
        while (current != null) {
            if (value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else if (value.compareTo(current.value) < 0) {
                parent = current;
                current = current.left;
            } else {
                return false;
            }
        }
        if (value.compareTo(parent.value) > 0) {
            parent.right = new Node<>(value);
        } else if (value.compareTo(parent.value) < 0) {
            parent.left = new Node<>(value);
        }
        size++;
        return true;
    }

    @Override
    public boolean search(E value) {
        if (root == null) {
            return false;
        }
        Node<E> temp = root;
        while (temp != null) {
            if (temp.value.compareTo(value) < 0) {
                temp = temp.right;
            } else if (temp.value.compareTo(value) > 0) {
                temp = temp.left;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(E value) {
        if (root == null) {
            return false;
        }
        Node<E> current = root, parent = root;
        while (current != null && current.value.compareTo(value) != 0) {
            if (value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }
        if (current == null)
            return false;
        if (current.left == null) {
            if (parent.value.compareTo(current.value) > 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
            return true;
        }
        Node<E> rightMost = current.left, rightMostParent = current;
        while (rightMost.right != null) {
            rightMostParent = rightMost;
            rightMost = rightMost.right;
        }
        current.value = rightMost.value;
        if (rightMostParent != current)
            rightMostParent.right = rightMost.left;
        else
            rightMostParent.left = rightMost.left;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.value + "  ");
        inorder(node.right);
    }

    public List<E> inorder2() {
        List<E> list = new ArrayList<>();
        inorder2(root, list);
        return list;
    }

    private void inorder2(Node<E> root, List<E> list) {
        Stack<Node<E>> stk = new Stack<>();
        HashSet<Node<E>> visited = new HashSet<>();
        Node<E> temp = root;
        stk.push(temp);
        while (!stk.isEmpty()) {
            temp = stk.pop();
            if (visited.contains(temp)) {
                list.add(temp.value);
            } else {
                if (temp.right != null)
                    stk.push(temp.right);
                stk.push(temp);
                visited.add(temp);
                if (temp.left != null)
                    stk.push(temp.left);
            }
        }
    }

    public void inorderIteration(Node<E> root) {
        if (root != null) {
            Stack<Node<E>> stk = new Stack<>();
            HashSet<Node<E>> visited = new HashSet<>();
            Node<E> temp = root;
            stk.push(temp);
            while (!stk.isEmpty()) {
                temp = stk.pop();
                if (visited.contains(temp)) System.out.print(temp.value + " ");
                else {
                    if (temp.right != null) stk.push(temp.right);
                    stk.push(temp);
                    visited.add(temp);
                    if (temp.left != null) stk.push(temp.left);
                }
            }
        }
    }

    @Override
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + "  ");
        preorder(node.left);
        preorder(node.right);
    }

    @Override
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(Node<E> node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + "  ");
    }

    public String pathToElement(E value) {
        String path = "";
        Node<E> temp = root;
        int comparison;
        while (temp != null) {
            comparison = value.compareTo(temp.value);
            if (comparison == 0) {
                path += temp.value;
                return path;
            } else {
                path += temp.value + " -> ";
                if (comparison > 0) {
                    temp = temp.right;
                } else {
                    temp = temp.left;
                }
            }
        }
        return value + " DOES NOT EXIST!";
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // BFT graph's approach
    public void BFT() {
        BFT(root);
        System.out.println();
    }

    private void BFT(Node<E> root) {
        if (root != null) {
            Queue<Node<E>> q = new LinkedList<>();
            Node<E> temp = root;
            q.add(temp);
            while (!q.isEmpty()) {
                temp = q.poll();
                System.out.print(temp.value + "  ");
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
        }
    }

    // BFT weird and complex approach (not recommended)
    public void BFT2() {
        int height = height();
        for (int i = 1; i <= height; i++) {
            BFT2(root, i);
        }
        System.out.println("");
    }

    private void BFT2(Node<E> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.value + " ");
        } else if (level > 1) {
            BFT2(node.left, level - 1);
            BFT2(node.right, level - 1);
        }
    }

    public int leavesCount() {
        return leavesCount(root);
    }

    private int leavesCount(Node<E> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return leavesCount(node.left) + leavesCount(node.right);
    }

    public List<E> leavesList() {
        List<E> list = new ArrayList<>();
        leavesList(root, list);
        return list;
    }

    private void leavesList(Node<E> node, List<E> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.value);
        }
        leavesList(node.left, list);
        leavesList(node.right, list);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int getLevel(E element) {
        if (!search(element)) {
            return -1;
        }
        return getLevel(root, element);
    }

    private int getLevel(Node<E> node, E element) {
        if (node == null) {
            return 0;
        }
        if (element.compareTo(node.value) > 0) {
            return 1 + getLevel(node.right, element);
        } else if (element.compareTo(node.value) < 0) {
            return 1 + getLevel(node.left, element);
        } else
            return 1;
    }

    public void invertTree() {
        invertTree(root);
    }

    private void invertTree(Node<E> node) {
        if (node == null)
            return;
        invertTree(node.left);
        invertTree(node.right);
        Node<E> temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public int heightOfElement(E value) {
        if (!search(value)) {
            return -1;
        }
        Node<E> current = root;
        while (current.value.compareTo(value) != 0) {
            if (current.value.compareTo(value) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return maxDepth(current);
    }

    public E getParentOf(E value) {
        Node<E> temp = getParentNode(value);
        if (temp != null) {
            return temp.value;
        }
        return null;
    }

    private Node<E> getParentNode(E value) {
        Node<E> current = root, parent = null;
        while (current != null) {
            if (value.compareTo(current.value) == 0) {
                return parent;
            } else if (value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }
        return null;
    }

    public E getGrandParentOf(E value) {
        Node<E> gp = getGrandParentNode(value);
        if (gp != null) {
            return gp.value;
        }
        return null;
    }

    private Node<E> getGrandParentNode(E value) {
        Node<E> current = root, parent = null, grandParent = null;
        while (current != null) {
            if (value.compareTo(current.value) > 0) {
                grandParent = parent;
                parent = current;
                current = current.right;
            } else if (value.compareTo(current.value) < 0) {
                grandParent = parent;
                parent = current;
                current = current.left;
            } else {
                return grandParent;
            }
        }
        return null;
    }

    public E getRootValue() {
        if (root != null) {
            return root.value;
        }
        return null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public int maxEdges(Node<E> node) {
        if (node == null)
            return -1;
        else
            return 1 + Math.max(maxEdges(node.left), maxEdges(node.right));
    }

}
