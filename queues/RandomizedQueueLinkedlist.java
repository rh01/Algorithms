/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class RandomizedQueueLinkedlist<Item> implements Iterable<Item> {

    private class Node {
        public Item item = null;
        public Node next = null;
    }

    // primary element.
    private Node first, last;
    private int size;

    public RandomizedQueueLinkedlist() {                    // construct an empty randomized queue
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {                  // is the randomized queue empty?
        return size == 0;
    }

    public int size() {                         // return the number of items on the randomized queue
        return size;
    }

    public void enqueue(Item item) {            // add the item
        // process exception when the item is null
        if (item == null) throw new java.lang.NullPointerException();

        Node oldlast = this.last;

        last = new Node();
        last.item = item;
        if (oldlast != null) oldlast.next = last;
        size++;
        if (first==null) first = last;
    }
    //
    // public Item dequeue() {                      // remove and return a random item
    //     if (isEmpty()) throw new java.util.NoSuchElementException();
    //
    // }
    //
    // public Item sample() {                      // return a random item (but do not remove it)
    // }

    public static void main(String[] args) {   // unit testing (optional)
    }


    public Iterator<Item> iterator() {          // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    }
}
