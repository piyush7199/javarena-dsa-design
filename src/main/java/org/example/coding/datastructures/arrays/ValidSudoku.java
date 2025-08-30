package org.example.coding.datastructures.arrays;

public class ValidSudoku {
    /**
     * Intuition:
     * -----------
     * We don't need to solve the Sudoku — just check if the current board configuration is valid
     * according to Sudoku rules:
     * 1. Each row must contain digits 1–9 without repetition.
     * 2. Each column must contain digits 1–9 without repetition.
     * 3. Each 3x3 sub-box must contain digits 1–9 without repetition.
     * <p>
     * Approach:
     * ----------
     * - Use 3 boolean arrays:
     * - rows[9][9] → tracks if a number already appeared in a row.
     * - cols[9][9] → tracks if a number already appeared in a column.
     * - boxes[9][9] → tracks if a number already appeared in a 3x3 sub-box.
     * - Traverse the board cell by cell:
     * - Skip empty cells ('.').
     * - Convert the character to an index (0–8).
     * - Check if the number is already marked in its row, column, or sub-box.
     * - If yes → invalid board (return false).
     * - Otherwise → mark it as seen.
     * <p>
     * Time Complexity:
     * ----------------
     * O(81) = O(1)
     * - We check each cell once (9x9 grid = 81 cells).
     * <p>
     * Space Complexity:
     * -----------------
     * O(9*9*3) = O(1)
     * - We use fixed-size boolean arrays (independent of input size).
     */

    public boolean isValidSudoku(char[][] board) {
        // Track seen numbers in rows, columns, and 3x3 sub-boxes
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        // Traverse each cell in the 9x9 board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue; // skip empty cells

                int num = board[i][j] - '1'; // map '1'..'9' to 0..8
                int boxIndex = (i / 3) * 3 + (j / 3); // find 3x3 box index (0..8)

                // If number already seen in row, column, or box → invalid
                if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                    return false;
                }

                // Mark number as seen in row, column, and box
                rows[i][num] = cols[j][num] = boxes[boxIndex][num] = true;
            }
        }
        return true; // board is valid if no conflicts found
    }
}


