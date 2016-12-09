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
import java.util.*;

public class Puff{
 private static boolean DEBUG = false;
 private final int zipID = 0x0BC0;
 private final String[] args;

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
            System.out.println("Size is now " + pq.size()); 
        }
        return pq.delMin();

    }

 private void go(){
  System.out.println("Going");
  FileIO channel = new FileIOC();
  BinaryIn input = channel.openBinaryInputFile(this.args[0]);
  FileWriter output = channel.openOutputFile();


  RedBlackBST<Integer, HuffCode> st = new RedBlackBST<Integer, HuffCode>();

  int current = 0;
  int fileZipID = input.readInt(16);
  System.out.println(fileZipID);
  if (fileZipID != zipID) System.out.println("File not compressed by CS102 Huff alg");
  else{
   int stSize = input.readInt(32);
   System.out.println(stSize);
   for (int i = 1; i <= stSize; i++){
   	System.out.println("St insertions" + i);
    int charCode = input.readInt(8);
    int charFreq = input.readInt(32);
    st.put(charCode, new HuffCodeC("", charFreq));
   }

  HuffTreeC finalTree = createFinal(st);
  System.out.print("Creating Final HuffTree...");
  finalTree.bitPaths(st);
  System.out.println("done");


  HuffTree currentHT = finalTree;
  while(current != -1){
   if (!input.isEmpty()) current = input.readInt(1);
   else break;

   if (current == 1) {
    currentHT = currentHT.getRight(); // need to implement getLeft and getRight
    if (currentHT.isLeaf()) { // need to make this public
        try{
        	System.out.print("Writing" + (char) currentHT.getSymbol() + "...");
            output.write( (char) currentHT.getSymbol());
            currentHT = finalTree; // implement this
            System.out.println("done");
        }
        catch(IOException e){}
    }
   }

   if (current == 0){
    currentHT = currentHT.getLeft();
    if (currentHT.isLeaf()) { // need to make this public
        try{
            output.write( (char) currentHT.getSymbol());
            currentHT = finalTree; // implement this
        }
        catch(IOException e){}
    }
   } 

  }
        try{
            output.close();
        }
        catch(IOException e){}

  }
 }


 public static void main(String args[]){
  new Puff(args).go();
 }


}