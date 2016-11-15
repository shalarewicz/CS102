// name: Huff.java
// author: Stephan Halarewicz
// date: 11/15/2016
// 
// compile with javac Huff.java
// run with java Huff "filename.txt"
// This results in a compressed zip file "filename.zip"
//
// Dependencies - BinaryTree.java,  FileIO.java, Queue.java

public class Huff{

	private BinaryTree readIn(String filename){
		FileReader input = openInputFile(filename);
		BinaryTree<HuffCode, int> st = new BinaryTree<HuffCode, int>(); // symbol table needs to be iterable for going into pq
		while (!StdIn.isEmpty()){
			// create BinaryTree<HuffCode, int> 
			// object HuffCode encapsualtes int length and int code
			// Value int is the frequency
			char next = Char.parseChar();
			HuffCode nextHuff = new HuffCode(next); // implement with HuffCode(){this.length = x, this.code = code}
			// int frequency = st.get(next);
			// if (frequency != null) frequency++;
			// else 
			st.put(next, frequency++); // might not work with null + 1 so each new node should by default be zero.
									   // this might not be wortch the space compared to the time it saves optimize later.
									   // can just use contains if needed
			}
		close(filename); // Need to figure out how to close the file. 		
		return st;
	}

	private class HuffTree{ // Nodes etc. 
	}

	private MaxPQC<HuffTree> createPQ(BinaryTree<HuffCode, int> st){
		MaxPQC<HuffTree> pq = new MaxPQC<HuffTree>();
		while (!st.isEmpty()){
			HuffCode next = st.min();
			int weight = st.get(next);
			HuffTree toInsert = new HuffTree(null, next, weight, null); // HuffTree needs to be Comparable by weight (2 constructors?)
			pq.insert(toInsert);
		}
		return pq;
	}

	private MaxPQC<HuffTree> collapse(){
		while(this.size() >= 2){
			HuffTree t1 = this.delMax();
			HuffTree t2 = this.delMax();
			int newWeight = t1.weight + t2.weight;
			HuffTree t = new HuffTree(t1, null, newWeight, t2); // (2 constructors?)
			this.insert(t);
		}
		return this;
	}

	private BinaryTree<HuffCode, int> write(HuffTree tree){ // move this into Huff tree class so you can use left and right nodes 
															// might need to move collapse as well
		// reucursive walk
		Queue<HuffRoute> toTry = new Queue(); // Need new object to record path taken
		HuffTree top = tree.root(); // not sure on type for top. might be a node
		// need new object with node and bitpath for queue to keep track
		toTry.enqueue(top); // need a better condidtion so you can change ST in for loop
		int size = tree.size();
		for (int i = 1, i < size, i++){
			HuffTree parent = toTry.dequeue();
			HuffTree left = parent.left; // this isn't a good idea and not part of the BST API
			HuffTree right = parent.right;
			if (isLeaf(left)) {
				toTry.enqueue(left);
				st.update(left); // just the basic idea also n
		}




	}


	// Helper Functions

	private static isLeaf(HuffTree x){
		return (x.left == null && x.right == null);
	}

	public static void main(String[] args){
		String filename = args[0];
		BinaryTree<HuffCode, int> st = readIn(filename);
		MaxPQC<HuffTree> pq = createPQ(st);
		pq.collapse();
		HuffTree finalTree = pq.delMax();
		st.write(finalTree);

	}

}

