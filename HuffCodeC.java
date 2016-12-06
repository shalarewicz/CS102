// name: HuffCodeC.java
// author: Stephan Halarewicz
// date: 11/22/2016
//
// Object HuffbitString used in Huffman Encoding. Encompasses frequency as well as bitpath

public class HuffCodeC implements HuffCode{

// Instance Variables
 private String bitString;
 private int frequency;

// Constructors

 public HuffCodeC(String bitString, int frequency){
  this.bitString = bitString;
  this.frequency = frequency;
 }


// Instance Methods

 public int frequency(){
  return this.frequency;
 }

 public String bitString(){
  return this.bitString;
 }

 public void increment(){
  this.frequency++;
 }
 public void updateBitString(String newBitString){
  this.bitString = newBitString;
 }

 public String toString(){
  return "The frequency is " + this.frequency + " and bitString is " + this.bitString + ".";
 }

 public static void main(String[] args){
 	HuffCodeC test = new HuffCodeC("10", 1);
 	System.out.println(test.toString());
 	System.out.println(test.frequency());
 	System.out.println(test.bitString());
 	test.increment();
 	test.updateBitString("101010");
 	System.out.println(test.toString());
 }
}
