// name: Puff.java
// author: Stephan Halarewicz
// date: 12/8/2016
// 
// compile with javac-algs4 Puff.java
// run with java-algs4 Puff "filename.txt"
// This results in a compressed zip file "filename.zip"
//
// Dependencies - algs4 library RedBlackBST.java,  FileIO.java, MinPQ.java


import java.io.*;

public class Puff{
	private static boolean DEBUG = false;
	private final int zipID = 0x0BC0;
	private final String args = String[] args;

	public Puff(String[] args){
		this.args = args;
	}

	  public static HuffTreeC createFinal(RedBlackBST<Integer, HuffCode> st){
        MinPQ<HuffTreeC> pq = new MinPQ<HuffTreeC>();
        for (Integer key : st.keys()){
            HuffTreeC leaf = new HuffTreeC(key, st.get(key).frequency());
            System.out.println("Adding leaf " + leaf.toString() + " to pq.");
            pq.insert(leaf);
        }
        System.out.println("The pq has size " + pq.size());
        System.out.println("The pq reads " + pq.toString());
        System.out.println();

        while(pq.size() > 1){
            HuffTreeC t1 = pq.delMin();
            HuffTreeC t2 = pq.delMin();
            HuffTreeC t = new HuffTreeC(t1, t2);
            pq.insert(t);
            // if (t1.weight() == 293 || t2. weight() == 293) System.out.println("Merging " + t1.toString() + " and " + t2.toString());
            System.out.println("Merging " + t1.toString() + " and " + t2.toString());
            System.out.println("Adding to pq " + t.toString());
        }
        return pq.delMin();

    }

	private void go(){
		FileIO channel = new FileIOC();
		FileReader input = channel.openBinaryInputFile(this.args[0]);

		RedBlackBST<Integer, HuffCode> st;

		int current = 0;
		try{
			current = input.read();
		}
		catch (IOEception e) {}
		if (current == -1) break;
		int fileZipID = input.readInt(8);
		if (fileZipID != zipID) System.out.println("File not compressed by CS102 Huff alg");
		else{
			int stSize = input.readInt(32);
			for (int i = 1; i <= stSize; i = i++){
				int charCode = input.readInt(8);
				int charFreq = input.readInt(32);
				st.insert(charCode, new HuffCode("", charFreq));
			}

		HuffTreeC finalTree = createFinal(st);
		finalTree.bitPaths(st);


		try{
			current = input.read();
		}
		catch (IOEception e) {}

		HuffTreeC currentHT = finalTree;
		while(current != -1){
			current = input.readInt(1);

			if (current == 1) {
				currentHT = currentHT.getRight()); // need to implement getLeft and getRight
				if (currentHT.isLeaf()) { // need to make this public
					output.write( (char) currentHT.getSymbol); // implement this
				}
			}

			if (current == 0){
				currentHT = currentHT.getLeft());
				if (currentHT.isLeaf()) { // need to make this public
					output.write( (char) currentHT.getSymbol); // implement this
				}
			} 

		}

		}
	}


	public static void main(String args[]){
		new Puff(args).go();
	}


}