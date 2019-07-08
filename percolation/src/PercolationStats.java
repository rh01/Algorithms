/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private static final double DEV = 1.96;

    private final double[] fractions;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // 处理异常
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        fractions = new double[trials];
        int itt = trials;
        while (itt > 0) {
            Percolation percolation = new Percolation(n);
            int it = n * n;
            while (it > 0) {
                percolation.open(StdRandom.uniform(1, n), StdRandom.uniform(1, n));
                if (percolation.percolates())
                    break;
                it--;
            }
            fractions[--itt] = fraction( percolation.numberOfOpenSites(), n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return (fractions.length > 1) ? StdStats.mean(fractions) : Double.NaN;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return (fractions.length > 1) ? StdStats.stddev(fractions) : Double.NaN;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - threshold();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + threshold();
    }

    private double threshold() {
        return (DEV * stddev() / Math.sqrt(fractions.length));
    }

    private double fraction(double x, double y) {
        return x*1.0 / y;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }
}

