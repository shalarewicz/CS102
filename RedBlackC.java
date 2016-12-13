// name: RedBlack.java
// author: Stephan Halarewicz
// date: 12/13/2016
//
// This is the specification of a implementation RedBlack Tree.

public class RedBlack<Key extends Comparable<Key>, Value> implements RedBlack<Key, Value>{
	// Instance Variables
	private int n;
	private RedBlack<Key, Value> left;
	private RedBlack<Key, Value> right;
	private Key key;
	private Value value;
	private boolean color;

	private final RED = true;
		
		
	//Getters
	private Value getValue(){
		return this.value;
	}
	private Key getKey(){
		return this.key;
	}
	private RedBlack<Key, Value> getLeft(){
		return this.left;
	}
	private RedBlack<Key, Value> getRight(){
		return this.right;
	}

	public boolean isEmpty(){
		return this.size() == 0;
	}
	
	public int size(){
		return this.size();
	}
	
	//Helpers
	private void rotateLeft();
	private void rotateRight();
	private void flipColors();
	private boolean isRed(){
		return this.color = RED;
	}


	//Instance Methods
	
	public Value get(Key key){
		Key current = this.getKey();
		if (key.compareTo(current) < 0) left.get(current);
		if (key.compareTo(current) > 0) right.get(current);
		if (key.compareTo(current) == 0) return key;
	}

	public RedBlack<Key, Value> put(Key key, Value val){
	}
	public String toString();
}
