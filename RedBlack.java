// name: RedBlack.java
// author: Stephan Halarewicz
// date: 12/13/2016
//
// This is the specification of a implementation RedBlack Tree.

public interface RedBlack<Key extends Comparable<Key>, Value>{
	public boolean isEmpty();
	public int size();
	public Value get(Key key);
	public RedBlack<Key, Value> put(Key key, Value val);
	public String toString();
}
