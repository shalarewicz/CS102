// name: RedBlackC.java
// author: Stephan Halarewicz
// date: 12/13/2016
//
// This is the specification of a implementation RedBlack Tree.

public class RedBlackC<Key extends Comparable<Key>, Value> implements RedBlack<Key, Value>{
	// Instance Variables
	private int n;
	private RedBlack<Key, Value> left;
	private RedBlack<Key, Value> right;
	private Key key;
	private Value value;
	private boolean color;

	private final RED = true;

	//Constructor
	public RedBlackC(Key key, Value val, RedBlack left, RedBlack right, Color color){
		this.key = key;
		this.value = value;
		this.left = left;
		this.right= right;
		this.color = color;
		this.n = left.size() + right.size() + 1;
	}
		
		
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
		return this.n == 0;
	}
	
	public int size(){
		return this.n;
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
		if (key.compareTo(current) < 0) this.getLeft().get(key);
		if (key.compareTo(current) > 0) this.getRight().get(key);
		if (key.compareTo(current) == 0) return this.value;
	}
	
	public boolean contains(Key key){
		Key current = this.getKey();
		if (key.compareTo(cuurent) < 0) this.getLeft().contains(key);
		if (key.compareTo(current) > 0) this.getRight().ontains(key);
		if (key.compareTo(current) == 0) return true;
		else return false;
	}


	public RedBlack<Key, Value> put(Key key, Value val){
		Key current = this.getKey();
		if (key.compareTo(cuurent) < 0) newLeft = this.getLeft().put(key, val);
		if (key.compareTo(current) > 0) newRight = this.getRight().put(key, val);
		if (key.compareTo(current) == 0) return new RedBlackC(curr);
		else return false;
	}
	}
	public String toString();
}
