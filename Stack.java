// name: Stack.java
// Author: Robert Muller
// Date: Sept. 15 2016
//
// API for ADT Stack used in PS3

public interface Stack<T>{
	public void push(T element);
	public T pop();
	public T peek();
	public boolean isEmpty();
	public int size();
}