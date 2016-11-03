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
  Node child = new Node();
  child.info = key;
  if (isEmpty()) { this.top = child;
    size++;
    return;
  }
  Node parent = findNode(this.size / 2);
  child.up = parent;
  size++;
  if (this.size % 2 == 0) {
    parent.left = child;
  } else if (this.size % 2 == 1){
    parent.right = child;
  }
  swim(child);
}

    // public void insert(Key key){
    //  Node toInsert = new Node();
    //  toInsert.info = key;
    //  if (isEmpty()){
    //    this.top = toInsert;
    //    // top.left = toInsert;
    //    top.up = null;
    //    size++;
    //  } else {
    //   Node oldTop = top;
    //   toInsert.left = oldTop.left;
    //   toInsert.right = oldTop.right;    
    //   if (greater(key, oldTop.info)) {
    //    top.right = toInsert;
    //    // top.left = toInsert;
    //    toInsert.up = top;
    //    sink(oldTop);
    //    // oldTop = null;
    //   }
    //   else sink(toInsert);
    //   size++;
    //  }
    // }

    public boolean isEmpty(){
     return size == 0;
    }

    public int size(){
     return size;
    }

    public String toString(){
     LinkedDeque<Node> queue = new LinkedDeque<Node>();
     Node start = findNode(0);
     queue.pushLeft(start);
     String s = "The MaxPQ from top to bottom reads ";
     while (!queue.isEmpty()){
      Node next = queue.popRight();
      try{ 
        s = s + next.info + ", ";
      }
      catch (NullPointerException x){}
      try{
        queue.pushLeft(next.left);
      }
      catch (NullPointerException x){}
      try{
        queue.pushLeft(next.right);
      }
      catch (NullPointerException x){}
     }
     return s + ".";
    }

// Helpers
    private boolean greater(Key a, Key b){
     return a.compareTo(b) > 0;
    }

    private Node findNode(int x){
      ResizingArrayStack<Integer> route = new ResizingArrayStack<Integer>();
      int i = x;
      while(i > 0){
        route.push(i % 2);
        i /= 2;
      }
      Node start = this.top;
      while (!route.isEmpty()){
        int next = route.pop();
        if (next == 1) start = start.left;
        if (next == 0) start = start.right;
      }
      return start;
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
      Key parentInfo = parent.info;
      Key childInfo = child.info;
      if (greater(childInfo, parentInfo)){
        parent.info = childInfo;
        child.info = parentInfo;
        if (parent == top) return;
        swim(parent);
      }
    }

    public static void main(String[] args){
     MaxPQ<Integer> pq = new MaxPQC<Integer>();

     pq.insert(1);
     pq.insert(10);
     pq.insert(11);
     pq.insert(7);
     pq.insert(6);
     pq.insert(15);
     pq.insert(1);
     System.out.println("After inserts " + pq.toString());
     // pq.delMax();
     // System.out.println("After deledtion " + pq.toString());
    }
}