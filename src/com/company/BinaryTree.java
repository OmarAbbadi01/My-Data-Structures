package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BinaryTree() {
    }

    public BinaryTree(int[] array) {
        for (int x : array) {
            add(x);
        }
    }

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            String path = Integer.toBinaryString(size + 1);
            add(root, value, path, 1);
        }
        size++;
    }

    private void add(Node node, int value, String path, int index) {
        if (node != null) {
            if (index == path.length() - 1) {
                if (path.charAt(index) == '1') {
                    node.right = new Node(value);
                } else {
                    node.left = new Node(value);
                }
            } else if (path.charAt(index) == '0') {
                add(node.left, value, path, index + 1);
            } else {
                add(node.right, value, path, index + 1);
            }
        }
    }

    public void BFT() {
        BFT(root);
    }

    private void BFT(Node root) {
        if (root != null) {
            Queue<Node> q = new LinkedList<>();
            Node temp = root;
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

    public int height() {
        return maxDepth(root);
    }

    private int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.left) + 1, rightDepth = maxDepth(node.right) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    public int countOfSameSubs() {
        return countOfSameSubs(root);
    }

    private int countOfSameSubs(Node node) {
        if (node == null) {
            return 0;
        }
        if (isEqualSubTree(node)) {
            return 1 + countOfSameSubs(node.right) + countOfSameSubs(node.left);
        }
        return countOfSameSubs(node.right) + countOfSameSubs(node.left);
    }

    private boolean isEqualSubTree(Node node) {
        if (node.right == null && node.left == null) {
            return true;
        }
        if (node.left == null) {
            if (node.value == node.right.value) {
                return isEqualSubTree(node.right);
            }
        }
        if (node.right == null) {
            if (node.value == node.left.value) {
                return isEqualSubTree(node.left);
            }
        }
        if (node.value == node.left.value && node.value == node.right.value) {
            if (isEqualSubTree(node.left)) {
                if (isEqualSubTree(node.right)) {
                    return true;
                }
            }
        }
        return false;
    }


}
