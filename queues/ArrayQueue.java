/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class ArrayQueue<Item> implements Iterable<Item> {

    /* data */
    private Item[] s;
    private int head, tail;

    private int capacity;

    public ArrayQueue(int N) {
        s = (Item[]) new Object[N];
        head = tail = 0;
        capacity = N;
    }

    public boolean isEmpty() {
        return head == tail;
    }


    /**
     * enqueue operations
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        int retain = tail - head;
        // resize
        if (tail > capacity - 1) {
            if (retain > capacity / 2) capacity *= 2;

            Item[] ss = (Item[]) new Object[capacity];
            for (int i = head, j = 0; i < tail; i++) {
                ss[j++] = s[i];
            }
            head = 0;
            tail = retain;
            s = ss;
        }
        s[tail++] = item;
    }

    /**
     * dequeue operations
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = s[head++];
        if (head == tail) {
            head = tail = 0;
        }
        return item;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(5);
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

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {

        private int current;

        @Override
        public boolean hasNext() {
            return current != tail;
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
