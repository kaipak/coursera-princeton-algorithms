/* *****************************************************************************
 *  Name:    Kai Pak
 *
 *  Description: Implementation of deque (double-ended queue) data structure
 *               which supports add/removal of objects to beginning or end.
 *
 *  Written:       04/14/2019
 *  Updated:       10/19/2019
 *
 *  % javac Deque.java
 *
 *****************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

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
        first = null;
        last = null;
        n = 0;
    }

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
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        n++;
    }

    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nothing to remove!");
        }
        Item item = first.item;
        --n; // decrement first so we're up to date on number of items

        // if block handles case where we're removing last item
        if (!isEmpty()) {
            first = first.next;
        } else {
            first = null;
            last = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Nothing to remove!");
        }
        Item returnItem = last.item;
        last = last.previous; // 2nd to last node is last
        last.next = null; // now make previous last empty
        --n;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return returnItem;
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

        // We don't generally want to mutate data in an iterator but this
        // needs to still be defined.
        public void remove() {
        }

        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException("Nothing to remove!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // Unit testing
    public static void main(String[] args) {

        // Test creation of integer deque and all methods
        Deque<Integer> test = new Deque<Integer>();
        test.addLast(2);
        test.addLast(4);
        test.addLast(6);
        test.addFirst(42);
        test.addFirst(82);
        System.out.println("Added, 2, 4, 6");
        for (Integer i : test)
            StdOut.println(i);
        System.out.println("-------------------------");
        // Should be 3 now
        System.out.println("Size of deque is now " + test.size());
        System.out.println(test.removeFirst());
        // Should be 0 now
        System.out.println("Size of deque is now " + test.size());
        test.addLast(128);
        test.removeLast();

        // Test iterator
        for (Integer i : test)
            StdOut.println(i);
    }
}
