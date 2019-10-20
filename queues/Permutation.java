/* *****************************************************************************
 *  Name:    Kai Pak
 *
 *  Description: Client class for RandomizedQueue
 *
 *
 *  Written:       04/19/2019
 *  Updated:       10/19/2019
 *
 *  % javac RandomizedQueue.java
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }
        int count = 0;
        for (String i : randomQueue) {
            if (count < k) {
                System.out.println(i);
            } else {
                break;
            }
            count++;

        }
    }
}
