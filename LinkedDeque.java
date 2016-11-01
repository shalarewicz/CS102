import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<T> implements Deque<T>, Iterable<T>{

 // instance variables

 private Node first;
 private Node last;
 private int n;

 // nested class Node
 private class Node {
  private T item; 
  private Node left;
  private Node right;
 }

 // constructor

 public LinkedDeque(){
  first = null;
  last = null;
  n = 0;
 }

 public void pushLeft(T item){
  Node oldFirst = first;
  first = new Node();
  first.item = item;
  first.right = oldFirst;
  if (isEmpty()) last = first;
  else oldFirst.left = first;
  n++;
 }

 public void pushRight(T item){
  Node oldLast = last;
  last = new Node();
  last.item = item;
  last.left = oldLast;
  if (isEmpty()) first = last;
  else oldLast.right = last;
  n++;
 }

 public T popLeft(){
  if (isEmpty()) return null;
  Node oldFirst = first;
  T item = oldFirst.item;
  first = oldFirst.right;
  oldFirst = null;
  --n;
  if (isEmpty()) {
    first = null;
    last = null;
    return item;
  }
  first.left = null;
  return item; 

 }

 public T popRight(){
  if (isEmpty()) return null;
  Node oldLast = last;
  T item = last.item;
  last = oldLast.left;
  oldLast = null;
  --n;
  if (isEmpty()) {
    first = null;
    last = null;
    return item;
  }
  last.right = null;
  return item; 
 }

 public boolean isEmpty() {return n == 0;}

 public int size() { return n;}

 public Iterator<T> iterator(){
  return new DequeIterator();
 }

 private class DequeIterator implements Iterator<T>{
  private Node current = first ;

  public boolean hasNext() { return current != null;}

  public void remove() { throw new UnsupportedOperationException(); }

  public T next(){
   if (!hasNext()) throw new NoSuchElementException();
   T item = current.item;
   current = current.right;
   return item;
  }
 }

 public String toString(){
  StringBuilder s = new StringBuilder();
  for (T item : this)
   s.append(item + " ");
  return s.toString();
 }

 public static void main(String[] args){
  Deque<String> deque = new LinkedDeque<String>();

  // deque.pushLeft("left");
  // deque.pushRight("right");
  // StdOut.println(deque.toString());
  while (!StdIn.isEmpty()) {
   String item = StdIn.readString();
   deque.pushLeft(item);
  }
  deque.pushLeft("left"); StdOut.println(deque.toString());
  StdOut.println(deque.popLeft()); 
  StdOut.println(deque.toString());
  deque.pushRight("right"); StdOut.println(deque.toString());
  StdOut.println(deque.popRight()); 
  StdOut.println(deque.toString());
  StdOut.println("Size: " + deque.size());
  StdOut.println("Empty? " + deque.isEmpty());

 }
}
