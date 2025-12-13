package com.javarena.dsa.datastructures.arrays;

/**
 * Largest Magic Square
 *
 * <p><b>Problem Statement:</b><br>
 * Find the size of the largest magic square in a given grid. A magic square is a square sub-matrix 
 * where sums of all rows, columns, and both diagonals are equal.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Precompute prefix sums for rows and columns for O(1) range sum queries
 * - Check from largest possible square size (min(n,m)) down to 2×2
 * - For each size, try all possible top-left corners
 * - Validate magic square by checking:
 *   - All row sums equal first row sum
 *   - All column sums equal first row sum
 *   - Both diagonals equal first row sum
 * - Return first valid size found (greedy from largest)
 * - Prefix sums enable efficient validation without recalculating
 *
 * <p><b>Time Complexity:</b> O(N × M × min(N,M)) - All corners × sizes × validation
 * <br><b>Space Complexity:</b> O(N × M) - Prefix sum arrays
 */
public class LargestMagicSquare {

    /**
     * Prefix sum arrays and dimensions.
     */
    private int n, m;
    private int[][] rows, cols;

    public int largestMagicSquare(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        rows = new int[n][m + 1];
        cols = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rows[i][j + 1] = grid[i][j] + rows[i][j];
                cols[i + 1][j] = grid[i][j] + cols[i][j];
            }
        }
        for (int l = Math.min(n, m); l > 1; l--) {
            for (int i = 0; i <= n - l; i++) {
                for (int j = 0; j <= m - l; j++) {
                    if (isMagic(grid, i, j, l)) return l;
                }
            }
        }
        return 1;
    }

    private boolean isMagic(int[][] grid, int x, int y, int l) {
        int sum = rows[x][y + l] - rows[x][y], d1 = 0, d2 = 0;
        for (int i = 0; i < l; i++) {
            if (cols[x + l][y + i] - cols[x][y + i] != sum || rows[x + i][y + l] - rows[x + i][y] != sum) return false;
        }
        for (int i = 0; i < l; i++) {
            d1 += grid[x + i][y + i];
            d2 += grid[x + l - 1 - i][y + i];
        }
        return d1 == sum && d2 == sum;
    }
}
