// name MaxPQ.java
// author: Stephan Halarewicz
// date: October 28th, 2016
//
// API for Maximum Priority Queue

public interface MaxPQ<Key extends Comparable<Key>> {
    public Key delMax();
    public void insert(Key key);
    public boolean isEmpty();
    public int size();
    public String toString();
}