// name MinPQC.java
// author: Stephan Halarewicz
// date: November 16th, 2016
//
// Implementation for MinPQ.java API
// 
// Dependencies ResizaingArrayStack.java, LinkedDeque.java
// compile with javac-algs4 MinPQC.java
// run with java-algs4 MinPQC.java

import java.util.*; 

public class MinPQC<Key extends Comparable<Key>> implements MinPQ<Key>{
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
 public MinPQC(){
  size = 0;
  top = null;
 }

// Client Methods

 public Key delMin(){
  Node min = this.top;
  Key minInfo = min.info;
  if (size == 1) {this.top = null; size--; return min.info;}
  Node last = findNode(size);
  exchange(min, last);
  Node parentLast = findNode(size / 2); 
  if (size % 2 == 1) parentLast.right = null;
  if (size % 2 == 0) parentLast.left = null;
  size--;
  sink(min);
  return minInfo;

 }

public void insert(Key key){
  Node child = new Node();
  child.info = key;
  if (isEmpty()) { 
    this.top = child;
    size++;
    return;
  }
  size++;
  Node parent = findNode(this.size / 2);
  child.up = parent;
  if (this.size % 2 == 0) {
    parent.left = child;
  } else if (this.size % 2 == 1){
    parent.right = child;
  }
  swim(child);
}

    public boolean isEmpty(){
     return this.size == 0;
    }

    public int size(){
     return this.size;
    }

    public String toString(){
      if (isEmpty()) return "The priority queue is empty.";
      else{
        String sz = "The MinPQ has size = " + this.size + ". ";
        String s = sz + "The MinPQ from top to bottom reads"; 
        LinkedDeque<Node> queue = new LinkedDeque<Node>();
        Node start = findNode(1);
        queue.pushLeft(start);
        while (!queue.isEmpty()){
          Node next = queue.popRight();
            s = s + " " + next.info;
            if (next.left != null) queue.pushLeft(next.left);
            if (next.right != null) queue.pushLeft(next.right);
          }
        return s + ".";
      }
    }

// Helpers
    private boolean less(Key a, Key b){
     return a.compareTo(b) <= 0;
    }

    private Node findNode(int x){
      ResizingArrayStack<Integer> route = new ResizingArrayStack<Integer>();
      int i = x;
      while(i > 1){
        route.push(i % 2);
        i /= 2;
      }
      Node start = this.top;
      while (!route.isEmpty()){
        int next = route.pop();
        if (next == 0) start = start.left;
        if (next == 1) start = start.right;
      }
      return start;
    }

    private void sink(Node x){
     if (isLeaf(x)) return;
     Node left = x.left;
     Node right = x.right;
     if (right != null && less(right.info, left.info) && less(right.info, x.info)){
      exchange(x, right);
      sink(right);
      return;
     }
     if (less(left.info, x.info)) {
      exchange(x, left);
      sink(left);
      return;
     }
    }

    private void swim(Node child){
      Node parent = child.up;
      Key parentInfo = parent.info;
      Key childInfo = child.info;
      if (less(childInfo, parentInfo)){
        parent.info = childInfo;
        child.info = parentInfo;
        if (parent == top) return;
        swim(parent);
      }
    }

    private void exchange(Node parent, Node child){
      Key parentInfo = parent.info;
      Key childInfo = child.info;
      parent.info = childInfo;
      child.info = parentInfo;
    }

    private boolean isLeaf(Node x){
      return (x.left == null) && (x.right == null);
    }

    public static void main(String[] args){
     MinPQ<Integer> pq = new MinPQC<Integer>();

     pq.insert(1);
     pq.insert(10);
     pq.insert(11);
     pq.insert(7);
     pq.insert(6);
     pq.insert(15);
     pq.insert(2);
     System.out.println("After inserts " + pq.toString());
     while(!pq.isEmpty()){
      System.out.println(pq.delMin() + " was deleted");
      System.out.println("After deletion " + pq.toString());
      System.out.println("After deletion the size is " + pq.size());
     }
    }
}
