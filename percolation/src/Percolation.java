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

public class Percolation {
    private final int gridSize;
    private int openSites;
    private final int virtUpper;
    private final int virtLower;
    private int[][] grid;
    private final WeightedQuickUnionUF gridMap;

    public Percolation(int n) {
        /* As API requirements state that the grid should start with (1, 1), we
         * initialize n + 1 rows and columns.
         *
         * We'll use virtual nodes at beginning and end of WeightedQuickUnionUF
         * object so we can determine if the system percolates without having to
         * search through all nodes.
         */
        grid = new int[n + 1][n + 1];
        gridSize = n;
        openSites = 0;

        // Pad with extra space so we can hold virtual nodes which will be
        // 0th and n * n + 2th positions on gridMap
        gridMap = new WeightedQuickUnionUF(n * n + 2);
        virtUpper = 0;
        virtLower = gridSize * gridSize + 1;
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
        if (!isOpen(m, n)) {
            grid[m][n] = 1;
            openSites++;
            int siteX = xyTo1D(m, n);
            // Connect to virtual sites if the node we're opening at top or
            // bottom of grid
            if (siteX >= 1 && siteX <= gridSize)
                gridMap.union(siteX, virtUpper);
            if (siteX >= gridSize * (gridSize - 1) + 1 &&
                    siteX <= (int) Math.pow(gridSize, 2))
                gridMap.union(siteX, virtLower);
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
    }

    public boolean isOpen(int m, int n) {
        throwsOutOfBoundsException(m, n);
        if (grid[m][n] == 1)
            return true;
        return false;
    }

    public boolean isFull(int m, int n) {
        throwsOutOfBoundsException(m, n);
        int siteX = xyTo1D(m, n);
        if (grid[m][n] == 1 && gridMap.connected(siteX, virtUpper))
            return true;
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (gridMap.connected(virtLower, virtUpper)) return true;
        return false;
    }
}
