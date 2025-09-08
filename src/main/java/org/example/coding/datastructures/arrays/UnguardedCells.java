package org.example.coding.datastructures.arrays;

public class UnguardedCells {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int cnt = 0;
        int grid[][] = new int[m][n];
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 1;
        }

        for (int[] w : walls) {
            grid[w[0]][w[1]] = 2;
        }

        for (int[] g : guards) {
            int i = g[0];
            int j = g[1];
            uper(i, j, grid);
            down(i, j, grid);
            left(i, j, grid);
            right(i, j, grid);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private void uper(int i, int j, int[][] grid) {
        for (int x = i - 1; x >= 0; x--) {
            if (grid[x][j] == 2 || grid[x][j] == 1) break; // wall/guard stops view
            if (grid[x][j] == 0) grid[x][j] = 3; // found unguarded
        }
    }

    private void down(int i, int j, int[][] grid) {
        for (int x = i + 1; x < grid.length; x++) {
            if (grid[x][j] == 2 || grid[x][j] == 1) break;
            if (grid[x][j] == 0) grid[x][j] = 3;
        }
    }

    private void left(int i, int j, int[][] grid) {
        for (int y = j - 1; y >= 0; y--) {
            if (grid[i][y] == 2 || grid[i][y] == 1) break;
            if (grid[i][y] == 0) grid[i][y] = 3;
        }
    }

    private void right(int i, int j, int[][] grid) {
        for (int y = j + 1; y < grid[0].length; y++) {
            if (grid[i][y] == 2 || grid[i][y] == 1) break;
            if (grid[i][y] == 0) grid[i][y] = 3;
        }
    }
}
