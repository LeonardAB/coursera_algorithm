package W1_percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] siteOpen;
    private int n;
    private int openCount;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gridTop;
    private int gridCount;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n should be larger or equal to 1");
        }
        grid = new WeightedQuickUnionUF(n * n + 2);
        gridTop = new WeightedQuickUnionUF(n * n + 1);
        gridCount = grid.count();
        siteOpen = new boolean[gridCount];

        this.n = n;
        this.openCount = 0;
        for (int i = 0; i < gridCount; i++) {
            siteOpen[i] = false; // initially all sites are blocked

        }
    }

    private void validateRowCol(int row, int col) {
        if (row <= 0 || row > n) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (col <= 0 || col > n) {
            throw new IndexOutOfBoundsException("column index i out of bounds");
        }
    }

    private int siteLoc(int row, int col) {
        validateRowCol(row, col);
        return ((row - 1) * n) + col;
    }

    public void open(int row, int col) {  // open site (row, col) if it is not open already
        validateRowCol(row, col);
// open the site
        int tempLoc = (siteLoc(row, col));
        if (!siteOpen[tempLoc]) {
            siteOpen[tempLoc] = true;
// connecting the site with adjacent open sites
// vertical union
if (row==1 || row==n)
{   if (row == 1) { //if it is in top row
                grid.union(0, tempLoc); //connect with source item
                gridTop.union(0, tempLoc); //connect with source item
                if (n > 1 && siteOpen[tempLoc + n]) {
                    grid.union(tempLoc, tempLoc + n);
                    gridTop.union(tempLoc, tempLoc + n);
                }
            }  
// no "else" here because there is possibility that n=1
// for the last row, the GridTop does not need to be connected since it is to avoid backwash
            if (row == n) { //if it is in bottom row
                   grid.union(n*n+1, tempLoc); //connet with target item
                if (n > 1 && siteOpen[tempLoc - n]) {
                    grid.union(tempLoc, tempLoc - n);
                    gridTop.union(tempLoc, tempLoc - n);
                }
            } 
        }
            else { //if it is neither top nor bottom
                if (siteOpen[tempLoc + n]) {
                    grid.union(tempLoc, tempLoc + n);
                    gridTop.union(tempLoc, tempLoc + n);
                }
                if (siteOpen[tempLoc - n]) {
                    grid.union(tempLoc, tempLoc - n);
                    gridTop.union(tempLoc, tempLoc - n);
                }
            }
// horizontal union
            if (n > 1) {
                if (col == 1) {
                    if (siteOpen[tempLoc + 1]) {
                        grid.union(tempLoc, tempLoc + 1);
                        gridTop.union(tempLoc, tempLoc + 1);
                    }
                } else if (col == n) {
                    if (siteOpen[tempLoc - 1]) {
                        grid.union(tempLoc, tempLoc - 1);
                        gridTop.union(tempLoc, tempLoc - 1);
                    }
                } else {
                    if (siteOpen[tempLoc + 1]) {
                        grid.union(tempLoc, tempLoc + 1);
                        gridTop.union(tempLoc, tempLoc + 1);
                    }
                    if (siteOpen[tempLoc - 1]) {
                        grid.union(tempLoc, tempLoc - 1);
                        gridTop.union(tempLoc, tempLoc - 1);
                    }
                }

            }
            openCount++;
        }

    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        validateRowCol(row, col);
        return siteOpen[(siteLoc(row, col))];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        validateRowCol(row, col);
        return gridTop.connected(0, siteLoc(row, col)); //avoiding backwash
        
        
    }

    public int numberOfOpenSites() {    // number of open sites
        return openCount;
    }

    public boolean percolates() { // does the system percolate?
//        for (int i = 1; i <= n; i++) {
//            if (isFull(n, i)) {
//                return true;
//            }
//        }

        return grid.connected(0, n*n+1);
    }

    public static void main(String[] args) {

    }
}
