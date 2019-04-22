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
 *****************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n; // number of items in deque

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private Deque() {
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
        }
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
        test.addLast(12);
        test.addLast(24);
        for (int i : test) {
            System.out.println(i);
        }
        System.out.println("Size: " + test.size());
        System.out.println(test.isEmpty());
        System.out.println("Getting first" + test.removeFirst());
        for (int i : test) {
            System.out.println(i);
        }
        System.out.println("Getting last" + test.removeLast());
        for (int i : test) {
            System.out.println(i);
        }
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        System.out.println("What's in it now?" + test.first + test.last);
        test.removeFirst();
    }
}
