package org.example.coding.algorithms.recursionAndBacktracking;

public class SudokuSolver {

    /**
     * Solves the Sudoku puzzle by filling the empty cells using backtracking.
     * <p>
     * Intuition:
     * - This is a classic backtracking problem where for each empty cell,
     * we try placing digits from 1 to 9.
     * - For each digit, we check whether placing it maintains Sudoku validity
     * (i.e., the digit is not repeated in the current row, column, or 3x3 box).
     * - If valid, we proceed recursively to solve the rest of the board.
     * - If any choice leads to a dead-end, we backtrack (undo the choice).
     * <p>
     * Time Complexity: O(9^(n)), where n is the number of empty cells.
     * - For each empty cell, we try up to 9 digits, so it's exponential in worst case.
     * <p>
     * Space Complexity: O(1)
     * - The board is modified in-place.
     * - No extra space other than recursion stack (which can go up to 81 levels).
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
