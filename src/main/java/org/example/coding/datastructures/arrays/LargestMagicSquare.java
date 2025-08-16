package org.example.coding.datastructures.arrays;

public class LargestMagicSquare {

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
