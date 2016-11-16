// name: HuffTreeC.java
// author: Stephan Halarewicz
// date: 11/16/2016
//
// This program implements the HuffTree API. HuffTree is a BST data structure
// used in the implementation of Huffman encoding. 
// 
// compile with javac HuffTreeC.java
// test with java HuffTreeC
// Dependencies: ?

public class HuffTreeC<Key extends Comparable<Key>, Value> implements HuffTree<Key, Value>{
	// Instance Variables
	private Node top;
	private int size;

	// Constructors
	public HuffTreeC(){
		size = 0;
		top = null;
	}
	// Nested Class Node
	private class Node{
		private Node left;
		private Node right;
		private int weight;
		private Key symbol;

		public Node(Node left, Key symbol, int weight, Node right){
			this.left = left; this.symbol = symbol; this.weight = weight; this.right = right;
		}
	}

    public boolean isEmpty(){
    	return this.size == 0;
    }

    public int size(){
    	return this.size;
    }
    public boolean contains(Key key){
    	// this.top = root;
    	// return contains(root, key);
    	Node current == this.top;
    	while (current != null){
    		if (key < current.symbol) {current = current.left; continue;}
    		else if (key > current.symbol) {current = current.right; continue;}
    		else if (key == current.symbol) {return true;}

    	}
    	return false
    }

    public Value get(Key key);

    public HuffTree<Key, Value> put(Key key, Value val);

    public HuffTree<Key, Value> deleteMin();

    public HuffTree<Key, Value> deleteMax();

    public HuffTree<Key, Value> delete(Key key);    // Hibbard Deletion

    public Key min();

    public Key max();

    public String toString();

    public Key floor(Key key);

    public int height(); 

}
}