import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private final int numSites;
    private final int trials;
    private final double mean;
    private final double stddev;
    private final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int t) {
        if (n <= 0 | t <= 0) {
            throw new IllegalArgumentException("n and t must be positive " +
                    "integers.");
        }
        numSites = n * n;
        trials = t;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int rowNum = StdRandom.uniform(1, n + 1);
                int colNum = StdRandom.uniform(1, n + 1);
                perc.open(rowNum, colNum);
            }
            results[i] = (double) perc.numberOfOpenSites() / (double) numSites;
        }
        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - (CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean + (CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.printf("%-23s %s %.16f\n", "mean", "=", stats.mean());
        System.out.printf("%-23s %s %.16f\n", "stddev", "=", stats.stddev());
        System.out.printf("%-23s %s [%.16f, %.16f]\n",
                "95% confidence interval", "=", stats.confidenceLo(),
                stats.confidenceHi());
    }
}
