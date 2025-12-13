package com.javarena.dsa.datastructures.arrays;

/**
 * Valid Sudoku
 *
 * <p><b>Problem Statement:</b><br>
 * Determine if a 9x9 Sudoku board is valid according to rules:
 * 1. Each row must contain digits 1-9 without repetition
 * 2. Each column must contain digits 1-9 without repetition
 * 3. Each 3x3 sub-box must contain digits 1-9 without repetition
 * Empty cells are marked with '.'
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use 3 boolean arrays to track seen numbers:
 *   - rows[9][9]: track if number appeared in row
 *   - cols[9][9]: track if number appeared in column
 *   - boxes[9][9]: track if number appeared in 3x3 box
 * - Traverse board cell by cell, skip empty ('.')
 * - Convert char to index (0-8)
 * - Calculate box index: (row/3)*3 + (col/3)
 * - If number already seen in row/col/box, return false
 * - Otherwise mark as seen and continue
 *
 * <p><b>Time Complexity:</b> O(1) - Fixed 81 cells
 * <br><b>Space Complexity:</b> O(1) - Fixed size arrays
 */
public class ValidSudoku {

    /**
     * Checks if Sudoku board configuration is valid.
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


