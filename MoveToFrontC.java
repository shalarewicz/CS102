// File: MoveToFront.java
// Name: Andrew Francl
// Date: Feb 12 2014

public class MoveToFrontC implements MoveToFront{
    private Node front;
    private Node back;
    private int N;


    public MoveToFrontC() {
        this.front = null;
        this.back = null;
        this.N = 0;
    }

    private class Node {
        private char info;
        private Node next;
        private Node previous;

        private Node(char info) {
            this.info = info;
            this.next = null;
            this.previous = null;
        }

        private Node getNext() {return this.next;}
        private Node getPrevious() {return this.previous;}
        private char getInfo() {return this.info;}
        private void setPrevious(Node n) {this.previous = n;}
        private void setNext(Node n) {this.next = n;}
    }

    public void push(char info) {
        Node tempNode = new Node(info);
        if (isEmpty()){
            this.front = tempNode;
            this.back = tempNode;
        }
        else if (inList(info) && N > 1) {
            tempNode.setNext(this.front);
            this.front.setPrevious(tempNode);
            this.front = tempNode;
        }
        else if (inList(info)) {

            tempNode.setNext(this.front);
            this.front.setPrevious(tempNode);
            this.front = tempNode;
        }
        else {
            tempNode.setNext(this.front);
            this.front.setPrevious(tempNode);
            this.front = tempNode;
        }
        this.N++;

    }

    private boolean inList(char character) {
        Node tempNode = this.front;
        while(tempNode!=null) {
            if (tempNode.getInfo() == character) {
                removeFromLink(character);
                this.N--;
                return true;
            }
            else
                tempNode = tempNode.getNext();
        }
        return false;
    }

    public void removeFromLink(char character) {
        Node tempNode = this.front;
        while(tempNode!=null) {


            if (tempNode.getInfo() == character && tempNode.getNext()==null && N >1) {
                this.back = tempNode.getPrevious();
                tempNode.previous = null;
                this.back.next = null;
                break;

            }
            else if (tempNode.getInfo() == character && tempNode.getPrevious()==null) {
                System.out.println("Hi");
                this.front = tempNode.getNext();
                tempNode.next = null;
                break;
            }
            else if (tempNode.getInfo() == character) {
                Node prev = tempNode.getPrevious();
                Node next = tempNode.getNext();
                prev.next = next;
                next.previous = prev;
                tempNode.previous = null;
                tempNode.next=null;
                break;
            }
            else
                tempNode = tempNode.getNext();

        }
        N--;
    }

    public boolean isEmpty() { return this.N == 0;}

    public String toString(){
        // Use Sting Builder?
        StringBuilder sb = new StringBuilder("Queue = ");
        Node tempNode = this.front;
        while(tempNode!=null) {
            sb.append(tempNode.getInfo() + " ");
            tempNode = tempNode.getNext();
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        MoveToFront movingList = new MoveToFrontC();
        movingList.push('a');
        movingList.push('b');
        movingList.push('c');
        movingList.push('c');
        movingList.push('d');
        movingList.push('a');
        System.out.println("Pushed a , b, c, c, a and " + movingList.toString());
    }

}