package W1_percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] fractions;
    private int  trials;

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n<=0 || trials<=0) {
            throw new java.lang.IllegalArgumentException("n or trials should be larger than 0");
        }
        this.trials = trials;
        int opened;
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            opened = 0;
            int row, col;
            while (!perc.percolates()) {
                row = StdRandom.uniform(1, n + 1); //System.out.println("row = " + row);
                col = StdRandom.uniform(1, n + 1); //System.out.println("col = " + col);
                perc.open(row, col);
                opened = perc.numberOfOpenSites();
                //  System.out.println("opened = " + opened);
            }
            double fraction = ((double) opened) / (n * n);
            fractions[i] = fraction;
            // System.out.println("-----");
        }

    }

    public double mean() {                      // sample mean of percolation threshold
        return StdStats.mean(fractions);
    }

    public double stddev() {                    // sample standard deviation of percolation threshold
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {              // low  endpoint of 95% confidence interval
        return (mean() - 1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {             // high endpoint of 95% confidence interval
        return (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {      // test client 

        PercolationStats check1 = new PercolationStats(1, 3);
        System.out.println("mean                    = " + check1.mean());
        System.out.println("stddev                  = " + check1.stddev());
        System.out.println("95% confidence interval = [" + check1.confidenceLo() + "," + check1.confidenceHi() + "]");

    }
}
