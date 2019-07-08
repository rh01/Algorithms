/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class GenericArrayStack<Item> {
    // array s[] store N item on Stack
    private Item[] s;
    // numbers of elements
    private int top;

    /**
     * initialize N elements in Array
     *
     * @param N number of elements
     */
    public GenericArrayStack(int N) {
        s = (Item[]) new Object[N];  // 必须强制类型转换
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
    public void push(Item item) {
        if (top > s.length) throw new java.lang.IndexOutOfBoundsException();
        s[top++] = item;
    }

    /**
     * pop a new element from stack
     *
     * @return string element
     */
    public Item pop() {
        if (isEmpty()) throw new java.lang.IndexOutOfBoundsException();
        Item item = s[--top]; // may be loitering
        s[top] = null;         // 避免出现游荡
        return item;
    }



    public static void main(String[] args) {

    }
}
