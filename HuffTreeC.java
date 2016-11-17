// name: HuffTreeC.java
// author: Stephan Halarewicz
// date: 11/16/2016
//
// This program implements the HuffTree API. HuffTree is a BST data structure
// used in the implementation of Huffman encoding. 
// 
// compile with javac-algs4 HuffTreeC.java
// test with java-algs4 HuffTreeC
// Dependencies: Queue.java

public class HuffTreeC<Key extends Comparable<Key>> implements HuffTree<Key>{
	// Instance Variables
	private Node root;
    private int size;

	// Constructors
	public HuffTreeC(){
		top = null;
        size = 0;
    }

    public leaf(Key symbol, int weight){
        Node root = new LeafNode(symbol, weight);
        top = root;   
    }

    public merge(HuffTreeC<Key> left, HuffTreeC<Key> right){
        Node root = new InteriorNode(left.top, left.weight() + right.weight(), right.top);
        top = root;
    }

	// Nested Class Node
	private class Node{
		private Node left;
		private Node right;
		private int weight;
		private Key symbol;

        public InteriorNode(Node left, int weight, Node right){
            this.left = left; this.weight = weight; this.right = right;
        }
        public LeafNode(Key symbol, int weight){
            this.symbol = symbol; this.weight = weight;
        }
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

    public int weight(){
        return this.top.weight;
    }

    public int compareTo(HuffTreeC<Key> x){
        if (this.top.weight < x.top.weight) return -1;
        if (this.top.weight > x.top.weight) return 1;
        if (this.top.weight == x.top.weight) return 0;
    }

    private class BitPath{
        Node x;
        String path;

        public BitPath(Node x, String path){
            this.x = x; this.path = path;
        }
    }

    private boolean isLeaf(x){
        return (x.left == null && x.right == null && x.symbol != null && x.weight!= null)
    }
    public ResizingArrayStack<BitPath> bitPaths(){ // can pass st as argument and avoid using stack
        Queue<BitPath> toDo = new Queue<BitPath>();
        ResizingArrayStack<BitPath> ans = new ResizingArrayStack<BitPath>();
        Node current = this.top;
        bitPath = new BitPath(current, "");
        toDo.enqueue(bitPath);
        while (!toDo.isEmpty()){
            BitPath next = toDo.dequeue();
            Node left = next.node.left;
            if (left != null){
                Bitpath leftPath = new BitPath(left, 0 + "");
                if (isLeaf(left)) ans.push(leftPath); // st.put(leftPath.node.symbol, leftPath.path)
                toDo.enqueue(leftPath)
            }
            Node right = next.node.right;
            if (right != null){
                Bitpath rightPath = new BitPath(right, 0 + "");
                if (isLeaf(right)) ans.push(rightPath);
                toDo.enqueue(rightPath)
            }
        }
        return ans;

    }
    // public boolean contains(Key key){
    // 	// this.top = root;
    // 	// return contains(root, key);
    // 	Node current == this.top;
    // 	while (current != null){
    // 		if (key < current.symbol) {current = current.left; continue;}
    // 		else if (key > current.symbol) {current = current.right; continue;}
    // 		else if (key == current.symbol) {return true;}

    // 	}
    // 	return false
    // }

}
