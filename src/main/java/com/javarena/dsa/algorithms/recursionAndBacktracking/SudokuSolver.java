package com.javarena.dsa.algorithms.recursionAndBacktracking;

/**
 * Sudoku Solver
 *
 * <p><b>Problem Statement:</b><br>
 * Fill empty cells (marked as '.') in a 9×9 Sudoku board such that each row, column, and 3×3 sub-box
 * contains digits 1-9 without repetition.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to fill empty cells one by one
 * - For each empty cell, try placing digits 1-9
 * - Check if placement is valid: digit not in same row, column, or 3×3 box
 * - If valid, place digit and recursively solve remaining board
 * - If recursion succeeds, return true (puzzle solved)
 * - If recursion fails, backtrack by resetting cell to '.' and try next digit
 * - If no digit works, return false (triggers backtracking in previous call)
 * - Modify board in-place
 *
 * <p><b>Time Complexity:</b> O(9^N) - N is number of empty cells, try up to 9 digits each
 * <br><b>Space Complexity:</b> O(1) - In-place modification, O(N) recursion stack
 */
public class SudokuSolver {

    /**
     * Solves the Sudoku puzzle using backtracking.
     */
    public void solveSudoku(char[][] board) {
        sudokuHelper(board, 0, 0);
    }

    private boolean sudokuHelper(char[][] board, int i, int j) {
        if (i >= 9) return true;
        if (j >= 9) {
            return sudokuHelper(board, i + 1, 0);
        }
        if (board[i][j] != '.') return sudokuHelper(board, i, j + 1);
        for (char k = '1'; k <= '9'; k++) {
            if (isSafe(board, i, j, k)) {
                board[i][j] = k;
                if (sudokuHelper(board, i, j + 1)) {
                    return true;
                }
                board[i][j] = '.';
            }
        }

        return false;
    }

    private boolean isSafe(char[][] board, int i, int j, char k) {
        for (int x = 0; x < 9; x++) {
            if (board[x][j] == k) return false;
            if (board[i][x] == k) return false;
        }
        int boxX = (i / 3) * 3;
        int boxY = (j / 3) * 3;
        for (int x = boxX; x < boxX + 3; x++) {
            for (int y = boxY; y < boxY + 3; y++) {
                if (board[x][y] == k) return false;
            }
        }
        return true;
    }
}
