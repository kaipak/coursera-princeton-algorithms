/* *****************************************************************************
 *  Name:    Kai Pak
 *
 *  Description: Determines if nxn grid object percolates, i.e., has an open
 *               path from top of grid to bottom. Implements weighted quick
 *               union find to keep track of which elements are connected
 *               together, and ultimately, which paths percolate.
 *
 *  Written:       03/10/2019
 *
 *  % javac Percolation.java
 *  % java Percolation
 *  Hello, World
 *
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class Percolation {
    private int gridSize;
    private int openSites;
    private int[][] grid;
    private WeightedQuickUnionUF gridMap;

    public Percolation(int n) {
       /* As API requirements state that the grid should start with (1, 1), we
        * initialize n + 1 rows and columns.
        *
        */
        grid = new int[n + 1][n + 1];
        gridSize = n;
        openSites = 0;
        gridMap = new WeightedQuickUnionUF(n * n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = 0;
    }

    private int xyTo1D(int m, int n) {
        return (m - 1) * gridSize + n;
    }

    private void throwsOutOfBoundsException(int m, int n) {
        if (m <= 0 || m > gridSize || n <= 0 || n > gridSize) {
            throw new IllegalArgumentException("Argument out of bounds.");
        }
    }

    public void open(int m, int n) {
        throwsOutOfBoundsException(m, n);
        grid[m][n] = 1;
        openSites++;
        int siteX = xyTo1D(m, n);
        // Now need to connect open adjacent sites
        if (m - 1 > 0 && isOpen(m - 1, n)) {
            int siteY = xyTo1D(m - 1, n);
            gridMap.union(siteX, siteY);
        }
        if (m + 1 <= gridSize && isOpen(m + 1, n)) {
            int siteY = xyTo1D(m + 1, n);
            gridMap.union(siteX, siteY);
        }
        if (n - 1 > 0 && isOpen(m, n - 1)) {
            int siteY = xyTo1D(m, n - 1);
            gridMap.union(siteX, siteY);
        }
        if (n + 1 <= gridSize && isOpen(m, n + 1)) {
            int siteY = xyTo1D(m, n + 1);
            gridMap.union(siteX, siteY);
        }
    }

    public boolean isOpen(int m, int n) {
        throwsOutOfBoundsException(m, n);

        if(grid[m][n] == 1)
            return true;
        else
            return false;
    }

    public boolean isFull(int m, int n) {
        throwsOutOfBoundsException(m, n);

        if(grid[m][n] == 0)
            return true;
        else
            return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return true;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(4, 4);
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(Arrays.deepToString(percolation.grid));
    }

}
