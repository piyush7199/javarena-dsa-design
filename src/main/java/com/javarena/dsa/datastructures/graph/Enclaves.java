package com.javarena.dsa.datastructures.graph;

/**
 * Number of Enclaves
 *
 * <p><b>Problem Statement:</b><br>
 * Count land cells (1s) in a grid that cannot reach the border. Land cells connected to border can "escape".
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use DFS from all border land cells to mark reachable cells
 * - Start DFS from:
 *   - Top row: all 1s at grid[0][j]
 *   - Bottom row: all 1s at grid[n-1][j]
 *   - Left column: all 1s at grid[i][0]
 *   - Right column: all 1s at grid[i][m-1]
 * - Mark all cells reachable from border as visited
 * - Count remaining unvisited 1s (these are enclaves)
 * - Enclaves = total 1s - border-reachable 1s
 *
 * <p><b>Time Complexity:</b> O(N × M) - Visit each cell at most once
 * <br><b>Space Complexity:</b> O(N × M) - Visited array + recursion stack
 */
public class Enclaves {
    /**
     * Counts land cells that cannot reach border.
     */
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        
        // DFS from top and bottom borders
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1 && !vis[0][j]) {
                dfs(grid, 0, j, vis);
            }
            if (grid[n - 1][j] == 1 && !vis[n - 1][j]) {
                dfs(grid, n - 1, j, vis);
            }
        }

        // DFS from left and right borders
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1 && !vis[i][0]) {
                dfs(grid, i, 0, vis);
            }
            if (grid[i][m - 1] == 1 && !vis[i][m - 1]) {
                dfs(grid, i, m - 1, vis);
            }
        }
        
        // Count unvisited 1s (enclaves)
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    ans += grid[i][j];
                }
            }
        }
        return ans;
    }

    /**
     * DFS to mark reachable land cells.
     */
    private void dfs(int[][] grid, int i, int j, boolean[][] vis) {
        vis[i][j] = true;
        int[] rows = {0, -1, 0, 1};
        int[] cols = {1, 0, -1, 0};
        
        for (int k = 0; k < 4; k++) {
            int nRow = i + rows[k];
            int nCol = j + cols[k];
            if (nRow >= 0 && nCol >= 0 && nRow < grid.length && nCol < grid[0].length
                    && grid[nRow][nCol] == 1 && !vis[nRow][nCol]) {
                dfs(grid, nRow, nCol, vis);
            }
        }
    }
}
