/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class ArrayStack {

    // array s[] store N item on Stack
    private String[] s;
    // numbers of elements
    private int top;

    /**
     * initialize N elements in Array
     *
     * @param N number of elements
     */
    public ArrayStack(int N) {
        s = new String[N];
        top = 0;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * push a string elem to stack
     *
     * @param item
     */
    public void push(String item) {
        if (top > s.length) throw new java.lang.IndexOutOfBoundsException();
        s[top++] = item;
    }

    /**
     * pop a new element from stack
     *
     * @return string element
     */
    public String pop() {
        if (isEmpty()) throw new java.lang.IndexOutOfBoundsException();
        String item = s[--top]; // may be loitering
        s[top] = null;         // 避免出现游荡
        return item;
    }



    public static void main(String[] args) {

    }
}
