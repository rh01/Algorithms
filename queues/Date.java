/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Smplified Version of java,util.Date
 */
public class Date implements Comparable<Date> {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Integer[] month = new Integer[N];
        Integer[] day = new Integer[N];
        Integer[] year = new Integer[N];
        Date[] dates = new Date[N];
        for (int i = 0; i < N; i++) {
            month[i] = StdRandom.uniform(1, 13);
            day[i] = StdRandom.uniform(1, 31);
            year[i] = StdRandom.uniform(1992, 2020);
            dates[i] = new Date(month[i], day[i], year[i]);
        }
        // STOPSHIP: 2019/5/10  for test
        StdOut.println(dates[0]);
        StdOut.println(dates[1]);
        StdOut.println(dates[0].compareTo(dates[1]));
        // StdOut.println();
    }

    private final int month, day, year;

    @Override
    public String toString() {
        return "Date{" +
                "month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return 1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return 1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return 1;
        return 0;
    }

    /*************************************************************
     *    helper function:
     *    - exch(Comparable v, Comparable w)
     *    - less(Comparable[] a, int i, int j)
     * ***********************************************************/

    /**
     * swap two Comparable data
     * @param a
     * @param b
     */
    private static void exch(Comparable a, Comparable b) {
        Comparable swap = a;
        a = b;
        b = swap;
    }

    /**
     *
     * @param a
     * @param i
     * @param j
     * @return true or flase, if {@code a[i]} > {@code a[j]} return true.
     */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }



}
