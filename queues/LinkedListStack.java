/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

/**
 * Stack implemented by Linkedlist.
 */
public class LinkedListStack {

    // inner class
    private class Node {
        String item; // data
        Node next;   // next pointer
    }

    // constructor
    public LinkedListStack() { }

    private Node first = null; // top node

    /**
     * is stack empty implemented by linkedlist?
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return first == null;
    }


    /**
     * push a elem with data field to stack.
     *
     * @param item string data
     */
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    /**
     * pop the top element from the stack
     *
     * @return the top elem
     */
    public String pop() {
        if (first == null) throw new java.lang.IndexOutOfBoundsException();
        String item = first.item;
        first = first.next;
        return item;
    }

    // client for stack
    public static void main(String[] args) {

    }
}
