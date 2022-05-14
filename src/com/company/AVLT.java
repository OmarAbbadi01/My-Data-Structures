package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class AVLT<E extends Comparable<E>> implements Tree<E> {

    class Node<E> {
        E value;
        Node<E> left, right, parent;

        Node() {

        }

        Node(E value) {
            this.value = value;
        }
    }

    private Node<E> root;
    private int size;

    public AVLT() {

    }

    public AVLT(E[] array) {
        for (E e : array)
            insert(e);
    }

    @Override
    public boolean insert(E element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        return insert(root, element);
    }

    private boolean insert(Node<E> node, E element) {
        if (node == null)
            return false;
        if (element.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node<>(element);
                node.right.parent = node;
                size++;
                checkBalance(node);
                return true;
            }
            return insert(node.right, element);
        } else if (element.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                node.left.parent = node;
                size++;
                checkBalance(node);
                return true;
            }
            return insert(node.left, element);
        } else
            return false;
    }

    @Override
    public boolean delete(E element) {
        if (root == null)
            return false;
        if (root.value.compareTo(element) == 0 && size == 1) {
            root = null;
            size = 0;
            return true;
        }
        return delete(root, element);
    }

    private boolean delete(Node<E> node, E element) {
        if (node == null)
            return false;
        if (element.compareTo(node.value) > 0)
            return delete(node.right, element);
        else if (element.compareTo(node.value) < 0)
            return delete(node.left, element);
        else {
            Node<E> parent = node.parent, toBeBalanceChecked = parent;
            // Case 1: the node doesn't has a left
            if (node.left == null) {
                // The node is the root
                if (parent == null) {
                    root = root.right;
                }
                // The node isn't the root
                else {
                    // The node is on the right of its parent
                    if (node.value.compareTo(parent.value) > 0) {
                        parent.right = node.right;
                    }
                    // the node is on the left of its parent
                    else {
                        parent.left = node.right;
                    }
                }
                if (node.right != null)
                    node.right.parent = parent;
            }
            // Case 2: the node does has a left
            else {
                Node<E> rightMost = node.left, rightMostParent;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMostParent = rightMost.parent;
                toBeBalanceChecked = rightMostParent;
                if (rightMost.value.compareTo(rightMostParent.value) < 0) {
                    rightMostParent.left = rightMost.left;
                } else {
                    rightMostParent.right = rightMost.left;
                }
                if (rightMost.left != null)
                    rightMost.left.parent = rightMostParent;
                node.value = rightMost.value;
            }
            checkBalance(toBeBalanceChecked);
            size--;
            return true;
        }
    }

    @Override
    public boolean search(E element) {
        return search(root, element);
    }

    private boolean search(Node<E> node, E element) {
        if (node == null)
            return false;
        if (element.compareTo(node.value) > 0)
            return search(node.right, element);
        else if (element.compareTo(node.value) < 0)
            return search(node.left, element);
        else
            return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
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
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<E> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value + " ");
            inorder(node.right);
        }
    }

    @Override
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(Node<E> node) {
        if (node != null) {
            inorder(node.left);
            inorder(node.right);
            System.out.println(node.value + " ");
        }
    }

    @Override
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node<E> node) {
        if (node != null) {
            System.out.println(node.value + " ");
            inorder(node.left);
            inorder(node.right);
        }
    }

    public void BFT() {
        if (root != null) {
            Queue<Node<E>> q = new LinkedList<>();
            Node<E> temp = root;
            q.add(temp);
            while (!q.isEmpty()) {
                temp = q.poll();
                System.out.print(temp.value + " ");
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
            System.out.println();
        }
    }

    public String pathToElement(E element) {
        String s = "";
        if (root == null || !search(element))
            return s;
        Node<E> temp = root;
        while (temp != null) {
            s += temp.value;
            if (element.compareTo(temp.value) > 0) {
                temp = temp.right;
                s += " -> ";
            } else if (element.compareTo(temp.value) < 0) {
                temp = temp.left;
                s += " -> ";
            } else
                break;
        }
        return s;
    }

    public int maxDepth() {
        if (root == null)
            return -1;
        return maxDepth(root) - 1;
    }

    private int maxDepth(Node<E> node) {
        if (node == null)
            return 0;
        int leftDepth = maxDepth(node.left), rightDepth = maxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    private int balanceFactor(Node<E> node) {
        return maxDepth(node.right) - maxDepth(node.left);
    }

    private void checkBalance(Node<E> node) {
        if (node != null) {
            int balanceFactor = balanceFactor(node);
            if (balanceFactor >= -1 && balanceFactor <= 1)
                checkBalance(node.parent);
            else {
                if (balanceFactor <= -2) {
                    // LL imbalance
                    if (balanceFactor(node.left) == -1 || balanceFactor(node.left) == 0) {
                        rotateRight(node);
                    }
                    // LR imbalance
                    else if (balanceFactor(node.left) == 1) {
                        rotateLeft(node.left);
                        rotateRight(node);
                    }
                } else if (balanceFactor > 2) {
                    //RR imbalance
                    if (balanceFactor(node.right) == 1 || balanceFactor(node.right) == 0) {
                        rotateLeft(node);
                    }
                    //RL imbalance
                    else if (balanceFactor(node.right) <= -1) {
                        rotateRight(node.right);
                        rotateLeft(node);
                    }
                }
            }
        }
    }

    private void rotateLeft(Node<E> node) {
        if (node != null) {
            Node<E> temp = node.right, grandParent = node.parent;
            node.right = temp.left;
            if (temp.left != null)
                temp.left.parent = node;
            temp.left = node;
            node.parent = temp;
            temp.parent = grandParent;
            if (grandParent != null) {
                if (temp.value.compareTo(grandParent.value) > 0)
                    grandParent.right = temp;
                else if (temp.value.compareTo(grandParent.value) < 0)
                    grandParent.left = temp;
            }
            if (node == root) {
                root = temp;
            }
        }
    }

    private void rotateRight(Node<E> node) {
        if (node != null) {
            Node<E> temp = node.left, grandParent = node.parent;
            node.left = temp.right;
            if (temp.right != null)
                temp.right.parent = node;
            temp.right = node;
            node.parent = temp;
            temp.parent = grandParent;
            if (grandParent != null) {
                if (temp.value.compareTo(grandParent.value) > 0)
                    grandParent.right = temp;
                else if (temp.value.compareTo(grandParent.value) < 0)
                    grandParent.left = temp;
            }
            if (node == root) {
                root = temp;
            }
        }
    }

    public E getRoot() {
        if (root == null)
            return null;
        return root.value;
    }
}
