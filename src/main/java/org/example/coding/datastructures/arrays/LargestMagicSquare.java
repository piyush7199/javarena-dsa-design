package org.example.coding.datastructures.arrays;

public class LargestMagicSquare {

    /**
     * Finds the size of the largest magic square in a given grid.
     *
     * <p>
     * A magic square is defined as a square sub-matrix where the sums of all rows,
     * all columns, and both diagonals are equal.
     * The algorithm leverages prefix sums of rows and columns to allow O(1)
     * sum queries for any row or column segment.
     * </p>
     *
     * <p><b>Intuition:</b><br>
     * Instead of brute-forcing sums directly (which is expensive),
     * we precompute prefix sums for rows and columns.
     * Then, we check from the largest possible square size down to 2×2,
     * returning immediately when a valid magic square is found.
     * This ensures we don’t waste time checking smaller sizes unnecessarily.
     * </p>
     *
     * <p><b>Approach:</b><br>
     * 1. Build prefix sums for rows and columns with 1-based indexing for easier queries.<br>
     * 2. Iterate over possible square sizes from max(n, m) downwards.<br>
     * 3. For each top-left corner (i, j), check if the square of size `l` is a magic square:<br>
     * - Validate all row sums equal the first row.<br>
     * - Validate all column sums equal the first row.<br>
     * - Validate both diagonals equal the same sum.<br>
     * 4. Return the first valid size found.<br>
     * </p>
     *
     * <p><b>Time Complexity:</b> O(n * m * min(n, m)) in the worst case.<br>
     * - There are O(n * m) possible top-left corners.<br>
     * - For each square, checking rows/cols/diagonals takes O(l).<br>
     * - In practice, the algorithm is much faster since it starts from the largest square and exits early.<br>
     * </p>
     *
     * <p><b>Space Complexity:</b> O(n * m) for prefix sum arrays.<br>
     * </p>
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
