package org.example.coding.datastructures.graph;

public class Enclaves {
    /**
     * Counts the number of land cells (1s) that cannot reach the border.
     * <p>
     * Intuition:
     * - Use DFS from border land cells to mark visited.
     * - Remaining unvisited 1s are enclaves.
     * <p>
     * Time Complexity: O(N * M)
     * Space Complexity: O(N * M)
     */
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1 && !vis[0][j]) {
                dfs(grid, 0, j, vis);
            }

            if (grid[n - 1][j] == 1 && !vis[n - 1][j]) {
                dfs(grid, n - 1, j, vis);
            }
        }

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1 && !vis[i][0]) {
                dfs(grid, i, 0, vis);
            }
            if (grid[i][m - 1] == 1 && !vis[i][m - 1]) {
                dfs(grid, i, m - 1, vis);
            }
        }
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
     * DFS to mark reachable land cells from the border.
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
