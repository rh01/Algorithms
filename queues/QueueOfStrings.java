/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class QueueOfStrings {

    // inner class for Node.
    private class Node {
        String item;
        Node next;
    }

    private Node first, last;

    public QueueOfStrings() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * enqueue operations
     *
     * @param item
     */
    public void enqueue(String item) {
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    /**
     * dequeue operations
     *
     * @return
     */
    public String dequeue() {

        String item = first.item;
        first = first.next;

        if (isEmpty()) last = null;
        return item;
    }

    public static void main(String[] args) {

    }
}
