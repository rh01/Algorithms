/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // inner class, I think it may be di-linkedlist
    private class Node {
        public Item item = null; // generic data type for item
        public Node prev = null; // prev pointer
        public Node next = null; // next pointer
    }

    private Node first, last; // first in the front, and last in the back of the queue.

    private int size;

    public Deque() {                           // construct an empty deque
        /* same as others*/
        size = 0;
        first = last = null;
    }

    public boolean isEmpty() {                // is the deque empty?
        return size == 0;
    }

    public int size() {                       // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {       // add the item to the front
        // process exception if item is null
        if (item == null) throw new java.lang.IllegalArgumentException();

        // store first to oldfirst
        Node oldfirst = this.first;

        first = new Node();
        first.item = item;
        first.next = oldfirst;

        if (oldfirst != null) oldfirst.prev = first;
        size++;
        if (last == null) last = first;
    }

    public void addLast(Item item) {        // add the item to the end
        // process exception
        if (item == null) throw new java.lang.IllegalArgumentException();

        Node oldlast = this.last;

        last = new Node();
        last.item = item;
        last.prev = oldlast;
        if (oldlast != null) oldlast.next = last;
        size++;
        if (first == null) first = last;
    }

    public Item removeFirst() {             // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item item = first.item;
        if (size == 1) first = last = null;
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }


    public Item removeLast() {              // remove and return the item from the end
        /* process exception which no such element*/
        if (size == 0) throw new java.util.NoSuchElementException();

        Item item = last.item;
        if (size == 1) first = last = null;
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {      // return an iterator over items in order from front to end
        return new DequeueIterator();
    }


    public static void main(String[] args) {

    }  // unit testing (optional)


    private class DequeueIterator implements Iterator<Item> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}

