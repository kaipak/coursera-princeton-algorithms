/* *****************************************************************************
 *  Name:    Kai Pak
 *
 *  Description: Implementation of randomized queue which supports random
 *  queue access and retrieval in amortized constant time.
 *
 *
 *  Written:       04/19/2019
 *
 *  % javac RandomizedQueue.java
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        n = 0;
    }

    private void resize(int size) {
        // Move queue to a new awrray of size
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0, i <n;
        i++)
        temp[i] = items[i];
        items = temp;
    }


    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    public void enqueue(Item item) {
        if (n == items.length) resize(2 * items.length);
        items[n++] = item;

    }

    public Item dequeue() {
        // Remove and return a random item
        int randomIndex = StdRandom.uniform(1, n + 1);
        Item item = items[randomIndex];
        items[randomIndex] = null;
        --n;
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return item;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        int randomIndex = StdRandom.uniform(1, n + 1);
        Item item = items[randomIndex];
        return item;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        
    }

    public static void main(String[] args)   // unit testing (optional)
}
