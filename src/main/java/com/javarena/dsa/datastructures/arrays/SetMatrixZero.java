package com.javarena.dsa.datastructures.arrays;

/**
 * Set Matrix Zeroes
 *
 * <p><b>Problem Statement:</b><br>
 * If an element in an m x n matrix is 0, set its entire row and column to 0. Do it in-place.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Cannot modify while scanning (causes false positives). Use two-pass approach:
 * 
 * 1. First Pass - Mark:
 *    - Create row[n] and col[m] arrays as markers
 *    - If matrix[i][j] == 0, mark row[i] = -1 and col[j] = -1
 * 
 * 2. Second Pass - Set:
 *    - If row[i] == -1 OR col[j] == -1, set matrix[i][j] = 0
 * 
 * Alternative O(1) space: Use first row/column as markers
 *
 * <p><b>Time Complexity:</b> O(N × M) - Two passes through matrix
 * <br><b>Space Complexity:</b> O(N + M) - Two marker arrays
 */
public class SetMatrixZero {
    /**
     * Sets entire rows and columns to zero if any element is zero.
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] row = new int[n];
        int[] col = new int[m];

        // Mark rows and columns that need to be zeroed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }

        // Set cells to zero based on the marked rows and columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == -1 || col[j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
