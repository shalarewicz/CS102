// name: Huff.java
// author: Stephan Halarewicz
// date: 11/15/2016
// 
// compile with javac Huff.java
// run with java Huff "filename.txt"
// This results in a compressed zip file "filename.zip"
//
// Dependencies - RedBlackBST.java,  FileIO.java, Queue.java

import java.io.*;

public class Huff{

 public static final boolean DEBUG = false;
 
 private static RedBlackBST<Integer, HuffCode> readIn(String filename){
  RedBlackBST<Integer, HuffCode> st = new RedBlackBST<Integer, HuffCode>();

  FileIO channel = new FileIOC();
  FileReader input = channel.openInputFile(filename);
  int current = 0;

  while (current != -1){
    try{
        current = input.read();
    }
    catch (IOException e){}
    if (current == -1) break;
    if (st.contains(current)) st.get(current).increment();
    else st.put(current, (new HuffCodeC("", 1)));
  }
  return st;
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
public static void write(RedBlackBST<Integer, HuffCode> st, String filename){
	FileIO channel = new FileIOC();
	FileReader input = channel.openInputFile(filename);
	BinaryOut output = channel.openBinaryOutputFile();
	int zipID = 0x0BC0;
	output.write(zipID, 16);
	output.write(st.size(), 32);
	for (int key : st.keys()){
		int frequency = st.get(key).frequency();
		System.out.println("Writing (" + (char) key + ", " + frequency +")");
		output.write(key, 8);
		output.write(frequency, 32);
	}

	int current = 0;

  	while (current != -1){
	    try{
	        current = input.read();
	    if (current == -1) break;
	    String path = st.get(current).bitString();
	    int bits = 0;
	    int size = path.length();
	    //System.out.println("String reads " + path + " length is " + size);
	    for (int i = 0; i < size; i++){
	    	int bit = 0;
	    	char c = path.charAt(i);
	    	//System.out.println("Parsing char " + c);
	    	if (c == '1') {bit = 1;} //System.out.println("Turned bit on");}
	    	bits = (bits << 1) | bit;
	    	//System.out.println("Added bit " + bit + " to bits " + bits);
	    	//System.out.println("Size incremented to " + size);

	    }
	    //System.out.println("Writing bits " + bits + " and size " + size);
	    //System.out.println();
	    output.write(bits, size);
		}	
	    catch (IOException e){System.out.println("Caught this exception" + e);}
	}
		output.flush();
		output.close();


}
 // Helper Functions

 public static void main(String[] args){
  String filename = args[0];
  RedBlackBST<Integer, HuffCode> st = readIn(filename);
  // for (Integer key : st.keys()){
  //           System.out.print("(" + key + ", " + st.get(key) + ") ");
  //       }
  //       System.out.println();
  HuffTreeC finalTree = createFinal(st);
  System.out.println("The final HuffTree is "+ finalTree.toString());
  System.out.println();
  finalTree.bitPaths(st);
  write(st, filename);

 }

}

