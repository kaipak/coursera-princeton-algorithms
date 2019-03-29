import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private int[][] Grid;
    private WeightedQuickUnionUF GridMap;

    public Percolation(int n) {
        Grid = new int[n][n];
        GridMap = new WeightedQuickUnionUF(n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Grid[i][j] = i * n + j;

        System.out.println(Arrays.deepToString(Grid));
    }

    public void open(int row, int col) {
        return;
    }

    public boolean isOpen(int row, int col) {
        return true;
    }

    public boolean isFull(int row, int col) {

        return true;

    }

    public int numberOfOpenSites() {

        return 2;
    }

    public boolean percolates() {

        return true;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
    }

}
