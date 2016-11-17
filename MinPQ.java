// name MinPQ.java
// author: Stephan Halarewicz
// date: November 16th, 2016
//
// API for Minimum Priority Queue

public interface MinPQ<Key extends Comparable<Key>> {
    public Key delMin();
    public void insert(Key key);
    public boolean isEmpty();
    public int size();
    public String toString();
}