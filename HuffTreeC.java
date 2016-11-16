// name: HuffTreeC.java
// author: Stephan Halarewicz
// date: 11/16/2016
//
// This program implements the HuffTree API. HuffTree is a Red-Black BST data structure
// used in the implementation of Huffman encoding. 
// 
// compile with javac HuffTreeC.java
// test with java HuffTreeC
// Dependencies: ?

import java.util.NoSuchElementException;

public class HuffTreeC<Key extends Comparable<Key>, Value> implements HuffTree<Key, Value>{
	// Instance Variables
	private Node root;
	private static final RED = true;
	private static final BLACK = false;

	// Constructors
	public HuffTreeC(){
		root = null;
	}
	// Nested Class Node
	private class Node{
		private Node left;
		private Node right;
		private int weight;
		private Key symbol;
		private boolean color;
		private int size;

		public Node(Node left, Key symbol, int weight, Node right){
			this.left = left; this.symbol = symbol; this.weight = weight; this.right = right;
			this.color = RED;
			this.size = 0;
		}
	}

	// Helpers
		private boolean isRed(x){
			if (x == null) return false;
			return x.color == RED;
		}

		private Node rotateLeft(Node x){
			Node right = x.right;
			x.right = right.left;
			right.left = x;
			right.color = x.color;
			x.color = RED;
			right.size = x.size;
			x.size = size(x.left) + size(x.left) + 1;

		}

	// Instance methods

    public boolean isEmpty(){
    	return this.root.size == 0;
    }

    public int size(){
    	return this.root.size;
    }
    public boolean contains(Key key){
    	// this.root = root;
    	// return contains(root, key);
    	Node current == this.root;
    	while (current != null){
    		if (key < current.symbol) {current = current.left; continue;}
    		else if (key > current.symbol) {current = current.right; continue;}
    		else if (key == current.symbol) {return true;}

    	}
    	return false;
    }

    public Value get(Key key){
    	return get(root, key);
    }

    private Value get(Node current, Key key){
    	if (current == null) return null;
    	int cmp = key.compareTo(current.key)
    	if (cmp < 0) return get(current.left, key);
    	else if (cmp > 0) return get(current.right, key);
    	else return current.weight;
    }

    public HuffTree<Key, Value> put(Key key, Value val){
    	put(root, key, value);

    }

    public HuffTree<Key, Value> put(Node root, Key key, Value val){
    	if (root == null) {
    		root = new Node(null, key, val, null);
    		size++;
    		return root;
    	}
    	return put(root, key, value);
    }

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