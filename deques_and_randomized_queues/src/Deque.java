/* *****************************************************************************
 *  Name:    Kai Pak
 *
 *  Description: Implementation of deque (double-ended queue) data structure
 *               which supports add/removal of objects to beginning or end.
 *
 *  Written:       04/14/2019
 *
 *  % javac Deque.java
 *
 **************************************************************************** */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n; // number of items in deque

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        n = 0;
    }                           // construct an empty deque

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node;
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node;
        last.item = item;
        oldlast.next = last;
        last.previous = oldlast;
    }

    public Item removeFirst() {
        

    }

    public Item removeLast()                 // remove and return the item from the end

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end

    public static void main(String[] args)   // unit testing (optional)
}
