/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class GenericLinkedlistStack<Item> {

    // inner class
    private class Node {
        Item item; // data
        Node next;   // next pointer
    }

    // constructor
    public GenericLinkedlistStack() { }

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
    public void push(Item item) {
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
    public Item pop() {
        if (first == null) throw new java.lang.IndexOutOfBoundsException();
        Item item = first.item;
        first = first.next;
        return item;
    }

    // client for stack
    public static void main(String[] args) {

    }
}
