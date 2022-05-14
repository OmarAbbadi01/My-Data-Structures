package com.company;

public interface Tree <E extends Comparable<E>>{
    public boolean insert(E e);
    public boolean delete(E e);
    public boolean search(E e);
    
    public void clear();
    public int size();
    public boolean isEmpty();
    
    public void inorder();
    public void postorder();
    public void preorder();    
    
}
