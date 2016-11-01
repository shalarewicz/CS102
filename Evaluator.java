// name: Evaluator.java
// author: Stephan Halarewicz
// date: Sept. 15 2016
//
// This is an implementation of Dijkstra's 2-stack algorithm for evaluating arithmetic expressions
// accounting for not fully parenthesized expressions and higher order operators ^, sqrt. However, 
// any parentheses used must be fully closed. Note that all operators and numbers must
// be separated by spaces. Evaluator utilizes some StdIn algs-4 libraries.
// 
// run with java Evaluator "( 1 + 3 ) * sqrt 5 + ( 1 / 6 ) ^ 4"
//
// operators (, ), ^, *, /, +, -, sqrt


public class Evaluator{

 private static int priority(String op1){
  if (op1.equals("+") | op1.equals("-")) return 1; else
  if (op1.equals("*") | op1.equals("/")) return 2; else 
  if (op1.equals("^") | op1.equals("sqrt")) return 3; else
  return 0;
 }

 private static boolean morePrecedent(String op1, String op2){
  return ((priority(op1) >= priority(op2)));
 }

 private static void evalParens(Stack<String> ops, Stack<Double> vals){

  while (!ops.peek().equals("(")) {
    String op = ops.pop();
    double x = vals.pop();
    if (op.equals("sqrt")) {vals.push(Math.sqrt(x)); continue;}
    double y = vals.pop();
    if (op.equals("+")) vals.push(y + x); 
    if (op.equals("-")) vals.push(y - x);
    if (op.equals("*")) vals.push(y * x);
    if (op.equals("/")) vals.push(y / x);
    if (op.equals("^")) vals.push(Math.pow(y, x));

  }
  ops.pop();
}

 private static void eval(Stack<String> ops, Stack<Double> vals, String op){

  while ((!ops.isEmpty()) && (morePrecedent(ops.peek(), op))) {
    String opBefore = ops.pop();
    double x = vals.pop();
    if (opBefore.equals("sqrt")) {vals.push(Math.sqrt(x)); continue;} 
    double y = vals.pop();
    if (opBefore.equals("+")) vals.push(y + x); 
    if (opBefore.equals("-")) vals.push(y - x);
    if (opBefore.equals("*")) vals.push(y * x);
    if (opBefore.equals("/")) vals.push(y / x);
    if (opBefore.equals("^")) vals.push(Math.pow(y, x));
  }
    ops.push(op);
}

 public static double calculate(){
  
  Stack<String> ops = new ResizingArrayStack<String>();
  Stack<Double> vals = new ResizingArrayStack<Double>();

  while (!StdIn.isEmpty()){
   String next = StdIn.readString();
   if (next.equals("(")) ops.push(next); 
   else if (next.equals("sqrt")) {eval(ops, vals, next);}
   else if (next.equals("^")) {eval(ops, vals, next);} 
   else if (next.equals("+")) {eval(ops, vals, next);}
   else if (next.equals("-")) {eval(ops, vals, next);}
   else if (next.equals("*")) {eval(ops, vals, next);}
   else if (next.equals("/")) {eval(ops, vals, next);}
   else if (next.equals(")")) evalParens(ops, vals);
   else {vals.push(Double.parseDouble(next));
   } 
  }
  eval(ops, vals, "");
  return vals.pop();

 }
 public static void main(String[] args){

  System.out.println("The answer is " + calculate());

 }
}