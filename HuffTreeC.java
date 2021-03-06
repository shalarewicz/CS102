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

public class HuffTreeC implements HuffTree, Comparable{
 // Instance Variables
    private HuffTreeC left;
    private HuffTreeC right;
    private int weight;
    private int symbol;

 // Constructors
    public HuffTreeC(){
        left = null;
        right = null;
        weight = 0;
        symbol = -1;
    }
    public HuffTreeC(int key, int weight){
        this.symbol = key;
        this.weight = weight;
    }

    public HuffTreeC(HuffTreeC left, HuffTreeC right){
        this.left = left;
        this.right = right;
        this.symbol = -1;
        this.weight = left.weight() + right.weight();
    }

    public HuffTree getLeft(){
    	return this.left;
    }

    public HuffTree getRight(){
    	return this.right;
    }

    public int getSymbol(){
        return this.symbol;
    }
    // public HuffTreeC(RedBlackBST<Integer, HuffCode> st){
    //     MinPQ<HuffTreeC> pq = new MinPQ<HuffTreeC>();
    //     for (Integer key : st.keys()){
    //         HuffTreeC leaf = new HuffTreeC(key, st.get(key).frequency());
    //         System.out.println("Adding leaf " + leaf.toString() + "with weight " + leaf.weight() + " to pq.");
    //         pq.insert(leaf);
    //     }

    //     while(pq.size() > 1){
    //         HuffTreeC t1 = pq.delMin();
    //         HuffTreeC t2 = pq.delMin();
    //         HuffTreeC t = new HuffTreeC(t1, t2);
    //         pq.insert(t);
    //         System.out.println("Merging " + t1.toString() + " and " + t2.toString());
    //         System.out.println("Adding to pq " + t.toString());
    //     }

    // }

    public boolean isEmpty(){
        return this.weight == 0;
    }

    public int weight(){
        return this.weight;
    }

    public int compareTo(Object other){
        HuffTreeC x = (HuffTreeC) other;
        int ans = 0;
        if (this.weight < x.weight) return ans - 1;
        if (this.weight > x.weight) return ans + 1;
        if (this.weight == x.weight) return ans;
        else return 0;
    }

    public boolean isLeaf(){
        return (this.left == null && this.right == null);
    }

    public void bitPaths(RedBlackBST<Integer, HuffCode> st){
        bitPaths("", st);
    }

    private void bitPaths(String path, RedBlackBST<Integer, HuffCode> st){
        if (this.isLeaf()) {
            HuffCode current = st.get(this.symbol);
            current.updateBitString(path);
            //System.out.println("Updating " + this.symbol + " with " + current.toString());
            st.put(this.symbol, current);
            //System.out.print("The st now reads ");
            // for (Integer key : st.keys()){
            //     System.out.print("(" + key + ", " + st.get(key) + ") ");
            // }
            // System.out.println();
            // System.out.println();



        }
        else {
            left.bitPaths(path + 0, st);
            right.bitPaths(path + 1, st);
        }
    }

    public String toString(){
      if (isEmpty()) return "The Huffman Tree is empty.";
      else{
        String s = "HT["; 
        LinkedDeque<HuffTreeC> queue = new LinkedDeque<HuffTreeC>();
        HuffTreeC start = this;
        queue.pushLeft(start);
        while (!queue.isEmpty()){
          HuffTreeC next = queue.popRight();
            if (next.symbol == 10 || next.symbol == 13){
                s = s + " (" + next.symbol + ", " + next.weight + ")";
            } 
            else {
                s = s + " (" + (char) next.symbol + ", " + next.weight + ")";
            }
            if (next.left != null) queue.pushLeft(next.left);
            if (next.right != null) queue.pushLeft(next.right);
          }
        return s + "].";
      }
    }


    public static void main(String[] args){
        RedBlackBST<Integer, HuffCode> st = new RedBlackBST<Integer, HuffCode>();
        st.put(12, new HuffCodeC("test", 1));
        st.put(13, new HuffCodeC("test", 1));
        st.put(14, new HuffCodeC("test", 1));
        st.put(15, new HuffCodeC("test", 1));
        System.out.print("The ST reads ");
        for (Integer key : st.keys()){
            System.out.print("(" + key + ", " + st.get(key).toString() + ") ");
        }
        System.out.println();

        // HuffTreeC finalTree = new HuffTreeC(st);

        // System.out.println("The Weight is " + finalTree.weight());
        // System.out.println();
        // System.out.println();
        
        // System.out.println(finalTree.toString());
        // System.out.println();

        // // finalTree.bitPaths(st);

        // for (Integer key : st.keys()){
        //     System.out.print("(" + key + ", " + st.get(key) + ") ");
        // }
        // System.out.println();
        // System.out.println();

        HuffTreeC left = new HuffTreeC(12, 1);
        System.out.println(left.toString());
        HuffTreeC right = new HuffTreeC(13, 1);
        System.out.println(right.toString());
        HuffTreeC left1 = new HuffTreeC(14, 1);
        System.out.println(left1.toString());
        HuffTreeC right1 = new HuffTreeC(15, 1);
        System.out.println(right1.toString());
        HuffTreeC merge = new HuffTreeC(left, right);
        HuffTreeC merge1 = new HuffTreeC(left1, right1);
        HuffTreeC mergeFinal = new HuffTreeC(merge, merge1);
        System.out.println(merge.toString());
        System.out.println(merge1.toString());
        System.out.println(mergeFinal.toString());
        mergeFinal.bitPaths(st);
        System.out.print("The ST reads ");
        for (Integer key : st.keys()){
            System.out.print("(" + key + ", " + st.get(key) + ") ");
        }
        System.out.println();
    }

}


