package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * N-Queens
 *
 * <p><b>Problem Statement:</b><br>
 * Place n queens on an n×n chessboard such that no two queens attack each other.
 * Return all distinct solutions where queens cannot be in the same row, column, or diagonal.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to place queens row by row
 * - For each row, try placing queen in each column
 * - Check if placement is safe (no queen in same column, diagonal, or anti-diagonal)
 * - If safe, place queen and recursively solve for next row
 * - If all N queens placed successfully, add board configuration to result
 * - Backtrack by removing queen and trying next column
 * - Use 2D board to track queen positions (1=queen, 0=empty)
 *
 * <p><b>Time Complexity:</b> O(N!) - Try all permutations of queen placements
 * <br><b>Space Complexity:</b> O(N²) - Board and recursion stack
 */
public class NQueens {

    /**
     * Solves the N-Queens problem and returns all valid board configurations.
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
