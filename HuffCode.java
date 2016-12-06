// name: HuffCode.java
// author: Stephan Halarewicz
// date: 11/24/2016
//
// Specification for Huffcode.java used in Huffman Encoding. 
// Encompasses frequency as well as bitpath

public interface HuffCode{
	public int frequency();
	public String bitString();
	public void increment();
	public void updateBitString(String bitString);
	public String toString();
}