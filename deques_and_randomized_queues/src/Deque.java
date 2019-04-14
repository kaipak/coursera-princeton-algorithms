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

import java.util.Iterator;

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
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = last;
        last.previous = oldlast;
        n++;
    }

    public Item removeFirst() {
        return first.item;
    }

    public Item removeLast() {
        return last.item;
    }                 // remove and return the item
    // from the end

    public Iterator<Item> iterator() {
        return new ForwardIterator();
    }

    private class ForwardIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(2);
        test.addFirst(4);
        test.addFirst(6);
        for (int i : test) {
            System.out.println(i);
        }
        System.out.println("Size: " + test.size());

    }
}
