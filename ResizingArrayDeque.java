import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Deque<Item>{

// instance variables
  private int N;
  private int nl;
  private int nr;
  private Item[] a;
// constructor
 public ResizingArrayDeque(){
   a = (Item[]) new Object[2];
   N = 0;
   nl = 0;
   nr = 0;
}

 public boolean isEmpty(){ return N == 0;}
 public int size(){return N;}

 private void resize(int max){
  Item[] newa = (Item[]) new Object[max];
   for (int i = a.length / 2 - nl; i < a.length / 2 + nr; i++){
    if (max > a.length) newa[newa.length / 2 - N / 2 + i] = a[i];
    else newa[newa.length / 2 - N / 2 - 1 + i] = a[i];
   }
   a = newa;
   nl = N / 2;
   nr = N - nl;
 }

 public void pushLeft(Item item){
  if (nl == a.length / 2) resize(a.length * 2);
  a[a.length / 2 - nl - 1] = item;
  nl++;
  N++;
 }
 public void pushRight(Item item){
  if (nr == a.length / 2) resize(a.length * 2);
  a[nr + a.length / 2] = item;
  nr++;
  N++;
 }
 public Item popLeft(){
  if (isEmpty()) {throw new NoSuchElementException();}
  Item item = a[a.length / 2 - nl];
  a[a.length / 2 - nl] = null;
  nl = nl -1;
  N = N - 1;
  if (a.length / 4 <= N) resize(a.length / 2);
  return item;
 }

 public Item popRight(){
  if (isEmpty()) {throw new NoSuchElementException();}
  Item item = a[a.length / 2 + nr - 1];
  a[a.length / 2 + nr - 1] = null;
  nr = nr - 1;
  N = N - 1;
  if (a.length / 4 <= N) resize(a.length / 2);
  return item;
 }
 public String toString(){
  String ans = "The elements in Deque from left to right are";
  for (int i = a.length / 2 - nl; i < a.length / 2 + nr; i++){
   ans = ans + " " + a[i];
  }
  return ans;
 }

 // public Iterator<Item> iterator(){
 //  return new ReverseArrayIterator();
 // }
 // private class ReverseArrayIterator implements Iterator<Item>{
 //  private int i = N;
 //  public boolean hasNext() {return i > nl;}
 //  public Item next() {return a[--i];}
 //  public void remove(){}
 // }

 public static void main(String[] args){
  Deque<String> deque = new ResizingArrayDeque<String>();

  while (!StdIn.isEmpty()) {
   deque.pushRight(StdIn.readString());
   if (!StdIn.isEmpty()) deque.pushLeft(StdIn.readString());
  }
    deque.pushLeft("left"); StdOut.println(deque.toString());
    StdOut.println(deque.popLeft()); 
    StdOut.println(deque.toString());
    deque.pushRight("right"); StdOut.println(deque.toString());
    StdOut.println(deque.popRight()); 
    StdOut.println(deque.toString());
    StdOut.println("Size: " + deque.size());
    StdOut.println("Empty? " + deque.isEmpty());

 }

}
