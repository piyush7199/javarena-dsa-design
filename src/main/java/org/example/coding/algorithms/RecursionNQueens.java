package org.example.coding.algorithms;

import java.util.ArrayList;
import java.util.List;

public class RecursionNQueens {

    /**
     * Solves the N-Queens problem and returns all valid board configurations.
     * <p>
     * Intuition:
     * - Uses backtracking to place queens row by row.
     * - For each row, it tries placing a queen in every column and checks if it's safe.
     * - A placement is safe if no queen exists in the same column, row, or diagonals.
     * - If all N queens are placed successfully, the current board configuration is added to the result.
     * <p>
     * Time Complexity: O(N!) — in the worst case, all permutations of queen placements are explored
     * Space Complexity: O(N²) — due to the board and recursive call stack
     */
    public List<List<String>> solveNQueens(int n) {
        int[][] board = new int[n][n];
        List<List<String>> ans = new ArrayList<>();
        solve(ans, 0, n, board);
        return ans;
    }

    /**
     * Helper recursive method to backtrack and build the solution list.
     */

    private void solve(List<List<String>> ans, int ind, int n, int[][] board) {
        if (ind == n) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String val = "";
                for (int j = 0; j < n; j++) {
                    val = val + (board[i][j] == 1 ? "Q" : ".");
                }
                res.add(val);
            }
            ans.add(res);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isSafe(board, ind, i, n)) {
                board[ind][i] = 1;
                solve(ans, ind + 1, n, board);
                board[ind][i] = 0;
            }
        }
    }

    /**
     * Checks whether placing a queen at board[i][j] is safe.
     * <p>
     * Intuition:
     * - A queen can't be placed if there's another queen in the same row, column,
     * upper-left diagonal, or upper-right diagonal.
     */
    private boolean isSafe(int[][] board, int i, int j, int n) {
        for (int ind = 0; ind < n; ind++) {
            if (board[i][ind] == 1) return false;
            if (board[ind][j] == 1) return false;
        }
        int x = i;
        int y = j;
        while (x >= 0 && y >= 0) {
            if (board[x][y] == 1) return false;
            x--;
            y--;
        }
        x = i;
        y = j;

        while (x >= 0 && y < n) {
            if (board[x][y] == 1) return false;
            x--;
            y++;
        }
        return true;
    }

}
