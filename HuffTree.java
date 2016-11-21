// name: HuffTree.java
// author: Stephan Halarewicz
// date: 11/16/2016
//
// This program specifies the HuffTree API. HuffTree is a BST data structure
// used in the implementation of Huffman encoding. 
// 
// compile with javac HuffTree.java
// run with java HuffTree
// Dependencies: ?

public interface HuffTree<Key extends java.lang.Comparable<Key>>{
 public boolean isEmpty();
 public int size();
 public int weight();
 public int compareTo(HuffTreeC<Key> x);
 public RedBlackBST<Key, String> bitPaths(RedBlackBST<Key, String> st);
 public String toString();

 
}