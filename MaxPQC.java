// name MaxPQC.java
// author: Stephan Halarewicz
// date: October 28th, 2016
//
// Implementation for MaxPQ.java API

import java.util.*; 

public class MaxPQC<Key extends Comparable<Key>> implements MaxPQ<Key>{
// Instance Variables
 private Node top;
 private int size;

 // nested class Node
 private class Node {
   private Key info;
   private Node up; 
   private Node left;
   private Node right;
 }

// Constructor
 public MaxPQC(){
  size = 0;
  top = null;
 }

// Client Methods

 public Key delMax(){
  Node max = top.right;
  Key ans = max.info;
  max.info = null;
  sink(max);
  max = null;
  return ans;

 }

    public void insert(Key key){
     Node toInsert = new Node();
     toInsert.info = key;
     if (isEmpty()){
       this.top = toInsert;
       // top.left = toInsert;
       top.up = null;
       size++;
     } else {
      Node oldTop = top;
      toInsert.left = oldTop.left;
      toInsert.right = oldTop.right;    
      if (greater(key, oldTop.info)) {
       top.right = toInsert;
       // top.left = toInsert;
       toInsert.up = top;
       sink(oldTop);
       // oldTop = null;
      }
      else sink(toInsert);
      size++;
     }
    }

    public boolean isEmpty(){
     return size == 0;
    }

    public int size(){
     return size;
    }

    public String toString(){
     LinkedDeque<Node> queue = new LinkedDeque<Node>();
     Node start = top.right;
     queue.pushLeft(start);
     String s = "The MaxPQ from top to bottom reads";
     while (!queue.isEmpty()){
      Node next = queue.popRight();
      s = s + next.info;
      queue.pushLeft(next.left);
      queue.pushLeft(next.right);
     }
     return s + ".";
    }

// Helpers
    private boolean greater(Key a, Key b){
     return a.compareTo(b) > 0;
    }

    private void sink(Node x){
     Node left = x.left;
     Node right = x.right;
     if (greater(left.info, right.info) && greater(left.info, x.info)) {
      x.left = left.left;
      x.right = left.right;
      left.up = x.up;
      x.up = left;
      left.left = x;
      left.right = right;
      sink(x); return;
     }
     if (greater(right.info, left.info) && greater(right.info, x.info)) {
      x.left = right.left;
      x.right = right.right;
      right.up = x.up;
      x.up = right;
      right.right = x;
      right.left = left;
      sink(x); return;
     }
     return;
    }

    private void swim(Node child){
      Node parent = child.up;
      if (parent == top) return;
      Key parentInfo = parent.info;
      Key childInfo = child.info;
      if (greater(childInfo, parentInfo)){
        parent.info = childInfo;
        child.info = parentInfo;
        swim(parent);
      }
    }

    public static void main(String[] args){
     MaxPQ<Integer> pq = new MaxPQC<Integer>();

     pq.insert(1);
     pq.insert(10);
     pq.insert(3);
     pq.insert(7);
     pq.insert(6);
     pq.insert(15);
     System.out.println("After inserts " + pq.toString());
     pq.delMax();
     System.out.println("After deledtion " + pq.toString());
    }
}