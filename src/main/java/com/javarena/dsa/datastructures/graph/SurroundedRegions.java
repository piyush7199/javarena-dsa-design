package com.javarena.dsa.datastructures.graph;

/**
 * Surrounded Regions
 *
 * <p><b>Problem Statement:</b><br>
 * Given a board with 'X' and 'O', capture all regions surrounded by 'X' by flipping 'O' to 'X'.
 * A region is captured if it's not connected to the border.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Any 'O' connected to border cannot be captured (has escape route)
 * - Use DFS from all border 'O' cells to mark safe regions
 * - Start from:
 *   - Top/bottom rows: board[0][j] and board[n-1][j]
 *   - Left/right columns: board[i][0] and board[i][m-1]
 * - Mark all connected 'O' cells as visited (safe)
 * - After DFS, flip all unvisited 'O' to 'X' (captured regions)
 * - This is similar to enclaves problem
 *
 * <p><b>Time Complexity:</b> O(N × M) - Visit each cell at most once
 * <br><b>Space Complexity:</b> O(N × M) - Visited array + recursion stack
 */
public class SurroundedRegions {

    /**
     * Captures surrounded regions by flipping 'O' to 'X'.
     */
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O' && !vis[0][j]) {
                dfs(board, 0, j, vis);
            }

            if (board[n - 1][j] == 'O' && !vis[n - 1][j]) {
                dfs(board, n - 1, j, vis);
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O' && !vis[i][0]) {
                dfs(board, i, 0, vis);
            }
            if (board[i][m - 1] == 'O' && !vis[i][m - 1]) {
                dfs(board, i, m - 1, vis);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * DFS utility to mark safe 'O' cells.
     */
    private void dfs(char[][] board, int i, int j, boolean[][] vis) {
        vis[i][j] = true;
        int[] rows = {0, -1, 0, 1};
        int[] cols = {1, 0, -1, 0};
        for (int k = 0; k < 4; k++) {
            int nRow = i + rows[k];
            int nCol = j + cols[k];
            if (nRow >= 0 && nCol >= 0 && nRow < board.length && nCol < board[0].length
                    && board[nRow][nCol] == 'O' && !vis[nRow][nCol]) {
                dfs(board, nRow, nCol, vis);
            }
        }
    }

    /**
     * Counts the number of islands formed by 'L' in a grid using DFS.
     * <p>
     * Intuition:
     * - Treat the grid as a graph.
     * - Each time an unvisited 'L' is found, start DFS and count it as an island.
     * <p>
     * Time Complexity: O(N * M)
     * Space Complexity: O(N * M)
     */
    public int countIslands(char[][] grid) {
        // Code here
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'L' && !vis[i][j]) {
                    dfs(grid, vis, i, j);
                    ans++;
                }
            }
        }

        return ans;

    }

    /**
     * DFS utility to traverse all connected 'L' cells.
     */
    private void dfs(char[][] grid, boolean[][] vis, int i, int j) {
        vis[i][j] = true;
        for (int delRow = -1; delRow <= 1; delRow++) {
            for (int delCol = -1; delCol <= 1; delCol++) {
                int nRow = i + delRow;
                int nCol = j + delCol;
                if (nRow >= 0 && nCol >= 0 && nRow < grid.length && nCol < grid[0].length
                        && !vis[nRow][nCol] && grid[nRow][nCol] == 'L') {
                    dfs(grid, vis, nRow, nCol);
                }
            }
        }
    }
}
