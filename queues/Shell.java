/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *****************************************************************************/

import java.util.Comparator;

public class Shell {


    public static void sort(Comparable[] a) {
        int N = a.length;

        // knuth suggestion
        int h = 1;
        while (h < N / 3) h *= 3 + 1; // 1.4,13

        while (h >= 1) {
            // h - sort the arrary
            // insert sorting
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }

    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // exchange a[i] and a[j]  (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    public static void main(String[] args) {

    }
}
