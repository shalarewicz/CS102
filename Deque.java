// name: DequeC.java
// 
// Taken from Sedewick and Wayne algs-4 problem 1-3-37. This is the API for the 
// implementation Deque.java

public interface Deque<T> {
	public void pushLeft(T item);
	public void pushRight(T item);
	public T popLeft();
	public T popRight();
	public boolean isEmpty();
	public int size();
	public String toString();
}