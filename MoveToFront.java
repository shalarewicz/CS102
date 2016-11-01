// name: MoveToFront.java
// author: Stephan Halarewicz
// date: Oct 3, 2016
//
// This program implements the common Move to Front strategy

public class MoveToFront implements MoveToFrontAF{

 // Instance Variables
 private Node first;
 private Node last;
 private int n;

 // Nested Class Node
 private class Node{
  private char item;
  private Node next;
  private Node previous;
 }

 // Constructor
 public MoveToFront(){
  first = null;
  last = null;
  n = 0;
 }

 public void push(char c){
  if (isEmpty()) last = first;
  Node oldFirst = first;
  first = new Node();
  first.item = c;
  first. = oldFirst;
  if (isEmpty()) last = first;
  else oldFirst.next = first;
  n++;
 }

 private void delete(Node x){
  Node previous = x.previous;
  Node next = x.next;
  previous.next = next;
  next.previous = previous;
  x = null;
  --n;
 }

 private void mem(char c){
    for (Node x = first; x != null; x = x.next){
     if (x.item == c){
      delete(x);
      push(c);
      break;
     }
     else{
      push(c);
     }
   }
 }
 
 public boolean isEmpty(){return n == 0;}
 

 public String toString(){
  StringBuilder s = new StringBuilder();
    for (Node x = first; x != null; x = x.next){
     s.append(x.item);
     }
  return s.toString();
 }

 public static void main(String[] args){
  MoveToFront stack = new MoveToFront();
  while (!StdIn.isEmpty()){
   char c = StdIn.readChar();
   stack.mem(c);
  }

  System.out.println(stack.toString());
 }
}