/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueueTest<Item> implements Iterable<Item> {


    // primary element.
    private int first, last;
    private int capacity;

    private Item[] s;

    public RandomizedQueueTest() {

    }

    private RandomizedQueueTest(int N) {                    // construct an empty randomized queue
        s = (Item[]) new Object[N];
        first = last = 0;
        capacity = N;
    }

    public boolean isEmpty() {                  // is the randomized queue empty?
        return first == last;
    }

    public int size() {                         // return the number of items on the randomized queue
        return last - first;
    }

    public void enqueue(Item item) {            // add the item
        // process exception when the item is null
        if (item == null) throw new java.lang.IllegalArgumentException();

        int size = this.size();
        // resize
        if (last > capacity - 1) {
            if (size > capacity / 2) capacity *= 2;

            Item[] ss = (Item[]) new Object[capacity];
            for (int i = first, j = 0; i < last; i++) {
                ss[j++] = s[i];
            }
            first = 0;
            last = size;
            s = ss;
        }
        s[last++] = item;

    }

    public Item dequeue() {                      // remove and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();


        int rand = getRandomOccupiedIndex();
        last--;
        Item item = s[rand];
        s[rand] = s[last];
        s[last] = null;

        // resize
        if (s.length > 4 && last <= capacity / 4) {
            capacity /= 2;
            Item[] resizedQueue = (Item[]) new Object[capacity];

            for (int i = first; i < this.last; i++) {
                resizedQueue[i] = s[i];
            }

            this.s = resizedQueue;
        }

        return item;
    }

    public Item sample() {                      // return a random item (but do not remove it)
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is currently empty.");
        }

        return s[getRandomOccupiedIndex()];
    }

    private int getRandomOccupiedIndex() {
        int rand = -1;
        // get index of queue existed
        while (true) {
            rand = StdRandom.uniform(0, capacity);
            if (s[rand] != null) {
                // break;
                return rand;
            }
        }
        // return 0;
    }

    public static void main(String[] args) {   // unit testing (optional)
        RandomizedQueueTest<Integer> arrayQueue = new RandomizedQueueTest<>(5);
        // arrayQueue.capacity = 5;
        arrayQueue.enqueue(1);
        System.out.println(arrayQueue.dequeue());

        arrayQueue.enqueue(2);

        arrayQueue.enqueue(3);
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        arrayQueue.enqueue(5);
        arrayQueue.enqueue(4);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(3);
        arrayQueue.enqueue(1);


        System.out.println(arrayQueue.capacity);

        // elegant writing
        Iterator<Integer> iterator = arrayQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    public Iterator<Item> iterator() {          // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;
        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public Item next() {
            return s[current++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
