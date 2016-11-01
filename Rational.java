// name: Rational.java
// Author: Robert Muller
// Date: Sept. 14 2016
//
// This is an API for the ADT Rational

public interface Rational{

	public int getNumerator();
	public int getDenominator();
	public Rational add(Rational other);
	public int compareTo(Rational other);
	public String toString();
}