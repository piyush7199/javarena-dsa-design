package org.example.coding.datastructures.graph;

public class SurroundedRegions {
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
}
