/*
 * Copyright 2018 @rh01 https://github.com/rh01
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * @program: hadoop
 * @description:
 * @Author: Shen Hengheng
 * @create: 2019-05-08 14:23
 **/
public class Percolation {
    /* N by N grid as percolates system */
    //public int id[][], full[][];
    private boolean[] mat = null;

    private int N = 0;
    // private int openStat = 0;

    private  WeightedQuickUnionUF qfObj = null;
    private  WeightedQuickUnionUF qfObjEx = null;

    private final int[] mx = { -1, 0, 1, 0 };
    private final int[] my = { 0, -1, 0, 1 };


    /*  create n-by-n grid, with all sites blocked */
    public Percolation(int N) {
        mat = new boolean[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i * N + j] = false; // blocked
            }
        }
        this.N = N;
        qfObj = new WeightedQuickUnionUF(N * N + 2);   // 1- (N*N) + 2 = N^2 + 2
        qfObjEx = new WeightedQuickUnionUF(N * N + 1); // 0-(N*N-1)+2 = N^2 +1
    }

    /*
     * open site (row, col) if it is not open already
     */
    public void open(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) throw new java.lang.IndexOutOfBoundsException();
        int ni = i - 1, nj = j - 1;
        if (!mat[ni * N + nj]) {
            mat[ni * N + nj] = true;
            for (int m = 0; m < 4; m++) {
                int mi = ni + mx[m];
                int mj = nj + my[m];
                if (mi >= 0 && mj >= 0 && mi < N && mj < N && mat[mi * N + mj]) {
                    qfObj.union(ni * N + nj, mi * N + mj);
                    qfObjEx.union(ni * N + nj, mi * N + mj);
                }
            }

            if (i == 1) {
                qfObjEx.union(ni * N + nj, N * N);
                qfObj.union(ni * N + nj, N * N);
            }
            if (i == N) {
                qfObj.union(ni * N + nj, N * N + 1);
            }
        }
    }


    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) throw new java.lang.IndexOutOfBoundsException();
        int ni = i - 1, nj = j - 1;
        return mat[ni * N + nj];
    }

    /*is site (row, col) full?*/
    public boolean isFull(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) throw new java.lang.IndexOutOfBoundsException();
        int ni = i - 1, nj = j - 1;
        return mat[ni * N + nj] && qfObjEx.connected(ni * N + nj, N * N);
    }


    /*
     * same as union operator
     */
    public boolean percolates() {
        return qfObj.connected(N * N, N * N + 1);
    }


    //    // number of open sites
    //    public int numberOfOpenSites() {
    //
    //    }


    // for test
    // public static void main(String[] args) {
    //
    // }

    public int numberOfOpenSites() {
        int sum = 0;
        for (int i = 0; i < N * N; i++) {
            if (mat[i]) ++sum;
        }
        return sum;
    }
}

/*
See the Assessment Guide for information on how to interpret this report.

ASSESSMENT SUMMARY

Compilation:  PASSED
API:          PASSED

Spotbugs:     PASSED
PMD:          FAILED (3 warnings)
Checkstyle:   FAILED (0 errors, 8 warnings)

Correctness:  26/33 tests passed
Memory:       8/8 tests passed
Timing:       18/20 tests passed

Aggregate score: 85.27%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]

ASSESSMENT DETAILS

The following files were submitted:
----------------------------------
3.6K May  8 09:02 Percolation.java
2.4K May  8 09:02 PercolationStats.java


********************************************************************************
*  COMPILING
********************************************************************************


% javac Percolation.java
*-----------------------------------------------------------

% javac PercolationStats.java
*-----------------------------------------------------------


================================================================


Checking the APIs of your programs.
*-----------------------------------------------------------
Percolation:

PercolationStats:

================================================================


********************************************************************************
*  CHECKING STYLE AND COMMON BUG PATTERNS
********************************************************************************


% spotbugs *.class
*-----------------------------------------------------------


================================================================


% pmd .
*-----------------------------------------------------------
Percolation.java:30: The private instance (or static) variable 'N' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
Percolation.java:33: The private instance (or static) variable 'qfObj' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
Percolation.java:34: The private instance (or static) variable 'qfObjEx' can be made 'final'; it is initialized only in the declaration or constructor. [ImmutableField]
PMD ends with 3 warnings.


================================================================


% checkstyle *.java
*-----------------------------------------------------------
[WARN] Percolation.java:27:7: '//' or '/*' is not followed by whitespace. [WhitespaceAfter]
[WARN] Percolation.java:30:17: The instance variable 'N' must start with a lowercase letter and use camelCase. [MemberName]
[WARN] Percolation.java:41:28: The parameter variable 'N' must start with a lowercase letter and use camelCase. [ParameterName]
[WARN] Percolation.java:90:7: '//' or '/*' is not followed by whitespace. [WhitespaceAfter]
Checkstyle ends with 0 errors and 4 warnings.

% custom checkstyle checks for Percolation.java
*-----------------------------------------------------------
[INFO] Percolation.java:61: Using a loop in this method might be a performance bug. [Performance]
[INFO] Percolation.java:119: Using a loop in this method might be a performance bug. [Performance]
[WARN] Percolation.java:1: We recommend defining at least one private helper method, e.g., to validate the row and column indices or to map from 2D to 1D indices. [Design]
[WARN] Percolation.java:36:19: Can you use the type 'boolean[]' instead of 'int[]'? [Design]
[WARN] Percolation.java:37:19: Can you use the type 'boolean[]' instead of 'int[]'? [Design]
[WARN] Percolation.java:61:33: The numeric literal '4' appears to be unnecessary. [NumericLiteral]
Checkstyle ends with 0 errors and 4 warnings.

% custom checkstyle checks for PercolationStats.java
*-----------------------------------------------------------


================================================================


********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of Percolation
*-----------------------------------------------------------
Running 18 total tests.

Tests 1 through 8 create a Percolation object using your code, then repeatedly
open sites by calling open(). After each call to open(), it checks the return
values of isOpen(), percolates(), numberOfOpenSites(), and isFull() in that order.
Tests 11 through 14 create a Percolation object using your code, then repeatedly
call the methods open(), isOpen(), isFull(), percolates(), and, numberOfOpenSites()
in random order with probabilities p = (p1, p2, p3, p4, p5). The tests stop
immediately after the system percolates.

Tests 16 through 18 test backwash.

Except as noted, a site is opened at most once.

Test 1: open predetermined list of sites using file inputs
  * filename = input6.txt
  * filename = input8.txt
  * filename = input8-no.txt
  * filename = input10-no.txt
  * filename = greeting57.txt
  * filename = heart25.txt
==> passed

Test 2: open random sites until just before system percolates
  * n = 3
  * n = 5
  * n = 10
  * n = 10
  * n = 20
  * n = 20
  * n = 50
  * n = 50
==> passed

Test 3: open predetermined sites for n = 1 and n = 2 (corner case test)
  * filename = input1.txt
  * filename = input1-no.txt
  * filename = input2.txt
  * filename = input2-no.txt
==> passed

Test 4: check predetermined sites with long percolating path
  * filename = snake13.txt
  * filename = snake101.txt
==> passed

Test 5: open every site
  * filename = input5.txt
==> passed

Test 6: open random sites until just before system percolates,
        allowing open() to be called on a site more than once
  * n = 3
  * n = 5
  * n = 10
  * n = 10
  * n = 20
  * n = 20
  * n = 50
  * n = 50
==> passed

Test 7: call methods with invalid arguments
  * n = 10, (row, col) = (-1, 5)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (11, 5)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (0, 5)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (5, -1)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (5, 11)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (5, 0)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (-2147483648, -2147483648)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

  * n = 10, (row, col) = (2147483647, 2147483647)
    - open() throws the wrong exception
    - open() throws a java.lang.IndexOutOfBoundsException
    - open() should throw a java.lang.IllegalArgumentException

    - isOpen() throws the wrong exception
    - isOpen() throws a java.lang.IndexOutOfBoundsException
    - isOpen() should throw a java.lang.IllegalArgumentException

    - isFull() throws the wrong exception
    - isFull() throws a java.lang.IndexOutOfBoundsException
    - isFull() should throw a java.lang.IllegalArgumentException

==> FAILED

Test 8: call constructor with invalid argument
  * n = -10
    - the constructor fails to throw an exception
    - the constructor should throw a java.lang.IllegalArgumentException

  * n = -1
    - the constructor fails to throw an exception
    - the constructor should throw a java.lang.IllegalArgumentException

  * n = 0
    - the constructor fails to throw an exception
    - the constructor should throw a java.lang.IllegalArgumentException

==> FAILED

Test 9: create multiple Percolation objects at the same time
        (to make sure you didn't store data in static variables)
==> passed

Test 10: open predetermined list of sites using file inputs,
         but permute the order in which methods are called
  * filename = input8.txt;  order =     isFull(),     isOpen(), percolates()
  * filename = input8.txt;  order =     isFull(), percolates(),     isOpen()
  * filename = input8.txt;  order =     isOpen(),     isFull(), percolates()
  * filename = input8.txt;  order =     isOpen(), percolates(),     isFull()
  * filename = input8.txt;  order = percolates(),     isOpen(),     isFull()
  * filename = input8.txt;  order = percolates(),     isFull(),     isOpen()
==> passed

Test 11: call open(), isOpen(), and numberOfOpenSites()
         in random order until system percolates
  * n = 3, trials = 40, p = (0.4, 0.4, 0.0, 0.0, 0.3)
  * n = 5, trials = 20, p = (0.4, 0.4, 0.0, 0.0, 0.3)
  * n = 7, trials = 10, p = (0.4, 0.4, 0.0, 0.0, 0.3)
  * n = 10, trials = 5, p = (0.4, 0.4, 0.0, 0.0, 0.3)
  * n = 20, trials = 2, p = (0.4, 0.4, 0.0, 0.0, 0.3)
  * n = 50, trials = 1, p = (0.4, 0.4, 0.0, 0.0, 0.3)
==> passed

Test 12: call open() and percolates() in random order until system percolates
  * n = 3, trials = 40, p = (0.5, 0.0, 0.0, 0.5, 0.0)
  * n = 5, trials = 20, p = (0.5, 0.0, 0.0, 0.5, 0.0)
  * n = 7, trials = 10, p = (0.5, 0.0, 0.0, 0.5, 0.0)
  * n = 10, trials = 5, p = (0.5, 0.0, 0.0, 0.5, 0.0)
  * n = 20, trials = 2, p = (0.5, 0.0, 0.0, 0.5, 0.0)
  * n = 50, trials = 1, p = (0.5, 0.0, 0.0, 0.5, 0.0)
==> passed

Test 13: call open() and isFull() in random order until system percolates
  * n = 3, trials = 40, p = (0.5, 0.0, 0.5, 0.0, 0.0)
  * n = 5, trials = 20, p = (0.5, 0.0, 0.5, 0.0, 0.0)
  * n = 7, trials = 10, p = (0.5, 0.0, 0.5, 0.0, 0.0)
  * n = 10, trials = 5, p = (0.5, 0.0, 0.5, 0.0, 0.0)
  * n = 20, trials = 2, p = (0.5, 0.0, 0.5, 0.0, 0.0)
  * n = 50, trials = 1, p = (0.5, 0.0, 0.5, 0.0, 0.0)
==> passed

Test 14: call all methods in random order until system percolates
  * n = 3, trials = 40, p = (0.2, 0.2, 0.2, 0.2, 0.2)
  * n = 5, trials = 20, p = (0.2, 0.2, 0.2, 0.2, 0.2)
  * n = 7, trials = 10, p = (0.2, 0.2, 0.2, 0.2, 0.2)
  * n = 10, trials = 5, p = (0.2, 0.2, 0.2, 0.2, 0.2)
  * n = 20, trials = 2, p = (0.2, 0.2, 0.2, 0.2, 0.2)
  * n = 50, trials = 1, p = (0.2, 0.2, 0.2, 0.2, 0.2)
==> passed

Test 15: call all methods in random order until almost all sites are open,
         but with inputs not prone to backwash
  * n = 3
  * n = 5
  * n = 7
  * n = 10
  * n = 20
  * n = 50
==> passed

Test 16: check for backwash with predetermined sites
  * filename = input20.txt
  * filename = input10.txt
  * filename = input50.txt
  * filename = jerry47.txt
  * filename = sedgewick60.txt
  * filename = wayne98.txt
==> passed

Test 17: check for backwash with predetermined sites that have
         multiple percolating paths
  * filename = input3.txt
  * filename = input4.txt
  * filename = input7.txt
==> passed

Test 18: call all methods in random order until all sites are open,
         allowing isOpen() to be called on a site more than once
         (these inputs are prone to backwash)
  * n = 3
  * n = 5
  * n = 7
  * n = 10
  * n = 20
  * n = 50
==> passed


Total: 16/18 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference Percolation)
********************************************************************************

Testing correctness of PercolationStats
*-----------------------------------------------------------
Running 15 total tests.

Test 1: check that methods in PercolationStats do not print to standard output
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 2: check that mean() returns value in expected range
  * n =   2, trials = 10000
    - student PercolationStats mean()     = 0.250000
    - true mean                           = 0.6666666666666666
    - 99.99% confidence interval          = [0.662284, 0.671050]
    - a correct solution will fail this test by bad luck 1 time in 10,000


  * n =   5, trials = 10000
    - student PercolationStats mean()     = 0.513168
    - true mean                           = 0.5939914915553629
    - 99.99% confidence interval          = [0.590174, 0.597809]
    - a correct solution will fail this test by bad luck 1 time in 10,000


  * n =  10, trials = 10000
    - student PercolationStats mean()     = 0.576659
    - true mean                           = 0.5904
    - 99.99% confidence interval          = [0.587665, 0.593135]
    - a correct solution will fail this test by bad luck 1 time in 10,000


  * n =  25, trials = 10000
    - student PercolationStats mean()     = 0.610100
    - true mean                           = 0.5917
    - 99.99% confidence interval          = [0.590140, 0.593260]
    - a correct solution will fail this test by bad luck 1 time in 10,000


==> FAILED

Test 3: check that stddev() returns value in expected range
  * n =   2, trials = 10000
    - student PercolationStats stddev() = 0.000000
    - true stddev                       = 0.117851
    - 99.99% confidence interval        = [0.114620, 0.121104]
    - a correct solution will fail this test by bad luck 1 time in 10,000

  * n =   5, trials = 10000
    - student PercolationStats stddev() = 0.050011
    - true stddev                       = 0.102653
    - 99.99% confidence interval        = [0.099839, 0.105487]
    - a correct solution will fail this test by bad luck 1 time in 10,000

  * n =  10, trials = 10000
    - student PercolationStats stddev() = 0.028463
    - true stddev                       = 0.073553
    - 99.99% confidence interval        = [0.071536, 0.075583]
    - a correct solution will fail this test by bad luck 1 time in 10,000

  * n =  25, trials = 10000
    - student PercolationStats stddev() = 0.012305
    - true stddev                       = 0.041952
    - 99.99% confidence interval        = [0.040802, 0.043110]
    - a correct solution will fail this test by bad luck 1 time in 10,000

==> FAILED

Test 4: check that PercolationStats creates trials Percolation objects, each of size n-by-n
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 5: check that PercolationStats calls open() until system percolates
  * n =  20, trials =  10
    - PercolationStats does not open sites until system percolates on Percolation object 0

  * n =  50, trials =  20
    - PercolationStats does not open sites until system percolates on Percolation object 0

  * n = 100, trials =  50
    - PercolationStats does not open sites until system percolates on Percolation object 0

  * n =  64, trials = 150
    - PercolationStats does not open sites until system percolates on Percolation object 0

==> FAILED

Test 6: check that PercolationStats does not call open() after system percolates
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 7: check that mean() is consistent with the number of intercepted calls to open()
        on blocked sites
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 8: check that stddev() is consistent with the number of intercepted calls to open()
        on blocked sites
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 9: check that confidenceLo() and confidenceHigh() are consistent with mean() and stddev()
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 10: check that exception is thrown if either n or trials is out of bounds
  * n = -23, trials =  42
  * n =  23, trials =   0
  * n = -42, trials =   0
  * n =  42, trials =  -1
  * n = -2147483648, trials = -2147483648
==> passed

Test 11: create two PercolationStats objects at the same time and check mean()
         (to make sure you didn't store data in static variables)
  * n1 =  50, trials1 =  10, n2 =  50, trials2 =   5
  * n1 =  50, trials1 =   5, n2 =  50, trials2 =  10
  * n1 =  50, trials1 =  10, n2 =  25, trials2 =  10
  * n1 =  25, trials1 =  10, n2 =  50, trials2 =  10
  * n1 =  50, trials1 =  10, n2 =  15, trials2 = 100
  * n1 =  15, trials1 = 100, n2 =  50, trials2 =  10
==> passed

Test 12: check that the methods return the same value, regardless of
         the order in which they are called
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 13: check that no calls to StdRandom.setSeed()
  * n = 20, trials = 10
  * n = 20, trials = 10
  * n = 40, trials = 10
  * n = 80, trials = 10
==> passed

Test 14: check distribution of number of sites opened until percolation
  * n = 2, trials = 100000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                2         0   33333.0          0.00
                3         0   66667.0          0.00
        -------------------------------------------
                          0  100000.0          0.00

    G-statistic = 0.00 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.


  * n = 3, trials = 100000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                3         0    3571.0          0.00
                4         0   13889.0          0.00
                5         0   29365.0          0.00
                6         0   32937.0          0.00
                7         0   20238.0          0.00
        -------------------------------------------
                          0  100000.0          0.00

    G-statistic = 0.00 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.


  * n = 4, trials = 100000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                4         0     220.0          0.00
                5         0    1154.0          0.00
                6         0    3497.0          0.00
                7         0    7822.0          0.00
                8         0   13850.0          0.00
                9         0   19542.0          0.00
               10         0   21522.0          0.00
               11         0   17924.0          0.00
               12         0   10733.0          0.00
               13         0    3736.0          0.00
        -------------------------------------------
                          0  100000.0          0.00

    G-statistic = 0.00 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.


==> FAILED

Test 15: check that each site is opened the expected number of times
  * n = 2, trials = 100000
    - the following sites were never opened:
      (1, 2), (2, 1), (2, 2)
    - this is extraordinarily unlikely in 100000 trials

  * n = 3, trials = 100000
    - the following sites were never opened:
      (1, 3), (2, 3), (3, 1), (3, 2), (3, 3)
    - this is extraordinarily unlikely in 100000 trials

  * n = 4, trials = 100000
    - the following sites were never opened:
      (1, 4), (2, 4), (3, 4), (4, 1), (4, 2), (4, 3), (4, 4)
    - this is extraordinarily unlikely in 100000 trials

==> FAILED


Total: 10/15 tests passed!


================================================================
********************************************************************************
*  MEMORY (substituting reference Percolation)
********************************************************************************

Analyzing memory of PercolationStats
*-----------------------------------------------------------
Running 4 total tests.

Test 1a-1d: check memory usage as a function of T trials for n = 100
            (max allowed: 8*T + 128 bytes)

                 T        bytes
--------------------------------------------
=> passed       16          176
=> passed       32          304
=> passed       64          560
=> passed      128         1072
==> 4/4 tests passed


Estimated student memory = 8.00 T + 48.00   (R^2 = 1.000)

Total: 4/4 tests passed!


================================================================



********************************************************************************
*  TIMING (substituting reference Percolation)
********************************************************************************

Timing PercolationStats
*-----------------------------------------------------------
Running 4 total tests.

Test 1: count calls to StdStats.mean() and StdStats.stddev()
  * n =  20, trials =  10
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n =  50, trials =  20
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n = 100, trials =  50
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

  * n =  64, trials = 150
    - calls StdStats.mean() the wrong number of times
    - number of student   calls to StdStats.mean() = 3
    - number of reference calls to StdStats.mean() = 1

    - calls StdStats.stddev() the wrong number of times
    - number of student   calls to StdStats.stddev() = 3
    - number of reference calls to StdStats.stddev() = 1

==> FAILED

Test 2: count calls to methods in StdRandom
  * n = 20, trials = 10
  * n = 20, trials = 10
  * n = 40, trials = 10
  * n = 80, trials = 10
==> passed

Test 3: count calls to methods in Percolation
  * n =  20, trials =  10
  * n =  50, trials =  20
  * n = 100, trials =  50
  * n =  64, trials = 150
==> passed

Test 4: Call PercolationStats methods with trials = 3 and values of n that go up
        by a factor of sqrt(2). The test passes when n reaches 2,896.

     The approximate order-of-growth is n ^ (log ratio)

         n  seconds log ratio
     ------------------------
       724     0.23       2.7
      1024     0.49       2.2
      1448     1.41       3.0
      2048     3.81       2.9
      2896     9.74       2.7
==> passed


Total: 3/4 tests passed!


================================================================



********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of Percolation
*-----------------------------------------------------------
Running 4 total tests.

Test 1a-1d: check that total memory <= 17 n^2 + 128 n + 1024 bytes

                 n        bytes
--------------------------------------------
=> passed       64        70008
=> passed      256      1114488
=> passed      512      4456824
=> passed     1024     17826168
==> 4/4 tests passed


Estimated student memory = 17.00 n^2 + 0.00 n + 376.00   (R^2 = 1.000)


Test 2 (bonus): check that total memory <= 11 n^2 + 128 n + 1024 bytes
   -  failed memory test for n = 64
==> FAILED


Total: 4/4 tests passed!


================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing Percolation
*-----------------------------------------------------------
Running 16 total tests.

Test 1a-1e: Creates an n-by-n percolation system; open sites at random until
            the system percolates, interleaving calls to percolates() and open().
            Count calls to connected(), union() and find().

                                       2 * connected()
                 n       union()              + find()        constructor
-----------------------------------------------------------------------------------
=> passed       16          360                   302                   2
=> passed       32         1312                  1166                   2
=> passed       64         5648                  4812                   2
=> passed      128        24216                 19880                   2
=> passed      256        88440                 76238                   2
=> passed      512       375532                313992                   2
=> passed     1024      1471137               1242032                   2
==> 7/7 tests passed


If one of the values in the table violates the performance limits
the factor by which you failed the test appears in parentheses.
For example, (9.6x) in the union() column indicates that it uses
9.6x too many calls.


Tests 2a-2f: Check whether the number of calls to union(), connected(), and find()
             is a constant per call to open(), isOpen(), isFull(), and percolates().
             The table shows the maximum number of union(), connected(), and
             find() calls made during a single call to open(), isOpen(), isFull(),
             and percolates().

                 n     per open()      per isOpen()    per isFull()    per percolates()
---------------------------------------------------------------------------------------------
=> passed       16        8               0               1               1
=> passed       32        8               0               1               1
=> passed       64        8               0               1               1
=> passed      128        8               0               1               1
=> passed      256        8               0               1               1
=> passed      512        8               0               1               1
=> passed     1024        8               0               1               1
==> 7/7 tests passed



Running time (in seconds) depends on the machine on which the script runs.


Test 3: Create an n-by-n percolation system; interleave calls to percolates()
        and open() until the system percolates. The values of n go up by a
        factor of sqrt(2). The test is passed if n >= 4096 in under 10 seconds.

     The approximate order-of-growth is n ^ (log ratio)

                        log   union-find     log
         n  seconds   ratio   operations   ratio
     -------------------------------------------
      1024     0.17     2.3      4163120     2.0
      1448     0.50     3.0      8381736     2.0
      2048     1.27     2.7     16667224     2.0
      2896     3.27     2.7     33430606     2.0
      4096     7.68     2.5     67420450     2.0
==> passed



Test 4: Create an n-by-n percolation system; interleave calls to open(),
        percolates(), isOpen(), isFull(), and numberOfOpenSites() until.
        the system percolates. The values of n go up by a factor of sqrt(2).
        The test is passed if n >= 4096 in under 10 seconds.

                        log   union-find     log
         n  seconds   ratio   operations   ratio
     -------------------------------------------
        91     0.22     4.0        33920     2.0
       128     0.85     3.9        63214     1.8
       181     3.56     4.1       133924     2.2
       256    13.31     3.8       262110     1.9
     [ exceeded the time limit of 10.0 seconds ]

==> FAILED


Total: 15/16 tests passed!


================================================================

 */