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
 private Node top;
    private int size;

 // Constructors
 public HuffTreeC(){
  top = null;
        size = 0;
    }
    public HuffTreeC(Key key, int weight){
        this.top = new Node(null, key, weight, null);
        size =1;
    }

    public HuffTreeC(HuffTreeC<Key> left, HuffTreeC<Key> right){
        this.top = new Node (left.top, null, 0, right.top);
        size = left.size() + right.size();
    }

 // Nested Class Node
 private class Node{
  private Node left;
  private Node right;
  private int weight;
  private Key symbol;

        public Node(Node left, int weight, Node right){
            this.left = left; this.weight = weight; this.right = right;
        }
        public Node(Key symbol, int weight){
            this.symbol = symbol; this.weight = weight;
        }
  public Node(Node left, Key symbol, int weight, Node right){
   this.left = left; this.symbol = symbol; 
            this.weight = weight; this.right = right;
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
        int ans = 0;
        if (this.top.weight < x.top.weight) return ans - 1;
        if (this.top.weight > x.top.weight) return ans + 1;
        if (this.top.weight == x.top.weight) return ans;
        else return 0;
    }
    // public HuffTreeC<Key> leaf(Key symbol, int weight){
    //     Node root = new Node(null, symbol, weight, null);
    //     top = root;   
    // }

    // public HuffTreeC<Key> merge(HuffTreeC<Key> left, HuffTreeC<Key> right){
    //     Node root = new Node(left.top, null, left.weight() + right.weight(), right.top);
    //     top = root;
    // }

    // private class BitPathNode{
    //     private Node x;
    //     private String path;

    //     public BitPath(Node x, String path){
    //         this.x = x; this.path = path;
    //     }
    // }

    private boolean isLeaf(Node x){
        return (x.left == null && x.right == null);
    }
    public RedBlackBST<Key, String> bitPaths(RedBlackBST<Key, String> st){
        return bitPaths(this.top, "", st);
    }

    private RedBlackBST<Key, String> bitPaths(Node root, String path, RedBlackBST<Key, String> st){
        Node left = root.left;
        Node right = root.right;
        RedBlackBST<Key, String> newST = st;
        if  (isLeaf(left)) newST.put(left.symbol, path + 0);
        else {
            newST = bitPaths(left, path + 0, newST);
        }
        if (isLeaf(right)) newST.put(right.symbol, path + 1);
        else {
            newST = bitPaths(right, path + 1, newST);
        }
        return newST;
    }

    public String toString(){
      if (isEmpty()) return "The Huffman Tree is empty.";
      else{
        String sz = "The Huffman Tree has size = " + this.size + ". ";
        String s = "The Huffman Tree from top to bottom reads"; 
        LinkedDeque<Node> queue = new LinkedDeque<Node>();
        Node start = this.top;
        queue.pushLeft(start);
        while (!queue.isEmpty()){
          Node next = queue.popRight();
            s = s + " " + next.symbol + " " + next.weight;
            if (next.left != null) queue.pushLeft(next.left);
            if (next.right != null) queue.pushLeft(next.right);
          }
        return s + ".";
      }
    }


    public static void main(String[] args){
        RedBlackBST<Character, String> st = new RedBlackBST<Character, String>();
        st.put('a', "5");
        st.put('b', "5");
        st.put('c', "5");
        st.put('d', "5");
        System.out.print("The ST reads ");
        for (Character key : st.keys()){
            System.out.print("(" + key + ", " + st.get(key) + ") ");
        }
        System.out.println();
        HuffTreeC<Character> left = new HuffTreeC<Character>('a', 1);
        System.out.println(left.toString());
        HuffTreeC<Character> right = new HuffTreeC<Character>('b', 1);
        System.out.println(right.toString());
        HuffTreeC<Character> left1 = new HuffTreeC<Character>('c', 1);
        System.out.println(left1.toString());
        HuffTreeC<Character> right1 = new HuffTreeC<Character>('d', 1);
        System.out.println(right1.toString());
        HuffTreeC<Character> merge = new HuffTreeC<Character>(left, right);
        HuffTreeC<Character> merge1 = new HuffTreeC<Character>(left1, right1);
        HuffTreeC<Character> mergeFinal = new HuffTreeC<Character>(merge, merge1);
        System.out.println(merge.toString());
        System.out.println(merge1.toString());
        System.out.println(mergeFinal.toString());
        mergeFinal.bitPaths(st);
        System.out.print("The ST reads ");
        for (Character key : st.keys()){
            System.out.print("(" + key + ", " + st.get(key) + ") ");
        }
        System.out.println();
    }

}


